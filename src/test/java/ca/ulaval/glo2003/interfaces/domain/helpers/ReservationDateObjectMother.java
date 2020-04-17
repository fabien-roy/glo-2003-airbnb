package ca.ulaval.glo2003.interfaces.domain.helpers;

import ca.ulaval.glo2003.interfaces.domain.ReservationDate;
import com.github.javafaker.Faker;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class ReservationDateObjectMother {

  private ReservationDateObjectMother() {}

  public static ReservationDate createBookingDate() {
    return new ReservationDate(
        Faker.instance()
            .date()
            .between(
                Date.valueOf(LocalDate.now().plusDays(1)),
                Date.valueOf(LocalDate.now().plusDays(31)))
            .toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate());
  }
}
