package ca.ulaval.glo2003.beds.domain.helpers;

import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.*;
import static ca.ulaval.glo2003.beds.domain.helpers.LodgingModeBuilder.aLodgingMode;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPackageName;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPricePerNight;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BedBuilder {

  private BedBuilder() {}

  private BedNumber DEFAULT_BED_NUMBER = createBedNumber();
  private BedNumber bedNumber = DEFAULT_BED_NUMBER;

  private PublicKey DEFAULT_OWNER_PUBLIC_KEY = createOwnerPublicKey();
  private PublicKey ownerPublicKey = DEFAULT_OWNER_PUBLIC_KEY;

  private Location DEFAULT_LOCATION = createLocation();
  private Location location = DEFAULT_LOCATION;

  private BedTypes DEFAULT_BED_TYPE = createBedType();
  private BedTypes bedType = DEFAULT_BED_TYPE;

  private CleaningFrequencies DEFAULT_CLEANING_FREQUENCY = createCleaningFrequency();
  private CleaningFrequencies cleaningFrequency = DEFAULT_CLEANING_FREQUENCY;

  private List<BloodTypes> DEFAULT_BLOOD_TYPES = createBloodTypes();
  private List<BloodTypes> bloodTypes = DEFAULT_BLOOD_TYPES;

  private int DEFAULT_CAPACITY = BedTypesCapacities.get(DEFAULT_BED_TYPE);
  private int capacity = DEFAULT_CAPACITY;

  private LodgingMode DEFAULT_BED_LODGING_MODE =
      aLodgingMode().withType(LodgingModes.PRIVATE).build();
  private LodgingMode lodgingMode = DEFAULT_BED_LODGING_MODE;

  private Map<Packages, Price> DEFAULT_PRICES_PER_NIGHT =
      Collections.singletonMap(createPackageName(), createPricePerNight());
  private Map<Packages, Price> pricesPerNight = DEFAULT_PRICES_PER_NIGHT;

  private List<Booking> DEFAULT_BOOKINGS = Collections.emptyList();
  private List<Booking> bookings = DEFAULT_BOOKINGS;

  public static BedBuilder aBed() {
    return new BedBuilder();
  }

  public BedBuilder withBedNumber(BedNumber bedNumber) {
    this.bedNumber = bedNumber;
    return this;
  }

  public BedBuilder withOwnerPublicKey(PublicKey ownerPublicKey) {
    this.ownerPublicKey = ownerPublicKey;
    return this;
  }

  public BedBuilder withLocation(Location location) {
    this.location = location;
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

  public BedBuilder withLodgingMode(LodgingMode lodgingMode) {
    this.lodgingMode = lodgingMode;
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
            bedType,
            cleaningFrequency,
            bloodTypes,
            capacity,
            lodgingMode,
            pricesPerNight);
    bed.setNumber(bedNumber);
    bed.setLocation(location);
    bookings.forEach(bed::book);
    return bed;
  }
}
