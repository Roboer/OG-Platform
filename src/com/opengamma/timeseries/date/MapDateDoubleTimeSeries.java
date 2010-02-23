/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 * 
 * Please see distribution for license.
 */
package com.opengamma.timeseries.date;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.opengamma.timeseries.DateTimeConverter;
import com.opengamma.timeseries.fast.DateTimeNumericEncoding;
import com.opengamma.timeseries.fast.integer.FastMapIntDoubleTimeSeries;
import com.opengamma.timeseries.fast.integer.FastMutableIntDoubleTimeSeries;

/**
 * @author jim
 * 
 */
public class MapDateDoubleTimeSeries extends MutableDateDoubleTimeSeries.Integer {
  public static final MapDateDoubleTimeSeries EMPTY_SERIES = new MapDateDoubleTimeSeries();
  private static final DateTimeConverter<Date> s_converter = new DateEpochDaysConverter();

  private MapDateDoubleTimeSeries() {
    super(s_converter, new FastMapIntDoubleTimeSeries(DateTimeNumericEncoding.DATE_EPOCH_DAYS));
  }

  public MapDateDoubleTimeSeries(final Date[] dates, final double[] values) {
    super(s_converter, new FastMapIntDoubleTimeSeries(DateTimeNumericEncoding.DATE_EPOCH_DAYS, s_converter.convertToInt(dates), values));
  }

  public MapDateDoubleTimeSeries(final TimeZone timeZone, final Date[] dates, final double[] values) {
    super(new DateEpochDaysConverter(timeZone), new FastMapIntDoubleTimeSeries(DateTimeNumericEncoding.DATE_EPOCH_DAYS, new DateEpochDaysConverter(timeZone).convertToInt(dates),
        values));
  }

  public MapDateDoubleTimeSeries(final List<Date> dates, final List<Double> values) {
    super(s_converter, new FastMapIntDoubleTimeSeries(DateTimeNumericEncoding.DATE_EPOCH_DAYS, s_converter.convertToInt(dates), values));
  }

  public MapDateDoubleTimeSeries(final TimeZone timeZone, final List<Date> dates, final List<Double> values) {
    super(new DateEpochDaysConverter(timeZone), new FastMapIntDoubleTimeSeries(DateTimeNumericEncoding.DATE_EPOCH_DAYS, new DateEpochDaysConverter(timeZone).convertToInt(dates),
        values));
  }

  public MapDateDoubleTimeSeries(final DateDoubleTimeSeries dts) {
    super(s_converter, (FastMutableIntDoubleTimeSeries) s_converter.convertToInt(new FastMapIntDoubleTimeSeries(DateTimeNumericEncoding.DATE_EPOCH_DAYS), dts));
  }

  public MapDateDoubleTimeSeries(final TimeZone timeZone, final DateDoubleTimeSeries dts) {
    super(new DateEpochDaysConverter(timeZone), (FastMutableIntDoubleTimeSeries) new DateEpochDaysConverter(timeZone).convertToInt(new FastMapIntDoubleTimeSeries(
        DateTimeNumericEncoding.DATE_EPOCH_DAYS), dts));
  }

  public MapDateDoubleTimeSeries(final FastMutableIntDoubleTimeSeries pmidts) {
    super(s_converter, pmidts);
  }

  public MapDateDoubleTimeSeries(final TimeZone timeZone, final FastMutableIntDoubleTimeSeries pmidts) {
    super(new DateEpochDaysConverter(timeZone), pmidts);
  }

  @Override
  public MutableDateDoubleTimeSeries newInstanceFast(final Date[] dateTimes, final double[] values) {
    return new MapDateDoubleTimeSeries(dateTimes, values);
  }
}
