package ca.ulaval.glo2003.beds.mappers;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.*;
import static ca.ulaval.glo2003.beds.rest.helpers.BedRequestBuilder.aBedRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.exceptions.*;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.util.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BedMapperTest {

  private static BedMapper bedMapper;
  private static PublicKeyMapper publicKeyMapper = mock(PublicKeyMapper.class);
  private static PackageMapper packageMapper = mock(PackageMapper.class);

  private static Bed bed;
  private static UUID bedNumber;
  private static PublicKey ownerPublicKey;
  private static Location location;
  private static BedTypes bedType;
  private static CleaningFrequencies cleaningFrequency;
  private static BloodTypes bloodType;
  private static BloodTypes otherBloodType;
  private static List<BloodTypes> bloodTypes;
  private static int capacity;
  private static LodgingModes lodgingMode;
  private static Map<Packages, Price> pricesPerNight = new EnumMap<>(Packages.class);
  private static int stars;

  private static BedRequest bedRequest;
  private static PackageRequest packageRequest = mock(PackageRequest.class);
  private static List<PackageRequest> packageRequests = Collections.singletonList(packageRequest);
  private static PackageResponse packageResponse = mock(PackageResponse.class);
  private static List<PackageResponse> packageResponses =
      Collections.singletonList(packageResponse);

  @BeforeAll
  public static void setUpMapper() {
    bedMapper = new BedMapper(publicKeyMapper, packageMapper);
  }

  @BeforeEach
  public void setUpMocks() {
    resetMocks();
    when(publicKeyMapper.fromString(ownerPublicKey.getValue())).thenReturn(ownerPublicKey);
    when(packageMapper.fromRequests(packageRequests)).thenReturn(pricesPerNight);
    when(packageMapper.toResponses(pricesPerNight)).thenReturn(packageResponses);
  }

  private static void resetMocks() {
    bedNumber = createBedNumber();
    ownerPublicKey = createOwnerPublicKey();
    location = createLocation();
    bedType = createBedType();
    cleaningFrequency = createCleaningFrequency();
    bloodType = BloodTypes.O_MINUS;
    otherBloodType = BloodTypes.O_MINUS;
    bloodTypes = Arrays.asList(bloodType, otherBloodType);
    capacity = 100;
    lodgingMode = createLodgingMode();
    stars = 3;

    bed = buildBed();
    bedRequest = buildBedRequest();
  }

  private static Bed buildBed() {
    return aBed()
        .withBedNumber(bedNumber)
        .withOwnerPublicKey(ownerPublicKey)
        .withLocation(location)
        .withBedType(bedType)
        .withCleaningFrequency(cleaningFrequency)
        .withBloodTypes(bloodTypes)
        .withCapacity(capacity)
        .withLodgingMode(lodgingMode)
        .withPricesPerNights(pricesPerNight)
        .build();
  }

  private static BedRequest buildBedRequest() {
    return aBedRequest()
        .withOwnerPublicKey(ownerPublicKey.getValue())
        .withBedType(bedType.toString())
        .withCleaningFrequency(cleaningFrequency.toString())
        .withBloodTypes(convertToStrings(bloodTypes))
        .withCapacity(capacity)
        .withLodgingMode(lodgingMode.toString())
        .build();
  }

  private static List<String> convertToStrings(List<BloodTypes> bloodTypes) {
    return bloodTypes.stream().map(BloodTypes::toString).collect(Collectors.toList());
  }

  @Test
  public void fromRequest_shouldMapOwnerPublicKey() {
    bed = bedMapper.fromRequest(bedRequest);

    assertEquals(ownerPublicKey, bed.getOwnerPublicKey());
  }

  @Test
  public void fromRequest_shouldMapBedType() {
    bed = bedMapper.fromRequest(bedRequest);

    assertEquals(bedType, bed.getBedType());
  }

  @Test
  public void fromRequest_withoutBedType_shouldThrowInvalidBedTypeException() {
    bedRequest = aBedRequest().withBedType(null).build();

    assertThrows(InvalidBedTypeException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void fromRequest_withInvalidBedType_shouldThrowInvalidBedTypeException() {
    bedRequest = aBedRequest().withBedType("invalidBedType").build();

    assertThrows(InvalidBedTypeException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void fromRequest_shouldMapCleaningFrequency() {
    bed = bedMapper.fromRequest(bedRequest);

    assertEquals(cleaningFrequency, bed.getCleaningFrequency());
  }

  @Test
  public void fromRequest_withoutCleaningFrequency_shouldThrowInvalidCleaningFrequencyException() {
    bedRequest = aBedRequest().withCleaningFrequency(null).build();

    assertThrows(InvalidCleaningFrequencyException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void fromRequest_withInvalidCleaningFrequency_shouldThrowInvalidFormatException() {
    bedRequest = aBedRequest().withCleaningFrequency("invalidCleaningFrequency").build();

    assertThrows(InvalidCleaningFrequencyException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void fromRequest_withSingleBloodType_shouldReturnBedWithSingleBloodType() {
    List<String> bloodTypeStrings = convertToStrings(Collections.singletonList(bloodType));
    bedRequest = aBedRequest().withBloodTypes(bloodTypeStrings).build();

    bed = bedMapper.fromRequest(bedRequest);

    assertEquals(1, bed.getBloodTypes().size());
    assertEquals(bloodType, bed.getBloodTypes().get(0));
  }

  @Test
  public void fromRequest_withMultipleBloodTypes_shouldReturnBedWithMultipleBloodTypes() {
    bed = bedMapper.fromRequest(bedRequest);

    assertEquals(2, bed.getBloodTypes().size());
    assertTrue(bed.getBloodTypes().contains(bloodType));
    assertTrue(bed.getBloodTypes().contains(otherBloodType));
  }

  // TODO : withInvalidBloodTypes (String instead of array) (also test numerical)

  @Test
  public void fromRequest_withoutBloodTypes_shouldThrowInvalidBloodTypesException() {
    bedRequest = aBedRequest().withBloodTypes(null).build();

    assertThrows(InvalidBloodTypesException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void fromRequest_withEmptyBloodTypes_shouldThrowInvalidBloodTypesException() {
    bedRequest = aBedRequest().withBloodTypes(Collections.emptyList()).build();

    assertThrows(InvalidBloodTypesException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  // TODO : withNullBloodType (null element in field)

  @Test
  public void fromRequest_withInvalidBloodType_shouldThrowInvalidBloodTypeException() {
    bedRequest =
        aBedRequest().withBloodTypes(Collections.singletonList("invalidBloodType")).build();

    assertThrows(InvalidBloodTypesException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void fromRequest_shouldMapCapacity() {
    bed = bedMapper.fromRequest(bedRequest);

    assertEquals(capacity, bed.getCapacity());
  }

  @Test
  public void fromRequest_withInvalidCapacity_shouldThrowInvalidCapacityException() {
    bedRequest = aBedRequest().withCapacity(-1).build();

    assertThrows(InvalidCapacityException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  // TODO : withStringifiedCapacity (String) (also test array)

  @Test
  public void fromRequest_shouldMapLodgingMode() {
    bed = bedMapper.fromRequest(bedRequest);

    assertEquals(lodgingMode, bed.getLodgingMode());
  }

  @Test
  public void fromRequest_withoutLodgingMode_shouldMapPrivateLodgingMode() {
    bedRequest = aBedRequest().withLodgingMode(null).build();

    bed = bedMapper.fromRequest(bedRequest);

    assertEquals(LodgingModes.PRIVATE, bed.getLodgingMode());
  }

  @Test
  public void fromRequest_withInvalidLodgingMode_shouldThrowInvalidLodgingModeException() {
    bedRequest = aBedRequest().withLodgingMode("invalidLodgingMode").build();

    assertThrows(InvalidLodgingModeException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void fromRequest_shouldMapPricesPerNight() {
    Bed bed = bedMapper.fromRequest(bedRequest);

    assertEquals(pricesPerNight, bed.getPricesPerNight());
  }

  @Test
  public void toResponseWithoutNumber_shouldNotMapBedNumber() {
    BedResponse bedResponse = bedMapper.toResponseWithoutNumber(bed, stars);

    assertNull(bedResponse.getBedNumber());
  }

  @Test
  public void toResponseWithoutNumber_shouldMapLocation() {
    BedResponse bedResponse = bedMapper.toResponseWithoutNumber(bed, stars);

    assertEquals(location.getZipCode().getValue(), bedResponse.getZipCode());
  }

  @Test
  public void toResponseWithoutNumber_shouldMapBedType() {
    BedResponse bedResponse = bedMapper.toResponseWithoutNumber(bed, stars);

    assertEquals(bedType.toString(), bedResponse.getBedType());
  }

  @Test
  public void toResponseWithoutNumber_shouldMapCleaningFrequency() {
    BedResponse bedResponse = bedMapper.toResponseWithoutNumber(bed, stars);

    assertEquals(cleaningFrequency.toString(), bedResponse.getCleaningFrequency());
  }

  @Test
  public void toResponseWithoutNumber_withSingleBloodType_shouldMapBloodType() {
    bed = aBed().withBloodTypes(bloodTypes.subList(0, 1)).build();

    BedResponse bedResponse = bedMapper.toResponseWithoutNumber(bed, stars);

    assertEquals(1, bedResponse.getBloodTypes().size());
    assertEquals(bloodType.toString(), bedResponse.getBloodTypes().get(0));
  }

  @Test
  public void toResponseWithoutNumber_withMultipleBloodTypes_shouldMapBloodTypes() {
    BedResponse bedResponse = bedMapper.toResponseWithoutNumber(bed, stars);

    assertEquals(2, bedResponse.getBloodTypes().size());
    assertTrue(
        bedResponse.getBloodTypes().stream()
            .anyMatch(bloodTypeString -> bloodTypeString.equals(bloodType.toString())));
    assertTrue(
        bedResponse.getBloodTypes().stream()
            .anyMatch(bloodTypeString -> bloodTypeString.equals(otherBloodType.toString())));
  }

  @Test
  public void toResponseWithoutNumber_shouldMapCapacity() {
    BedResponse bedResponse = bedMapper.toResponseWithoutNumber(bed, stars);

    assertEquals(capacity, bedResponse.getCapacity());
  }

  @Test
  public void toResponseWithoutNumber_shouldMapLodgingMode() {
    BedResponse bedResponse = bedMapper.toResponseWithoutNumber(bed, stars);

    assertEquals(lodgingMode.toString(), bedResponse.getLodgingMode());
  }

  @Test
  public void toResponseWithoutNumber_shouldMapPricesPerNights() {
    BedResponse bedResponse = bedMapper.toResponseWithoutNumber(bed, stars);

    assertEquals(packageResponses, bedResponse.getPackages());
  }

  @Test
  public void toResponseWithoutNumber_shouldMapStars() {
    BedResponse bedResponse = bedMapper.toResponseWithoutNumber(bed, stars);

    assertEquals(stars, bedResponse.getStars());
  }

  @Test
  public void toResponseWithNumber_shouldMapBedNumber() {
    BedResponse bedResponse = bedMapper.toResponseWithNumber(bed, stars);

    assertEquals(bedNumber.toString(), bedResponse.getBedNumber());
  }
}
