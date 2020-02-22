package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.domain.Package;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidMinimalCapacityException;
import java.util.*;
import java.util.stream.Collectors;

public class BedMapper {

  public static final String BED_TYPE_PARAM = "bedType";
  public static final String CLEANING_FREQUENCY_PARAM = "cleaningFrequency";
  public static final String BLOOD_TYPES_PARAM = "bloodTypes";
  public static final String CAPACITY_PARAM = "capacity";
  public static final String PACKAGE_NAME_PARAM = "packages";

  public Bed fromRequest(BedRequest request) {
    BedTypes bedType = BedTypes.get(request.getBedType());

    // TODO : Add other values in BedMapper.fromRequest
    return new Bed(bedType, null, new ArrayList<>(), 0, new ArrayList<>());
  }

  public Bed fromRequestParams(Map<String, String> params) {
    BedTypes bedType = null;
    CleaningFrequencies cleaningFrequency = null;
    List<BloodTypes> bloodTypes = null;
    int capacity = 0;
    List<Package> packages = null;

    if (params.get(BED_TYPE_PARAM) != null) {
      bedType = BedTypes.get(params.get(BED_TYPE_PARAM));
    }

    if (params.get(CLEANING_FREQUENCY_PARAM) != null) {
      cleaningFrequency = CleaningFrequencies.get(params.get(CLEANING_FREQUENCY_PARAM));
    }

    if (params.get(BLOOD_TYPES_PARAM) != null) {
      bloodTypes = parseBloodTypeString(params.get(BLOOD_TYPES_PARAM));
    }

    if (params.get(CAPACITY_PARAM) != null) {
      capacity = parseCapacity(params.get(CAPACITY_PARAM));
    }

    if (params.get(PACKAGE_NAME_PARAM) != null) {
      String packageName = params.get(PACKAGE_NAME_PARAM);
      packages = Collections.singletonList(new Package(PackageNames.get(packageName)));
    }

    return new Bed(bedType, cleaningFrequency, bloodTypes, capacity, packages);
  }

  public BedResponse toResponse(Bed bed) {
    // TODO
    return new BedResponse();
  }

  private List<BloodTypes> parseBloodTypeString(String bloodTypesInParams) {
    List<String> bloodTypeStrings = Arrays.asList(bloodTypesInParams.split(","));
    return bloodTypeStrings.stream().map(BloodTypes::get).collect(Collectors.toList());
  }

  private int parseCapacity(String capacity) {
    int parsedCapacity;

    try {
      parsedCapacity = Integer.parseInt(capacity);
    } catch (NumberFormatException e) {
      throw new InvalidMinimalCapacityException();
    }

    if (parsedCapacity < 0) {
      throw new InvalidMinimalCapacityException();
    }

    return parsedCapacity;
  }
}
