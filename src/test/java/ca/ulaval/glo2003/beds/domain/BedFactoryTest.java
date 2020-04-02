package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createLocation;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.locations.domain.Location;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BedFactoryTest {

  private static BedFactory bedFactory;

  private Bed bed = aBed().build();
  private Bed otherBed = aBed().build();
  private Location location = createLocation();

  @BeforeAll
  public static void setUpFactory() {
    bedFactory = new BedFactory();
  }

  @Test
  public void create_shouldSetBedNumber() {
    bed = bedFactory.create(bed, location);

    assertNotNull(bed.getNumber());
  }

  @Test
  public void create_shouldSetZipCode() {
    bed = bedFactory.create(bed, location);

    assertEquals(location, bed.getLocation());
  }

  @Test
  public void create_shouldSetDifferentBedNumbers() {
    bed = bedFactory.create(bed, location);
    otherBed = bedFactory.create(otherBed, location);

    assertNotEquals(bed.getNumber(), otherBed.getNumber());
  }
}
