package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedTypes;
import ca.ulaval.glo2003.beds.domain.BloodTypes;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidBloodTypesException;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidCapacityException;
import ca.ulaval.glo2003.interfaces.rest.exceptions.InvalidFormatException;
import java.util.ArrayList;
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
    validateFormat(request);
    CapacityMapper.validateCapacity(request);

    BedTypes bedType = BedTypes.get(request.getBedType());
    CleaningFrequencies cleaningFrequencies =
        CleaningFrequencies.get(request.getCleaningFrequency());
    List<BloodTypes> bloodTypes = parseBloodTypes(request.getBloodTypes());
    int capacity = request.getCapacity();

    return new Bed(bedType, cleaningFrequencies, bloodTypes, capacity, new ArrayList<>());
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

  private void validateFormat(BedRequest request) {
    if (request.getBloodTypes().isEmpty()) {
      throw new InvalidBloodTypesException();
    }

    if (request.getBedType() == null
        || request.getCleaningFrequency() == null
        || request.getBloodTypes().get(0) == null) {
      throw new InvalidFormatException();
    }

    if (request.getCapacity() < 0) throw new InvalidCapacityException();
  }

  private List<BloodTypes> parseBloodTypes(List<String> bloodTypes) {
    return bloodTypes.stream().map(BloodTypes::get).collect(Collectors.toList());
  }
}
