package ca.ulaval.glo2003.reports.domain;

import static ca.ulaval.glo2003.time.domain.helpers.TimeDateBuilder.aTimeDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.time.domain.TimeDate;
import ca.ulaval.glo2003.time.domain.TimePeriod;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ReportPeriodTest {

  private static ReportPeriod reportPeriod;

  private static String name = "name";
  private static TimePeriod timePeriod = mock(TimePeriod.class);
  private static TimeDate date = aTimeDate().build();
  private static List<ReportEvent> events = Collections.singletonList(mock(ReportEvent.class));

  @BeforeEach
  public void setUpPeriod() {
    reportPeriod = new ReportPeriod(name, timePeriod);
  }

  private void setUpPeriodThatContains(boolean contains) {
    reset(timePeriod);
    when(timePeriod.contains(eq(date))).thenReturn(contains);
    setUpPeriod();
  }

  @Test
  public void setSingleData_shouldSetTransactionsAsSingleData() {
    reportPeriod.setSingleData(events);
    List<ReportPeriodData> data = reportPeriod.getData();

    assertEquals(1, data.size());
    assertSame(events.get(0), data.get(0).getEvents().get(0));
  }

  @ParameterizedTest
  @ValueSource(booleans = {true, false})
  public void contains_withContainingTimestamp_shouldReturnTrue(boolean contains) {
    setUpPeriodThatContains(contains);

    boolean result = reportPeriod.contains(date);

    assertEquals(contains, result);
  }
}
