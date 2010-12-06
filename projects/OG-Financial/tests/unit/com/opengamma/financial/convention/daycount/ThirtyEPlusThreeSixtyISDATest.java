/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.convention.daycount;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.opengamma.core.convention.DayCount;

/**
 * Test ThirtyEPlusThreeSixtyISDA.
 */
public class ThirtyEPlusThreeSixtyISDATest extends DayCountTestCase {

  private static final ThirtyEPlusThreeSixtyISDA DC = new ThirtyEPlusThreeSixtyISDA();

  @Override
  protected DayCount getDayCount() {
    return DC;
  }

  @Test
  public void test() {
    assertEquals(COUPON * DC.getDayCountFraction(D1, D2), DC.getAccruedInterest(D1, D2, D3, COUPON, PAYMENTS), 0);
    assertEquals(DC.getConventionName(), "30E+/360 ISDA");
  }

}
