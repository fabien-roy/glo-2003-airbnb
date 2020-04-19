package ca.ulaval.glo2003.reports.domain;

import static ca.ulaval.glo2003.time.domain.helpers.TimestampObjectMother.createTimestamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.time.domain.TimeDate;
import ca.ulaval.glo2003.time.domain.TimePeriod;
import ca.ulaval.glo2003.time.domain.Timestamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ReportPeriodTest {

  private static ReportPeriod reportPeriod;

  private static String name = "name";
  private static TimePeriod timePeriod = mock(TimePeriod.class);
  private static ReportPeriodData singleData = mock(ReportPeriodData.class);
  private static Timestamp timestamp = createTimestamp();

  @BeforeEach
  public void setUpPeriod() {
    reportPeriod = new ReportPeriod(name, timePeriod);
  }

  private void setUpPeriodThatContains(Timestamp timestamp, boolean contains) {
    reset(timePeriod);
    TimeDate date = new TimeDate(timestamp.toLocalDate());
    timePeriod = mock(TimePeriod.class);
    when(timePeriod.contains(eq(date))).thenReturn(contains);
    setUpPeriod();
  }

  @Test
  public void setSingleData_shouldSetDataAsSingleData() {
    reportPeriod.setSingleData(singleData);

    assertEquals(1, reportPeriod.getData().size());
    assertSame(singleData, reportPeriod.getData().get(0));
  }

  @ParameterizedTest
  @ValueSource(booleans = {true, false})
  public void contains_withContainingTimestamp_shouldReturnTrue(boolean contains) {
    setUpPeriodThatContains(timestamp, contains);

    boolean result = reportPeriod.contains(timestamp);

    assertEquals(contains, result);
  }
}
