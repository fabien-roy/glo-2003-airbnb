package ca.ulaval.glo2003.beds.bookings.domain.helpers;

import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.ZoneId;

public class BookingObjectMother {

  private BookingObjectMother() {}

  public static String createTenantPublicKey() {
    return Faker.instance().chuckNorris().fact(); // TODO : Generate correct tenant public key
  }

  public static LocalDate createArrivalDate() {
    return Faker.instance()
        .date()
        .birthday()
        .toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();
  }

  public static int createNumberOfNights() {
    return Faker.instance().number().numberBetween(1, 5);
  }
}
