/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.batch;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.time.calendar.LocalDate;

import org.joda.beans.BeanDefinition;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.BasicMetaBean;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectMetaProperty;

import com.opengamma.util.db.PagingRequest;

/**
 * Request for searching for batch data.
 * <p>
 * Documents will be returned that match the search criteria.
 * This class provides the ability to page the results and to search
 * as at a specific version and correction instant.
 */
@BeanDefinition
public class BatchDataSearchRequest extends DirectBean {

  /**
   * The request for paging.
   * By default all matching items will be returned.
   */
  @PropertyDefinition
  private PagingRequest _pagingRequest = PagingRequest.ALL;
  /**
   * The batch date, not null
   */
  @PropertyDefinition
  private LocalDate _observationDate;
  /**
   * The batch time, such as LDN_CLOSE. Not null
   */
  @PropertyDefinition
  private String _observationTime;

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code BatchDataSearchRequest}.
   * @return the meta-bean, not null
   */
  public static BatchDataSearchRequest.Meta meta() {
    return BatchDataSearchRequest.Meta.INSTANCE;
  }

  @Override
  public BatchDataSearchRequest.Meta metaBean() {
    return BatchDataSearchRequest.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName) {
    switch (propertyName.hashCode()) {
      case -2092032669:  // pagingRequest
        return getPagingRequest();
      case 950748666:  // observationDate
        return getObservationDate();
      case 951232793:  // observationTime
        return getObservationTime();
    }
    return super.propertyGet(propertyName);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue) {
    switch (propertyName.hashCode()) {
      case -2092032669:  // pagingRequest
        setPagingRequest((PagingRequest) newValue);
        return;
      case 950748666:  // observationDate
        setObservationDate((LocalDate) newValue);
        return;
      case 951232793:  // observationTime
        setObservationTime((String) newValue);
        return;
    }
    super.propertySet(propertyName, newValue);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the request for paging.
   * By default all matching items will be returned.
   * @return the value of the property
   */
  public PagingRequest getPagingRequest() {
    return _pagingRequest;
  }

  /**
   * Sets the request for paging.
   * By default all matching items will be returned.
   * @param pagingRequest  the new value of the property
   */
  public void setPagingRequest(PagingRequest pagingRequest) {
    this._pagingRequest = pagingRequest;
  }

  /**
   * Gets the the {@code pagingRequest} property.
   * By default all matching items will be returned.
   * @return the property, not null
   */
  public final Property<PagingRequest> pagingRequest() {
    return metaBean().pagingRequest().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the batch date, not null
   * @return the value of the property
   */
  public LocalDate getObservationDate() {
    return _observationDate;
  }

  /**
   * Sets the batch date, not null
   * @param observationDate  the new value of the property
   */
  public void setObservationDate(LocalDate observationDate) {
    this._observationDate = observationDate;
  }

  /**
   * Gets the the {@code observationDate} property.
   * @return the property, not null
   */
  public final Property<LocalDate> observationDate() {
    return metaBean().observationDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the batch time, such as LDN_CLOSE. Not null
   * @return the value of the property
   */
  public String getObservationTime() {
    return _observationTime;
  }

  /**
   * Sets the batch time, such as LDN_CLOSE. Not null
   * @param observationTime  the new value of the property
   */
  public void setObservationTime(String observationTime) {
    this._observationTime = observationTime;
  }

  /**
   * Gets the the {@code observationTime} property.
   * @return the property, not null
   */
  public final Property<String> observationTime() {
    return metaBean().observationTime().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code BatchDataSearchRequest}.
   */
  public static class Meta extends BasicMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code pagingRequest} property.
     */
    private final MetaProperty<PagingRequest> _pagingRequest = DirectMetaProperty.ofReadWrite(this, "pagingRequest", PagingRequest.class);
    /**
     * The meta-property for the {@code observationDate} property.
     */
    private final MetaProperty<LocalDate> _observationDate = DirectMetaProperty.ofReadWrite(this, "observationDate", LocalDate.class);
    /**
     * The meta-property for the {@code observationTime} property.
     */
    private final MetaProperty<String> _observationTime = DirectMetaProperty.ofReadWrite(this, "observationTime", String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<Object>> _map;

    @SuppressWarnings({"unchecked", "rawtypes" })
    protected Meta() {
      LinkedHashMap temp = new LinkedHashMap();
      temp.put("pagingRequest", _pagingRequest);
      temp.put("observationDate", _observationDate);
      temp.put("observationTime", _observationTime);
      _map = Collections.unmodifiableMap(temp);
    }

    @Override
    public BatchDataSearchRequest createBean() {
      return new BatchDataSearchRequest();
    }

    @Override
    public Class<? extends BatchDataSearchRequest> beanType() {
      return BatchDataSearchRequest.class;
    }

    @Override
    public Map<String, MetaProperty<Object>> metaPropertyMap() {
      return _map;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code pagingRequest} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<PagingRequest> pagingRequest() {
      return _pagingRequest;
    }

    /**
     * The meta-property for the {@code observationDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<LocalDate> observationDate() {
      return _observationDate;
    }

    /**
     * The meta-property for the {@code observationTime} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> observationTime() {
      return _observationTime;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
