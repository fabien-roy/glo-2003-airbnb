package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.rest.exceptions.BedAlreadyBookedException;
import ca.ulaval.glo2003.beds.rest.exceptions.BookingNotAllowedException;
import ca.ulaval.glo2003.beds.rest.exceptions.PackageNotAvailableException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.swing.*;

public class Bed {

  private UUID number;
  private String ownerPublicKey;
  private String zipCode;
  private BedTypes bedType;
  private CleaningFrequencies cleaningFrequency;
  private List<BloodTypes> bloodTypes;
  private int capacity;
  private List<Package> packages; // TODO : Make this a EnumMap
  private List<Booking> bookings = new ArrayList<>();

  public Bed(
      String ownerPublicKey,
      String zipCode,
      BedTypes bedType,
      CleaningFrequencies cleaningFrequency,
      List<BloodTypes> bloodTypes,
      int capacity,
      List<Package> packages) {
    this.ownerPublicKey = ownerPublicKey;
    this.zipCode = zipCode;
    this.bedType = bedType;
    this.cleaningFrequency = cleaningFrequency;
    this.bloodTypes = bloodTypes;
    this.capacity = capacity;
    this.packages = packages;
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

  public List<Package> getPackages() {
    return packages;
  }

  public List<Booking> getBookings() {
    return bookings;
  }

  // TODO : Simplify, once enum map is used
  public Price getPriceForPackage(PackageNames packageName) {
    return packages.stream()
        .filter(possiblePackage -> possiblePackage.getName().equals(packageName))
        .findFirst()
        .get()
        .getPricePerNight();
  }

  public void book(Booking booking, PackageNames bookingPackage) {
    if (ownerPublicKey.equals(booking.getTenantPublicKey())) throw new BookingNotAllowedException();

    if (!isPackageAvailable(bookingPackage)) throw new PackageNotAvailableException();

    if (isBedAlreadyBooked(booking)) throw new BedAlreadyBookedException();

    bookings.add(booking);
  }

  private boolean isPackageAvailable(PackageNames bookingPackage) {
    return packages.stream().anyMatch(bedPackage -> bedPackage.getName().equals(bookingPackage));
  }

  private boolean isBedAlreadyBooked(Booking booking) {
    return bookings.stream().anyMatch(presentBooking -> presentBooking.isOverlapping(booking));
  }
}
