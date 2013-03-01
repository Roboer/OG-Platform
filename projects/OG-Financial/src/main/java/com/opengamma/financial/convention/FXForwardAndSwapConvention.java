/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.convention;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.id.ExternalId;
import com.opengamma.id.ExternalIdBundle;

/**
 *
 */
@BeanDefinition
public class FXForwardAndSwapConvention extends Convention {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The spot convention.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalId _spotConvention;

  /**
   * The business day convention.
   */
  @PropertyDefinition(validate = "notNull")
  private BusinessDayConvention _businessDayConvention;

  /**
   * Should dates follow the end-of-month rule.
   */
  @PropertyDefinition
  private boolean _isEOM;

  /**
   * The settlement region.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalId _settlementRegion;

  /**
   * For the builder.
   */
  public FXForwardAndSwapConvention() {
  }

  public FXForwardAndSwapConvention(final String name, final ExternalIdBundle externalIdBundle, final ExternalId spotConvention, final BusinessDayConvention businessDayConvention,
      final boolean isEOM, final ExternalId settlementRegion) {
    super(name, externalIdBundle);
    setSpotConvention(spotConvention);
    setBusinessDayConvention(businessDayConvention);
    setIsEOM(isEOM);
    setSettlementRegion(settlementRegion);
  }
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code FXForwardAndSwapConvention}.
   * @return the meta-bean, not null
   */
  public static FXForwardAndSwapConvention.Meta meta() {
    return FXForwardAndSwapConvention.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(FXForwardAndSwapConvention.Meta.INSTANCE);
  }

  @Override
  public FXForwardAndSwapConvention.Meta metaBean() {
    return FXForwardAndSwapConvention.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -1912646125:  // spotConvention
        return getSpotConvention();
      case -1002835891:  // businessDayConvention
        return getBusinessDayConvention();
      case 100464505:  // isEOM
        return isIsEOM();
      case -534226563:  // settlementRegion
        return getSettlementRegion();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -1912646125:  // spotConvention
        setSpotConvention((ExternalId) newValue);
        return;
      case -1002835891:  // businessDayConvention
        setBusinessDayConvention((BusinessDayConvention) newValue);
        return;
      case 100464505:  // isEOM
        setIsEOM((Boolean) newValue);
        return;
      case -534226563:  // settlementRegion
        setSettlementRegion((ExternalId) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_spotConvention, "spotConvention");
    JodaBeanUtils.notNull(_businessDayConvention, "businessDayConvention");
    JodaBeanUtils.notNull(_settlementRegion, "settlementRegion");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      FXForwardAndSwapConvention other = (FXForwardAndSwapConvention) obj;
      return JodaBeanUtils.equal(getSpotConvention(), other.getSpotConvention()) &&
          JodaBeanUtils.equal(getBusinessDayConvention(), other.getBusinessDayConvention()) &&
          JodaBeanUtils.equal(isIsEOM(), other.isIsEOM()) &&
          JodaBeanUtils.equal(getSettlementRegion(), other.getSettlementRegion()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getSpotConvention());
    hash += hash * 31 + JodaBeanUtils.hashCode(getBusinessDayConvention());
    hash += hash * 31 + JodaBeanUtils.hashCode(isIsEOM());
    hash += hash * 31 + JodaBeanUtils.hashCode(getSettlementRegion());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the spot convention.
   * @return the value of the property, not null
   */
  public ExternalId getSpotConvention() {
    return _spotConvention;
  }

  /**
   * Sets the spot convention.
   * @param spotConvention  the new value of the property, not null
   */
  public void setSpotConvention(ExternalId spotConvention) {
    JodaBeanUtils.notNull(spotConvention, "spotConvention");
    this._spotConvention = spotConvention;
  }

  /**
   * Gets the the {@code spotConvention} property.
   * @return the property, not null
   */
  public final Property<ExternalId> spotConvention() {
    return metaBean().spotConvention().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the business day convention.
   * @return the value of the property, not null
   */
  public BusinessDayConvention getBusinessDayConvention() {
    return _businessDayConvention;
  }

  /**
   * Sets the business day convention.
   * @param businessDayConvention  the new value of the property, not null
   */
  public void setBusinessDayConvention(BusinessDayConvention businessDayConvention) {
    JodaBeanUtils.notNull(businessDayConvention, "businessDayConvention");
    this._businessDayConvention = businessDayConvention;
  }

  /**
   * Gets the the {@code businessDayConvention} property.
   * @return the property, not null
   */
  public final Property<BusinessDayConvention> businessDayConvention() {
    return metaBean().businessDayConvention().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets should dates follow the end-of-month rule.
   * @return the value of the property
   */
  public boolean isIsEOM() {
    return _isEOM;
  }

  /**
   * Sets should dates follow the end-of-month rule.
   * @param isEOM  the new value of the property
   */
  public void setIsEOM(boolean isEOM) {
    this._isEOM = isEOM;
  }

  /**
   * Gets the the {@code isEOM} property.
   * @return the property, not null
   */
  public final Property<Boolean> isEOM() {
    return metaBean().isEOM().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the settlement region.
   * @return the value of the property, not null
   */
  public ExternalId getSettlementRegion() {
    return _settlementRegion;
  }

  /**
   * Sets the settlement region.
   * @param settlementRegion  the new value of the property, not null
   */
  public void setSettlementRegion(ExternalId settlementRegion) {
    JodaBeanUtils.notNull(settlementRegion, "settlementRegion");
    this._settlementRegion = settlementRegion;
  }

  /**
   * Gets the the {@code settlementRegion} property.
   * @return the property, not null
   */
  public final Property<ExternalId> settlementRegion() {
    return metaBean().settlementRegion().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code FXForwardAndSwapConvention}.
   */
  public static class Meta extends Convention.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code spotConvention} property.
     */
    private final MetaProperty<ExternalId> _spotConvention = DirectMetaProperty.ofReadWrite(
        this, "spotConvention", FXForwardAndSwapConvention.class, ExternalId.class);
    /**
     * The meta-property for the {@code businessDayConvention} property.
     */
    private final MetaProperty<BusinessDayConvention> _businessDayConvention = DirectMetaProperty.ofReadWrite(
        this, "businessDayConvention", FXForwardAndSwapConvention.class, BusinessDayConvention.class);
    /**
     * The meta-property for the {@code isEOM} property.
     */
    private final MetaProperty<Boolean> _isEOM = DirectMetaProperty.ofReadWrite(
        this, "isEOM", FXForwardAndSwapConvention.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code settlementRegion} property.
     */
    private final MetaProperty<ExternalId> _settlementRegion = DirectMetaProperty.ofReadWrite(
        this, "settlementRegion", FXForwardAndSwapConvention.class, ExternalId.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "spotConvention",
        "businessDayConvention",
        "isEOM",
        "settlementRegion");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1912646125:  // spotConvention
          return _spotConvention;
        case -1002835891:  // businessDayConvention
          return _businessDayConvention;
        case 100464505:  // isEOM
          return _isEOM;
        case -534226563:  // settlementRegion
          return _settlementRegion;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends FXForwardAndSwapConvention> builder() {
      return new DirectBeanBuilder<FXForwardAndSwapConvention>(new FXForwardAndSwapConvention());
    }

    @Override
    public Class<? extends FXForwardAndSwapConvention> beanType() {
      return FXForwardAndSwapConvention.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code spotConvention} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> spotConvention() {
      return _spotConvention;
    }

    /**
     * The meta-property for the {@code businessDayConvention} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BusinessDayConvention> businessDayConvention() {
      return _businessDayConvention;
    }

    /**
     * The meta-property for the {@code isEOM} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> isEOM() {
      return _isEOM;
    }

    /**
     * The meta-property for the {@code settlementRegion} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> settlementRegion() {
      return _settlementRegion;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
