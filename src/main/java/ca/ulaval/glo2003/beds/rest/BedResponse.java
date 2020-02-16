package ca.ulaval.glo2003.beds.rest;

import java.util.List;
import java.util.UUID;

public class BedResponse {

  private UUID bedNumber;
  private String zipCode;
  private String bedType;
  private String cleaningFrequency;
  private List<String> bloodTypes;
  private int capacity;
  private List<PackageResponse> packages;
  private int stars;
}
