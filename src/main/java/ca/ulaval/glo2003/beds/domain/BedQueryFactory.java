package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.exceptions.InvalidCapacityException;
import ca.ulaval.glo2003.beds.exceptions.InvalidMaxDistanceException;
import ca.ulaval.glo2003.bookings.exceptions.InvalidNumberOfNightsException;
import ca.ulaval.glo2003.bookings.rest.mappers.BookingDateMapper;
import ca.ulaval.glo2003.locations.infrastructure.ZippopotamusClient;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class BedQueryFactory {

  // TODO : With BedQueryMapBuilder, we will have the correct params
  public static final String BED_TYPE_PARAM = "bedType";
  public static final String CLEANING_FREQUENCY_PARAM = "cleaningFreq";
  public static final String BLOOD_TYPES_PARAM = "bloodTypes";
  public static final String PACKAGE_NAME_PARAM = "packages";
  public static final String MIN_CAPACITY_PARAM = "minCapacity";
  public static final String ARRIVAL_DATE_PARAM = "arrivalDate";
  public static final String NUMBER_OF_NIGHTS_PARAM = "numberOfNights";
  public static final String LODGING_MODE_PARAM = "lodgingMode";
  public static final String ORIGIN_PARAM = "origin";
  public static final String MAX_DISTANCE_PARAM = "maxDistance";

  private final BedQueryBuilder bedQueryBuilder;
  private final BookingDateMapper bookingDateMapper;
  private final ZippopotamusClient
      zippopotamusClient; // TODO : Use an interface, like ZipCodeClient

  @Inject
  public BedQueryFactory(
      BedQueryBuilder bedQueryBuilder,
      BookingDateMapper bookingDateMapper,
      ZippopotamusClient zippopotamusClient) {
    this.bedQueryBuilder = bedQueryBuilder;
    this.bookingDateMapper = bookingDateMapper;
    this.zippopotamusClient = zippopotamusClient;
  }

  public BedQuery create(Map<String, String[]> params) {
    BedQueryBuilder builder = bedQueryBuilder.aBedQuery();

    if (params.get(BED_TYPE_PARAM) != null)
      builder = builder.withBedType(BedTypes.get(params.get(BED_TYPE_PARAM)[0]));

    if (params.get(CLEANING_FREQUENCY_PARAM) != null)
      builder =
          builder.withCleaningFrequency(
              CleaningFrequencies.get(params.get(CLEANING_FREQUENCY_PARAM)[0]));

    if (params.get(PACKAGE_NAME_PARAM) != null)
      builder = builder.withPackage(Packages.get(params.get(PACKAGE_NAME_PARAM)[0]));

    if (params.get(MIN_CAPACITY_PARAM) != null)
      builder = builder.withMinCapacity(parseMinCapacity(params.get(MIN_CAPACITY_PARAM)[0]));

    if (params.get(ARRIVAL_DATE_PARAM) != null)
      builder =
          builder.withArrivalDate(bookingDateMapper.fromString(params.get(ARRIVAL_DATE_PARAM)[0]));

    if (params.get(NUMBER_OF_NIGHTS_PARAM) != null)
      builder =
          builder.withNumberOfNights(parseNumberOfNights(params.get(NUMBER_OF_NIGHTS_PARAM)[0]));

    if (params.get(BLOOD_TYPES_PARAM) != null)
      builder = builder.withBloodTypes(parseBloodTypes(params.get(BLOOD_TYPES_PARAM)));

    if (params.get(LODGING_MODE_PARAM) != null)
      builder = builder.withLodgingMode(LodgingModes.get(params.get(LODGING_MODE_PARAM)[0]));

    if (params.get(ORIGIN_PARAM) != null)
      builder = builder.withOrigin(zippopotamusClient.validateZipCode(params.get(ORIGIN_PARAM)[0]));

    if (params.get(MAX_DISTANCE_PARAM) != null)
      builder = builder.withMaxDistance(parseMaxDistance(params.get(MAX_DISTANCE_PARAM)[0]));

    return builder.build();
  }

  private List<BloodTypes> parseBloodTypes(String[] bloodTypes) {
    return Arrays.stream(bloodTypes).map(BloodTypes::get).collect(Collectors.toList());
  }

  private int parseMinCapacity(String minCapacity) {
    return parsePositiveInteger(minCapacity, new InvalidCapacityException());
  }

  private int parseNumberOfNights(String numberOfNights) {
    return parsePositiveInteger(numberOfNights, new InvalidNumberOfNightsException());
  }

  private int parseMaxDistance(String maxDistance) {
    return parsePositiveInteger(maxDistance, new InvalidMaxDistanceException());
  }

  // TODO : Check if the exception thrown fit user stories
  private int parsePositiveInteger(String integer, RuntimeException exception) {
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
}
