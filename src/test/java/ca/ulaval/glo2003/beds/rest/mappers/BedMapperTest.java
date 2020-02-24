package ca.ulaval.glo2003.beds.rest.mappers;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createBedNumber;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createZipCode;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BedMapperTest {

  private BedMapper bedMapper;

  @BeforeEach
  public void setUpMapper() {
    bedMapper = new BedMapper();
  }

  // TODO : Complete BedMapper.fromRequest

  @Test
  public void fromRequest_withSingleBloodType_shouldReturnBedWithSingleBloodType() {
    /*
    BloodTypes expectedBloodType = BloodTypes.O_MINUS;
    List<String> bloodTypes = Collections.singletonList(expectedBloodType.toString());
    BedRequest bedRequest = mock(BedRequest.class);
    when(bedRequest.getBloodTypes()).thenReturn(bloodTypes);

    Bed bed = bedMapper.fromRequest(bedRequest);

    assertEquals(1, bed.getBloodTypes().size());
    assertEquals(expectedBloodType, bed.getBloodTypes().get(0));
    */
  }

  @Test
  public void fromRequest_withMultipleBloodTypes_shouldReturnBedWithMultipleBloodTypes() {
    /*
    BloodTypes expectedBloodType = BloodTypes.O_MINUS;
    BloodTypes otherExpectedBloodType = BloodTypes.O_PLUS;
    List<String> bloodTypes =
        Arrays.asList(expectedBloodType.toString(), otherExpectedBloodType.toString());
    BedRequest bedRequest = mock(BedRequest.class);
    when(bedRequest.getBloodTypes()).thenReturn(bloodTypes);

    Bed bed = bedMapper.fromRequest(bedRequest);

    assertEquals(2, bed.getBloodTypes().size());
    assertTrue(bed.getBloodTypes().contains(expectedBloodType));
    assertTrue(bed.getBloodTypes().contains(otherExpectedBloodType));
    */
  }

  @Test
  public void fromRequest_withInvalidBloodType_shouldThrowInvalidBloodTypeException() {
    String invalidBloodType = "invalidBloodType";
    List<String> bloodTypes = Collections.singletonList(invalidBloodType);
    BedRequest bedRequest = mock(BedRequest.class);
    when(bedRequest.getBloodTypes()).thenReturn(bloodTypes);

    // assertThrows(InvalidBloodTypeException.class, () -> bedMapper.fromRequest(bedRequest));
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
    // TODO
  }

  @Test
  public void toResponse_withMultiplePackages_shouldMapPackages() {
    // TODO
  }

  @Test
  public void toResponse_shouldMapStars() {
    int expectedStars = 3;
    Bed bed = aBed().build();

    BedResponse bedResponse = bedMapper.toResponse(bed, expectedStars);

    assertEquals(expectedStars, bedResponse.getStars());
  }
}
