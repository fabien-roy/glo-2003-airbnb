package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.domain.Package;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidBloodTypesException;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidCapacityException;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidZipCodeException;
import ca.ulaval.glo2003.interfaces.rest.exceptions.InvalidFormatException;
import java.util.List;
import java.util.stream.Collectors;

public class BedMapper {

  public static final String OWNER_PUBLIC_KEY_PATTERN = "([A-Z]|[0-9]){64}";
  public static final String ZIP_CODE_PATTERN = "([0-9]){5}"; // TODO : Will be useful.

  private final PackageMapper packageMapper;

  public BedMapper(PackageMapper packageMapper) {
    this.packageMapper = packageMapper;
  }

  public Bed fromRequest(BedRequest request) {
    validateRequestFormat(request);

    BedTypes bedType = BedTypes.get(request.getBedType());
    CleaningFrequencies cleaningFrequencies =
        CleaningFrequencies.get(request.getCleaningFrequency());
    List<BloodTypes> bloodTypes =
        request.getBloodTypes().stream().map(BloodTypes::get).collect(Collectors.toList());
    int capacity = request.getCapacity();
    List<Package> packages =
        request.getPackages().stream().map(packageMapper::fromRequest).collect(Collectors.toList());
    String ownerPublicKey = request.getOwnerPublicKey();
    String zipCode = request.getZipCode();

    validateZipCode(zipCode);

    return new Bed(
        ownerPublicKey, zipCode, bedType, cleaningFrequencies, bloodTypes, capacity, packages);
  }

  // TODO : toResponse should only set bedNumber for getAll, not for get
  public BedResponse toResponse(Bed bed, int stars) {
    List<String> bloodTypes =
        bed.getBloodTypes().stream().map(BloodTypes::toString).collect(Collectors.toList());

    List<PackageResponse> packageResponses =
        bed.getPackages().stream().map(packageMapper::toResponse).collect(Collectors.toList());

    return new BedResponse(
        bed.getNumber(),
        bed.getZipCode(),
        bed.getBedType().toString(),
        bed.getCleaningFrequency().toString(),
        bloodTypes,
        bed.getCapacity(),
        packageResponses,
        stars);
  }

  private void validateRequestFormat(BedRequest request) {
    if (request.getBloodTypes().isEmpty()) {
      throw new InvalidBloodTypesException();
    }

    if (request.getBedType() == null
        || request.getCleaningFrequency() == null
        || request.getBloodTypes().get(0) == null
        || request.getZipCode() == null) {
      throw new InvalidFormatException();
    }

    if (request.getCapacity() < 0) {
      throw new InvalidCapacityException();
    }
  }

  private void validateZipCode(String zipCode) {
    if (!zipCode.matches(ZIP_CODE_PATTERN)) {
      throw new InvalidZipCodeException();
    }
    ;
  }
}
