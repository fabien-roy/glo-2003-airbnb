package ca.ulaval.glo2003.beds.rest.mappers;

import static ca.ulaval.glo2003.beds.domain.BedMatcher.UNSET_INT;

import ca.ulaval.glo2003.beds.bookings.domain.BookingDate;
import ca.ulaval.glo2003.beds.bookings.exceptions.InvalidNumberOfNightsException;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingDateMapper;
import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.exceptions.*;
import ca.ulaval.glo2003.interfaces.domain.ZipCode;
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
  public static final String ARRIVAL_DATE_PARAM = "arrivalDate";
  public static final String NUMBER_OF_NIGHTS_PARAM = "numberOfNights";
  public static final String LODGING_MODE_PARAM = "lodgingMode";
  public static final String ORIGIN_PARAM = "origin";
  public static final String MAX_DISTANCE_PARAM = "maxDistance";

  static final BookingDate DEFAULT_ARRIVAL_DATE = new BookingDate();
  static final int DEFAULT_NUMBER_OF_NIGHTS = 3;
  static final int DEFAULT_MAX_DISTANCE = 10;

  private final BookingDateMapper bookingDateMapper;

  public BedMatcherMapper(BookingDateMapper bookingDateMapper) {
    this.bookingDateMapper = bookingDateMapper;
  }

  public BedMatcher fromRequestParams(Map<String, String[]> params) {
    BedMatcher bedMatcher = buildBedMatcher(params);

    applyMinCapacityDependencies(bedMatcher);
    applyOriginDependencies(bedMatcher);

    return bedMatcher;
  }

  private BedMatcher buildBedMatcher(Map<String, String[]> params) {
    BedTypes bedType =
        params.get(BED_TYPE_PARAM) == null ? null : BedTypes.get(params.get(BED_TYPE_PARAM)[0]);

    CleaningFrequencies cleaningFrequency =
        params.get(CLEANING_FREQUENCY_PARAM) == null
            ? null
            : CleaningFrequencies.get(params.get(CLEANING_FREQUENCY_PARAM)[0]);

    List<BloodTypes> bloodTypes =
        params.get(BLOOD_TYPES_PARAM) == null
            ? null
            : parseBloodTypes(params.get(BLOOD_TYPES_PARAM));

    int minCapacity =
        params.get(MIN_CAPACITY_PARAM) == null
            ? UNSET_INT
            : parseCapacity(params.get(MIN_CAPACITY_PARAM)[0]);

    Packages packageName =
        params.get(PACKAGE_NAME_PARAM) == null
            ? null
            : Packages.get(params.get(PACKAGE_NAME_PARAM)[0]);

    BookingDate arrivalDate =
        params.get(ARRIVAL_DATE_PARAM) == null
            ? null
            : bookingDateMapper.fromString(params.get(ARRIVAL_DATE_PARAM)[0]);

    int numberOfNights =
        params.get(NUMBER_OF_NIGHTS_PARAM) == null
            ? UNSET_INT
            : parseNumberOfNights(params.get(NUMBER_OF_NIGHTS_PARAM)[0]);

    LodgingModes lodgingMode =
        params.get(LODGING_MODE_PARAM) == null
            ? null
            : LodgingModes.get(params.get(LODGING_MODE_PARAM)[0]);

    ZipCode origin =
        params.get(ORIGIN_PARAM) == null ? null : new ZipCode(params.get(ORIGIN_PARAM)[0]);

    int maxDistance =
        params.get(MAX_DISTANCE_PARAM) == null
            ? UNSET_INT
            : parseMaxDistance(params.get(MAX_DISTANCE_PARAM)[0]);

    return new BedMatcher(
        bedType,
        cleaningFrequency,
        bloodTypes,
        minCapacity,
        packageName,
        arrivalDate,
        numberOfNights,
        lodgingMode,
        origin,
        maxDistance);
  }

  private List<BloodTypes> parseBloodTypes(String[] bloodTypes) {
    return Arrays.stream(bloodTypes).map(BloodTypes::get).collect(Collectors.toList());
  }

  private int parseCapacity(String capacity) {
    return parsePositiveInteger(capacity, new InvalidCapacityException());
  }

  private int parseMaxDistance(String maxDistance) {
    return parsePositiveInteger(maxDistance, new InvalidMaxDistanceException());
  }

  private int parseNumberOfNights(String numberOfNights) {
    return parsePositiveInteger(numberOfNights, new InvalidNumberOfNightsException());
  }

  private int parsePositiveInteger(String integer, BedException exception) {
    int parsedInteger;

    try {
      parsedInteger = Integer.parseInt(integer);
    } catch (NumberFormatException e) {
      throw exception;
    }

    if (parsedInteger < 0) {
      throw exception;
    }

    return parsedInteger;
  }

  private void applyMinCapacityDependencies(BedMatcher bedMatcher) {
    if (bedMatcher.getMinCapacity() != UNSET_INT) {
      if (bedMatcher.getArrivalDate() == null) bedMatcher.setArrivalDate(DEFAULT_ARRIVAL_DATE);
      if (bedMatcher.getNumberOfNights() == UNSET_INT)
        bedMatcher.setNumberOfNights(DEFAULT_NUMBER_OF_NIGHTS);
    } else {
      if (bedMatcher.getArrivalDate() != null)
        throw new ArrivalDateWithoutMinimalCapacityException();

      if (bedMatcher.getNumberOfNights() != UNSET_INT)
        throw new NumberOfNightsWithoutMinimalCapacityException();
    }
  }

  private void applyOriginDependencies(BedMatcher bedMatcher) {
    if (bedMatcher.getOrigin() != null) {
      if (bedMatcher.getMaxDistance() == UNSET_INT) bedMatcher.setMaxDistance(DEFAULT_MAX_DISTANCE);
    } else if (bedMatcher.getMaxDistance() != UNSET_INT) {
      throw new MaxDistanceWithoutOriginException();
    }
  }
}
