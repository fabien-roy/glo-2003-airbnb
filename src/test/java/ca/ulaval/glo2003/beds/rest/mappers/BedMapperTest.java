package ca.ulaval.glo2003.beds.rest.mappers;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createBedNumber;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createZipCode;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageBuilder.aPackage;
import static ca.ulaval.glo2003.beds.rest.helpers.BedRequestBuilder.aBedRequest;
import static ca.ulaval.glo2003.beds.rest.helpers.PackageRequestBuilder.aPackageRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.domain.Package;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
import ca.ulaval.glo2003.beds.rest.exceptions.*;
import ca.ulaval.glo2003.interfaces.rest.exceptions.InvalidFormatException;
import java.util.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BedMapperTest {

  private BedMapper bedMapper;
  private PackageMapper packageMapper;

  @BeforeEach
  public void setUpMapper() {
    packageMapper = mock(PackageMapper.class);
    bedMapper = new BedMapper(packageMapper);
  }

  @Test
  public void fromRequest_withBedType_shouldReturnBedWithBedType() {
    BedTypes expectedBedType = BedTypes.LATEX;
    BedRequest bedRequest = aBedRequest().withBedType(expectedBedType.toString()).build();

    Bed bed = bedMapper.fromRequest(bedRequest);

    assertEquals(expectedBedType, bed.getBedType());
  }

  @Test
  public void fromRequest_withoutBedType_shouldThrowInvalidFormatException() {
    BedRequest bedRequest = aBedRequest().withBedType(null).build();

    assertThrows(InvalidFormatException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void fromRequest_withInvalidBedType_shouldThrowInvalidBedTypeException() {
    String invalidBedType = "invalidBedType";
    BedRequest bedRequest = aBedRequest().withBedType(invalidBedType).build();

    assertThrows(InvalidBedTypeException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void fromRequest_withCleaningFrequency_shouldReturnBedWithCleaningFrequency() {
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
  public void fromRequest_withoutCleaningFrequency_shouldThrowInvalidFormat() {
    BedRequest bedRequest = aBedRequest().withCleaningFrequency(null).build();

    assertThrows(InvalidFormatException.class, () -> bedMapper.fromRequest(bedRequest));
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
  public void fromRequest_withoutBloodTypes_shouldThrowInvalidFormatException() {
    List<String> bloodTypes = Collections.singletonList(null);
    BedRequest bedRequest = aBedRequest().withBloodTypes(bloodTypes).build();

    assertThrows(InvalidFormatException.class, () -> bedMapper.fromRequest(bedRequest));
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
  public void fromRequest_withCapacity_shouldReturnBedWithCapacity() {
    int expectedCapacity = 1000;
    BedRequest bedRequest =
        aBedRequest()
            .withBedType(BedTypes.SPRINGS.toString())
            .withCapacity(expectedCapacity)
            .build();

    Bed bed = bedMapper.fromRequest(bedRequest);

    assertEquals(expectedCapacity, bed.getCapacity());
  }

  @Test
  public void fromRequest_withInvalidCapacity_shouldThrowInvalidCapacityException() {
    int capacity = -100;
    BedRequest bedRequest =
        aBedRequest().withBedType(BedTypes.LATEX.toString()).withCapacity(capacity).build();

    assertThrows(InvalidCapacityException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void fromRequest_withSinglePackage_shouldMapPackage() {
    PackageRequest packageRequest = aPackageRequest().build();
    List<PackageRequest> packageRequests = Collections.singletonList(packageRequest);
    BedRequest bedRequest = aBedRequest().withPackages(packageRequests).build();
    Package expectedPackage = mock(Package.class);
    when(packageMapper.fromRequest(packageRequest)).thenReturn(expectedPackage);

    Bed bed = bedMapper.fromRequest(bedRequest);

    assertEquals(1, bed.getPackages().size());
    assertEquals(expectedPackage, bed.getPackages().get(0));
  }

  @Test
  public void fromRequest_withMultiplePackages_shouldMapPackages() {
    // TODO
  }

  @Test
  public void fromRequest_withoutPackage_throwInvalidPackageException() {
    // TODO
  }

  @Test
  public void toResponse_shouldMapBedNumber() {
    UUID expectedBedNumber = createBedNumber();
    Bed bed = aBed().withBedNumber(expectedBedNumber).build();

    BedResponse bedResponse = bedMapper.toResponse(bed, 0);

    assertEquals(expectedBedNumber, bedResponse.getBedNumber());
  }

  @Test
  public void toResponse_shouldMapZipCode() {
    String expectedZipCode = createZipCode();
    Bed bed = aBed().withZipCode(expectedZipCode).build();

    BedResponse bedResponse = bedMapper.toResponse(bed, 0);

    assertEquals(expectedZipCode, bedResponse.getZipCode());
  }

  @Test
  public void toResponse_shouldMapBedType() {
    BedTypes expectedBedType = BedTypes.LATEX;
    Bed bed = aBed().withBedType(expectedBedType).build();

    BedResponse bedResponse = bedMapper.toResponse(bed, 0);

    assertEquals(expectedBedType.toString(), bedResponse.getBedType());
  }

  @Test
  public void toResponse_shouldMapCleaningFrequency() {
    CleaningFrequencies expectedCleaningFrequency = CleaningFrequencies.ANNUAL;
    Bed bed = aBed().withCleaningFrequency(expectedCleaningFrequency).build();

    BedResponse bedResponse = bedMapper.toResponse(bed, 0);

    assertEquals(expectedCleaningFrequency.toString(), bedResponse.getCleaningFrequency());
  }

  @Test
  public void toResponse_withSingleBloodType_shouldMapBloodType() {
    BloodTypes expectedBloodType = BloodTypes.O_MINUS;
    List<BloodTypes> bloodTypes = Collections.singletonList(expectedBloodType);
    Bed bed = aBed().withBloodTypes(bloodTypes).build();

    BedResponse bedResponse = bedMapper.toResponse(bed, 0);

    assertEquals(1, bedResponse.getBloodTypes().size());
    assertEquals(expectedBloodType.toString(), bedResponse.getBloodTypes().get(0));
  }

  @Test
  public void toResponse_withMultipleBloodTypes_shouldMapBloodTypes() {
    BloodTypes expectedBloodType = BloodTypes.O_MINUS;
    BloodTypes otherExpectedBloodType = BloodTypes.O_PLUS;
    List<BloodTypes> bloodTypes = Arrays.asList(expectedBloodType, otherExpectedBloodType);
    Bed bed = aBed().withBloodTypes(bloodTypes).build();

    BedResponse bedResponse = bedMapper.toResponse(bed, 0);

    assertEquals(2, bedResponse.getBloodTypes().size());
    assertTrue(
        bedResponse.getBloodTypes().stream()
            .anyMatch(bloodType -> bloodType.equals(expectedBloodType.toString())));
    assertTrue(
        bedResponse.getBloodTypes().stream()
            .anyMatch(bloodType -> bloodType.equals(otherExpectedBloodType.toString())));
  }

  @Test
  public void toResponse_shouldMapCapacity() {
    int expectedCapacity = 100;
    Bed bed = aBed().withCapacity(expectedCapacity).build();

    BedResponse bedResponse = bedMapper.toResponse(bed, 0);

    assertEquals(expectedCapacity, bedResponse.getCapacity());
  }

  @Test
  public void toResponse_withSinglePackage_shouldMapPackage() {
    Package bedPackage = aPackage().build();
    List<Package> packages = Collections.singletonList(bedPackage);
    Bed bed = aBed().withPackages(packages).build();
    PackageResponse expectedPackageResponse = mock(PackageResponse.class);
    when(packageMapper.toResponse(bedPackage)).thenReturn(expectedPackageResponse);

    BedResponse bedResponse = bedMapper.toResponse(bed, 0);

    assertEquals(1, bedResponse.getPackages().size());
    assertEquals(expectedPackageResponse, bedResponse.getPackages().get(0));
  }

  @Test
  public void toResponse_withMultiplePackages_shouldMapPackages() {
    Package bedPackage = aPackage().build();
    Package otherBedPackage = aPackage().build();
    List<Package> packages = Arrays.asList(bedPackage, otherBedPackage);
    Bed bed = aBed().withPackages(packages).build();
    PackageResponse expectedPackageResponse = mock(PackageResponse.class);
    PackageResponse otherExpectedPackageResponse = mock(PackageResponse.class);
    when(packageMapper.toResponse(bedPackage)).thenReturn(expectedPackageResponse);
    when(packageMapper.toResponse(otherBedPackage)).thenReturn(otherExpectedPackageResponse);

    BedResponse bedResponse = bedMapper.toResponse(bed, 0);

    assertEquals(2, bedResponse.getPackages().size());
    assertTrue(bedResponse.getPackages().contains(expectedPackageResponse));
    assertTrue(bedResponse.getPackages().contains(otherExpectedPackageResponse));
  }

  @Test
  public void toResponse_shouldMapStars() {
    int expectedStars = 3;
    Bed bed = aBed().build();

    BedResponse bedResponse = bedMapper.toResponse(bed, expectedStars);

    assertEquals(expectedStars, bedResponse.getStars());
  }
}
