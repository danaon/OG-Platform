/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.currency;

import java.util.List;

import com.opengamma.engine.function.config.AbstractRepositoryConfigurationBean;
import com.opengamma.engine.function.config.FunctionConfiguration;
import com.opengamma.engine.function.config.RepositoryConfigurationSource;
import com.opengamma.engine.value.ValueRequirementNames;

/**
 * Function repository configuration source for the functions contained in this package.
 */
public class CurrencyFunctions extends AbstractRepositoryConfigurationBean {

  /**
   * Default instance of a repository configuration source exposing the functions from this package.
   *
   * @return the configuration source exposing functions from this package
   */
  public static RepositoryConfigurationSource instance() {
    return new CurrencyFunctions().getObjectCreating();
  }

  protected void addDefaultCurrencyFunction(final List<FunctionConfiguration> functions, final String requirementName) {
    functions.add(functionConfiguration(DefaultCurrencyFunction.Permissive.class, requirementName));
  }

  public void addCurrencyConversionFunction(final List<FunctionConfiguration> functions, final String requirementName) {
    functions.add(functionConfiguration(CurrencyConversionFunction.class, requirementName));
    addDefaultCurrencyFunction(functions, requirementName);
  }

  public void addCurrencySeriesConversionFunction(final List<FunctionConfiguration> functions, final String requirementName) {
    functions.add(functionConfiguration(CurrencySeriesConversionFunction.class, requirementName));
    addDefaultCurrencyFunction(functions, requirementName);
  }

  @Override
  protected void addAllConfigurations(final List<FunctionConfiguration> functions) {
    addCurrencyConversionFunction(functions, ValueRequirementNames.DAILY_PNL);
    addCurrencyConversionFunction(functions, ValueRequirementNames.DV01);
    addCurrencyConversionFunction(functions, ValueRequirementNames.FAIR_VALUE);
    addCurrencySeriesConversionFunction(functions, ValueRequirementNames.PNL_SERIES);
    addCurrencyConversionFunction(functions, ValueRequirementNames.PRESENT_VALUE);
    //TODO PRESENT_VALUE_CURVE_SENSITIVITY
    addCurrencyConversionFunction(functions, ValueRequirementNames.PV01);
    addCurrencyConversionFunction(functions, ValueRequirementNames.VALUE_DELTA);
    addCurrencyConversionFunction(functions, ValueRequirementNames.VALUE_GAMMA);
    addCurrencyConversionFunction(functions, ValueRequirementNames.VALUE_PHI);
    addCurrencyConversionFunction(functions, ValueRequirementNames.VALUE_RHO);
    addCurrencyConversionFunction(functions, ValueRequirementNames.VALUE_SPEED);
    addCurrencyConversionFunction(functions, ValueRequirementNames.VALUE_THETA);
    addCurrencyConversionFunction(functions, ValueRequirementNames.VALUE_VANNA);
    addCurrencyConversionFunction(functions, ValueRequirementNames.VALUE_VEGA);
    addCurrencyConversionFunction(functions, ValueRequirementNames.VALUE_VOMMA);
    addCurrencyConversionFunction(functions, ValueRequirementNames.YIELD_CURVE_NODE_SENSITIVITIES);
    addCurrencyConversionFunction(functions, ValueRequirementNames.MONETIZED_VEGA);
  }

}
