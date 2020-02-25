package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.rest.exceptions.BedAlreadyBookedException;
import ca.ulaval.glo2003.beds.rest.exceptions.BookingNotAllowedException;
import ca.ulaval.glo2003.beds.rest.exceptions.PackageNotAvailableException;
import java.util.*;
import javax.swing.*;

public class Bed {

  private UUID number;
  private String ownerPublicKey;
  private String zipCode;
  private BedTypes bedType;
  private CleaningFrequencies cleaningFrequency;
  private List<BloodTypes> bloodTypes;
  private int capacity;
  private Map<PackageNames, Price> pricesPerNight;
  private List<Booking> bookings = new ArrayList<>();

  public Bed(
      String ownerPublicKey,
      String zipCode,
      BedTypes bedType,
      CleaningFrequencies cleaningFrequency,
      List<BloodTypes> bloodTypes,
      int capacity,
      Map<PackageNames, Price> pricesPerNight) {
    this.ownerPublicKey = ownerPublicKey;
    this.zipCode = zipCode;
    this.bedType = bedType;
    this.cleaningFrequency = cleaningFrequency;
    this.bloodTypes = bloodTypes;
    this.capacity = capacity;
    this.pricesPerNight = pricesPerNight;
  }

  public UUID getNumber() {
    return number;
  }

  public void setNumber(UUID number) {
    this.number = number;
  }

  public String getOwnerPublicKey() {
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

  public Map<PackageNames, Price> getPricesPerNight() {
    return pricesPerNight;
  }

  public Price getPricePerNight(PackageNames packageName) {
    return pricesPerNight.get(packageName);
  }

  public List<Booking> getBookings() {
    return bookings;
  }

  public void book(Booking booking, PackageNames bookingPackage) {
    if (ownerPublicKey.equals(booking.getTenantPublicKey())) throw new BookingNotAllowedException();

    if (!isPackageAvailable(bookingPackage)) throw new PackageNotAvailableException();

    if (isBedAlreadyBooked(booking)) throw new BedAlreadyBookedException();

    bookings.add(booking);
  }

  public boolean isPackageAvailable(PackageNames bookingPackage) {
    return pricesPerNight.containsKey(bookingPackage);
  }

  private boolean isBedAlreadyBooked(Booking booking) {
    return bookings.stream().anyMatch(presentBooking -> presentBooking.isOverlapping(booking));
  }
}
