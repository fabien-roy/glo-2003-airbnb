package ca.ulaval.glo2003.reports.domain.dimensions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.reports.domain.ReportEvent;
import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class ReportDimensionTest {

  protected static ReportDimension dimension;

  private static ReportPeriodData data = mock(ReportPeriodData.class);
  private static ReportPeriodData otherData = mock(ReportPeriodData.class);

  protected static List<ReportPeriodData> singleData;
  private static List<ReportPeriodData> multipleData;

  protected abstract List<ReportEvent> buildEvents();

  protected abstract int numberOfValues();

  @BeforeEach
  public void setUpMocks() {
    singleData = Collections.singletonList(data);
    multipleData = Arrays.asList(data, otherData);
    List<ReportEvent> events = buildEvents();

    reset(data, otherData);
    when(data.getEvents()).thenReturn(events);
    when(otherData.getEvents()).thenReturn(Collections.emptyList());
  }

  @Test
  public void splitAll_withoutData_shouldNotSplitData() {
    List<ReportPeriodData> data = dimension.splitAll(Collections.emptyList());

    assertTrue(data.isEmpty());
  }

  @Test
  public void splitAll_withSingleData_shouldSplitDataInValues() {
    List<ReportPeriodData> splitData = dimension.splitAll(singleData);

    assertEquals(numberOfValues(), splitData.size());
  }

  @Test
  public void splitAll_withMultipleData_shouldSplitDataInValues() {
    List<ReportPeriodData> splitData = dimension.splitAll(multipleData);

    assertEquals(numberOfValues() * 2, splitData.size());
  }
}
