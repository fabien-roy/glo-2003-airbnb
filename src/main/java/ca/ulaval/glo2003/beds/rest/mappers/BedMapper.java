package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidBloodTypesException;
import ca.ulaval.glo2003.interfaces.rest.exceptions.InvalidFormatException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;


public class BedMapper {

  public static final String OWNER_PUBLIC_KEY_PATTERN = "([A-Z]|[0-9]){64}";
  public static final String ZIP_CODE_PATTERN = "([0-9]){5}"; // TODO : Will be useful.

  public Bed fromRequest(BedRequest request) {
    validateFormat(request);

    BedTypes bedType = BedTypes.get(request.getBedType());
    CleaningFrequencies cleaningFrequencies =
        CleaningFrequencies.get(request.getCleaningFrequency());
    List<BloodTypes> bloodTypes = parseBloodTypes(request.getBloodTypes());
    int capacity = request.getCapacity();

    return new Bed(bedType, cleaningFrequencies, bloodTypes, capacity, new ArrayList<>());
  }

  public BedResponse toResponse(Bed bed, int stars) {
    // TODO
    UUID bedNumber = null;
    String zipCode = null;
    String bedType = null;
    String cleaningFrequency = null;
    List<String> bloodTypes = null;
    int capacity = 0;
    List<PackageResponse> packages = null;

    return new BedResponse(
        bedNumber, zipCode, bedType, cleaningFrequency, bloodTypes, capacity, packages, stars);
  }

  private void validateFormat(BedRequest request) {
    if (request.getBloodTypes().isEmpty()) throw new InvalidBloodTypesException();

    if (request.getBedType() == null
        || request.getCleaningFrequency() == null
        || request.getBloodTypes().get(0) == null) {
      throw new InvalidFormatException();
    }
  }

  private List<BloodTypes> parseBloodTypes(List<String> bloodTypes) {
    return bloodTypes.stream().map(BloodTypes::get).collect(Collectors.toList());
  }
}
