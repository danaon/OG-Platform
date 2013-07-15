/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.interestrate.bond.calculator;

import org.apache.commons.lang.Validate;

import com.opengamma.analytics.financial.interestrate.InstrumentDerivativeVisitorAdapter;
import com.opengamma.analytics.financial.interestrate.bond.definition.BondFixedSecurity;
import com.opengamma.analytics.financial.interestrate.bond.method.BondSecurityDiscountingMethod;

/**
 * Bond clean price from the conventional yield-to-maturity function.
 */
public final class CleanPriceFromYieldCalculator extends InstrumentDerivativeVisitorAdapter<Double, Double> {

  private static final CleanPriceFromYieldCalculator INSTANCE = new CleanPriceFromYieldCalculator();

  public static CleanPriceFromYieldCalculator getInstance() {
    return INSTANCE;
  }

  private CleanPriceFromYieldCalculator() {
  }

  @Override
  public Double visitBondFixedSecurity(final BondFixedSecurity bond, final Double yield) {
    Validate.notNull(bond, "bond");
    Validate.notNull(yield, "yield");
    return BondSecurityDiscountingMethod.getInstance().cleanPriceFromYield(bond, yield);
  }
}
