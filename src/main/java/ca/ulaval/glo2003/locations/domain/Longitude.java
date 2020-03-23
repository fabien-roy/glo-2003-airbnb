package ca.ulaval.glo2003.locations.domain;

public class Longitude {

  private static final double KM_PER_DEGREE = 111.320;

  private double value;

  public Longitude(double value) {
    this.value = value;
  }

  public double getValue() {
    return value;
  }

  public double toKm() {
    return value * KM_PER_DEGREE;
  }
}
