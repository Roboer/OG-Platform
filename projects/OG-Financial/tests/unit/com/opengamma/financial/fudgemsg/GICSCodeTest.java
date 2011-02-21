/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.fudgemsg;

import static org.junit.Assert.assertEquals;

import org.fudgemsg.FudgeMsgField;
import org.fudgemsg.types.PrimitiveFieldTypes;
import org.junit.Test;

import com.opengamma.financial.security.equity.GICSCode;

/**
 * Test GICSCode Fudge support.
 */
public class GICSCodeTest extends FinancialTestBase {

  private static final GICSCode s_ref = GICSCode.getInstance(10203040);

  @Test
  public void testCycle() {
    assertEquals(s_ref, cycleObject(GICSCode.class, s_ref));
  }

  @Test
  public void testFromInteger () {
    assertEquals(s_ref, getFudgeContext().getFieldValue(GICSCode.class,
        FudgeMsgField.of(PrimitiveFieldTypes.INT_TYPE, s_ref.getCode())));
  }

}
