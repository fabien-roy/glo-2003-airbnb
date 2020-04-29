package ca.ulaval.glo2003.reports.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimensionData;
import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimensions;
import ca.ulaval.glo2003.reports.rest.ReportDimensionDataResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportDimensionDataConverterTest {

  private static ReportDimensionDataConverter reportDimensionDataConverter;

  private static ReportDimensionData dimension = mock(ReportDimensionData.class);
  private static ReportDimensionData otherDimension = mock(ReportDimensionData.class);
  private static ReportDimensions dimensionName = ReportDimensions.PACKAGE;
  private static ReportDimensions otherDimensionName = ReportDimensions.LODGING_MODE;
  private static String dimensionValue = "dimensionValue";
  private static String otherDimensionValue = "otherDimensionValue";
  private static List<ReportDimensionData> singleDimension = Collections.singletonList(dimension);
  private static List<ReportDimensionData> multipleDimensions =
      Arrays.asList(dimension, otherDimension);
  private static List<ReportDimensionDataResponse> responses;

  @BeforeAll
  public static void setUpConverter() {
    reportDimensionDataConverter = new ReportDimensionDataConverter();
  }

  @BeforeEach
  public void setUpMocks() {
    when(dimension.getName()).thenReturn(dimensionName);
    when(dimension.getValue()).thenReturn(dimensionValue);
    when(otherDimension.getName()).thenReturn(otherDimensionName);
    when(otherDimension.getValue()).thenReturn(otherDimensionValue);
  }

  @Test
  public void toResponses_withSingleDimension_shouldMapASingleDimension() {
    responses = reportDimensionDataConverter.toResponses(singleDimension);

    assertEquals(1, responses.size());
  }

  @Test
  public void toResponses_withMultipleDimensions_shouldMapMultipleDimensions() {
    responses = reportDimensionDataConverter.toResponses(multipleDimensions);

    assertEquals(2, responses.size());
  }

  @Test
  public void toResponses_withSingleDimension_shouldMapName() {
    responses = reportDimensionDataConverter.toResponses(singleDimension);

    assertEquals(dimensionName.toString(), responses.get(0).getName());
  }

  @Test
  public void toResponses_withMultipleDimensions_shouldMapNames() {
    responses = reportDimensionDataConverter.toResponses(multipleDimensions);

    assertEquals(dimensionName.toString(), responses.get(0).getName());
    assertEquals(otherDimensionName.toString(), responses.get(1).getName());
  }

  @Test
  public void toResponses_withSingleDimension_shouldMapValue() {
    responses = reportDimensionDataConverter.toResponses(singleDimension);

    assertEquals(dimensionValue, responses.get(0).getValue());
  }

  @Test
  public void toResponses_withMultipleDimensions_shouldMapValues() {
    responses = reportDimensionDataConverter.toResponses(multipleDimensions);

    assertEquals(dimensionValue, responses.get(0).getValue());
    assertEquals(otherDimensionValue, responses.get(1).getValue());
  }
}
