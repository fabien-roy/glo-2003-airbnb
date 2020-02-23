package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.helpers.BedBuilder.aBed;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BedFactoryTest {

  BedFactory bedFactory;
  BedStarsCalculator starsCalculator;

  @BeforeEach
  public void setUpFactory() {
    starsCalculator = mock(BedStarsCalculator.class);
    bedFactory = new BedFactory(starsCalculator);
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

  @Test
  public void create_shouldSetStars() {
    int expectedStars = 1;
    Bed bed = aBed().build();
    when(starsCalculator.calculateStars(bed)).thenReturn(expectedStars);

    bed = bedFactory.create(bed);

    assertEquals(expectedStars, bed.getStars());
  }
}
