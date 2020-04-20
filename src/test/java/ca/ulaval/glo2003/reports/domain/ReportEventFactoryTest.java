package ca.ulaval.glo2003.reports.domain;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingBuilder.aBooking;
import static ca.ulaval.glo2003.reports.domain.helpers.ReportEventObjectMother.createEventType;
import static ca.ulaval.glo2003.transactions.domain.helpers.PriceObjectMother.createPrice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.LodgingModes;
import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.time.domain.TimeDate;
import ca.ulaval.glo2003.transactions.domain.Price;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class ReportEventFactoryTest {

  private static ReportEventFactory factory;

  private static ReportEvent event;

  private static ReportEventTypes type = createEventType();
  private static Bed bed;
  private static Booking booking;
  private static Price price = mock(Price.class);
  private static Price incomes = createPrice();

  @BeforeAll
  public static void setUpFactory() {
    factory = new ReportEventFactory();

    bed = aBed().build();
    booking = aBooking().build();
    when(price.getServiceFees()).thenReturn(incomes);
  }

  @ParameterizedTest
  @EnumSource(ReportEventTypes.class)
  public void create_shouldSetType(ReportEventTypes type) {
    event = factory.create(type, bed, booking);

    assertEquals(type, event.getType());
  }

  @Test
  public void create_shouldSetDateToNow() {
    TimeDate now = TimeDate.now();

    event = factory.create(type, bed, booking);

    assertEquals(now, event.getDate());
  }

  @ParameterizedTest
  @EnumSource(LodgingModes.class)
  public void create_shouldSetLodgingMode(LodgingModes lodgingMode) {
    bed = aBed().withLodgingModeType(lodgingMode).build();

    event = factory.create(type, bed, booking);

    assertEquals(lodgingMode, event.getLodgingMode());
  }

  @ParameterizedTest
  @EnumSource(Packages.class)
  public void create_shouldSetPackage(Packages packageName) {
    booking = aBooking().withPackage(packageName).build();

    event = factory.create(type, bed, booking);

    assertEquals(packageName, event.getPackage());
  }

  @Test
  public void create_shouldSetIncomes() {
    booking = aBooking().withPrice(price).build();

    event = factory.create(type, bed, booking);

    assertSame(incomes, event.getIncomes());
  }
}
