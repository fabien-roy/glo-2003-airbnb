package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.exceptions.InvalidCapacityException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BedMatcherMapper {

  public static final String BED_TYPE_PARAM = "bedType";
  public static final String CLEANING_FREQUENCY_PARAM = "cleaningFreq";
  public static final String BLOOD_TYPES_PARAM = "bloodTypes";
  public static final String MIN_CAPACITY_PARAM = "minCapacity";
  public static final String PACKAGE_NAME_PARAM = "packages";

  public BedMatcher fromRequestParams(Map<String, String[]> params) {
    BedTypes bedType = null;
    CleaningFrequencies cleaningFrequency = null;
    List<BloodTypes> bloodTypes = null;
    int capacity = 0;
    Packages packageName = null;

    if (params.get(BED_TYPE_PARAM) != null) {
      bedType = BedTypes.get(params.get(BED_TYPE_PARAM)[0]);
    }

    if (params.get(CLEANING_FREQUENCY_PARAM) != null) {
      cleaningFrequency = CleaningFrequencies.get(params.get(CLEANING_FREQUENCY_PARAM)[0]);
    }

    if (params.get(BLOOD_TYPES_PARAM) != null) {
      bloodTypes = parseBloodTypes(params.get(BLOOD_TYPES_PARAM));
    }

    if (params.get(MIN_CAPACITY_PARAM) != null) {
      capacity = parseCapacity(params.get(MIN_CAPACITY_PARAM)[0]);
    }

    if (params.get(PACKAGE_NAME_PARAM) != null) {
      packageName = Packages.get(params.get(PACKAGE_NAME_PARAM)[0]);
    }

    return new BedMatcher(bedType, cleaningFrequency, bloodTypes, capacity, packageName);
  }

  private List<BloodTypes> parseBloodTypes(String[] bloodTypes) {
    return Arrays.stream(bloodTypes).map(BloodTypes::get).collect(Collectors.toList());
  }

  private int parseCapacity(String capacity) {
    int parsedCapacity;

    try {
      parsedCapacity = Integer.parseInt(capacity);
    } catch (NumberFormatException e) {
      throw new InvalidCapacityException();
    }

    if (parsedCapacity < 0) {
      throw new InvalidCapacityException();
    }

    return parsedCapacity;
  }
}
