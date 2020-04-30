package ca.ulaval.glo2003.reports.rest.serializers;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DoubleReportMetricDataValueSerializer extends ReportMetricDataValueSerializer {

  protected NumberFormat getNumberFormat() {
    return new DecimalFormat("0.00", getOtherSymbols());
  }
}
