/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.provider.description.interestrate;

import com.opengamma.analytics.financial.instrument.index.IborIndex;
import com.opengamma.analytics.financial.model.option.definition.SABRInterestRateParameters;

/**
 * Implementation for swaption SABR parameters provider for one underlying when multi-curves are described by a MulticurveProviderDiscount.
 */
public class SABRSTIRFuturesProvider implements SABRSTIRFuturesProviderInterface {

  /**
   * The multicurve provider.
   */
  private final MulticurveProviderInterface _multicurveProvider;
  /**
   * The SABR parameters.
   */
  private final SABRInterestRateParameters _parameters;
  /**
   * The underlying swaps generators.
   */
  private final IborIndex _index;

  /**
   * @param multicurveProvider The multicurve provider.
   * @param parameters The SABR parameters.
   * @param index The cap/floor index.
   */
  public SABRSTIRFuturesProvider(MulticurveProviderInterface multicurveProvider, SABRInterestRateParameters parameters, final IborIndex index) {
    _multicurveProvider = multicurveProvider;
    _parameters = parameters;
    _index = index;
  }

  @Override
  public SABRSTIRFuturesProvider copy() {
    MulticurveProviderInterface multicurveProvider = _multicurveProvider.copy();
    return new SABRSTIRFuturesProvider(multicurveProvider, _parameters, _index);
  }

  @Override
  public SABRInterestRateParameters getSABRParameters() {
    return _parameters;
  }

  @Override
  public IborIndex getSABRIndex() {
    return _index;
  }

  @Override
  public MulticurveProviderInterface getMulticurveProvider() {
    return _multicurveProvider;
  }

}
