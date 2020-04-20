package ca.ulaval.glo2003.reports.domain.dimensions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.LodgingModes;
import ca.ulaval.glo2003.reports.domain.ReportEvent;
import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

class LodgingModeDimensionTest extends ReportDimensionTest {

  private static ReportEvent privateEvent = mock(ReportEvent.class);
  private static ReportEvent cohabitationEvent = mock(ReportEvent.class);

  @BeforeAll
  public static void setUpDimension() {
    dimension = new LodgingModeDimension();
    setUpEvents();
  }

  private static void setUpEvents() {
    when(privateEvent.getLodgingMode()).thenReturn(LodgingModes.PRIVATE);
    when(cohabitationEvent.getLodgingMode()).thenReturn(LodgingModes.COHABITATION);
  }

  @Override
  protected List<ReportEvent> buildEvents() {
    return Arrays.asList(privateEvent, cohabitationEvent);
  }

  @Override
  protected int numberOfValues() {
    return LodgingModes.values().length;
  }

  @ParameterizedTest
  @EnumSource(LodgingModes.class)
  public void splitAll_shouldSplitWithLodgingModeValues(LodgingModes lodgingMode) {
    List<ReportPeriodData> splitData = dimension.splitAll(singleData);

    assertTrue(
        splitData.stream()
            .anyMatch(data -> data.getDimensions().get(0).getValue().equals(lodgingMode)));
  }

  @Test
  public void splitAll_shouldSplitWithLodgingModeDimensionName() {
    List<ReportPeriodData> splitData = dimension.splitAll(singleData);

    assertTrue(
        splitData.stream()
            .allMatch(
                data ->
                    data.getDimensions().get(0).getName().equals(ReportDimensions.LODGING_MODE)));
  }

  @ParameterizedTest
  @MethodSource("provideLodgingModeEvents")
  public void splitAll_withSingleEventPerPackage_shouldSplitEventsByPackage(
      LodgingModes value, ReportEvent event) {
    List<ReportPeriodData> splitData = dimension.splitAll(singleData);
    List<ReportPeriodData> filteredData = filterData(splitData, value);

    assertEquals(1, filteredData.size());
    assertEquals(1, filteredData.get(0).getEvents().size());
    assertEquals(event, filteredData.get(0).getEvents().get(0));
  }

  private static Stream<Arguments> provideLodgingModeEvents() {
    return Stream.of(
        Arguments.of(LodgingModes.PRIVATE, privateEvent),
        Arguments.of(LodgingModes.COHABITATION, cohabitationEvent));
  }

  private List<ReportPeriodData> filterData(List<ReportPeriodData> data, LodgingModes value) {
    return data.stream()
        .filter(datum -> datum.getDimensions().get(0).getValue().equals(value))
        .collect(Collectors.toList());
  }
}
