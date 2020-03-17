package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidCapacityException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
  public static final String ORIGIN_PARAM = "origin";
  public static final String MAX_DISTANCE_PARAM = "maxDistance";
  public static final String ARRIVAL_DATE_PARAM = "arrivalDate";
  public static final String NUMBER_OF_NIGHTS_PARAM = "numberOfNights";
  public static final String LODGING_MODE_PARAM = "lodgingMode";

  public BedMatcher fromRequestParams(Map<String, String[]> params) {
    BedTypes bedType = null;
    CleaningFrequencies cleaningFrequency = null;
    List<BloodTypes> bloodTypes = null;
    int capacity = 0;
    Packages packageName = null;
    LocalDate arrivalDate = LocalDate.now();
    int numberOfNights = 3;
    LodgingModes lodgingModes = null;

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

    if (params.get(ARRIVAL_DATE_PARAM) != null) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      arrivalDate = LocalDate.parse(params.get(ARRIVAL_DATE_PARAM)[0], formatter);
    }

    if (params.get(NUMBER_OF_NIGHTS_PARAM) != null) {
      numberOfNights = parseCapacity(params.get(NUMBER_OF_NIGHTS_PARAM)[0]);
    }

    if (params.get(LODGING_MODE_PARAM) != null) {
      lodgingModes = LodgingModes.get(params.get(LODGING_MODE_PARAM)[0]);
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
