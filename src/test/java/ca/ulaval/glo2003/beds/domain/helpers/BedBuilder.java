package ca.ulaval.glo2003.beds.domain.helpers;

import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.*;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPackageName;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPricePerNight;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.domain.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BedBuilder {

  private BedBuilder() {}

  private UUID DEFAULT_BED_NUMBER = createBedNumber();
  private UUID bedNumber = DEFAULT_BED_NUMBER;

  private PublicKey DEFAULT_OWNER_PUBLIC_KEY = createOwnerPublicKey();
  private PublicKey ownerPublicKey = DEFAULT_OWNER_PUBLIC_KEY;

  private String DEFAULT_ZIP_CODE = createZipCode();
  private String zipCode = DEFAULT_ZIP_CODE;

  private BedTypes DEFAULT_BED_TYPE = createBedType();
  private BedTypes bedType = DEFAULT_BED_TYPE;

  private CleaningFrequencies DEFAULT_CLEANING_FREQUENCY = createCleaningFrequency();
  private CleaningFrequencies cleaningFrequency = DEFAULT_CLEANING_FREQUENCY;

  private List<BloodTypes> DEFAULT_BLOOD_TYPES = createBloodTypes();
  private List<BloodTypes> bloodTypes = DEFAULT_BLOOD_TYPES;

  private int DEFAULT_CAPACITY = BedTypesCapacities.get(DEFAULT_BED_TYPE);
  private int capacity = DEFAULT_CAPACITY;

  private Map<Packages, Price> DEFAULT_PRICES_PER_NIGHT =
      Collections.singletonMap(createPackageName(), createPricePerNight());
  private Map<Packages, Price> pricesPerNight = DEFAULT_PRICES_PER_NIGHT;

  private List<Booking> DEFAULT_BOOKINGS = Collections.emptyList();
  private List<Booking> bookings = DEFAULT_BOOKINGS;

  private LodgingModes DEFAULT_BED_LODGING_MODE = createLodgingMode();
  private LodgingModes lodgingMode = DEFAULT_BED_LODGING_MODE;

  public static BedBuilder aBed() {
    return new BedBuilder();
  }

  public BedBuilder withBedNumber(UUID bedNumber) {
    this.bedNumber = bedNumber;
    return this;
  }

  public BedBuilder withOwnerPublicKey(PublicKey ownerPublicKey) {
    this.ownerPublicKey = ownerPublicKey;
    return this;
  }

  public BedBuilder withZipCode(String zipCode) {
    this.zipCode = zipCode;
    return this;
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

  public BedBuilder withPricesPerNights(Map<Packages, Price> pricesPerNight) {
    this.pricesPerNight = pricesPerNight;
    return this;
  }

  public BedBuilder withBookings(List<Booking> bookings) {
    this.bookings = bookings;
    return this;
  }

  public Bed build() {
    Bed bed =
        new Bed(
            ownerPublicKey,
            zipCode,
            bedType,
            cleaningFrequency,
            bloodTypes,
            capacity,
            pricesPerNight,
            lodgingMode);
    bed.setNumber(bedNumber);
    bookings.forEach(bed::book);
    return bed;
  }
}
