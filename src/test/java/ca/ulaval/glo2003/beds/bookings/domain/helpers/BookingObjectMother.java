package ca.ulaval.glo2003.beds.bookings.domain.helpers;

import com.github.javafaker.Faker;
import java.util.Date;

public class BookingObjectMother {

  private BookingObjectMother() {}

  public static String createTenantPublicKey() {
    return Faker.instance().chuckNorris().fact(); // TODO : Generate correct tenant public key
  }

  public static Date createArrivalDate() {
    return Faker.instance().date().birthday();
  }

  public static int createNumberOfNights() {
    return Faker.instance().number().numberBetween(1, 5);
  }
}
