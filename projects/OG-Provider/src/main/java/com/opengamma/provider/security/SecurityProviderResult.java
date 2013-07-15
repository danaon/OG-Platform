/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.provider.security;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.google.common.collect.Maps;
import com.opengamma.core.security.Security;
import com.opengamma.id.ExternalIdBundle;
import com.opengamma.util.PublicSPI;

/**
 * Result from getting one or more securities.
 * <p>
 * This class is mutable and not thread-safe.
 */
@PublicSPI
@BeanDefinition
public class SecurityProviderResult extends DirectBean {

  /**
   * The securities that were obtained.
   */
  @PropertyDefinition
  private final Map<ExternalIdBundle, Security> _resultMap = Maps.newHashMap();

  /**
   * Creates an instance.
   */
  public SecurityProviderResult() {
  }

  /**
   * Creates an instance.
   * 
   * @param result  the map of results, not null
   */
  public SecurityProviderResult(Map<ExternalIdBundle, ? extends Security> result) {
    getResultMap().putAll(result);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code SecurityProviderResult}.
   * @return the meta-bean, not null
   */
  public static SecurityProviderResult.Meta meta() {
    return SecurityProviderResult.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(SecurityProviderResult.Meta.INSTANCE);
  }

  @Override
  public SecurityProviderResult.Meta metaBean() {
    return SecurityProviderResult.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -1819569153:  // resultMap
        return getResultMap();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -1819569153:  // resultMap
        setResultMap((Map<ExternalIdBundle, Security>) newValue);
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
      SecurityProviderResult other = (SecurityProviderResult) obj;
      return JodaBeanUtils.equal(getResultMap(), other.getResultMap());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getResultMap());
    return hash;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the securities that were obtained.
   * @return the value of the property
   */
  public Map<ExternalIdBundle, Security> getResultMap() {
    return _resultMap;
  }

  /**
   * Sets the securities that were obtained.
   * @param resultMap  the new value of the property
   */
  public void setResultMap(Map<ExternalIdBundle, Security> resultMap) {
    this._resultMap.clear();
    this._resultMap.putAll(resultMap);
  }

  /**
   * Gets the the {@code resultMap} property.
   * @return the property, not null
   */
  public final Property<Map<ExternalIdBundle, Security>> resultMap() {
    return metaBean().resultMap().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code SecurityProviderResult}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code resultMap} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Map<ExternalIdBundle, Security>> _resultMap = DirectMetaProperty.ofReadWrite(
        this, "resultMap", SecurityProviderResult.class, (Class) Map.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "resultMap");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1819569153:  // resultMap
          return _resultMap;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends SecurityProviderResult> builder() {
      return new DirectBeanBuilder<SecurityProviderResult>(new SecurityProviderResult());
    }

    @Override
    public Class<? extends SecurityProviderResult> beanType() {
      return SecurityProviderResult.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code resultMap} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Map<ExternalIdBundle, Security>> resultMap() {
      return _resultMap;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
