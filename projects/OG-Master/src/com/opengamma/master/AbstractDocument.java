/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master;

import java.util.Map;

import javax.time.Instant;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.id.MutableUniqueIdentifiable;
import com.opengamma.id.ObjectId;
import com.opengamma.id.ObjectIdentifiable;
import com.opengamma.id.UniqueId;
import com.opengamma.id.UniqueIdentifiable;
import com.opengamma.util.PublicSPI;

/**
 * A document used to pass into and out of a master.
 * <p>
 * This abstract class is intended for providing the standard design for documents
 * stored in a master.
 */
@PublicSPI
@BeanDefinition
public abstract class AbstractDocument extends DirectBean
    implements UniqueIdentifiable, MutableUniqueIdentifiable, ObjectIdentifiable {

  /**
   * The start of an interval that the version of the document is accurate for.
   * This field is populated and managed by the master.
   */
  @PropertyDefinition
  private Instant _versionFromInstant;
  /**
   * The end of an interval that the version of the document is accurate for.
   * Null indicates this is the latest version.
   * This field is populated and managed by the master.
   */
  @PropertyDefinition
  private Instant _versionToInstant;
  /**
   * The start of an interval that the correction of the version of the document is accurate for.
   * This field is populated and managed by the master.
   */
  @PropertyDefinition
  private Instant _correctionFromInstant;
  /**
   * The end of an interval that the correction of the version of the document is accurate for.
   * Null indicates this is the latest correction.
   * This field is populated and managed by the master.
   */
  @PropertyDefinition
  private Instant _correctionToInstant;

  /**
   * Creates an instance.
   */
  public AbstractDocument() {
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the principal object held in the document.
   *
   * @return the principal object of the document, may be null
   */
  public abstract UniqueIdentifiable getObject();

  //-------------------------------------------------------------------------
  /**
   * Gets the unique identifier of the document.
   * <p>
   * This may be derived from an object held within the document.
   *
   * @return the unique identifier, may be null, not null when returned from a query
   */
  @Override
  public abstract UniqueId getUniqueId();

  /**
   * Sets the unique identifier of the document.
   * <p>
   * This may be stored in an object held within the document.
   *
   * @param uniqueId  the unique identifier, may be null
   */
  @Override
  public abstract void setUniqueId(UniqueId uniqueId);

  //-------------------------------------------------------------------------
  /**
   * Gets the object identifier of the document.
   * <p>
   * This may be derived from an object held within the document.
   *
   * @return the object identifier, may be null, not null when returned from a query
   */
  @Override
  public ObjectId getObjectId() {
    return getUniqueId().getObjectId();
  }

  //-------------------------------------------------------------------------
  /**
   * Checks if this is the latest version and correction of the document.
   * <p>
   * An earlier version, or a deleted document, will return false.
   *
   * @return true if this is the latest document version/correction
   */
  public boolean isLatest() {
    return getVersionToInstant() == null && getCorrectionToInstant() == null;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code AbstractDocument}.
   * @return the meta-bean, not null
   */
  public static AbstractDocument.Meta meta() {
    return AbstractDocument.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(AbstractDocument.Meta.INSTANCE);
  }

  @Override
  public AbstractDocument.Meta metaBean() {
    return AbstractDocument.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 2006263519:  // versionFromInstant
        return getVersionFromInstant();
      case 1577022702:  // versionToInstant
        return getVersionToInstant();
      case 1808757913:  // correctionFromInstant
        return getCorrectionFromInstant();
      case 973465896:  // correctionToInstant
        return getCorrectionToInstant();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 2006263519:  // versionFromInstant
        setVersionFromInstant((Instant) newValue);
        return;
      case 1577022702:  // versionToInstant
        setVersionToInstant((Instant) newValue);
        return;
      case 1808757913:  // correctionFromInstant
        setCorrectionFromInstant((Instant) newValue);
        return;
      case 973465896:  // correctionToInstant
        setCorrectionToInstant((Instant) newValue);
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
      AbstractDocument other = (AbstractDocument) obj;
      return JodaBeanUtils.equal(getVersionFromInstant(), other.getVersionFromInstant()) &&
          JodaBeanUtils.equal(getVersionToInstant(), other.getVersionToInstant()) &&
          JodaBeanUtils.equal(getCorrectionFromInstant(), other.getCorrectionFromInstant()) &&
          JodaBeanUtils.equal(getCorrectionToInstant(), other.getCorrectionToInstant());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getVersionFromInstant());
    hash += hash * 31 + JodaBeanUtils.hashCode(getVersionToInstant());
    hash += hash * 31 + JodaBeanUtils.hashCode(getCorrectionFromInstant());
    hash += hash * 31 + JodaBeanUtils.hashCode(getCorrectionToInstant());
    return hash;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the start of an interval that the version of the document is accurate for.
   * This field is populated and managed by the master.
   * @return the value of the property
   */
  public Instant getVersionFromInstant() {
    return _versionFromInstant;
  }

  /**
   * Sets the start of an interval that the version of the document is accurate for.
   * This field is populated and managed by the master.
   * @param versionFromInstant  the new value of the property
   */
  public void setVersionFromInstant(Instant versionFromInstant) {
    this._versionFromInstant = versionFromInstant;
  }

  /**
   * Gets the the {@code versionFromInstant} property.
   * This field is populated and managed by the master.
   * @return the property, not null
   */
  public final Property<Instant> versionFromInstant() {
    return metaBean().versionFromInstant().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the end of an interval that the version of the document is accurate for.
   * Null indicates this is the latest version.
   * This field is populated and managed by the master.
   * @return the value of the property
   */
  public Instant getVersionToInstant() {
    return _versionToInstant;
  }

  /**
   * Sets the end of an interval that the version of the document is accurate for.
   * Null indicates this is the latest version.
   * This field is populated and managed by the master.
   * @param versionToInstant  the new value of the property
   */
  public void setVersionToInstant(Instant versionToInstant) {
    this._versionToInstant = versionToInstant;
  }

  /**
   * Gets the the {@code versionToInstant} property.
   * Null indicates this is the latest version.
   * This field is populated and managed by the master.
   * @return the property, not null
   */
  public final Property<Instant> versionToInstant() {
    return metaBean().versionToInstant().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the start of an interval that the correction of the version of the document is accurate for.
   * This field is populated and managed by the master.
   * @return the value of the property
   */
  public Instant getCorrectionFromInstant() {
    return _correctionFromInstant;
  }

  /**
   * Sets the start of an interval that the correction of the version of the document is accurate for.
   * This field is populated and managed by the master.
   * @param correctionFromInstant  the new value of the property
   */
  public void setCorrectionFromInstant(Instant correctionFromInstant) {
    this._correctionFromInstant = correctionFromInstant;
  }

  /**
   * Gets the the {@code correctionFromInstant} property.
   * This field is populated and managed by the master.
   * @return the property, not null
   */
  public final Property<Instant> correctionFromInstant() {
    return metaBean().correctionFromInstant().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the end of an interval that the correction of the version of the document is accurate for.
   * Null indicates this is the latest correction.
   * This field is populated and managed by the master.
   * @return the value of the property
   */
  public Instant getCorrectionToInstant() {
    return _correctionToInstant;
  }

  /**
   * Sets the end of an interval that the correction of the version of the document is accurate for.
   * Null indicates this is the latest correction.
   * This field is populated and managed by the master.
   * @param correctionToInstant  the new value of the property
   */
  public void setCorrectionToInstant(Instant correctionToInstant) {
    this._correctionToInstant = correctionToInstant;
  }

  /**
   * Gets the the {@code correctionToInstant} property.
   * Null indicates this is the latest correction.
   * This field is populated and managed by the master.
   * @return the property, not null
   */
  public final Property<Instant> correctionToInstant() {
    return metaBean().correctionToInstant().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code AbstractDocument}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code versionFromInstant} property.
     */
    private final MetaProperty<Instant> _versionFromInstant = DirectMetaProperty.ofReadWrite(
        this, "versionFromInstant", AbstractDocument.class, Instant.class);
    /**
     * The meta-property for the {@code versionToInstant} property.
     */
    private final MetaProperty<Instant> _versionToInstant = DirectMetaProperty.ofReadWrite(
        this, "versionToInstant", AbstractDocument.class, Instant.class);
    /**
     * The meta-property for the {@code correctionFromInstant} property.
     */
    private final MetaProperty<Instant> _correctionFromInstant = DirectMetaProperty.ofReadWrite(
        this, "correctionFromInstant", AbstractDocument.class, Instant.class);
    /**
     * The meta-property for the {@code correctionToInstant} property.
     */
    private final MetaProperty<Instant> _correctionToInstant = DirectMetaProperty.ofReadWrite(
        this, "correctionToInstant", AbstractDocument.class, Instant.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "versionFromInstant",
        "versionToInstant",
        "correctionFromInstant",
        "correctionToInstant");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 2006263519:  // versionFromInstant
          return _versionFromInstant;
        case 1577022702:  // versionToInstant
          return _versionToInstant;
        case 1808757913:  // correctionFromInstant
          return _correctionFromInstant;
        case 973465896:  // correctionToInstant
          return _correctionToInstant;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends AbstractDocument> builder() {
      throw new UnsupportedOperationException("AbstractDocument is an abstract class");
    }

    @Override
    public Class<? extends AbstractDocument> beanType() {
      return AbstractDocument.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code versionFromInstant} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Instant> versionFromInstant() {
      return _versionFromInstant;
    }

    /**
     * The meta-property for the {@code versionToInstant} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Instant> versionToInstant() {
      return _versionToInstant;
    }

    /**
     * The meta-property for the {@code correctionFromInstant} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Instant> correctionFromInstant() {
      return _correctionFromInstant;
    }

    /**
     * The meta-property for the {@code correctionToInstant} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Instant> correctionToInstant() {
      return _correctionToInstant;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
