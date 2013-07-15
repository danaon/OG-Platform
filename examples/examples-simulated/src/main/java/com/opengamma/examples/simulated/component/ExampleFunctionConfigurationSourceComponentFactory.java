/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.examples.simulated.component;

import java.util.List;
import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.component.factory.source.FunctionConfigurationSourceComponentFactory;
import com.opengamma.engine.function.config.FunctionConfigurationSource;
import com.opengamma.examples.simulated.function.ExampleStandardFunctionConfiguration;
import com.opengamma.examples.simulated.function.SyntheticVolatilityCubeFunctions;
import com.opengamma.examples.simulated.tutorial.TutorialFunctions;

/**
 * Component factory for the function configuration source.
 */
@BeanDefinition
public class ExampleFunctionConfigurationSourceComponentFactory extends FunctionConfigurationSourceComponentFactory {

  @Override
  protected FunctionConfigurationSource standardConfiguration() {
    return ExampleStandardFunctionConfiguration.instance();
  }

  @Override
  protected FunctionConfigurationSource cubeConfigurations() {
    return SyntheticVolatilityCubeFunctions.instance();
  }

  protected FunctionConfigurationSource tutorialConfiguration() {
    return TutorialFunctions.instance();
  }

  @Override
  protected List<FunctionConfigurationSource> initSources() {
    final List<FunctionConfigurationSource> sources = super.initSources();
    // sources.add(tutorialConfiguration());
    return sources;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ExampleFunctionConfigurationSourceComponentFactory}.
   * @return the meta-bean, not null
   */
  public static ExampleFunctionConfigurationSourceComponentFactory.Meta meta() {
    return ExampleFunctionConfigurationSourceComponentFactory.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(ExampleFunctionConfigurationSourceComponentFactory.Meta.INSTANCE);
  }

  @Override
  public ExampleFunctionConfigurationSourceComponentFactory.Meta metaBean() {
    return ExampleFunctionConfigurationSourceComponentFactory.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  public boolean equals(Object obj) {
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
    int hash = 7;
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ExampleFunctionConfigurationSourceComponentFactory}.
   */
  public static class Meta extends FunctionConfigurationSourceComponentFactory.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap());

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    public BeanBuilder<? extends ExampleFunctionConfigurationSourceComponentFactory> builder() {
      return new DirectBeanBuilder<ExampleFunctionConfigurationSourceComponentFactory>(new ExampleFunctionConfigurationSourceComponentFactory());
    }

    @Override
    public Class<? extends ExampleFunctionConfigurationSourceComponentFactory> beanType() {
      return ExampleFunctionConfigurationSourceComponentFactory.class;
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
