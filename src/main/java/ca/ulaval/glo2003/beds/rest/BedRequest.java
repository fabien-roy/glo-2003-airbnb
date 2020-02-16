package ca.ulaval.glo2003.beds.rest;

import java.util.List;

public class BedRequest {

  private String ownerPublicKey;
  private String zipCode;
  private String bedType;
  private String cleaningFrequency;
  private List<String> bloodTypes;
  private int capacity;
  private List<PackageRequest> packages;
}
