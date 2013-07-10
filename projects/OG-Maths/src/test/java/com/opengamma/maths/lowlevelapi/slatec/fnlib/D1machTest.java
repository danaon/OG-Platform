/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.lowlevelapi.functions.D1mach;

/**
 * Tests double precision machine constants found in D1mach
 */
public class D1machTest {

  @Test
  public void d1machTestone() {
    assertTrue(D1mach.one()==2.2250738585072014E-308);
  }

  @Test
  public void d1machTesttwo() {
    assertTrue(D1mach.two()==1.7976931348623157E308);
  }
  
  @Test
  public void d1machTestthree() {
    assertTrue(D1mach.three()==1.1102230246251565E-16);
  }
  
  @Test
  public void d1machTestfour() {
    assertTrue(D1mach.four()==2.220446049250313E-16);
  }
  
  @Test
  public void d1machTestfive() {
    assertTrue(D1mach.five()==0.3010299956639812);
  }

}
