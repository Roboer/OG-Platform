/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 * 
 * Please see distribution for license.
 */
package com.opengamma.timeseries.fast.integer;

import com.opengamma.timeseries.fast.DateTimeNumericEncoding;

/**
 * @author jim
 *         Contains methods to make Primitive time series work with the normal
 *         non-primitive time series interface (where possible)
 */
public abstract class AbstractFastMutableIntDoubleTimeSeries extends AbstractFastIntDoubleTimeSeries implements FastMutableIntDoubleTimeSeries {

  protected AbstractFastMutableIntDoubleTimeSeries(final DateTimeNumericEncoding encoding) {
    super(encoding);
  }

  public void putDataPoint(final Integer time, final Double value) {
    primitivePutDataPoint(time, value);
  }

  public void removeDataPoint(final Integer time) {
    primitiveRemoveDataPoint(time);
  }

}
