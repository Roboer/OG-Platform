/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.web.portfolio;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.joda.beans.impl.flexi.FlexiBean;

import com.opengamma.id.UniqueId;
import com.opengamma.master.portfolio.ManageablePortfolioNode;
import com.opengamma.master.portfolio.PortfolioDocument;
import com.opengamma.master.position.PositionSearchRequest;
import com.opengamma.master.position.PositionSearchResult;

/**
 * RESTful resource for a node in a portfolio version.
 */
@Path("/portfolios/{portfolioId}/versions/{versionId}/nodes/{nodeId}")
public class WebPortfolioVersionNodeResource extends WebPortfolioNodeResource {

  /**
   * Creates the resource.
   * @param parent  the parent resource, not null
   */
  public WebPortfolioVersionNodeResource(AbstractWebPortfolioResource parent) {
    super(parent);
  }

  //-------------------------------------------------------------------------
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String getHTML() {
    FlexiBean out = createPortfolioNodeData();
    return getFreemarker().build(HTML_DIR + "portfolionode.ftl", out);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getJSON() {
    FlexiBean out = createPortfolioNodeData();
    String s = getFreemarker().build(JSON_DIR + "portfolionode.ftl", out);
    return Response.ok(s).build();
  }

  private FlexiBean createPortfolioNodeData() {
    ManageablePortfolioNode node = data().getNode();
    PositionSearchRequest positionSearch = new PositionSearchRequest();
    positionSearch.setPositionObjectIds(node.getPositionIds());
    PositionSearchResult positionsResult = data().getPositionMaster().search(positionSearch);
    resolveSecurities(positionsResult.getPositions());

    FlexiBean out = createRootData();
    out.put("positionsResult", positionsResult);
    out.put("positions", positionsResult.getPositions());
    return out;
  }

  //-------------------------------------------------------------------------
  @Path("positions")
  public WebPortfolioNodePositionsResource findPositions() {
    return new WebPortfolioNodePositionsResource(this);
  }

  //-------------------------------------------------------------------------
  /**
   * Creates the output root data.
   * @return the output root data, not null
   */
  protected FlexiBean createRootData() {
    FlexiBean out = super.createRootData();
    PortfolioDocument doc = data().getVersioned();
    ManageablePortfolioNode node = data().getNode();
    out.put("portfolioDoc", doc);
    out.put("portfolio", doc.getPortfolio());
    out.put("parentNode", data().getParentNode());
    out.put("node", node);
    out.put("childNodes", node.getChildNodes());
    out.put("deleted", !doc.isLatest());
    out.put("pathNodes", getPathNodes(node));
    return out;
  }

  //-------------------------------------------------------------------------
  /**
   * Builds a URI for this resource.
   * @param data  the data, not null
   * @return the URI, not null
   */
  public static URI uri(final WebPortfoliosData data) {
    return uri(data, null);
  }

  /**
   * Builds a URI for this resource.
   * @param data  the data, not null
   * @param overrideNodeId  the override node id, null uses information from data
   * @return the URI, not null
   */
  public static URI uri(final WebPortfoliosData data, final UniqueId overrideNodeId) {
    String portfolioId = data.getBestPortfolioUriId(null);
    String nodeId = data.getBestNodeUriId(overrideNodeId);
    return data.getUriInfo().getBaseUriBuilder().path(WebPortfolioNodeResource.class).build(portfolioId, nodeId);
  }

}
