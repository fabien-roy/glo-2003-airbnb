package ca.ulaval.glo2003.beds.infrastructure.filters;

import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createColonySize;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createNumberOfNights;
import static ca.ulaval.glo2003.interfaces.domain.helpers.ReservationDateObjectMother.createDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.interfaces.domain.ReservationDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryAvailabilityFilterTest {

  private static InMemoryBedFilter filter;
  private static int minCapacity = createColonySize();
  private static ReservationDate arrivalDate = createDate();
  private static int numberOfNights = createNumberOfNights();

  private Bed bedThatIsAvailable = mock(Bed.class);
  private List<Bed> beds;

  @BeforeAll
  public static void setUpFilter() {
    filter = new InMemoryAvailabilityFilter(minCapacity, arrivalDate, numberOfNights);
  }

  @BeforeEach
  public void setUpMocks() {
    when(bedThatIsAvailable.isAvailable(minCapacity, arrivalDate, numberOfNights)).thenReturn(true);
    Bed otherBed = mock(Bed.class);
    when(otherBed.isAvailable(minCapacity, arrivalDate, numberOfNights)).thenReturn(false);
    beds = Arrays.asList(bedThatIsAvailable, otherBed);
  }

  @Test
  public void filter_shouldFilterAvailability() {
    List<Bed> filteredBeds = filter.filter(beds);

    assertEquals(1, filteredBeds.size());
    assertSame(bedThatIsAvailable, filteredBeds.get(0));
  }
}
