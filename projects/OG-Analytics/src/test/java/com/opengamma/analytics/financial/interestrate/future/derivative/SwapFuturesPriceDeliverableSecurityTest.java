/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.interestrate.future.derivative;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;
import org.threeten.bp.Period;
import org.threeten.bp.ZonedDateTime;

import com.opengamma.analytics.financial.instrument.index.GeneratorSwapFixedIbor;
import com.opengamma.analytics.financial.instrument.index.GeneratorSwapFixedIborMaster;
import com.opengamma.analytics.financial.instrument.swap.SwapFixedIborDefinition;
import com.opengamma.analytics.financial.interestrate.payments.derivative.Coupon;
import com.opengamma.analytics.financial.interestrate.swap.derivative.SwapFixedCoupon;
import com.opengamma.analytics.financial.schedule.ScheduleCalculator;
import com.opengamma.analytics.util.time.TimeCalculator;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.financial.convention.calendar.MondayToFridayCalendar;
import com.opengamma.util.time.DateUtils;

/**
 * Tests related to the description of Deliverable Interest Rate Swap Futures as traded on CME.
 */
public class SwapFuturesPriceDeliverableSecurityTest {

  private static final Calendar NYC = new MondayToFridayCalendar("NYC");
  private static final GeneratorSwapFixedIbor USD6MLIBOR3M = GeneratorSwapFixedIborMaster.getInstance().getGenerator("USD6MLIBOR3M", NYC);
  private static final ZonedDateTime EFFECTIVE_DATE = DateUtils.getUTCDate(2012, 12, 19);
  private static final ZonedDateTime LAST_TRADING_DATE = ScheduleCalculator.getAdjustedDate(EFFECTIVE_DATE, -USD6MLIBOR3M.getSpotLag(), NYC);
  private static final Period TENOR = Period.ofYears(10);
  private static final double NOTIONAL = 100000;
  private static final double RATE = 0.0175;
  private static final SwapFixedIborDefinition SWAP_DEFINITION = SwapFixedIborDefinition.from(EFFECTIVE_DATE, TENOR, USD6MLIBOR3M, 1.0, RATE, false);

  private static final ZonedDateTime REFERENCE_DATE = DateUtils.getUTCDate(2012, 9, 21);
  private static final String DSC_CURVE_NAME = "USD Discounting";
  private static final String FWD3_CURVE_NAME = "USD Forward 3M";
  private static final String[] CURVE_NAMES = {DSC_CURVE_NAME, FWD3_CURVE_NAME };
  private static final SwapFixedCoupon<? extends Coupon> SWAP = SWAP_DEFINITION.toDerivative(REFERENCE_DATE, CURVE_NAMES);
  private static final double LAST_TRADING_TIME = TimeCalculator.getTimeBetween(REFERENCE_DATE, LAST_TRADING_DATE);
  private static final double EFFECTIVE_TIME = TimeCalculator.getTimeBetween(REFERENCE_DATE, EFFECTIVE_DATE);

  private static final SwapFuturesPriceDeliverableSecurity SWAP_FUTURES_SECURITY = new SwapFuturesPriceDeliverableSecurity(LAST_TRADING_TIME, EFFECTIVE_TIME, SWAP, NOTIONAL);

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void nullSwap() {
    new SwapFuturesPriceDeliverableSecurity(LAST_TRADING_TIME, EFFECTIVE_TIME, null, NOTIONAL);
  }

  @Test
  /**
   * Tests the getter methods.
   */
  public void getter() {
    assertEquals("DeliverableSwapFuturesSecurity: getter", LAST_TRADING_TIME, SWAP_FUTURES_SECURITY.getLastTradingTime());
    assertEquals("DeliverableSwapFuturesSecurity: getter", EFFECTIVE_TIME, SWAP_FUTURES_SECURITY.getDeliveryTime());
    assertEquals("DeliverableSwapFuturesSecurity: getter", SWAP, SWAP_FUTURES_SECURITY.getUnderlyingSwap());
    assertEquals("DeliverableSwapFuturesSecurity: getter", NOTIONAL, SWAP_FUTURES_SECURITY.getNotional());
  }

}
