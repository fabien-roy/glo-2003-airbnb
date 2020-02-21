package ca.ulaval.glo2003.beds.rest.mappers;

import static ca.ulaval.glo2003.beds.rest.mappers.BedMapper.*;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedTypes;
import ca.ulaval.glo2003.beds.domain.BloodTypes;
import ca.ulaval.glo2003.beds.domain.CleaningFrequencies;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidBedTypeException;
import java.util.*;

import ca.ulaval.glo2003.beds.rest.exceptions.InvalidBloodTypesException;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidCleaningFrequencyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BedMapperTest {

  private BedMapper bedMapper;

  @BeforeEach
  public void setUpMapper() {
    bedMapper = new BedMapper();
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

  @Test
  public void fromRequestParams_withCleaningFrequency_shouldReturnBedWithCleaningFrequency() {
    CleaningFrequencies expectedCleaningFrequency = CleaningFrequencies.ANNUAL;
    Map<String, String> params = new HashMap<>();
    params.put(CLEANING_FREQUENCY_PARAM, expectedCleaningFrequency.toString());

    Bed bed = bedMapper.fromRequestParams(params);

    assertEquals(expectedCleaningFrequency, bed.getCleaningFrequency());
  }

  @Test
  public void fromRequestParams_withInvalidCleaningFrequency_shouldThrowInvalidCleaningFrequencyException() {
    Map<String, String> params = new HashMap<>();
    params.put(CLEANING_FREQUENCY_PARAM, "invalidCleaningFrequency");

    assertThrows(InvalidCleaningFrequencyException.class, () -> bedMapper.fromRequestParams(params));
  }

  @Test
  public void fromRequestParams_withBloodType_shouldReturnBedWithBloodType() {

    BloodTypes expectedBloodTypes = BloodTypes.O_MINUS;
    Map<String, String> params = new HashMap<>();
    params.put(BLOOD_TYPES_PARAM, expectedBloodTypes.toString());

    Bed bed = bedMapper.fromRequestParams(params);

    assertEquals(1, bed.getBloodTypes().size());
    assertEquals(expectedBloodTypes, bed.getBloodTypes().get(0));
  }

  @Test
  public void fromRequestParams_withInvalidBloodType_shouldThrowInvalidBloodTypeException() {
    Map<String, String> params = new HashMap<>();
    params.put(BLOOD_TYPES_PARAM, "invalidBloodTypes");

    assertThrows(InvalidBloodTypesException.class, () -> bedMapper.fromRequestParams(params));
  }
}
