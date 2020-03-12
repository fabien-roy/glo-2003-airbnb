package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.bookings.rest.exceptions.BookingNotFoundException;
import ca.ulaval.glo2003.beds.rest.exceptions.BedAlreadyBookedException;
import ca.ulaval.glo2003.beds.rest.exceptions.BookingNotAllowedException;
import ca.ulaval.glo2003.beds.rest.exceptions.PackageNotAvailableException;
import java.util.*;

public class Bed {

  private UUID number;
  private PublicKey ownerPublicKey;
  private String zipCode;
  private BedTypes bedType;
  private CleaningFrequencies cleaningFrequency;
  private List<BloodTypes> bloodTypes;
  private int capacity;
  private Map<Packages, Price> pricesPerNight;
  private List<Booking> bookings = new ArrayList<>();
  private LodgingModes lodgingMode;

  public Bed(
      PublicKey ownerPublicKey,
      String zipCode,
      BedTypes bedType,
      CleaningFrequencies cleaningFrequency,
      List<BloodTypes> bloodTypes,
      int capacity,
      Map<Packages, Price> pricesPerNight,
      LodgingModes lodgingMode) {
    this.ownerPublicKey = ownerPublicKey;
    this.zipCode = zipCode;
    this.bedType = bedType;
    this.cleaningFrequency = cleaningFrequency;
    this.bloodTypes = bloodTypes;
    this.capacity = capacity;
    this.pricesPerNight = pricesPerNight;
    this.lodgingMode = lodgingMode;
  }

  public UUID getNumber() {
    return number;
  }

  public void setNumber(UUID number) {
    this.number = number;
  }

  public void setLodgingMode(LodgingModes mode) {
    this.lodgingMode = mode;
  }

  public PublicKey getOwnerPublicKey() {
    return ownerPublicKey;
  }

  public String getZipCode() {
    return zipCode;
  }

  public BedTypes getBedType() {
    return bedType;
  }

  public CleaningFrequencies getCleaningFrequency() {
    return cleaningFrequency;
  }

  public List<BloodTypes> getBloodTypes() {
    return bloodTypes;
  }

  public int getCapacity() {
    return capacity;
  }

  public Map<Packages, Price> getPricesPerNight() {
    return pricesPerNight;
  }

  public Price getPricePerNight(Packages packageName) {
    return pricesPerNight.get(packageName);
  }

  public List<Booking> getBookings() {
    return bookings;
  }

  public Booking getBookingByNumber(UUID number) {
    Optional<Booking> foundBooking =
        bookings.stream().filter(booking -> booking.getNumber().equals(number)).findAny();

    if (!foundBooking.isPresent()) {
      throw new BookingNotFoundException(number.toString());
    }

    return foundBooking.get();
  }

  public LodgingModes getLodgingMode() {
    return lodgingMode;
  }

  public Set<Packages> getPackages() {
    return pricesPerNight.keySet();
  }

  public void book(Booking booking) {
    if (ownerPublicKey.equals(booking.getTenantPublicKey())) throw new BookingNotAllowedException();

    if (!isPackageAvailable(booking.getPackage())) throw new PackageNotAvailableException();

    if (isBedAlreadyBooked(booking)) throw new BedAlreadyBookedException();

    bookings.add(booking);
  }

  public boolean isPackageAvailable(Packages bookingPackage) {
    return pricesPerNight.containsKey(bookingPackage);
  }

  private boolean isBedAlreadyBooked(Booking booking) {
    return bookings.stream().anyMatch(presentBooking -> presentBooking.isOverlapping(booking));
  }
}
