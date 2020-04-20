package ca.ulaval.glo2003.reports.domain;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.time.domain.TimeDate;

// TODO : Test ReportEventFactory
public class ReportEventFactory {

  public ReportEvent create(ReportEventTypes event, Bed bed, Booking booking) {
    return new ReportEvent(event, TimeDate.now(), bed.getLodgingMode().getName(), booking.getPackage(), booking.getPrice().getServiceFees());
  }
}
