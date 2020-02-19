package ca.ulaval.glo2003.beds.rest.mappers;

import static ca.ulaval.glo2003.beds.rest.mappers.BedMapper.BED_TYPE_PARAM;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedTypes;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidBedTypeException;
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
