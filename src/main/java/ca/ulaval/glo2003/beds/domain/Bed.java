package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.exceptions.BedAlreadyBookedException;
import ca.ulaval.glo2003.beds.exceptions.BookingNotAllowedException;
import ca.ulaval.glo2003.beds.exceptions.PackageNotAvailableException;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.exceptions.BookingNotFoundException;
import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.util.*;

public class Bed {

  private UUID number;
  private PublicKey ownerPublicKey;
  private Location location;
  private BedTypes bedType;
  private CleaningFrequencies cleaningFrequency;
  private List<BloodTypes> bloodTypes;
  private int capacity;
  private LodgingModes lodgingMode;
  private Map<Packages, Price> pricesPerNight;
  private List<Booking> bookings = new ArrayList<>();

  public Bed(
      PublicKey ownerPublicKey,
      BedTypes bedType,
      CleaningFrequencies cleaningFrequency,
      List<BloodTypes> bloodTypes,
      int capacity,
      LodgingModes lodgingMode,
      Map<Packages, Price> pricesPerNight) {
    this.ownerPublicKey = ownerPublicKey;
    this.bedType = bedType;
    this.cleaningFrequency = cleaningFrequency;
    this.bloodTypes = bloodTypes;
    this.capacity = capacity;
    this.lodgingMode = lodgingMode;
    this.pricesPerNight = pricesPerNight;
  }

  public UUID getNumber() {
    return number;
  }

  public void setNumber(UUID number) {
    this.number = number;
  }

  public PublicKey getOwnerPublicKey() {
    return ownerPublicKey;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
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

  public LodgingModes getLodgingMode() {
    return lodgingMode;
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
