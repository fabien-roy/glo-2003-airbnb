package ca.ulaval.glo2003.beds.bookings.helpers;

import static ca.ulaval.glo2003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.rest.mappers.BedMapper;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;
import java.util.UUID;

public class BookingObjectMother {

  private static final FakeValuesService fakeValuesService =
      new FakeValuesService(new Locale("en-US"), new RandomService());

  private BookingObjectMother() {}

  public static UUID createBookingNumber() {
    return UUID.randomUUID();
  }

  public static String createTenantPublicKey() {
    return fakeValuesService.regexify(BedMapper.OWNER_PUBLIC_KEY_PATTERN);
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

  public static Packages createPackageName() {
    return randomEnum(Packages.class);
  }
}
