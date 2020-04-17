package ca.ulaval.glo2003.interfaces.domain.helpers;

import static ca.ulaval.glo2003.interfaces.domain.helpers.ReservationDateObjectMother.createDate;

import ca.ulaval.glo2003.interfaces.domain.ReservationDate;
import ca.ulaval.glo2003.interfaces.domain.ReservationPeriod;
import com.github.javafaker.Faker;

public class ReservationPeriodObjectMother {

  private ReservationPeriodObjectMother() {}

  public static ReservationPeriod createPeriod() {
    ReservationDate startDate = createDate();
    return new ReservationPeriod(startDate, startDate.plusDays(createDays()));
  }

  private static int createDays() {
    return Faker.instance().random().nextInt(1, 30);
  }
}
