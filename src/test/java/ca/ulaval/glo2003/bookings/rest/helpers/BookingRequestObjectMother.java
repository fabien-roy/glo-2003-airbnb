package ca.ulaval.glo2003.bookings.rest.helpers;

import static ca.ulaval.glo2003.beds.domain.helpers.PublicKeyObjectMother.createPublicKey;
import static ca.ulaval.glo2003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo2003.beds.domain.Packages;
import com.github.javafaker.Faker;
import java.time.LocalDate;

public class BookingRequestObjectMother {

  public BookingRequestObjectMother() {}

  public static int createNumberOfNights() {
    return Faker.instance().number().numberBetween(1, 89);
  }

  public static int createColonySize() {
    return Faker.instance().number().numberBetween(1, 100);
  }

  public static String createTenantPublicKey() {
    return createPublicKey().getValue();
  }

  public static String createArrivalDate() {
    return LocalDate.now().toString();
  }

  public static String createPackageName() {
    return randomEnum(Packages.class).toString();
  }
}
