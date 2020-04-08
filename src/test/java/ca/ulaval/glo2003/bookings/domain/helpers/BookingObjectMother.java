package ca.ulaval.glo2003.bookings.domain.helpers;

import ca.ulaval.glo2003.bookings.domain.BookingNumber;
import com.github.javafaker.Faker;
import java.util.UUID;

public class BookingObjectMother {

  private BookingObjectMother() {}

  public static BookingNumber createBookingNumber() {
    return new BookingNumber(UUID.randomUUID());
  }

  public static int createNumberOfNights() {
    return Faker.instance().number().numberBetween(1, 5);
  }

  public static Integer createColonySize() {
    return Faker.instance().number().numberBetween(1, 100);
  }
}
