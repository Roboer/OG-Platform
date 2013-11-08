/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.credit.isdastandardmodel;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

/**
 * An ISDA compliant credit curve.
 */

public class ISDACompliantCreditCurve extends ISDACompliantCurve {

  /**
   * Creates a flat credit (hazard) curve at hazard rate h.
   * 
   * @param t  the (arbitrary) single knot point (t > 0)
   * @param h  the level
   */
  public ISDACompliantCreditCurve(final double t, final double h) {
    super(t, h);
  }

  /**
   * Creates a credit (hazard) curve with knots at times, t, zero hazard rates, h,
   * at the knots and piecewise constant forward hazard rates between knots
   * (i.e. linear interpolation of h*t or the -log(survival-probability).
   * 
   * @param t  the knot (node) times, not null
   * @param h  the zero hazard rates, not null
   */
  public ISDACompliantCreditCurve(final double[] t, final double[] h) {
    super(t, h);
  }

  /**
   * Creates a shallow copy of the specified curve, used to down cast from ISDACompliantCurve.
   * 
   * @param from  the curve to clone from, not null
   */
  public ISDACompliantCreditCurve(final ISDACompliantCurve from) {
    super(from);
  }

  /**
   * Creates an instance, used by deserialization.
   * 
   * @param t  the set of times that form the knots of the curve. Must be ascending with the first value >= 0.
   * @param r  the set of zero rates
   * @param rt  the set of rates at the knot times
   * @deprecated This constructor is deprecated
   */
  @Deprecated
  public ISDACompliantCreditCurve(final double[] t, final double[] r, final double[] rt, final double[] invDt) {
    super(t, r, rt, invDt);
  }

  //-------------------------------------------------------------------------
  /**
   * Get the zero hazard rate at time t (note: this simply a pseudonym for getZeroRate).
   * 
   * @param t  the time
   * @return zero hazard rate at time t
   */
  public double getHazardRate(final double t) {
    return getZeroRate(t);
  }

  /**
   * Get the survival probability at time t (note: this simply a pseudonym for getDiscountFactor).
   * 
   * @param t  the time
   * @return survival probability at time t
   */
  public double getSurvivalProbability(final double t) {
    return getDiscountFactor(t);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISDACompliantCreditCurve withRates(final double[] r) {
    return new ISDACompliantCreditCurve(super.withRates(r));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISDACompliantCreditCurve withRate(final double rate, final int index) {
    return new ISDACompliantCreditCurve(super.withRate(rate, index));
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ISDACompliantCreditCurve}.
   * @return the meta-bean, not null
   */
  public static ISDACompliantCreditCurve.Meta meta() {
    return ISDACompliantCreditCurve.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ISDACompliantCreditCurve.Meta.INSTANCE);
  }

  @Override
  public ISDACompliantCreditCurve.Meta metaBean() {
    return ISDACompliantCreditCurve.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  @Override
  public ISDACompliantCreditCurve clone() {
    return (ISDACompliantCreditCurve) super.clone();
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      return super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    final int hash = 7;
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    final StringBuilder buf = new StringBuilder(32);
    buf.append("ISDACompliantCreditCurve{");
    final int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  @Override
  protected void toString(final StringBuilder buf) {
    super.toString(buf);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ISDACompliantCreditCurve}.
   */
  public static class Meta extends ISDACompliantCurve.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(this, (DirectMetaPropertyMap) super.metaPropertyMap());

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    public BeanBuilder<? extends ISDACompliantCreditCurve> builder() {
      throw new UnsupportedOperationException();
    }

    @Override
    public Class<? extends ISDACompliantCreditCurve> beanType() {
      return ISDACompliantCreditCurve.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
