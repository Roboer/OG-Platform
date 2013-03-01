/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.interestrate.future.calculator;

import org.apache.commons.lang.Validate;

import com.opengamma.analytics.financial.interestrate.InstrumentDerivativeVisitorAdapter;
import com.opengamma.analytics.financial.interestrate.YieldCurveBundle;
import com.opengamma.analytics.financial.interestrate.future.derivative.BondFuture;
import com.opengamma.analytics.financial.interestrate.future.method.BondFutureDiscountingMethod;

/**
 * Calculate security prices for futures (bond and interest rate).
 */
public final class PriceFromCurvesDiscountingCalculator extends InstrumentDerivativeVisitorAdapter<YieldCurveBundle, Double> {

  /**
   * The calculator instance.
   */
  private static final PriceFromCurvesDiscountingCalculator s_instance = new PriceFromCurvesDiscountingCalculator();
  /**
   * The method to compute bond future prices.
   */
  private static final BondFutureDiscountingMethod METHOD_BOND_FUTURE = BondFutureDiscountingMethod.getInstance();

  //  /**
  //   * The method to compute interest rate future prices.
  //   */
  //  private static final InterestRateFutureSecurityDiscountingMethod METHOD_RATE_FUTURE = InterestRateFutureSecurityDiscountingMethod.getInstance();

  /**
   * Return the calculator instance.
   * @return The instance.
   */
  public static PriceFromCurvesDiscountingCalculator getInstance() {
    return s_instance;
  }

  /**
   * Private constructor.
   */
  private PriceFromCurvesDiscountingCalculator() {
  }

  //  @Override
  //  public Double visitInterestRateFuture(final InterestRateFutureTransaction future, final YieldCurveBundle curves) {
  //    Validate.notNull(curves);
  //    Validate.notNull(future);
  //    return METHOD_RATE_FUTURE.price(future.getUnderlying(), curves);
  //  }

  @Override
  public Double visitBondFuture(final BondFuture future, final YieldCurveBundle curves) {
    Validate.notNull(curves);
    Validate.notNull(future);
    return METHOD_BOND_FUTURE.price(future, curves);
  }

}
