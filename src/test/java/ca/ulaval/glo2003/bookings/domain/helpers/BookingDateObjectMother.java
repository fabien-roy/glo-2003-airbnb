package ca.ulaval.glo2003.bookings.domain.helpers;

import ca.ulaval.glo2003.bookings.domain.BookingDate;
import com.github.javafaker.Faker;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class BookingDateObjectMother {

  private BookingDateObjectMother() {}

  public static BookingDate createBookingDate() {
    return new BookingDate(
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
