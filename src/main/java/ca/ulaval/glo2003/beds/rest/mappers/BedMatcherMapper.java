package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidMinimalCapacityException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BedMatcherMapper {

  public static final String BED_TYPE_PARAM = "bedType";
  public static final String CLEANING_FREQUENCY_PARAM = "cleaningFrequency";
  public static final String BLOOD_TYPES_PARAM = "bloodTypes";
  public static final String CAPACITY_PARAM = "capacity";
  public static final String PACKAGE_NAME_PARAM = "packages";

  public BedMatcher fromRequestParams(Map<String, String> params) {
    BedTypes bedType = null;
    CleaningFrequencies cleaningFrequency = null;
    List<BloodTypes> bloodTypes = null;
    int capacity = 0;
    PackageNames packageName = null;

    if (params.get(BED_TYPE_PARAM) != null) {
      bedType = BedTypes.get(params.get(BED_TYPE_PARAM));
    }

    if (params.get(CLEANING_FREQUENCY_PARAM) != null) {
      cleaningFrequency = CleaningFrequencies.get(params.get(CLEANING_FREQUENCY_PARAM));
    }

    if (params.get(BLOOD_TYPES_PARAM) != null) {
      bloodTypes = parseBloodTypes(params.get(BLOOD_TYPES_PARAM));
    }

    if (params.get(CAPACITY_PARAM) != null) {
      capacity = parseCapacity(params.get(CAPACITY_PARAM));
    }

    if (params.get(PACKAGE_NAME_PARAM) != null) {
      packageName = PackageNames.get(params.get(PACKAGE_NAME_PARAM));
    }

    return new BedMatcher(bedType, cleaningFrequency, bloodTypes, capacity, packageName);
  }

  private List<BloodTypes> parseBloodTypes(String bloodTypes) {
    List<String> parsedBloodTypes = Arrays.asList(bloodTypes.split(","));
    return parsedBloodTypes.stream().map(BloodTypes::get).collect(Collectors.toList());
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
