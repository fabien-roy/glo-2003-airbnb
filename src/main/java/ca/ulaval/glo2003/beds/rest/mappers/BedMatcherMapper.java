package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.bookings.exceptions.InvalidNumberOfNights;
import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.exceptions.InvalidCapacityException;
import ca.ulaval.glo2003.beds.exceptions.InvalidMaxDistanceException;
import ca.ulaval.glo2003.beds.exceptions.MaxDistanceWithoutOriginException;
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
    int minCapacity = 0;
    Packages packageName = null;
    String origin = null;
    int maxDistance = 10;
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
      minCapacity = parseCapacity(params.get(MIN_CAPACITY_PARAM)[0]);
    }

    if (params.get(PACKAGE_NAME_PARAM) != null) {
      packageName = Packages.get(params.get(PACKAGE_NAME_PARAM)[0]);
    }

    if (params.get(ARRIVAL_DATE_PARAM) != null) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      arrivalDate = LocalDate.parse(params.get(ARRIVAL_DATE_PARAM)[0], formatter);
    }

    if (params.get(NUMBER_OF_NIGHTS_PARAM) != null) {
      numberOfNights = parseNumberOfNights(params.get(NUMBER_OF_NIGHTS_PARAM)[0]);
    }

    if (params.get(LODGING_MODE_PARAM) != null) {
      lodgingModes = LodgingModes.get(params.get(LODGING_MODE_PARAM)[0]);
    }

    if (params.get(ORIGIN_PARAM) != null) {
      origin = params.get(ORIGIN_PARAM)[0];
      if (params.get(MAX_DISTANCE_PARAM) != null) {
        maxDistance = parseDistance(params.get(MAX_DISTANCE_PARAM)[0]);
      } else {
        maxDistance = 10;
      }
    } else {
      if (params.get(MAX_DISTANCE_PARAM) != null) {
        throw new MaxDistanceWithoutOriginException();
      }
    }

    return new BedMatcher(
        bedType,
        cleaningFrequency,
        bloodTypes,
        minCapacity,
        packageName,
        arrivalDate,
        numberOfNights,
        lodgingModes,
        origin,
        maxDistance);
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

  private int parseNumberOfNights(String numberOfNights) {
    int parsedNumberOfNights;

    try {
      parsedNumberOfNights = Integer.parseInt(numberOfNights);
    } catch (NumberFormatException e) {
      throw new InvalidNumberOfNights();
    }

    if (parsedNumberOfNights < 0) {
      throw new InvalidNumberOfNights();
    }

    return parsedNumberOfNights;
  }

  private int parseDistance(String distance) {
    int parsedDistance;

    try {
      parsedDistance = Integer.parseInt(distance);
    } catch (NumberFormatException e) {
      throw new InvalidMaxDistanceException();
    }

    if (parsedDistance < 0) {
      throw new InvalidMaxDistanceException();
    }

    return parsedDistance;
  }
}
