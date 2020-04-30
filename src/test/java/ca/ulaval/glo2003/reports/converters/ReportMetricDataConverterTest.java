package ca.ulaval.glo2003.reports.converters;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.reports.domain.metrics.ReportMetricData;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetricFormats;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetrics;
import ca.ulaval.glo2003.reports.rest.DoubleReportMetricDataResponse;
import ca.ulaval.glo2003.reports.rest.IntegerReportMetricDataResponse;
import ca.ulaval.glo2003.reports.rest.ReportMetricDataResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportMetricDataConverterTest {

  private static ReportMetricDataConverter reportMetricDataConverter;

  private static ReportMetricData integerMetric = mock(ReportMetricData.class);
  private static ReportMetricData otherIntegerMetric = mock(ReportMetricData.class);
  private static ReportMetricData doubleMetric = mock(ReportMetricData.class);
  private static ReportMetrics integerMetricName = ReportMetrics.RESERVATIONS;
  private static ReportMetrics otherIntegerMetricName = ReportMetrics.CANCELATIONS;
  private static ReportMetrics doubleMetricName = ReportMetrics.INCOMES;
  private static double integerMetricValue = 100;
  private static double otherIntegerMetricValue = 150;
  private static double doubleMetricValue = 200.14;
  private static List<ReportMetricData> singleIntegerMetric =
      Collections.singletonList(integerMetric);
  private static List<ReportMetricData> singleDoubleMetric =
      Collections.singletonList(doubleMetric);
  private static List<ReportMetricData> twoIntegerMetrics =
      Arrays.asList(integerMetric, otherIntegerMetric);
  private static List<ReportMetricData> multiFormatMetrics =
      Arrays.asList(integerMetric, doubleMetric);
  private static List<ReportMetricDataResponse> responses;

  @BeforeAll
  public static void setUpConverter() {
    reportMetricDataConverter = new ReportMetricDataConverter();
  }

  @BeforeEach
  public void setUpMocks() {
    when(integerMetric.getName()).thenReturn(integerMetricName);
    when(integerMetric.getValue()).thenReturn(integerMetricValue);
    when(integerMetric.getFormat()).thenReturn(ReportMetricFormats.INTEGER);
    when(otherIntegerMetric.getName()).thenReturn(otherIntegerMetricName);
    when(otherIntegerMetric.getValue()).thenReturn(otherIntegerMetricValue);
    when(otherIntegerMetric.getFormat()).thenReturn(ReportMetricFormats.INTEGER);
    when(doubleMetric.getName()).thenReturn(doubleMetricName);
    when(doubleMetric.getValue()).thenReturn(doubleMetricValue);
    when(doubleMetric.getFormat()).thenReturn(ReportMetricFormats.DOUBLE);
  }

  @Test
  public void toResponses_withSingleMetric_shouldMapASingleMetric() {
    responses = reportMetricDataConverter.toResponses(singleIntegerMetric);

    assertEquals(1, responses.size());
  }

  @Test
  public void toResponses_withMultipleMetrics_shouldMapMultipleMetrics() {
    responses = reportMetricDataConverter.toResponses(twoIntegerMetrics);

    assertEquals(2, responses.size());
  }

  @Test
  public void toResponses_withSingleMetric_shouldMapName() {
    responses = reportMetricDataConverter.toResponses(singleIntegerMetric);

    assertEquals(integerMetricName.toString(), responses.get(0).getName());
  }

  @Test
  public void toResponses_withMultipleMetrics_shouldMapNames() {
    responses = reportMetricDataConverter.toResponses(twoIntegerMetrics);

    assertEquals(integerMetricName.toString(), responses.get(0).getName());
    assertEquals(otherIntegerMetricName.toString(), responses.get(1).getName());
  }

  @Test
  public void toResponses_withSingleMetric_shouldMapValue() {
    responses = reportMetricDataConverter.toResponses(singleIntegerMetric);

    assertEquals(integerMetricValue, responses.get(0).getValue());
  }

  @Test
  public void toResponses_withMultipleMetrics_shouldMapValues() {
    responses = reportMetricDataConverter.toResponses(twoIntegerMetrics);

    assertEquals(integerMetricValue, responses.get(0).getValue());
    assertEquals(otherIntegerMetricValue, responses.get(1).getValue());
  }

  @Test
  public void toResponses_withIntegerMetric_shouldMapIntegerFormat() {
    responses = reportMetricDataConverter.toResponses(singleIntegerMetric);

    assertTrue(responses.get(0) instanceof IntegerReportMetricDataResponse);
  }

  @Test
  public void toResponses_withDoubleMetric_shouldMapDoubleFormat() {
    responses = reportMetricDataConverter.toResponses(singleDoubleMetric);

    assertTrue(responses.get(0) instanceof DoubleReportMetricDataResponse);
  }

  @Test
  public void toResponses_withMultipleFormatsOfMetrics_shouldMapCorrectFormats() {
    responses = reportMetricDataConverter.toResponses(multiFormatMetrics);

    assertTrue(responses.get(0) instanceof IntegerReportMetricDataResponse);
    assertTrue(responses.get(1) instanceof DoubleReportMetricDataResponse);
  }
}
