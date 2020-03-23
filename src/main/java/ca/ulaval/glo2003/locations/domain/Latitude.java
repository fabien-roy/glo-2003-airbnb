package ca.ulaval.glo2003.locations.domain;

public class Latitude {

  private static final double KM_PER_DEGREE = 110.574;

  private double value;

  public Latitude(double value) {
    this.value = value;
  }

  public double getValue() {
    return value;
  }

  public double toKm() {
    return value * KM_PER_DEGREE;
  }
}
