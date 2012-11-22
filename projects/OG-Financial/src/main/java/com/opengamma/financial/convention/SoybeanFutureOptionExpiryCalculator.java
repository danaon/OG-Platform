/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.convention;

import java.util.Arrays;

import javax.time.calendar.DateAdjuster;
import javax.time.calendar.DateAdjusters;
import javax.time.calendar.DayOfWeek;
import javax.time.calendar.LocalDate;
import javax.time.calendar.MonthOfYear;

import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.util.ArgumentChecker;

/**
 *
 */
public final class SoybeanFutureOptionExpiryCalculator implements ExchangeTradedInstrumentExpiryCalculator {
  /** Name of the calculator */
  public static final String NAME = "SoybeanFutureOptionExpiryCalculator";
  private static final DateAdjuster LAST_DAY_ADJUSTER = DateAdjusters.lastDayOfMonth();
  private static final DateAdjuster PREVIOUS_OR_CURRENT_FRIDAY_ADJUSTER = DateAdjusters.previousOrCurrent(DayOfWeek.FRIDAY);
  private static final DateAdjuster PREVIOUS_FRIDAY_ADJUSTER = DateAdjusters.previous(DayOfWeek.FRIDAY);
  private static final SoybeanFutureOptionExpiryCalculator INSTANCE = new SoybeanFutureOptionExpiryCalculator();

  private static final MonthOfYear[] SOYBEAN_FUTURE_EXPIRY_MONTHS =
  {MonthOfYear.JANUARY, MonthOfYear.MARCH, MonthOfYear.MAY, MonthOfYear.JULY,
    MonthOfYear.AUGUST, MonthOfYear.SEPTEMBER, MonthOfYear.NOVEMBER
  };

  public static SoybeanFutureOptionExpiryCalculator getInstance() {
    return INSTANCE;
  }

  private SoybeanFutureOptionExpiryCalculator() {
  }


  /**
   * Expiry date of Soybean Future Options:
   * The last Friday which precedes by at least two business days the last business day of the month preceding the option month.
   * See http://www.cmegroup.com/trading/agricultural/grain-and-oilseed/soybean_contractSpecs_options.html#prodType=AME
   * TODO Confirm adjustment made if Friday is not a business day. We use the business day before
   * @param n n'th expiry date after today
   * @param today valuation date
   * @param holidayCalendar holiday calendar
   * @return True expiry date of the option
   */
  @Override
  public LocalDate getExpiryDate(final int n, final LocalDate today, final Calendar holidayCalendar) {
    ArgumentChecker.isTrue(n > 0, "n must be greater than zero; have {}", n);
    ArgumentChecker.notNull(today, "today");
    ArgumentChecker.notNull(holidayCalendar, "holiday calendar");

    final LocalDate expiryMonth = getExpiryMonth(n, today);
    final LocalDate actualExpiryMonth = expiryMonth.minusMonths(1);
    final LocalDate lastDayOfMonth = LAST_DAY_ADJUSTER.adjustDate(actualExpiryMonth);
    final LocalDate lastFridayOfMonth = PREVIOUS_OR_CURRENT_FRIDAY_ADJUSTER.adjustDate(lastDayOfMonth);
    LocalDate expiryDate = lastFridayOfMonth;

    int nBusinessDays = 0;
    LocalDate date = lastFridayOfMonth.plusDays(1);
    while (!date.isAfter(lastDayOfMonth)) {
      if (holidayCalendar.isWorkingDay(date)) {
        nBusinessDays++;
      }
      if (nBusinessDays >= 2) {
        while (!holidayCalendar.isWorkingDay(expiryDate)) {
          expiryDate = expiryDate.minusDays(1);
        }
        return expiryDate;
      }
      date = date.plusDays(1);
    }
    LocalDate result = PREVIOUS_FRIDAY_ADJUSTER.adjustDate(expiryDate);
    while (!holidayCalendar.isWorkingDay(result)) {
      result = result.minusDays(1);
    }
    return result;
  }

  @Override
  /**
   * Given a LocalDate representing the valuation date and
   * an integer representing the n'th expiry after that date,
   * returns a date in the expiry month
   * Used in BloombergFutureUtils.getExpiryCodeForSoybeanFutureOptions()
   *
   */
  public LocalDate getExpiryMonth(final int n, final LocalDate today) {
    ArgumentChecker.isTrue(n > 0, "n must be greater than zero");
    ArgumentChecker.notNull(today, "today");
    // There are 3 serial options
    if (n < 4) {
      return today.plusMonths(n); //
    }
    int m = n - 3;
    LocalDate expiryDate = today.plusMonths(3);
    while (m > 0) {
      expiryDate = getNextExpiryMonth(expiryDate);
      m--;
    }
    return expiryDate;
  }

  private LocalDate getNextExpiryMonth(final LocalDate dtCurrent) {
    MonthOfYear mthCurrent = dtCurrent.getMonthOfYear();
    int idx = Arrays.binarySearch(SOYBEAN_FUTURE_EXPIRY_MONTHS, mthCurrent);
    if (idx >= (SOYBEAN_FUTURE_EXPIRY_MONTHS.length - 1)) {
      return LocalDate.of(dtCurrent.getYear() + 1, MonthOfYear.JANUARY, dtCurrent.getDayOfMonth());
    } else if (idx >= 0) {
      return dtCurrent.with(SOYBEAN_FUTURE_EXPIRY_MONTHS[idx + 1]);
    } else {
      return dtCurrent.with(SOYBEAN_FUTURE_EXPIRY_MONTHS[-1 - idx]);
    }
  }

  @Override
  public String getName() {
    return NAME;
  }
}
