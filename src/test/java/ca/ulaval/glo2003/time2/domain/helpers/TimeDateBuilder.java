package ca.ulaval.glo2003.time2.domain.helpers;

import static ca.ulaval.glo2003.time2.domain.helpers.TimeDateObjectMother.createDate;

import ca.ulaval.glo2003.time2.domain.TimeDate;
import java.time.LocalDate;

public class TimeDateBuilder {

  public static LocalDate DEFAULT_DATE = createDate();
  public static LocalDate date = DEFAULT_DATE;

  public static TimeDateBuilder aTimeDate() {
    return new TimeDateBuilder();
  }

  public TimeDate build() {
    return new TimeDate(date);
  }
}
