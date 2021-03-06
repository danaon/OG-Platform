/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.shiftedlognormal;

import java.util.List;

import com.opengamma.engine.function.config.AbstractFunctionConfigurationBean;
import com.opengamma.engine.function.config.FunctionConfiguration;
import com.opengamma.engine.function.config.FunctionConfigurationSource;

/**
 * Adds shifted lognormal pricing and risk functions to the function configuration.
 */
public class ShiftedLognormalDiscountingPricingFunctions extends AbstractFunctionConfigurationBean {

  /**
   * Gets an instance of this class.
   * @return The instance
   */
  public static FunctionConfigurationSource instance() {
    return new ShiftedLognormalDiscountingPricingFunctions().getObjectCreating();
  }

  @Override
  protected void addAllConfigurations(final List<FunctionConfiguration> functions) {
    functions.add(functionConfiguration(LognormalVolatilityShiftFunction.class));
    functions.add(functionConfiguration(ShiftedLognormalDiscountingBCSCapFloorFunction.class));
    functions.add(functionConfiguration(ShiftedLognormalDiscountingPVCapFloorFunction.class));
    functions.add(functionConfiguration(ShiftedLognormalDiscountingPV01CapFloorFunction.class));
    functions.add(functionConfiguration(ShiftedLognormalDiscountingYCNSCapFloorFunction.class));
  }
}
