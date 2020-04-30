package ca.ulaval.glo2003.reports.converters;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimensionData;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetricData;
import ca.ulaval.glo2003.reports.rest.ReportDimensionDataResponse;
import ca.ulaval.glo2003.reports.rest.ReportMetricDataResponse;
import ca.ulaval.glo2003.reports.rest.ReportPeriodDataResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportPeriodDataConverterTest {

  private static ReportPeriodDataConverter reportPeriodDataConverter;
  private static ReportDimensionDataConverter reportDimensionDataConverter =
      mock(ReportDimensionDataConverter.class);
  private static ReportMetricDataConverter reportMetricDataConverter =
      mock(ReportMetricDataConverter.class);

  private static ReportPeriodData data = mock(ReportPeriodData.class);
  private static ReportPeriodData otherData = mock(ReportPeriodData.class);
  private static List<ReportPeriodData> singleData = Collections.singletonList(data);
  private static List<ReportPeriodData> multipleData = Arrays.asList(data, otherData);
  private static List<ReportDimensionData> dimensions =
      Collections.singletonList(mock(ReportDimensionData.class));
  private static List<ReportDimensionData> otherDimensions =
      Collections.singletonList(mock(ReportDimensionData.class));
  private static List<ReportMetricData> metrics =
      Collections.singletonList(mock(ReportMetricData.class));
  private static List<ReportMetricData> otherMetrics =
      Collections.singletonList(mock(ReportMetricData.class));
  private static List<ReportPeriodDataResponse> responses;
  private static List<ReportDimensionDataResponse> dimensionResponses =
      Collections.singletonList(mock(ReportDimensionDataResponse.class));
  private static List<ReportDimensionDataResponse> otherDimensionResponses =
      Arrays.asList(
          mock(ReportDimensionDataResponse.class), mock(ReportDimensionDataResponse.class));
  private static List<ReportMetricDataResponse> metricResponses =
      Collections.singletonList(mock(ReportMetricDataResponse.class));
  private static List<ReportMetricDataResponse> otherMetricResponses =
      Arrays.asList(mock(ReportMetricDataResponse.class), mock(ReportMetricDataResponse.class));

  @BeforeAll
  public static void setUpConverter() {
    reportPeriodDataConverter =
        new ReportPeriodDataConverter(reportDimensionDataConverter, reportMetricDataConverter);
  }

  @BeforeEach
  public void setUpMocks() {
    setUpDimensions();
    setUpMetrics();
  }

  private void setUpDimensions() {
    when(data.getDimensions()).thenReturn(dimensions);
    when(otherData.getDimensions()).thenReturn(otherDimensions);
    when(reportDimensionDataConverter.toResponses(dimensions)).thenReturn(dimensionResponses);
    when(reportDimensionDataConverter.toResponses(otherDimensions))
        .thenReturn(otherDimensionResponses);
  }

  private void setUpMetrics() {
    when(data.getMetrics()).thenReturn(metrics);
    when(otherData.getMetrics()).thenReturn(otherMetrics);
    when(reportMetricDataConverter.toResponses(metrics)).thenReturn(metricResponses);
    when(reportMetricDataConverter.toResponses(otherMetrics)).thenReturn(otherMetricResponses);
  }

  @Test
  public void toResponses_withSingleData_shouldMapASingleData() {
    responses = reportPeriodDataConverter.toResponses(singleData);

    assertEquals(1, responses.size());
  }

  @Test
  public void toResponses_withMultipleData_shouldMapMultipleData() {
    responses = reportPeriodDataConverter.toResponses(multipleData);

    assertEquals(2, responses.size());
  }

  @Test
  public void toResponses_withSingleData_shouldMapDimensions() {
    responses = reportPeriodDataConverter.toResponses(singleData);

    assertEquals(1, responses.get(0).getDimensions().size());
  }

  @Test
  public void toResponses_withMultipleData_shouldMapDimensions() {
    responses = reportPeriodDataConverter.toResponses(multipleData);

    assertEquals(1, responses.get(0).getDimensions().size());
    assertEquals(2, responses.get(1).getDimensions().size());
  }

  @Test
  public void toResponses_withSingleData_shouldMapMetrics() {
    responses = reportPeriodDataConverter.toResponses(singleData);

    assertEquals(1, responses.get(0).getMetrics().size());
  }

  @Test
  public void toResponses_withMultipleData_shouldMapMetrics() {
    responses = reportPeriodDataConverter.toResponses(multipleData);

    assertEquals(1, responses.get(0).getMetrics().size());
    assertEquals(2, responses.get(1).getMetrics().size());
  }
}
