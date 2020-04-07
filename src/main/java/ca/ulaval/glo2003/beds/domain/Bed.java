package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.exceptions.BookingNotAllowedException;
import ca.ulaval.glo2003.beds.exceptions.ExceedingAccommodationCapacityException;
import ca.ulaval.glo2003.beds.exceptions.PackageNotAvailableException;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.bookings.exceptions.BookingNotFoundException;
import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.util.*;
import java.util.stream.Collectors;

public class Bed {

  private UUID number;
  private PublicKey ownerPublicKey;
  private Location location;
  private BedTypes bedType;
  private CleaningFrequencies cleaningFrequency;
  private List<BloodTypes> bloodTypes;
  private int capacity;
  private LodgingMode lodgingMode;
  private Map<Packages, Price> pricesPerNight;
  private List<Booking> bookings = new ArrayList<>();

  public Bed(
      PublicKey ownerPublicKey,
      BedTypes bedType,
      CleaningFrequencies cleaningFrequency,
      List<BloodTypes> bloodTypes,
      int capacity,
      LodgingMode lodgingMode,
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

  public LodgingMode getLodgingMode() {
    return lodgingMode;
  }

  public Map<Packages, Price> getPricesPerNight() {
    return pricesPerNight;
  }

  public Price getPricePerNight(Packages packageName) {
    validatePackageAvailable(packageName);
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

  public int getRemainingCapacityOnDate(BookingDate date) {
    int remainingCapacity = capacity;

    for (Booking booking : getBookingsOnDate(date)) remainingCapacity -= booking.getColonySize();

    return remainingCapacity;
  }

  public void book(Booking booking) {
    validateOwnerNotTenant(booking.getTenantPublicKey());
    validatePackageAvailable(booking.getPackage());
    validateMinCapacity(booking.getColonySize());
    lodgingMode.validateAvailable(this, booking);

    bookings.add(booking);
  }

  public boolean isAvailable(Integer minCapacity, BookingDate arrivalData, int numberOfNights) {
    if (isExceedingAccommodationCapacity(minCapacity)) return false;

    return lodgingMode.isAvailable(this, minCapacity, arrivalData, numberOfNights);
  }

  public boolean isPackageAvailable(Packages bookingPackage) {
    return pricesPerNight.containsKey(bookingPackage);
  }

  public boolean hasOverlappingBookings(Booking booking) {
    return bookings.stream().anyMatch(presentBooking -> presentBooking.isOverlapping(booking));
  }

  private List<Booking> getBookingsOnDate(BookingDate date) {
    return bookings.stream()
        .filter(booking -> !booking.isCanceled())
        .filter(booking -> booking.isOverlapping(date))
        .collect(Collectors.toList());
  }

  private void validateOwnerNotTenant(PublicKey tenantPublicKey) {
    if (ownerPublicKey.equals(tenantPublicKey)) throw new BookingNotAllowedException();
  }

  private void validatePackageAvailable(Packages packageName) {
    if (!isPackageAvailable(packageName)) throw new PackageNotAvailableException();
  }

  private void validateMinCapacity(Integer minCapacity) {
    if (isExceedingAccommodationCapacity(minCapacity))
      throw new ExceedingAccommodationCapacityException();
  }

  private boolean isExceedingAccommodationCapacity(Integer minCapacity) {
    return minCapacity != null && minCapacity > capacity;
  }
}
