package ca.ulaval.glo2003.beds.bookings.helpers;

import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingMapper;
import ca.ulaval.glo2003.beds.domain.Packages;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import java.time.LocalDate;
import java.util.Locale;

public class BookingRequestObjectMother {

  private static final FakeValuesService fakeValuesService =
      new FakeValuesService(new Locale("en-US"), new RandomService());

  public BookingRequestObjectMother() {}

  public static int createNumberOfNights() {
    return Faker.instance().number().numberBetween(1, 89);
  }

  public static String createTenantPublicKey() {
    return fakeValuesService.regexify(BookingMapper.OWNER_PUBLIC_KEY_PATTERN);
  }

  public static String createArrivalDate() {
    return LocalDate.now().toString();
  }

  public static String createPackageName() {
    return Packages.ALL_YOU_CAN_DRINK.toString();
  }
}
