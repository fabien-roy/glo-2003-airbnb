package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.helpers.BedBuilder.aBed;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BedTest {

  @Test
  public void matches_withSameBedTypes_shouldReturnTrue() {
    BedTypes bedType = BedTypes.LATEX;
    Bed bed = aBed().withBedType(bedType).build();
    Bed otherBed = aBed().withBedType(bedType).build();

    boolean matches = bed.matches(otherBed);

    assertTrue(matches);
  }

  @Test
  public void matches_withDifferentBedTypes_shouldReturnFalse() {
    Bed bed = aBed().withBedType(BedTypes.LATEX).build();
    Bed otherBed = aBed().withBedType(BedTypes.MEMORY_FOAM).build();

    boolean matches = bed.matches(otherBed);

    assertFalse(matches);
  }
}
