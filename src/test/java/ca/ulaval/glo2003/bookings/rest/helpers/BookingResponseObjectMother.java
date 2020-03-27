package ca.ulaval.glo2003.bookings.rest.helpers;

import static ca.ulaval.glo2003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.bookings.domain.BookingStatuses;
import com.github.javafaker.Faker;
import java.time.LocalDate;

public class BookingResponseObjectMother {

  public BookingResponseObjectMother() {}

  public static String createArrivalDate() {
    return LocalDate.now().toString();
  }

  public static int createNumberOfNights() {
    return Faker.instance().number().numberBetween(1, 89);
  }

  public static int createColonySize() {
    return Faker.instance().number().numberBetween(1, 100);
  }

  public static String createBookingPackage() {
    return randomEnum(Packages.class).toString();
  }

  public static Double createTotal() {
    return Faker.instance().number().randomDouble(2, 100, 1000);
  }

  public static String createStatus() {
    return randomEnum(BookingStatuses.class).toString();
  }
}
