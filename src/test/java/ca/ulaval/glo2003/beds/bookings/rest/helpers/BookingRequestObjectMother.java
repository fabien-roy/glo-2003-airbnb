package ca.ulaval.glo2003.beds.bookings.rest.helpers;

import static ca.ulaval.glo2003.beds.domain.helpers.PublicKeyObjectMother.createPublicKey;

import ca.ulaval.glo2003.beds.domain.Packages;
import com.github.javafaker.Faker;
import java.time.LocalDate;

public class BookingRequestObjectMother {

  public BookingRequestObjectMother() {}

  public static int createNumberOfNights() {
    return Faker.instance().number().numberBetween(1, 89);
  }

  public static String createTenantPublicKey() {
    return createPublicKey().getValue();
  }

  public static String createArrivalDate() {
    return LocalDate.now().toString();
  }

  public static String createPackageName() {
    return Packages.ALL_YOU_CAN_DRINK.toString();
  }
}
