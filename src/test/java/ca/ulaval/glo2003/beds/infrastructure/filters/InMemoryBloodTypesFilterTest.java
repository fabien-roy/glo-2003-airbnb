package ca.ulaval.glo2003.beds.infrastructure.filters;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BloodTypes;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryBloodTypesFilterTest {

  private static InMemoryBedFilter filter;
  private static BloodTypes bloodType = BloodTypes.O_MINUS;
  private static BloodTypes otherBloodType = BloodTypes.AB_MINUS;
  private static BloodTypes anotherBloodType = BloodTypes.B_PLUS;

  private Bed bedWithBloodTypes;
  private List<Bed> beds;

  @BeforeAll
  public static void setUpFilter() {
    filter = new InMemoryBloodTypesFilter(Arrays.asList(bloodType, otherBloodType));
  }

  @BeforeEach
  public void setUpMocks() {
    bedWithBloodTypes = aBed().withBloodTypes(Arrays.asList(bloodType, otherBloodType)).build();
    Bed otherBed = aBed().withBloodTypes(Arrays.asList(otherBloodType, anotherBloodType)).build();
    beds = Arrays.asList(bedWithBloodTypes, otherBed);
  }

  @Test
  public void filter_shouldFilterBloodTypes() {
    List<Bed> filteredBeds = filter.filter(beds);

    assertEquals(1, filteredBeds.size());
    assertSame(bedWithBloodTypes, filteredBeds.get(0));
  }
}
