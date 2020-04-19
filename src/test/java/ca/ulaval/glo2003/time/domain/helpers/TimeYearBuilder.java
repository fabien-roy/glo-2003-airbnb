package ca.ulaval.glo2003.time.domain.helpers;

import static ca.ulaval.glo2003.time.domain.helpers.TimeDateObjectMother.createYear;

import ca.ulaval.glo2003.time.domain.TimeYear;
import java.time.Year;

public class TimeYearBuilder {

  private Year DEFAULT_YEAR = createYear();
  private Year year = DEFAULT_YEAR;

  public static TimeYearBuilder aTimeYear() {
    return new TimeYearBuilder();
  }

  public TimeYear build() {
    return new TimeYear(year);
  }
}
