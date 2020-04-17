package ca.ulaval.glo2003.interfaces.domain;

import java.time.LocalDate;
import java.time.Month;

public class ReservationDate {

  private LocalDate value;

  public static ReservationDate now() {
    return new ReservationDate(LocalDate.now());
  }

  public static ReservationDate firstDayOfYear(int year) {
    return new ReservationDate(LocalDate.of(year, Month.JANUARY, 1));
  }

  public static ReservationDate lastDayOfYear(int year) {
    return new ReservationDate(LocalDate.of(year, Month.DECEMBER, 31));
  }

  public ReservationDate(LocalDate value) {
    this.value = value;
  }

  public int getYear() {
    return value.getYear();
  }

  public ReservationDate minusDays(int days) {
    return new ReservationDate(value.minusDays(days));
  }

  public ReservationDate plusDays(int days) {
    return new ReservationDate(value.plusDays(days));
  }

  public boolean isBefore(ReservationDate other) {
    return value.isBefore(other.toLocalDate());
  }

  public boolean isAfter(ReservationDate other) {
    return value.isAfter(other.toLocalDate());
  }

  public ReservationPeriod periodToDays(int days) {
    return periodTo(plusDays(days));
  }

  private ReservationPeriod periodTo(ReservationDate other) {
    return new ReservationPeriod(this, other);
  }

  public ReservationPeriod toPeriod() {
    return new ReservationPeriod(this, this);
  }

  public ReservationTimestamp toTimestamp() {
    return new ReservationTimestamp(value);
  }

  public LocalDate toLocalDate() {
    return value;
  }

  @Override
  public String toString() {
    return value.toString();
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    ReservationDate reservationDate = (ReservationDate) object;

    return value.equals(reservationDate.toLocalDate());
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }
}
