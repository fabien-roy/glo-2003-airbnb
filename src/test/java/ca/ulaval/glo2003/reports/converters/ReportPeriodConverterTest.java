package ca.ulaval.glo2003.reports.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import ca.ulaval.glo2003.reports.rest.ReportPeriodDataResponse;
import ca.ulaval.glo2003.reports.rest.ReportPeriodResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportPeriodConverterTest {

  private static ReportPeriodConverter reportPeriodConverter;
  private static ReportPeriodDataConverter reportPeriodDataConverter =
      mock(ReportPeriodDataConverter.class);

  private static ReportPeriod period = mock(ReportPeriod.class);
  private static ReportPeriod otherPeriod = mock(ReportPeriod.class);
  private static String periodName = "periodName";
  private static String otherPeriodName = "otherPeriodName";
  private static List<ReportPeriod> singlePeriod = Collections.singletonList(period);
  private static List<ReportPeriod> multiplePeriods = Arrays.asList(period, otherPeriod);
  private static List<ReportPeriodData> data =
      Collections.singletonList(mock(ReportPeriodData.class));
  private static List<ReportPeriodData> otherData =
      Collections.singletonList(mock(ReportPeriodData.class));
  private static List<ReportPeriodResponse> responses;
  private static List<ReportPeriodDataResponse> dataResponses =
      Collections.singletonList(mock(ReportPeriodDataResponse.class));
  private static List<ReportPeriodDataResponse> otherDataResponses =
      Arrays.asList(mock(ReportPeriodDataResponse.class), mock(ReportPeriodDataResponse.class));

  @BeforeAll
  public static void setUpConverter() {
    reportPeriodConverter = new ReportPeriodConverter(reportPeriodDataConverter);
  }

  @BeforeEach
  public void setUpMocks() {
    when(period.getName()).thenReturn(periodName);
    when(otherPeriod.getName()).thenReturn(otherPeriodName);
    when(period.getData()).thenReturn(data);
    when(otherPeriod.getData()).thenReturn(otherData);
    when(reportPeriodDataConverter.toResponses(data)).thenReturn(dataResponses);
    when(reportPeriodDataConverter.toResponses(otherData)).thenReturn(otherDataResponses);
  }

  @Test
  public void toResponses_withSinglePeriod_shouldMapASinglePeriod() {
    responses = reportPeriodConverter.toResponses(singlePeriod);

    assertEquals(1, responses.size());
  }

  @Test
  public void toResponses_withMultiplePeriods_shouldMapMultiplePeriods() {
    responses = reportPeriodConverter.toResponses(multiplePeriods);

    assertEquals(2, responses.size());
  }

  @Test
  public void toResponses_withSinglePeriod_shouldMapName() {
    responses = reportPeriodConverter.toResponses(singlePeriod);

    assertEquals(periodName, responses.get(0).getPeriod());
  }

  @Test
  public void toResponses_withMultiplePeriods_shouldMapNames() {
    responses = reportPeriodConverter.toResponses(multiplePeriods);

    assertEquals(periodName, responses.get(0).getPeriod());
    assertEquals(otherPeriodName, responses.get(1).getPeriod());
  }

  @Test
  public void toResponses_withSinglePeriod_shouldMapData() {
    responses = reportPeriodConverter.toResponses(singlePeriod);

    assertEquals(1, responses.get(0).getData().size());
  }

  @Test
  public void toResponses_withMultiplePeriods_shouldMapData() {
    responses = reportPeriodConverter.toResponses(multiplePeriods);

    assertEquals(1, responses.get(0).getData().size());
    assertEquals(2, responses.get(1).getData().size());
  }
}
