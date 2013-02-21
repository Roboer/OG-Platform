/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.web.analytics;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.DataNotFoundException;
import com.opengamma.core.position.Portfolio;
import com.opengamma.core.position.PositionSource;
import com.opengamma.core.security.SecuritySource;
import com.opengamma.engine.ComputationTargetResolver;
import com.opengamma.engine.marketdata.NamedMarketDataSpecificationRepository;
import com.opengamma.engine.view.ViewDefinition;
import com.opengamma.engine.view.ViewProcessor;
import com.opengamma.engine.view.client.ViewClient;
import com.opengamma.engine.view.compilation.PortfolioCompiler;
import com.opengamma.id.UniqueId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.livedata.UserPrincipal;
import com.opengamma.master.config.ConfigMaster;
import com.opengamma.master.marketdatasnapshot.MarketDataSnapshotMaster;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.web.analytics.blotter.BlotterColumnMapper;
import com.opengamma.web.analytics.push.ClientConnection;
import com.opengamma.web.server.AggregatedViewDefinitionManager;

/**
 * Creates and manages {@link AnalyticsView} implementations.
 */
public class AnalyticsViewManager {

  /* TODO handle userId and clientId
  when view is created wrap in an impl that contains the IDs
  could just add them to add them to SimpleAnalyticsView
  when view is requested (including IDs) create another impl that wraps those IDs and delegates to the one that
  holds the real IDs. but before delegating it compares the IDs
  */

  private static final Logger s_logger = LoggerFactory.getLogger(AnalyticsViewManager.class);

  private final ViewProcessor _viewProcessor;
  private final AggregatedViewDefinitionManager _aggregatedViewDefManager;
  private final MarketDataSnapshotMaster _snapshotMaster;
  private final Map<String, AnalyticsViewClientConnection> _viewConnections = new ConcurrentHashMap<String, AnalyticsViewClientConnection>();
  private final ComputationTargetResolver _targetResolver;
  private final NamedMarketDataSpecificationRepository _marketDataSpecificationRepository;
  private final BlotterColumnMapper _blotterColumnMapper;
  private final PositionSource _positionSource;
  private final ConfigMaster _configMaster;
  private final SecuritySource _securitySource;

  public AnalyticsViewManager(ViewProcessor viewProcessor,
                              AggregatedViewDefinitionManager aggregatedViewDefManager,
                              MarketDataSnapshotMaster snapshotMaster,
                              ComputationTargetResolver targetResolver,
                              NamedMarketDataSpecificationRepository marketDataSpecificationRepository,
                              BlotterColumnMapper blotterColumnMapper,
                              PositionSource positionSource,
                              ConfigMaster configMaster,
                              SecuritySource securitySource) {
    ArgumentChecker.notNull(viewProcessor, "viewProcessor");
    ArgumentChecker.notNull(aggregatedViewDefManager, "aggregatedViewDefManager");
    ArgumentChecker.notNull(snapshotMaster, "snapshotMaster");
    ArgumentChecker.notNull(targetResolver, "targetResolver");
    ArgumentChecker.notNull(marketDataSpecificationRepository, "marketDataSpecificationRepository");
    ArgumentChecker.notNull(blotterColumnMapper, "blotterColumnMapper");
    ArgumentChecker.notNull(positionSource, "positionSource");
    ArgumentChecker.notNull(configMaster, "configMaster");
    ArgumentChecker.notNull(securitySource, "securitySource");
    _positionSource = positionSource;
    _configMaster = configMaster;
    _securitySource = securitySource;
    _blotterColumnMapper = blotterColumnMapper;
    _targetResolver = targetResolver;
    _viewProcessor = viewProcessor;
    _aggregatedViewDefManager = aggregatedViewDefManager;
    _snapshotMaster = snapshotMaster;
    _marketDataSpecificationRepository = marketDataSpecificationRepository;
  }

  /**
   * Creates a new view.
   * @param request Details of the view
   * @param clientId ID of the client connection
   * @param user User requesting the view
   * @param clientConnection Connection that will be notified of changes to the view
   * @param viewId ID of the view, must be unique
   * @param viewCallbackId ID that's passed to the listener when the view's portfolio grid structure changes
   * @param portfolioGridId ID that's passed to the listener when the view's portfolio grid structure changes
   * @param primitivesGridId ID that's passed to the listener when the view's primitives grid structure changes
   */
  public void createView(ViewRequest request,
                         String clientId,
                         UserPrincipal user,
                         ClientConnection clientConnection,
                         String viewId,
                         Object viewCallbackId,
                         String portfolioGridId,
                         String primitivesGridId) {
    if (_viewConnections.containsKey(viewId)) {
      throw new IllegalArgumentException("View ID " + viewId + " is already in use");
    }
    // TODO need to load and fully resolve the portfolio, need portfolio and security master
    // portfolio ID only available in the view def, need config master
    ViewDefinition viewDef = (ViewDefinition) _configMaster.get(request.getViewDefinitionId()).getConfig().getValue();
    UniqueId portfolioId = viewDef.getPortfolioId();
    // TODO confirm the correct versioning behaviour
    Portfolio portfolio = _positionSource.getPortfolio(portfolioId.getObjectId(), VersionCorrection.LATEST);
    // TODO something a bit more sophisticated with the executor
    Portfolio resolvedPortfolio =
        PortfolioCompiler.resolvePortfolio(portfolio, Executors.newSingleThreadExecutor(), _securitySource);
    ViewClient viewClient = _viewProcessor.createViewClient(user);
    s_logger.debug("Client ID {} creating new view with ID {}", clientId, viewId);
    ViewportListener viewportListener = new LoggingViewportListener(viewClient);
    AnalyticsView view = new SimpleAnalyticsView(resolvedPortfolio,
                                                 viewId,
                                                 portfolioGridId,
                                                 primitivesGridId,
                                                 _targetResolver,
                                                 viewportListener,
                                                 _blotterColumnMapper,
                                                 request.showBlotterColumns());
    AnalyticsView lockingView = new LockingAnalyticsView(view);
    AnalyticsView timingView = new TimingAnalyticsView(lockingView);
    AnalyticsView notifyingView = new NotifyingAnalyticsView(timingView, clientConnection);
    AnalyticsViewClientConnection connection = new AnalyticsViewClientConnection(request,
                                                                                 viewClient,
                                                                                 notifyingView,
                                                                                 _marketDataSpecificationRepository,
                                                                                 _aggregatedViewDefManager,
                                                                                 _snapshotMaster);
    _viewConnections.put(viewId, connection);
    // need to notify the listener that the view has been created
    // TODO would it be neater to leave this to the constructor of NotifyingAnalyticsView
    clientConnection.itemUpdated(viewCallbackId);
    connection.start();
    clientConnection.addDisconnectionListener(new DisconnectionListener(viewId));
  }

  /**
   * Deletes a view.
   * @param viewId ID of the view
   * @throws DataNotFoundException If there's no view with the specified ID
   */
  public void deleteView(String viewId) {
    AnalyticsViewClientConnection connection = _viewConnections.remove(viewId);
    if (connection == null) {
      throw new DataNotFoundException("No view found with ID " + viewId);
    }
    s_logger.debug("Closing view with ID {}", viewId);
    connection.close();
  }

  /**
   * Returns a view given its ID.
   * @param viewId ID of the view
   * @return The view
   * @throws DataNotFoundException If there's no view with the specified ID
   */
  public AnalyticsView getView(String viewId) {
    AnalyticsViewClientConnection connection = _viewConnections.get(viewId);
    if (connection == null) {
      throw new DataNotFoundException("No view found with ID " + viewId);
    }
    return connection.getView();
  }

  /**
   * Closes a view if it's still open when the associated client disconnects.
   */
  private final class DisconnectionListener implements ClientConnection.DisconnectionListener {

    private final String _viewId;

    private DisconnectionListener(String viewId) {
      _viewId = viewId;
    }

    @Override
    public void clientDisconnected() {
      AnalyticsViewClientConnection connection = _viewConnections.remove(_viewId);
      if (connection != null) {
        s_logger.debug("Client disconnected, closing view with ID {}", _viewId);
        connection.close();
      }
    }
  }
}
