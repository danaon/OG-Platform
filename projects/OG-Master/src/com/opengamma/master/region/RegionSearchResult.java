/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.joda.beans.BeanDefinition;
import org.joda.beans.MetaProperty;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.master.AbstractSearchResult;
import com.opengamma.util.PublicSPI;

/**
 * Result from searching for regions.
 * <p>
 * The returned documents will match the search criteria.
 * See {@link RegionSearchRequest} for more details.
 */
@PublicSPI
@BeanDefinition
public class RegionSearchResult extends AbstractSearchResult<RegionDocument> {

  /**
   * Creates an instance.
   */
  public RegionSearchResult() {
  }

  /**
   * Creates an instance.
   * 
   * @param coll  the collection of documents to add, not null
   */
  public RegionSearchResult(Collection<RegionDocument> coll) {
    super(coll);
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the returned regions from within the documents.
   * 
   * @return the regions, not null
   */
  public List<ManageableRegion> getRegions() {
    List<ManageableRegion> result = new ArrayList<ManageableRegion>();
    if (getDocuments() != null) {
      for (RegionDocument doc : getDocuments()) {
        result.add(doc.getRegion());
      }
    }
    return result;
  }

  /**
   * Gets the first region, or null if no documents.
   * 
   * @return the first region, null if none
   */
  public ManageableRegion getFirstRegion() {
    return getDocuments().size() > 0 ? getDocuments().get(0).getRegion() : null;
  }

  /**
   * Gets the single result expected from a query.
   * <p>
   * This throws an exception if more than 1 result is actually available.
   * Thus, this method implies an assumption about uniqueness of the queried region.
   * 
   * @return the matching region, not null
   * @throws IllegalStateException if no region was found
   */
  public ManageableRegion getSingleRegion() {
    if (getDocuments().size() != 1) {
      throw new OpenGammaRuntimeException("Expecting zero or single resulting match, and was " + getDocuments().size());
    } else {
      return getDocuments().get(0).getRegion();
    }
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code RegionSearchResult}.
   * @return the meta-bean, not null
   */
  @SuppressWarnings("unchecked")
  public static RegionSearchResult.Meta meta() {
    return RegionSearchResult.Meta.INSTANCE;
  }

  @Override
  public RegionSearchResult.Meta metaBean() {
    return RegionSearchResult.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName) {
    switch (propertyName.hashCode()) {
    }
    return super.propertyGet(propertyName);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue) {
    switch (propertyName.hashCode()) {
    }
    super.propertySet(propertyName, newValue);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code RegionSearchResult}.
   */
  public static class Meta extends AbstractSearchResult.Meta<RegionDocument> {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<Object>> _map;

    @SuppressWarnings({"unchecked", "rawtypes" })
    protected Meta() {
      LinkedHashMap temp = new LinkedHashMap(super.metaPropertyMap());
      _map = Collections.unmodifiableMap(temp);
    }

    @Override
    public RegionSearchResult createBean() {
      return new RegionSearchResult();
    }

    @Override
    public Class<? extends RegionSearchResult> beanType() {
      return RegionSearchResult.class;
    }

    @Override
    public Map<String, MetaProperty<Object>> metaPropertyMap() {
      return _map;
    }

    //-----------------------------------------------------------------------
  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
