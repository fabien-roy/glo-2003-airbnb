package ca.ulaval.glo2003.interfaces.domain;

import java.time.*;

public class ReservationTimestamp {

  public static final LocalTime LOCAL_TIME = LocalTime.MAX;
  public static final ZoneOffset ZONE_OFFSET = ZoneOffset.UTC;

  private Instant value;

  public static ReservationTimestamp now() {
    return new ReservationTimestamp(LocalDateTime.now().toInstant(ZONE_OFFSET));
  }

  public ReservationTimestamp(Instant value) {
    this.value = value;
  }

  public ReservationTimestamp(LocalDate localDate) {
    value = localDate.atTime(LOCAL_TIME).toInstant(ZONE_OFFSET);
  }

  public Instant toInstant() {
    return value;
  }

  public LocalDate toLocalDate() {
    return value.atZone(ZONE_OFFSET).toLocalDate();
  }

  public boolean isMaxTime() {
    ReservationTimestamp reservationTimestampAtMaxTime = new ReservationTimestamp(toLocalDate());
    return equals(reservationTimestampAtMaxTime);
  }

  @Override
  public String toString() {
    return value.toString();
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    ReservationTimestamp reservationTimestamp = (ReservationTimestamp) object;

    return value.equals(reservationTimestamp.toInstant());
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }
}
