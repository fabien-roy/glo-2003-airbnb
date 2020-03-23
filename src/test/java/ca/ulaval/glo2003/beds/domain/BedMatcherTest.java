package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedMatcherBuilder.aBedMatcher;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createLocation;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPricePerNight;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.io.IOException;
import java.util.*;
import org.junit.jupiter.api.Test;

class BedMatcherTest {

  @Test
  public void matches_withEmptyBed_shouldReturnTrue() throws IOException {
    BedMatcher bedMatcher = aBedMatcher().build();
    Bed bed = aBed().build();

    boolean matches = bedMatcher.matches(bed);

    assertTrue(matches);
  }

  @Test
  public void matches_withSameBedType_shouldReturnTrue() throws IOException {
    BedTypes bedType = BedTypes.LATEX;
    BedMatcher bedMatcher = aBedMatcher().withBedType(bedType).build();
    Bed bed = aBed().withBedType(bedType).build();

    boolean matches = bedMatcher.matches(bed);

    assertTrue(matches);
  }

  @Test
  public void matches_withDifferentBedType_shouldReturnFalse() throws IOException {
    BedTypes bedType = BedTypes.LATEX;
    BedTypes otherBedType = BedTypes.MEMORY_FOAM;
    BedMatcher bedMatcher = aBedMatcher().withBedType(bedType).build();
    Bed bed = aBed().withBedType(otherBedType).build();

    boolean matches = bedMatcher.matches(bed);

    assertFalse(matches);
  }

  @Test
  public void matches_withSameCleaningFrequency_shouldReturnTrue() throws IOException {
    CleaningFrequencies cleaningFrequencies = CleaningFrequencies.ANNUAL;
    BedMatcher bedMatcher = aBedMatcher().withCleaningFrequency(cleaningFrequencies).build();
    Bed bed = aBed().withCleaningFrequency(cleaningFrequencies).build();

    boolean matches = bedMatcher.matches(bed);

    assertTrue(matches);
  }

  @Test
  public void matches_withDifferentCleaningFrequency_shouldReturnFalse() throws IOException {
    CleaningFrequencies cleaningFrequency = CleaningFrequencies.ANNUAL;
    CleaningFrequencies otherCleaningFrequency = CleaningFrequencies.MONTHLY;
    BedMatcher bedMatcher = aBedMatcher().withCleaningFrequency(cleaningFrequency).build();
    Bed bed = aBed().withCleaningFrequency(otherCleaningFrequency).build();

    boolean matches = bedMatcher.matches(bed);

    assertFalse(matches);
  }

  @Test
  public void matches_withSinglePresentBloodType_shouldReturnTrue() throws IOException {
    List<BloodTypes> requestedBloodTypes = Collections.singletonList(BloodTypes.O_MINUS);
    List<BloodTypes> bloodTypes = Arrays.asList(BloodTypes.O_MINUS, BloodTypes.O_PLUS);
    BedMatcher bedMatcher = aBedMatcher().withBloodTypes(requestedBloodTypes).build();
    Bed bed = aBed().withBloodTypes(bloodTypes).build();

    boolean matches = bedMatcher.matches(bed);

    assertTrue(matches);
  }

  @Test
  public void matches_withMultiplePresentBloodTypes_shouldReturnTrue() throws IOException {
    List<BloodTypes> requestedBloodTypes = Arrays.asList(BloodTypes.O_MINUS, BloodTypes.O_PLUS);
    List<BloodTypes> bloodTypes =
        Arrays.asList(BloodTypes.O_MINUS, BloodTypes.O_PLUS, BloodTypes.AB_MINUS);
    BedMatcher bedMatcher = aBedMatcher().withBloodTypes(requestedBloodTypes).build();
    Bed bed = aBed().withBloodTypes(bloodTypes).build();

    boolean matches = bedMatcher.matches(bed);

    assertTrue(matches);
  }

  @Test
  public void matches_withSingleNotPresentBloodType_shouldReturnFalse() throws IOException {
    List<BloodTypes> requestedBloodTypes = Collections.singletonList(BloodTypes.AB_MINUS);
    List<BloodTypes> bloodTypes = Arrays.asList(BloodTypes.O_MINUS, BloodTypes.O_PLUS);
    BedMatcher bedMatcher = aBedMatcher().withBloodTypes(requestedBloodTypes).build();
    Bed bed = aBed().withBloodTypes(bloodTypes).build();

    boolean matches = bedMatcher.matches(bed);

    assertFalse(matches);
  }

  @Test
  public void matches_withSingleNotPresentBloodTypeInMultipleBloodTypes_shouldReturnFalse()
      throws IOException {
    List<BloodTypes> requestedBloodTypes = Arrays.asList(BloodTypes.AB_MINUS, BloodTypes.A_MINUS);
    List<BloodTypes> bloodTypes =
        Arrays.asList(BloodTypes.O_MINUS, BloodTypes.O_PLUS, BloodTypes.A_MINUS);
    BedMatcher bedMatcher = aBedMatcher().withBloodTypes(requestedBloodTypes).build();
    Bed bed = aBed().withBloodTypes(bloodTypes).build();

    boolean matches = bedMatcher.matches(bed);

    assertFalse(matches);
  }

  @Test
  public void matches_withMultipleNotPresentBloodTypes_shouldReturnFalse() throws IOException {
    List<BloodTypes> requestedBloodTypes = Arrays.asList(BloodTypes.AB_MINUS, BloodTypes.A_MINUS);
    List<BloodTypes> bloodTypes =
        Arrays.asList(BloodTypes.O_MINUS, BloodTypes.O_PLUS, BloodTypes.B_PLUS);
    BedMatcher bedMatcher = aBedMatcher().withBloodTypes(requestedBloodTypes).build();
    Bed bed = aBed().withBloodTypes(bloodTypes).build();

    boolean matches = bedMatcher.matches(bed);

    assertFalse(matches);
  }

  @Test
  public void matches_withSameCapacity_shouldReturnTrue() throws IOException {
    int capacity = 100;
    BedMatcher bedMatcher = aBedMatcher().withMinCapacity(capacity).build();
    Bed bed = aBed().withCapacity(capacity).build();

    boolean matches = bedMatcher.matches(bed);

    assertTrue(matches);
  }

  @Test
  public void matches_withHigherCapacity_shouldReturnTrue() throws IOException {
    int capacity = 100;
    int higherCapacity = 101;
    BedMatcher bedMatcher = aBedMatcher().withMinCapacity(capacity).build();
    Bed bed = aBed().withCapacity(higherCapacity).build();

    boolean matches = bedMatcher.matches(bed);

    assertTrue(matches);
  }

  @Test
  public void matches_withLowerCapacity_shouldReturnFalse() throws IOException {
    int capacity = 100;
    int lowerCapacity = 99;
    BedMatcher bedMatcher = aBedMatcher().withMinCapacity(capacity).build();
    Bed bed = aBed().withCapacity(lowerCapacity).build();

    boolean matches = bedMatcher.matches(bed);

    assertFalse(matches);
  }

  @Test
  public void matches_withPresentPackageName_shouldReturnTrue() throws IOException {
    Packages packageName = Packages.BLOODTHIRSTY;
    Map<Packages, Price> pricesPerNight = new EnumMap<>(Packages.class);
    pricesPerNight.put(packageName, createPricePerNight());
    pricesPerNight.put(Packages.ALL_YOU_CAN_DRINK, createPricePerNight());
    BedMatcher bedMatcher = aBedMatcher().withPackageName(packageName).build();
    Bed bed = aBed().withPricesPerNights(pricesPerNight).build();

    boolean matches = bedMatcher.matches(bed);

    assertTrue(matches);
  }

  @Test
  public void matches_withNotPresentPackageName_shouldReturnFalse() throws IOException {
    Packages packageName = Packages.BLOODTHIRSTY;
    Map<Packages, Price> pricesPerNight = new EnumMap<>(Packages.class);
    pricesPerNight.put(Packages.SWEET_TOOTH, createPricePerNight());
    pricesPerNight.put(Packages.ALL_YOU_CAN_DRINK, createPricePerNight());
    BedMatcher bedMatcher = aBedMatcher().withPackageName(packageName).build();
    Bed bed = aBed().withPricesPerNights(pricesPerNight).build();

    boolean matches = bedMatcher.matches(bed);

    assertFalse(matches);
  }

  @Test
  public void matches_withAllSameAttributes_shouldReturnTrue() throws IOException {
    BedTypes bedType = BedTypes.LATEX;
    CleaningFrequencies cleaningFrequency = CleaningFrequencies.ANNUAL;
    BedMatcher bedMatcher =
        aBedMatcher().withBedType(bedType).withCleaningFrequency(cleaningFrequency).build();
    Bed bed = aBed().withBedType(bedType).withCleaningFrequency(cleaningFrequency).build();

    boolean matches = bedMatcher.matches(bed);

    assertTrue(matches);
  }

  @Test
  public void matches_withASingleDifferentAttribute_shouldReturnFalse() throws IOException {
    BedTypes bedType = BedTypes.LATEX;
    CleaningFrequencies cleaningFrequency = CleaningFrequencies.ANNUAL;
    CleaningFrequencies otherCleaningFrequency = CleaningFrequencies.MONTHLY;
    BedMatcher bedMatcher =
        aBedMatcher().withBedType(bedType).withCleaningFrequency(cleaningFrequency).build();
    Bed bed = aBed().withBedType(bedType).withCleaningFrequency(otherCleaningFrequency).build();

    boolean matches = bedMatcher.matches(bed);

    assertFalse(matches);
  }

  @Test
  public void matches_withAllDifferentAttributes_shouldReturnFalse() throws IOException {
    BedTypes bedType = BedTypes.LATEX;
    BedTypes otherBedtype = BedTypes.MEMORY_FOAM;
    CleaningFrequencies cleaningFrequency = CleaningFrequencies.ANNUAL;
    CleaningFrequencies otherCleaningFrequency = CleaningFrequencies.MONTHLY;
    BedMatcher bedMatcher =
        aBedMatcher().withBedType(bedType).withCleaningFrequency(cleaningFrequency).build();
    Bed bed =
        aBed().withBedType(otherBedtype).withCleaningFrequency(otherCleaningFrequency).build();

    boolean matches = bedMatcher.matches(bed);

    assertFalse(matches);
  }

  @Test
  public void matches_withLocationWithinRadius_shouldReturnTrue() throws IOException {
    int maxDistance = 10;
    Location origin = mock(Location.class);
    Location bedLocation = createLocation();
    BedMatcher bedMatcher = aBedMatcher().withOriginAndMaxDistance(origin, maxDistance).build();
    Bed bed = aBed().withLocation(bedLocation).build();
    when(origin.isWithinRadius(bedLocation, maxDistance)).thenReturn(true);

    boolean matches = bedMatcher.matches(bed);

    assertTrue(matches);
  }

  @Test
  public void matches_withSameLodgingMode_shouldReturnTrue() throws IOException {
    BedMatcher bedMatcher = aBedMatcher().withLodgingMode(LodgingModes.PRIVATE).build();
    Bed bed = aBed().withLodgingMode(LodgingModes.PRIVATE).build();

    boolean matches = bedMatcher.matches(bed);

    assertTrue(matches);
  }

  @Test
  public void matches_withDifferentLodgingMode_shouldReturnFalse() throws IOException {
    BedMatcher bedMatcher = aBedMatcher().withLodgingMode(LodgingModes.PRIVATE).build();
    Bed bed = aBed().withLodgingMode(LodgingModes.COHABITATION).build();

    boolean matches = bedMatcher.matches(bed);

    assertFalse(matches);
  }
}
