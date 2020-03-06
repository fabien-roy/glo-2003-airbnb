package ca.ulaval.glo2003.beds.rest.mappers;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.*;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPackageName;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPricePerNight;
import static ca.ulaval.glo2003.beds.rest.helpers.BedRequestBuilder.aBedRequest;
import static ca.ulaval.glo2003.beds.rest.helpers.PackageRequestBuilder.aPackageRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
import ca.ulaval.glo2003.beds.rest.exceptions.*;
import java.util.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BedMapperTest {

  private BedMapper bedMapper;
  private PublicKeyMapper publicKeyMapper;
  private PackageMapper packageMapper;

  @BeforeEach
  public void setUpMapper() {
    publicKeyMapper = mock(PublicKeyMapper.class);
    packageMapper = mock(PackageMapper.class);
    bedMapper = new BedMapper(publicKeyMapper, packageMapper);
  }

  @Test
  public void fromRequest_shouldMapBedType() {
    BedTypes expectedBedType = BedTypes.LATEX;
    BedRequest bedRequest = aBedRequest().withBedType(expectedBedType.toString()).build();

    Bed bed = bedMapper.fromRequest(bedRequest);

    assertEquals(expectedBedType, bed.getBedType());
  }

  @Test
  public void fromRequest_withoutBedType_shouldThrowInvalidBedTypeException() {
    BedRequest bedRequest = aBedRequest().withBedType(null).build();

    assertThrows(InvalidBedTypeException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void fromRequest_withInvalidBedType_shouldThrowInvalidBedTypeException() {
    String invalidBedType = "invalidBedType";
    BedRequest bedRequest = aBedRequest().withBedType(invalidBedType).build();

    assertThrows(InvalidBedTypeException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void fromRequest_shouldMapCleaningFrequency() {
    CleaningFrequencies expectedCleaningFrequency = CleaningFrequencies.ANNUAL;
    BedRequest bedRequest =
        aBedRequest().withCleaningFrequency(expectedCleaningFrequency.toString()).build();

    Bed bed = bedMapper.fromRequest(bedRequest);

    assertEquals(expectedCleaningFrequency, bed.getCleaningFrequency());
  }

  @Test
  public void fromRequest_withInvalidCleaningFrequency_shouldThrowInvalidFormatException() {
    String invalidCleaningFrequency = "invalidCleaningFrequency";
    BedRequest bedRequest = aBedRequest().withCleaningFrequency(invalidCleaningFrequency).build();

    assertThrows(InvalidCleaningFrequencyException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void fromRequest_withoutCleaningFrequency_shouldThrowInvalidCleaningFrequencyException() {
    BedRequest bedRequest = aBedRequest().withCleaningFrequency(null).build();

    assertThrows(InvalidCleaningFrequencyException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void fromRequest_withSingleBloodType_shouldReturnBedWithSingleBloodType() {
    BloodTypes expectedBloodType = BloodTypes.O_MINUS;
    List<String> bloodTypes = Collections.singletonList(expectedBloodType.toString());
    BedRequest bedRequest = aBedRequest().withBloodTypes(bloodTypes).build();

    Bed bed = bedMapper.fromRequest(bedRequest);

    assertEquals(1, bed.getBloodTypes().size());
    assertEquals(expectedBloodType, bed.getBloodTypes().get(0));
  }

  @Test
  public void fromRequest_withMultipleBloodTypes_shouldReturnBedWithMultipleBloodTypes() {
    BloodTypes expectedBloodType = BloodTypes.O_MINUS;
    BloodTypes otherExpectedBloodType = BloodTypes.O_PLUS;
    List<String> bloodTypes =
        Arrays.asList(expectedBloodType.toString(), otherExpectedBloodType.toString());
    BedRequest bedRequest = aBedRequest().withBloodTypes(bloodTypes).build();

    Bed bed = bedMapper.fromRequest(bedRequest);

    assertEquals(2, bed.getBloodTypes().size());
    assertTrue(bed.getBloodTypes().contains(expectedBloodType));
    assertTrue(bed.getBloodTypes().contains(otherExpectedBloodType));
  }

  @Test
  public void fromRequest_withoutBloodTypes_shouldThrowInvalidBloodTypesException() {
    List<String> bloodTypes = Collections.singletonList(null);
    BedRequest bedRequest = aBedRequest().withBloodTypes(bloodTypes).build();

    assertThrows(InvalidBloodTypesException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void fromRequest_withEmptyBloodTypes_shouldThrowInvalidBloodTypesException() {
    List<String> bloodTypes = Collections.emptyList();
    BedRequest bedRequest = aBedRequest().withBloodTypes(bloodTypes).build();

    assertThrows(InvalidBloodTypesException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void fromRequest_withInvalidBloodType_shouldThrowInvalidBloodTypeException() {
    String invalidBloodType = "invalidBloodType";
    List<String> bloodTypes = Collections.singletonList(invalidBloodType);
    BedRequest bedRequest = aBedRequest().withBloodTypes(bloodTypes).build();

    assertThrows(InvalidBloodTypesException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void fromRequest_shouldMapCapacity() {
    int expectedCapacity = 1000;
    BedRequest bedRequest = aBedRequest().withCapacity(expectedCapacity).build();

    Bed bed = bedMapper.fromRequest(bedRequest);

    assertEquals(expectedCapacity, bed.getCapacity());
  }

  @Test
  public void fromRequest_withInvalidCapacity_shouldThrowInvalidCapacityException() {
    int capacity = -100;
    BedRequest bedRequest = aBedRequest().withCapacity(capacity).build();

    assertThrows(InvalidCapacityException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void fromRequest_shouldMapPricesPerNight() {
    PackageRequest packageRequest = aPackageRequest().build();
    List<PackageRequest> packageRequests = Collections.singletonList(packageRequest);
    BedRequest bedRequest = aBedRequest().withPackages(packageRequests).build();
    Map<Packages, Price> expectedPricesPerNight = new EnumMap<>(Packages.class);
    when(packageMapper.fromRequests(packageRequests)).thenReturn(expectedPricesPerNight);

    Bed bed = bedMapper.fromRequest(bedRequest);

    assertEquals(expectedPricesPerNight, bed.getPricesPerNight());
  }

  @Test
  public void fromRequest_shouldMapZipCode() {
    String expectedZipCode = createZipCode();
    BedRequest bedRequest = aBedRequest().withZipCode(expectedZipCode).build();

    Bed bed = bedMapper.fromRequest(bedRequest);

    assertEquals(expectedZipCode, bed.getZipCode());
  }

  @Test
  public void fromRequest_withInvalidZipCode_shouldThrowInvalidZipCodeException() {
    String invalidZipCode = "invalidZipCode";
    BedRequest bedRequest = aBedRequest().withZipCode(invalidZipCode).build();

    assertThrows(InvalidZipCodeException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void fromRequest_withoutZipCode_shouldThrowInvalidZipCodeException() {
    BedRequest bedRequest = aBedRequest().withZipCode(null).build();

    assertThrows(InvalidZipCodeException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void fromRequest_shouldMapOwnerPublicKey() {
    PublicKey expectedOwnerPublicKey = BedObjectMother.createOwnerPublicKey();
    BedRequest bedRequest =
        aBedRequest().withOwnerPublicKey(expectedOwnerPublicKey.getValue()).build();
    when(publicKeyMapper.fromString(expectedOwnerPublicKey.getValue()))
        .thenReturn(expectedOwnerPublicKey);

    Bed bed = bedMapper.fromRequest(bedRequest);

    assertEquals(expectedOwnerPublicKey, bed.getOwnerPublicKey());
  }

  @Test
  public void toResponseWithoutNumber_shouldNotMapBedNumber() {
    Bed bed = aBed().build();

    BedResponse bedResponse = bedMapper.toResponseWithoutNumber(bed, 0);

    assertNull(bedResponse.getBedNumber());
  }

  @Test
  public void toResponseWithoutNumber_shouldMapZipCode() {
    String expectedZipCode = createZipCode();
    Bed bed = aBed().withZipCode(expectedZipCode).build();

    BedResponse bedResponse = bedMapper.toResponseWithoutNumber(bed, 0);

    assertEquals(expectedZipCode, bedResponse.getZipCode());
  }

  @Test
  public void toResponseWithoutNumber_shouldMapBedType() {
    BedTypes expectedBedType = BedTypes.LATEX;
    Bed bed = aBed().withBedType(expectedBedType).build();

    BedResponse bedResponse = bedMapper.toResponseWithoutNumber(bed, 0);

    assertEquals(expectedBedType.toString(), bedResponse.getBedType());
  }

  @Test
  public void toResponseWithoutNumber_shouldMapCleaningFrequency() {
    CleaningFrequencies expectedCleaningFrequency = CleaningFrequencies.ANNUAL;
    Bed bed = aBed().withCleaningFrequency(expectedCleaningFrequency).build();

    BedResponse bedResponse = bedMapper.toResponseWithoutNumber(bed, 0);

    assertEquals(expectedCleaningFrequency.toString(), bedResponse.getCleaningFrequency());
  }

  @Test
  public void toResponseWithoutNumber_withSingleBloodType_shouldMapBloodType() {
    BloodTypes expectedBloodType = BloodTypes.O_MINUS;
    List<BloodTypes> bloodTypes = Collections.singletonList(expectedBloodType);
    Bed bed = aBed().withBloodTypes(bloodTypes).build();

    BedResponse bedResponse = bedMapper.toResponseWithoutNumber(bed, 0);

    assertEquals(1, bedResponse.getBloodTypes().size());
    assertEquals(expectedBloodType.toString(), bedResponse.getBloodTypes().get(0));
  }

  @Test
  public void toResponseWithoutNumber_withMultipleBloodTypes_shouldMapBloodTypes() {
    BloodTypes expectedBloodType = BloodTypes.O_MINUS;
    BloodTypes otherExpectedBloodType = BloodTypes.O_PLUS;
    List<BloodTypes> bloodTypes = Arrays.asList(expectedBloodType, otherExpectedBloodType);
    Bed bed = aBed().withBloodTypes(bloodTypes).build();

    BedResponse bedResponse = bedMapper.toResponseWithoutNumber(bed, 0);

    assertEquals(2, bedResponse.getBloodTypes().size());
    assertTrue(
        bedResponse.getBloodTypes().stream()
            .anyMatch(bloodType -> bloodType.equals(expectedBloodType.toString())));
    assertTrue(
        bedResponse.getBloodTypes().stream()
            .anyMatch(bloodType -> bloodType.equals(otherExpectedBloodType.toString())));
  }

  @Test
  public void toResponseWithoutNumber_shouldMapCapacity() {
    int expectedCapacity = 100;
    Bed bed = aBed().withCapacity(expectedCapacity).build();

    BedResponse bedResponse = bedMapper.toResponseWithoutNumber(bed, 0);

    assertEquals(expectedCapacity, bedResponse.getCapacity());
  }

  @Test
  public void toResponseWithoutNumber_shouldMapPricesPerNights() {
    Map<Packages, Price> pricesPerNight =
        Collections.singletonMap(createPackageName(), createPricePerNight());
    Bed bed = aBed().withPricesPerNights(pricesPerNight).build();
    PackageResponse expectedPackageResponse = mock(PackageResponse.class);
    List<PackageResponse> expectedPackageResponses =
        Collections.singletonList(expectedPackageResponse);
    when(packageMapper.toResponses(pricesPerNight)).thenReturn(expectedPackageResponses);

    BedResponse bedResponse = bedMapper.toResponseWithoutNumber(bed, 0);

    assertEquals(expectedPackageResponses, bedResponse.getPackages());
  }

  @Test
  public void toResponseWithoutNumber_shouldMapStars() {
    int expectedStars = 3;
    Bed bed = aBed().build();

    BedResponse bedResponse = bedMapper.toResponseWithoutNumber(bed, expectedStars);

    assertEquals(expectedStars, bedResponse.getStars());
  }

  @Test
  public void toResponseWithNumber_shouldMapBedNumber() {
    UUID expectedBedNumber = createBedNumber();
    Bed bed = aBed().withBedNumber(expectedBedNumber).build();

    BedResponse bedResponse = bedMapper.toResponseWithNumber(bed, 0);

    assertEquals(expectedBedNumber, bedResponse.getBedNumber());
  }
}
