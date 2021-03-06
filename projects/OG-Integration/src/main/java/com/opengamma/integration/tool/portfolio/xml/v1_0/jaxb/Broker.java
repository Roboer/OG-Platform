/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.tool.portfolio.xml.v1_0.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnumValue;

import org.joda.beans.BeanDefinition;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBean;

import com.opengamma.id.ExternalId;
import java.util.Map;
import org.joda.beans.BeanBuilder;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

@BeanDefinition
public class Broker extends DirectBean {

  public enum BrokerType {
    @XmlEnumValue(value = "clearingBroker")
    CLEARING_BROKER,
    @XmlEnumValue(value = "executingBroker")
    EXECUTING_BROKER
  }

  @XmlAttribute(name = "type", required = true)
  @PropertyDefinition(validate = "notNull")
  private BrokerType _brokerType;

  @XmlElement(name = "id", required = true)
  @PropertyDefinition(validate = "notNull")
  private ExtId _externalId;


  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code Broker}.
   * @return the meta-bean, not null
   */
  public static Broker.Meta meta() {
    return Broker.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(Broker.Meta.INSTANCE);
  }

  @Override
  public Broker.Meta metaBean() {
    return Broker.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 1681647283:  // brokerType
        return getBrokerType();
      case -1699764666:  // externalId
        return getExternalId();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 1681647283:  // brokerType
        setBrokerType((BrokerType) newValue);
        return;
      case -1699764666:  // externalId
        setExternalId((ExtId) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_brokerType, "brokerType");
    JodaBeanUtils.notNull(_externalId, "externalId");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      Broker other = (Broker) obj;
      return JodaBeanUtils.equal(getBrokerType(), other.getBrokerType()) &&
          JodaBeanUtils.equal(getExternalId(), other.getExternalId());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getBrokerType());
    hash += hash * 31 + JodaBeanUtils.hashCode(getExternalId());
    return hash;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the brokerType.
   * @return the value of the property, not null
   */
  public BrokerType getBrokerType() {
    return _brokerType;
  }

  /**
   * Sets the brokerType.
   * @param brokerType  the new value of the property, not null
   */
  public void setBrokerType(BrokerType brokerType) {
    JodaBeanUtils.notNull(brokerType, "brokerType");
    this._brokerType = brokerType;
  }

  /**
   * Gets the the {@code brokerType} property.
   * @return the property, not null
   */
  public final Property<BrokerType> brokerType() {
    return metaBean().brokerType().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the externalId.
   * @return the value of the property, not null
   */
  public ExtId getExternalId() {
    return _externalId;
  }

  /**
   * Sets the externalId.
   * @param externalId  the new value of the property, not null
   */
  public void setExternalId(ExtId externalId) {
    JodaBeanUtils.notNull(externalId, "externalId");
    this._externalId = externalId;
  }

  /**
   * Gets the the {@code externalId} property.
   * @return the property, not null
   */
  public final Property<ExtId> externalId() {
    return metaBean().externalId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code Broker}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code brokerType} property.
     */
    private final MetaProperty<BrokerType> _brokerType = DirectMetaProperty.ofReadWrite(
        this, "brokerType", Broker.class, BrokerType.class);
    /**
     * The meta-property for the {@code externalId} property.
     */
    private final MetaProperty<ExtId> _externalId = DirectMetaProperty.ofReadWrite(
        this, "externalId", Broker.class, ExtId.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "brokerType",
        "externalId");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1681647283:  // brokerType
          return _brokerType;
        case -1699764666:  // externalId
          return _externalId;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends Broker> builder() {
      return new DirectBeanBuilder<Broker>(new Broker());
    }

    @Override
    public Class<? extends Broker> beanType() {
      return Broker.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code brokerType} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BrokerType> brokerType() {
      return _brokerType;
    }

    /**
     * The meta-property for the {@code externalId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExtId> externalId() {
      return _externalId;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
