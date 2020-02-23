package ca.ulaval.glo2003.beds.helpers;

import ca.ulaval.glo2003.beds.domain.PackageNames;
import com.github.javafaker.Faker;
import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class BookingObjectMother {

  // TODO : This was built during testing for Booking.matches(...)
  // TODO : Might turn out irrelevant

  private BookingObjectMother() {}

  public static UUID createBookingNumber() {
    return UUID.fromString(Faker.instance().internet().uuid());
  }

  public static String createTenantPublicKey() {
    // TODO A revoir, chuckNorris est deja utilise pour createOwnerPublicKey
    return Faker.instance().chuckNorris().fact();
  }

  public static Date createArrivalDate() {
    return Faker.instance()
        .date()
        .future(
            Integer.MAX_VALUE,
            TimeUnit.DAYS); // A random date between today and today + Integer.MAX_VALUE
  }

  public static int createNumberOfNights() {
    final int MIN_NUMBER_OF_NIGHTS = 1;
    final int MAX_NUMBER_OF_NIGHTS = 90;
    return ThreadLocalRandom.current().nextInt(MIN_NUMBER_OF_NIGHTS, MAX_NUMBER_OF_NIGHTS + 1);
  }

  public static ca.ulaval.glo2003.beds.domain.Package createPackage() {
    // TODO Introduire des pricePerNights calcules en fonction du numberOfNights
    List<Map.Entry<PackageNames, BigDecimal>> pricesPerNight =
        Arrays.asList(
            new AbstractMap.SimpleEntry<PackageNames, BigDecimal>(
                PackageNames.BLOODTHIRSTY, new BigDecimal(50.00)),
            new AbstractMap.SimpleEntry<PackageNames, BigDecimal>(
                PackageNames.ALL_YOU_CAN_DRINK, new BigDecimal(75.00)),
            new AbstractMap.SimpleEntry<PackageNames, BigDecimal>(
                PackageNames.SWEET_TOOTH, new BigDecimal(100.00)));
    // TODO  randomEnum plutôt ? Nota : la List ci-dessus ne change pas en même temps que l'enum
    int index = ThreadLocalRandom.current().nextInt(0, pricesPerNight.size());
    return new ca.ulaval.glo2003.beds.domain.Package(
        pricesPerNight.get(index).getKey(), pricesPerNight.get(index).getValue());
  }
}
