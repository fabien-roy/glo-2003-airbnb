package ca.ulaval.glo2003.reports.domain;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.time.domain.TimeDate;

public class ReportEventFactory {

  public ReportEvent create(ReportEventTypes type, Bed bed, Booking booking) {
    return new ReportEvent(
        type,
        TimeDate.now(),
        bed.getLodgingMode().getName(),
        booking.getPackage(),
        booking.getPrice().getServiceFees());
  }
}
