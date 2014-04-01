/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.orgs;

import com.opengamma.id.ObjectId;
import com.opengamma.id.ObjectIdentifiable;
import com.opengamma.master.AbstractDocument;
import com.opengamma.master.AbstractSearchRequest;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.PublicSPI;
import com.opengamma.util.RegexUtils;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Request for searching for organizations.
 * <p/>
 * Documents will be returned that match the search criteria.
 * This class provides the ability to page the results and to search
 * as at a specific version and correction instant.
 * See {@link com.opengamma.master.orgs.OrganizationHistoryRequest} for more details on how history works.
 */
@PublicSPI
@BeanDefinition
public class OrganizationSearchRequest extends AbstractSearchRequest {

  /**
   * The set of organization object identifiers, null to not limit by organization object identifiers.
   * Note that an empty set will return no organizations.
   */
  @PropertyDefinition(set = "manual")
  private List<ObjectId> _organizationObjectIds;

  /**
   * The organization ticker, wildcards allowed, null to not match on ticker.
   */
  @PropertyDefinition
  private String _obligorTicker;

  /**
   * The organization short name, wildcards allowed, null to not match on short name.
   */
  @PropertyDefinition
  private String _obligorShortName;

  /**
   * The Reference Entity Database code of the organization, wildcards allowed, null to not match on RED code.
   */
  @PropertyDefinition
  private String _obligorREDCode;


  /**
   * Creates an instance.
   */
  public OrganizationSearchRequest() {
  }

  //-------------------------------------------------------------------------

  /**
   * Adds a single organization object identifier to the set.
   *
   * @param organizationId the organization object identifier to add, not null
   */
  public void addOrganizationObjectId(ObjectIdentifiable organizationId) {
    ArgumentChecker.notNull(organizationId, "organizationId");
    if (_organizationObjectIds == null) {
      _organizationObjectIds = new ArrayList<ObjectId>();
    }
    _organizationObjectIds.add(organizationId.getObjectId());
  }

  /**
   * Sets the set of organization object identifiers, null to not limit by organization object identifiers.
   * Note that an empty set will return no organizations.
   *
   * @param organizationIds the new organization identifiers, null clears the position id search
   */
  public void setOrganizationObjectIds(Iterable<? extends ObjectIdentifiable> organizationIds) {
    if (organizationIds == null) {
      _organizationObjectIds = null;
    } else {
      _organizationObjectIds = new ArrayList<ObjectId>();
      for (ObjectIdentifiable organizationId : organizationIds) {
        _organizationObjectIds.add(organizationId.getObjectId());
      }
    }
  }


  //-------------------------------------------------------------------------
  @Override
  public boolean matches(final AbstractDocument obj) {
    if (obj instanceof OrganizationDocument == false) {
      return false;
    }
    final OrganizationDocument document = (OrganizationDocument) obj;
    final ManageableOrganization organization = document.getOrganization();
    if (getOrganizationObjectIds() != null && getOrganizationObjectIds().contains(document.getObjectId()) == false) {
      return false;
    }
    if (getObligorShortName() != null && RegexUtils.wildcardMatch(getObligorShortName(), organization.getObligor().getObligorShortName()) == false) {
      return false;
    }
    if (getObligorREDCode() != null && RegexUtils.wildcardMatch(getObligorREDCode(), organization.getObligor().getObligorREDCode()) == false) {
      return false;
    }
    if (getObligorTicker() != null && !getObligorShortName().equalsIgnoreCase(organization.getObligor().getObligorShortName())) {
      return false;
    }
    return true;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code OrganizationSearchRequest}.
   * @return the meta-bean, not null
   */
  public static OrganizationSearchRequest.Meta meta() {
    return OrganizationSearchRequest.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(OrganizationSearchRequest.Meta.INSTANCE);
  }

  @Override
  public OrganizationSearchRequest.Meta metaBean() {
    return OrganizationSearchRequest.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 1296014086:  // organizationObjectIds
        return getOrganizationObjectIds();
      case 896190372:  // obligorTicker
        return getObligorTicker();
      case -1066272179:  // obligorShortName
        return getObligorShortName();
      case -823370556:  // obligorREDCode
        return getObligorREDCode();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 1296014086:  // organizationObjectIds
        setOrganizationObjectIds((List<ObjectId>) newValue);
        return;
      case 896190372:  // obligorTicker
        setObligorTicker((String) newValue);
        return;
      case -1066272179:  // obligorShortName
        setObligorShortName((String) newValue);
        return;
      case -823370556:  // obligorREDCode
        setObligorREDCode((String) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      OrganizationSearchRequest other = (OrganizationSearchRequest) obj;
      return JodaBeanUtils.equal(getOrganizationObjectIds(), other.getOrganizationObjectIds()) &&
          JodaBeanUtils.equal(getObligorTicker(), other.getObligorTicker()) &&
          JodaBeanUtils.equal(getObligorShortName(), other.getObligorShortName()) &&
          JodaBeanUtils.equal(getObligorREDCode(), other.getObligorREDCode()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getOrganizationObjectIds());
    hash += hash * 31 + JodaBeanUtils.hashCode(getObligorTicker());
    hash += hash * 31 + JodaBeanUtils.hashCode(getObligorShortName());
    hash += hash * 31 + JodaBeanUtils.hashCode(getObligorREDCode());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the set of organization object identifiers, null to not limit by organization object identifiers.
   * Note that an empty set will return no organizations.
   * @return the value of the property
   */
  public List<ObjectId> getOrganizationObjectIds() {
    return _organizationObjectIds;
  }

  /**
   * Gets the the {@code organizationObjectIds} property.
   * Note that an empty set will return no organizations.
   * @return the property, not null
   */
  public final Property<List<ObjectId>> organizationObjectIds() {
    return metaBean().organizationObjectIds().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the organization ticker, wildcards allowed, null to not match on ticker.
   * @return the value of the property
   */
  public String getObligorTicker() {
    return _obligorTicker;
  }

  /**
   * Sets the organization ticker, wildcards allowed, null to not match on ticker.
   * @param obligorTicker  the new value of the property
   */
  public void setObligorTicker(String obligorTicker) {
    this._obligorTicker = obligorTicker;
  }

  /**
   * Gets the the {@code obligorTicker} property.
   * @return the property, not null
   */
  public final Property<String> obligorTicker() {
    return metaBean().obligorTicker().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the organization short name, wildcards allowed, null to not match on short name.
   * @return the value of the property
   */
  public String getObligorShortName() {
    return _obligorShortName;
  }

  /**
   * Sets the organization short name, wildcards allowed, null to not match on short name.
   * @param obligorShortName  the new value of the property
   */
  public void setObligorShortName(String obligorShortName) {
    this._obligorShortName = obligorShortName;
  }

  /**
   * Gets the the {@code obligorShortName} property.
   * @return the property, not null
   */
  public final Property<String> obligorShortName() {
    return metaBean().obligorShortName().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the Reference Entity Database code of the organization, wildcards allowed, null to not match on RED code.
   * @return the value of the property
   */
  public String getObligorREDCode() {
    return _obligorREDCode;
  }

  /**
   * Sets the Reference Entity Database code of the organization, wildcards allowed, null to not match on RED code.
   * @param obligorREDCode  the new value of the property
   */
  public void setObligorREDCode(String obligorREDCode) {
    this._obligorREDCode = obligorREDCode;
  }

  /**
   * Gets the the {@code obligorREDCode} property.
   * @return the property, not null
   */
  public final Property<String> obligorREDCode() {
    return metaBean().obligorREDCode().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code OrganizationSearchRequest}.
   */
  public static class Meta extends AbstractSearchRequest.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code organizationObjectIds} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<List<ObjectId>> _organizationObjectIds = DirectMetaProperty.ofReadWrite(
        this, "organizationObjectIds", OrganizationSearchRequest.class, (Class) List.class);
    /**
     * The meta-property for the {@code obligorTicker} property.
     */
    private final MetaProperty<String> _obligorTicker = DirectMetaProperty.ofReadWrite(
        this, "obligorTicker", OrganizationSearchRequest.class, String.class);
    /**
     * The meta-property for the {@code obligorShortName} property.
     */
    private final MetaProperty<String> _obligorShortName = DirectMetaProperty.ofReadWrite(
        this, "obligorShortName", OrganizationSearchRequest.class, String.class);
    /**
     * The meta-property for the {@code obligorREDCode} property.
     */
    private final MetaProperty<String> _obligorREDCode = DirectMetaProperty.ofReadWrite(
        this, "obligorREDCode", OrganizationSearchRequest.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "organizationObjectIds",
        "obligorTicker",
        "obligorShortName",
        "obligorREDCode");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1296014086:  // organizationObjectIds
          return _organizationObjectIds;
        case 896190372:  // obligorTicker
          return _obligorTicker;
        case -1066272179:  // obligorShortName
          return _obligorShortName;
        case -823370556:  // obligorREDCode
          return _obligorREDCode;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends OrganizationSearchRequest> builder() {
      return new DirectBeanBuilder<OrganizationSearchRequest>(new OrganizationSearchRequest());
    }

    @Override
    public Class<? extends OrganizationSearchRequest> beanType() {
      return OrganizationSearchRequest.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code organizationObjectIds} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<List<ObjectId>> organizationObjectIds() {
      return _organizationObjectIds;
    }

    /**
     * The meta-property for the {@code obligorTicker} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> obligorTicker() {
      return _obligorTicker;
    }

    /**
     * The meta-property for the {@code obligorShortName} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> obligorShortName() {
      return _obligorShortName;
    }

    /**
     * The meta-property for the {@code obligorREDCode} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> obligorREDCode() {
      return _obligorREDCode;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}