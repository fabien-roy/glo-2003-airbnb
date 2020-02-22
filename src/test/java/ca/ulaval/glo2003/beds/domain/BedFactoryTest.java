package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.helpers.BedBuilder.aBed;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BedFactoryTest {

  BedFactory bedFactory;

  @BeforeEach
  public void setUpFactory() {
    bedFactory = new BedFactory();
  }

  @Test
  public void create_shouldSetBedNumber() {
    Bed bed = aBed().build();

    bed = bedFactory.create(bed);

    assertNotNull(bed.getNumber());
  }

  @Test
  public void create_shouldSetDifferentBedNumbers() {
    Bed bed = aBed().build();
    Bed otherBed = aBed().build();

    bed = bedFactory.create(bed);
    otherBed = bedFactory.create(otherBed);

    assertNotEquals(bed.getNumber(), otherBed.getNumber());
  }
}
