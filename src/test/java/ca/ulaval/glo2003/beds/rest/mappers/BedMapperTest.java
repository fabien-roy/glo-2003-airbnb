package ca.ulaval.glo2003.beds.rest.mappers;

import static ca.ulaval.glo2003.beds.rest.mappers.BedMapper.BED_TYPE_PARAM;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedTypes;
import ca.ulaval.glo2003.beds.domain.BloodTypes;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidBedTypeException;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidBloodTypeException;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BedMapperTest {

  private BedMapper bedMapper;

  @BeforeEach
  public void setUpMapper() {
    bedMapper = new BedMapper();
  }

  @Test
  public void fromRequest_withSingleBloodType_shouldReturnBedWithSingleBloodType() {
    BloodTypes expectedBloodType = BloodTypes.O_MINUS;
    List<String> bloodTypes = Collections.singletonList(expectedBloodType.toString());
    BedRequest bedRequest = mock(BedRequest.class);
    when(bedRequest.getBloodTypes()).thenReturn(bloodTypes);

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
    BedRequest bedRequest = mock(BedRequest.class);
    when(bedRequest.getBloodTypes()).thenReturn(bloodTypes);

    Bed bed = bedMapper.fromRequest(bedRequest);

    assertEquals(2, bed.getBloodTypes().size());
    assertTrue(bed.getBloodTypes().contains(expectedBloodType));
    assertTrue(bed.getBloodTypes().contains(otherExpectedBloodType));
  }

  @Test
  public void fromRequest_withInvalidBloodType_shouldThrowInvalidBloodTypeException() {
    String invalidBloodType = "invalidBloodType";
    List<String> bloodTypes = Collections.singletonList(invalidBloodType);
    BedRequest bedRequest = mock(BedRequest.class);
    when(bedRequest.getBloodTypes()).thenReturn(bloodTypes);

    assertThrows(InvalidBloodTypeException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void fromRequestParams_withNoParams_shouldReturnBedWithNullAttributes() {
    Map<String, String> params = new HashMap<>();

    Bed bed = bedMapper.fromRequestParams(params);

    assertNull(bed.getBedType());
    assertNull(bed.getCleaningFrequency());
    assertNull(bed.getBloodTypes());
    assertEquals(0, bed.getCapacity());
    assertNull(bed.getPackages());
  }

  @Test
  public void fromRequestParams_withBedType_shouldReturnBedWithBedType() {
    BedTypes expectedBedType = BedTypes.LATEX;
    Map<String, String> params = new HashMap<>();
    params.put(BED_TYPE_PARAM, expectedBedType.toString());

    Bed bed = bedMapper.fromRequestParams(params);

    assertEquals(expectedBedType, bed.getBedType());
  }

  @Test
  public void fromRequestParams_withInvalidBedType_shouldThrowInvalidBedTypeException() {
    Map<String, String> params = new HashMap<>();
    params.put(BED_TYPE_PARAM, "invalidBedType");

    assertThrows(InvalidBedTypeException.class, () -> bedMapper.fromRequestParams(params));
  }
}
