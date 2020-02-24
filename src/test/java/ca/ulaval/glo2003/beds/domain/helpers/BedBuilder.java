package ca.ulaval.glo2003.beds.domain.helpers;

import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.*;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.domain.Package;
import java.util.Collections;
import java.util.List;

public class BedBuilder {

  private BedBuilder() {}

  private String DEFAULT_OWNER_PUBLIC_KEY = createOwnerPublicKey();
  private String ownerPublicKey = DEFAULT_OWNER_PUBLIC_KEY;

  private String DEFAULT_ZIP_CODE = createZipCode();
  private String zipCode = DEFAULT_ZIP_CODE;

  private BedTypes DEFAULT_BED_TYPE = createBedType();
  private BedTypes bedType = DEFAULT_BED_TYPE;

  private CleaningFrequencies DEFAULT_CLEANING_FREQUENCY = createCleaningFrequency();
  private CleaningFrequencies cleaningFrequency = DEFAULT_CLEANING_FREQUENCY;

  private List<BloodTypes> DEFAULT_BLOOD_TYPES = createBloodTypes();
  private List<BloodTypes> bloodTypes = DEFAULT_BLOOD_TYPES;

  private int DEFAULT_CAPACITY = createCapacity();
  private int capacity = DEFAULT_CAPACITY;

  private List<Package> DEFAULT_PACKAGES = createPackages();
  private List<Package> packages = DEFAULT_PACKAGES;

  private List<Booking> DEFAULT_BOOKINGS = Collections.emptyList();
  private List<Booking> bookings = DEFAULT_BOOKINGS;

  public static BedBuilder aBed() {
    return new BedBuilder();
  }

  public BedBuilder withBedType(BedTypes bedType) {
    this.bedType = bedType;
    return this;
  }

  public BedBuilder withCleaningFrequency(CleaningFrequencies cleaningFrequency) {
    this.cleaningFrequency = cleaningFrequency;
    return this;
  }

  public BedBuilder withBloodTypes(List<BloodTypes> bloodTypes) {
    this.bloodTypes = bloodTypes;
    return this;
  }

  public BedBuilder withCapacity(int capacity) {
    this.capacity = capacity;
    return this;
  }

  public BedBuilder withPackages(List<Package> packages) {
    this.packages = packages;
    return this;
  }

  public BedBuilder withBookings(List<Booking> bookings) {
    this.bookings = bookings;
    return this;
  }

  public Bed build() {
    Bed bed =
        new Bed(
            ownerPublicKey, zipCode, bedType, cleaningFrequency, bloodTypes, capacity, packages);
    bookings.forEach(bed::book);
    return bed;
  }
}
