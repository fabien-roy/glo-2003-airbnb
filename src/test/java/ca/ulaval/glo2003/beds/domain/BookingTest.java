package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.helpers.BookingBuilder.aBooking;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import java.math.BigDecimal;
import java.util.Date;
import org.junit.jupiter.api.Test;

class BookingTest {

  @Test
  public void matches_withEmptyBooking_shouldReturnTrue() {

    Booking booking = aBooking().build();
    Booking otherBooking = aBooking().withNullAttributes().build();

    boolean matches = booking.matches(otherBooking);

    assertTrue(matches);
  };

  @Test
  public void matches_withSameArrivalDate_shouldReturnTrue() {
    Date arrivalDate = new Date();
    Booking booking = aBooking().withArrivalDate(arrivalDate).build();
    Booking otherBooking = aBooking().withNullAttributes().withArrivalDate(arrivalDate).build();

    boolean matches = booking.matches(otherBooking);

    assertTrue(matches);
  };

  @Test
  public void matches_withDifferentArrivalDate_shouldReturnFalse() {
    Date arrivalDate = new Date();
    Date otherArrivalDate = new Date(Integer.MAX_VALUE);
    Booking booking = aBooking().withArrivalDate(arrivalDate).build();
    Booking otherBooking =
        aBooking().withNullAttributes().withArrivalDate(otherArrivalDate).build();

    boolean matches = booking.matches(otherBooking);

    assertFalse(matches);
  };

  @Test
  public void matches_withSameNumberOfNights_shouldReturnTrue() {
    int numberOfNights = 1;
    Booking booking = aBooking().withNumberOfNights(numberOfNights).build();
    Booking otherBooking =
        aBooking().withNullAttributes().withNumberOfNights(numberOfNights).build();

    boolean matches = booking.matches(otherBooking);

    assertTrue(matches);
  };

  @Test
  public void matches_withDifferentNumberOfNights_shouldReturnFalse() {
    int numberOfNights = 1;
    int otherNumberOfNights = 90;
    Booking booking = aBooking().withNumberOfNights(numberOfNights).build();
    Booking otherBooking =
        aBooking().withNullAttributes().withNumberOfNights(otherNumberOfNights).build();

    boolean matches = booking.matches(otherBooking);

    assertFalse(matches);
  };

  @Test
  public void matches_withSamePackage_shouldReturnTrue() {
    ca.ulaval.glo2003.beds.domain.Package paquet =
        new ca.ulaval.glo2003.beds.domain.Package(PackageNames.BLOODTHIRSTY, new BigDecimal(45.00));
    Booking booking = aBooking().withPackage(paquet).build();
    Booking otherBooking = aBooking().withNullAttributes().withPackage(paquet).build();

    boolean matches = booking.matches(otherBooking);

    assertTrue(matches);
  };

  @Test
  public void matches_withDifferentPackage_shouldReturnFalse() {
    ca.ulaval.glo2003.beds.domain.Package paquet =
        new ca.ulaval.glo2003.beds.domain.Package(PackageNames.BLOODTHIRSTY, new BigDecimal(45.00));
    ca.ulaval.glo2003.beds.domain.Package otherPaquet =
        new ca.ulaval.glo2003.beds.domain.Package(PackageNames.SWEET_TOOTH, new BigDecimal(80.00));
    Booking booking = aBooking().withPackage(paquet).build();
    Booking otherBooking = aBooking().withNullAttributes().withPackage(otherPaquet).build();

    boolean matches = booking.matches(otherBooking);

    assertFalse(matches);
  };

  @Test
  public void matches_withAllSameAttributes_shouldReturnTrue() {

    Date arrivalDate = new Date();
    int numberOfNights = 90;
    ca.ulaval.glo2003.beds.domain.Package paquet =
        new ca.ulaval.glo2003.beds.domain.Package(PackageNames.BLOODTHIRSTY, new BigDecimal(45.00));
    Booking booking =
        aBooking()
            .withArrivalDate(arrivalDate)
            .withNumberOfNights(numberOfNights)
            .withPackage(paquet)
            .build();
    Booking otherBooking =
        aBooking()
            .withArrivalDate(arrivalDate)
            .withNumberOfNights(numberOfNights)
            .withPackage(paquet)
            .build();

    boolean matches = booking.matches(otherBooking);

    assertTrue(matches);
  };

  @Test
  public void matches_withAsingleDifferentAttribute_shouldReturnFalse() {

    Date arrivalDate = new Date();
    int numberOfNights = 1;
    ca.ulaval.glo2003.beds.domain.Package paquet =
        new ca.ulaval.glo2003.beds.domain.Package(
            PackageNames.ALL_YOU_CAN_DRINK, new BigDecimal(65.00));
    Booking booking =
        aBooking()
            .withArrivalDate(arrivalDate)
            .withNumberOfNights(numberOfNights)
            .withPackage(paquet)
            .build();

    int otherNumberOfNights = 90;
    Booking otherBooking =
        aBooking()
            .withArrivalDate(arrivalDate)
            .withNumberOfNights(otherNumberOfNights)
            .withPackage(paquet)
            .build();

    boolean matches = booking.matches(otherBooking);

    assertFalse(matches);
  };

  @Test
  public void matches_withAllDifferentAttributes_shouldReturnFalse() {

    Date arrivalDate = new Date();
    int numberOfNights = 1;
    ca.ulaval.glo2003.beds.domain.Package paquet =
        new ca.ulaval.glo2003.beds.domain.Package(PackageNames.BLOODTHIRSTY, new BigDecimal(45.00));
    Booking booking =
        aBooking()
            .withArrivalDate(arrivalDate)
            .withNumberOfNights(numberOfNights)
            .withPackage(paquet)
            .build();

    Date otherArrivalDate = new Date(Integer.MAX_VALUE);
    int otherNumberOfNights = 90;
    ca.ulaval.glo2003.beds.domain.Package otherPaquet =
        new ca.ulaval.glo2003.beds.domain.Package(
            PackageNames.ALL_YOU_CAN_DRINK, new BigDecimal(65.00));
    Booking otherBooking =
        aBooking()
            .withArrivalDate(otherArrivalDate)
            .withNumberOfNights(otherNumberOfNights)
            .withPackage(otherPaquet)
            .build();

    boolean matches = booking.matches(otherBooking);

    assertFalse(matches);
  };
}
