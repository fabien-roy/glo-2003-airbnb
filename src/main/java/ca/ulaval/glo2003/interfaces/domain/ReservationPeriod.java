package ca.ulaval.glo2003.interfaces.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReservationPeriod {

  private ReservationDate start;
  private ReservationDate end;

  public static ReservationPeriod ofYear(int year) {
    return new ReservationPeriod(
        ReservationDate.firstDayOfYear(year), ReservationDate.lastDayOfYear(year));
  }

  public ReservationPeriod(ReservationDate start, ReservationDate end) {
    this.start = start;
    this.end = end;
  }

  public ReservationDate getStart() {
    return start;
  }

  public ReservationDate getEnd() {
    return end;
  }

  public boolean isOverlapping(ReservationPeriod other) {
    return !(start.isAfter(other.getEnd()) || end.isBefore(other.getStart()));
  }

  public boolean contains(ReservationDate other) {
    return isOverlapping(other.toPeriod());
  }

  public List<ReservationDate> getDates() {
    List<ReservationDate> dates = new ArrayList<>();

    ReservationDate date = start;
    do {
      dates.add(date);
      date = date.plusDays(1);
    } while (date.isBefore(end));

    return dates;
  }

  public List<Integer> getYears() {
    Set<Integer> years = new HashSet<>();

    getDates().forEach(date -> years.add(date.getYear()));

    return new ArrayList<>(years);
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    ReservationPeriod reservationPeriod = (ReservationPeriod) object;

    return start.equals(reservationPeriod.getStart()) && end.equals(reservationPeriod.getEnd());
  }

  @Override
  public int hashCode() {
    return start.hashCode() + end.hashCode();
  }
}
