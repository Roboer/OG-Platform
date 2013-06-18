/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.curve;

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

import com.opengamma.core.config.Config;

/**
 * Configuration object for curves that are to be used as an issuer curve.
 */
@BeanDefinition
@Config
public class IssuerCurveTypeConfiguration extends CurveTypeConfiguration {

  /** Serialization version */
  private static final long serialVersionUID = 1L;

  /**
   * The issuer name.
   */
  @PropertyDefinition(validate = "notNull")
  private String _issuerName;

  /**
   * The underlying code.
   */
  @PropertyDefinition(validate = "notNull")
  private String _underlyingCode;

  /**
   * For the fudge builder
   */
  IssuerCurveTypeConfiguration() {
    super();
  }

  /**
   * @param name The curve name, not null
   * @param issuerName The issuer name, not null
   * @param underlyingCode The underlying code (e.g. a currency) for the curve
   */
  public IssuerCurveTypeConfiguration(final String name, final String issuerName, final String underlyingCode) {
    super(name);
    setIssuerName(issuerName);
    setUnderlyingCode(underlyingCode);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code IssuerCurveTypeConfiguration}.
   * @return the meta-bean, not null
   */
  public static IssuerCurveTypeConfiguration.Meta meta() {
    return IssuerCurveTypeConfiguration.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(IssuerCurveTypeConfiguration.Meta.INSTANCE);
  }

  @Override
  public IssuerCurveTypeConfiguration.Meta metaBean() {
    return IssuerCurveTypeConfiguration.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 1459772644:  // issuerName
        return getIssuerName();
      case 1496937194:  // underlyingCode
        return getUnderlyingCode();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 1459772644:  // issuerName
        setIssuerName((String) newValue);
        return;
      case 1496937194:  // underlyingCode
        setUnderlyingCode((String) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_issuerName, "issuerName");
    JodaBeanUtils.notNull(_underlyingCode, "underlyingCode");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      IssuerCurveTypeConfiguration other = (IssuerCurveTypeConfiguration) obj;
      return JodaBeanUtils.equal(getIssuerName(), other.getIssuerName()) &&
          JodaBeanUtils.equal(getUnderlyingCode(), other.getUnderlyingCode()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getIssuerName());
    hash += hash * 31 + JodaBeanUtils.hashCode(getUnderlyingCode());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the issuer name.
   * @return the value of the property, not null
   */
  public String getIssuerName() {
    return _issuerName;
  }

  /**
   * Sets the issuer name.
   * @param issuerName  the new value of the property, not null
   */
  public void setIssuerName(String issuerName) {
    JodaBeanUtils.notNull(issuerName, "issuerName");
    this._issuerName = issuerName;
  }

  /**
   * Gets the the {@code issuerName} property.
   * @return the property, not null
   */
  public final Property<String> issuerName() {
    return metaBean().issuerName().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the underlying code.
   * @return the value of the property, not null
   */
  public String getUnderlyingCode() {
    return _underlyingCode;
  }

  /**
   * Sets the underlying code.
   * @param underlyingCode  the new value of the property, not null
   */
  public void setUnderlyingCode(String underlyingCode) {
    JodaBeanUtils.notNull(underlyingCode, "underlyingCode");
    this._underlyingCode = underlyingCode;
  }

  /**
   * Gets the the {@code underlyingCode} property.
   * @return the property, not null
   */
  public final Property<String> underlyingCode() {
    return metaBean().underlyingCode().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code IssuerCurveTypeConfiguration}.
   */
  public static class Meta extends CurveTypeConfiguration.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code issuerName} property.
     */
    private final MetaProperty<String> _issuerName = DirectMetaProperty.ofReadWrite(
        this, "issuerName", IssuerCurveTypeConfiguration.class, String.class);
    /**
     * The meta-property for the {@code underlyingCode} property.
     */
    private final MetaProperty<String> _underlyingCode = DirectMetaProperty.ofReadWrite(
        this, "underlyingCode", IssuerCurveTypeConfiguration.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "issuerName",
        "underlyingCode");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1459772644:  // issuerName
          return _issuerName;
        case 1496937194:  // underlyingCode
          return _underlyingCode;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends IssuerCurveTypeConfiguration> builder() {
      return new DirectBeanBuilder<IssuerCurveTypeConfiguration>(new IssuerCurveTypeConfiguration());
    }

    @Override
    public Class<? extends IssuerCurveTypeConfiguration> beanType() {
      return IssuerCurveTypeConfiguration.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code issuerName} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> issuerName() {
      return _issuerName;
    }

    /**
     * The meta-property for the {@code underlyingCode} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> underlyingCode() {
      return _underlyingCode;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
