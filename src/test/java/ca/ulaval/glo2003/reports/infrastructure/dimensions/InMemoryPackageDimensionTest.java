package ca.ulaval.glo2003.reports.infrastructure.dimensions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.reports.domain.ReportEvent;
import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimensions;
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

class InMemoryPackageDimensionTest extends InMemoryReportDimensionTest {

  private static ReportEvent bloodthirstyEvent = mock(ReportEvent.class);
  private static ReportEvent allYouCanDrinkEvent = mock(ReportEvent.class);
  private static ReportEvent sweetToothEvent = mock(ReportEvent.class);

  @BeforeAll
  public static void setUpDimension() {
    dimension = new InMemoryPackageDimension();
    setUpEvents();
  }

  private static void setUpEvents() {
    when(bloodthirstyEvent.getPackage()).thenReturn(Packages.BLOODTHIRSTY);
    when(allYouCanDrinkEvent.getPackage()).thenReturn(Packages.ALL_YOU_CAN_DRINK);
    when(sweetToothEvent.getPackage()).thenReturn(Packages.SWEET_TOOTH);
  }

  @Override
  protected List<ReportEvent> buildEvents() {
    return Arrays.asList(bloodthirstyEvent, allYouCanDrinkEvent, sweetToothEvent);
  }

  @Override
  protected int numberOfValues() {
    return Packages.values().length;
  }

  @ParameterizedTest
  @EnumSource(Packages.class)
  public void splitAll_shouldSplitWithPackageValues(Packages packageName) {
    List<ReportPeriodData> splitData = dimension.splitAll(singleData);

    assertTrue(
        splitData.stream()
            .anyMatch(data -> data.getDimensions().get(0).getValue().equals(packageName)));
  }

  @Test
  public void splitAll_shouldSplitWithPackageDimensionName() {
    List<ReportPeriodData> splitData = dimension.splitAll(singleData);

    assertTrue(
        splitData.stream()
            .allMatch(
                data -> data.getDimensions().get(0).getName().equals(ReportDimensions.PACKAGE)));
  }

  @ParameterizedTest
  @MethodSource("providePackageEvents")
  public void splitAll_withSingleEventPerPackage_shouldSplitEventsByPackage(
      Packages value, ReportEvent event) {
    List<ReportPeriodData> splitData = dimension.splitAll(singleData);
    List<ReportPeriodData> filteredData = filterData(splitData, value);

    assertEquals(1, filteredData.size());
    assertEquals(1, filteredData.get(0).getEvents().size());
    assertEquals(event, filteredData.get(0).getEvents().get(0));
  }

  private static Stream<Arguments> providePackageEvents() {
    return Stream.of(
        Arguments.of(Packages.BLOODTHIRSTY, bloodthirstyEvent),
        Arguments.of(Packages.ALL_YOU_CAN_DRINK, allYouCanDrinkEvent),
        Arguments.of(Packages.SWEET_TOOTH, sweetToothEvent));
  }

  private List<ReportPeriodData> filterData(List<ReportPeriodData> data, Packages value) {
    return data.stream()
        .filter(datum -> datum.getDimensions().get(0).getValue().equals(value))
        .collect(Collectors.toList());
  }
}
