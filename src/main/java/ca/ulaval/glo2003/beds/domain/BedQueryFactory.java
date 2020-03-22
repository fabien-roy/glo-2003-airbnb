package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.domain.assemblers.BedQueryParamAssembler;
import ca.ulaval.glo2003.beds.exceptions.InvalidMaxDistanceException;
import ca.ulaval.glo2003.bookings.exceptions.InvalidNumberOfNightsException;
import ca.ulaval.glo2003.locations.infrastructure.ZippopotamusClient;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class BedQueryFactory {

  public static final String NUMBER_OF_NIGHTS_PARAM = "numberOfNights";
  public static final String LODGING_MODE_PARAM = "lodgingMode";
  public static final String ORIGIN_PARAM = "origin";
  public static final String MAX_DISTANCE_PARAM = "maxDistance";

  private final BedQueryBuilder bedQueryBuilder;
  private final List<BedQueryParamAssembler> queryParamAssemblers;
  private final ZippopotamusClient
      zippopotamusClient; // TODO : Use an interface, like ZipCodeClient

  @Inject
  public BedQueryFactory(
      BedQueryBuilder bedQueryBuilder,
      List<BedQueryParamAssembler> queryParamAssemblers,
      ZippopotamusClient zippopotamusClient) {
    this.bedQueryBuilder = bedQueryBuilder;
    this.queryParamAssemblers = queryParamAssemblers;
    this.zippopotamusClient = zippopotamusClient;
  }

  public BedQuery create(Map<String, String[]> params) {
    BedQueryBuilder builder = bedQueryBuilder.aBedQuery();

    for (BedQueryParamAssembler queryParamAssembler : queryParamAssemblers)
      builder = queryParamAssembler.assemble(builder, params);

    if (params.get(NUMBER_OF_NIGHTS_PARAM) != null)
      builder =
          builder.withNumberOfNights(parseNumberOfNights(params.get(NUMBER_OF_NIGHTS_PARAM)[0]));

    if (params.get(LODGING_MODE_PARAM) != null)
      builder = builder.withLodgingMode(LodgingModes.get(params.get(LODGING_MODE_PARAM)[0]));

    if (params.get(ORIGIN_PARAM) != null)
      builder = builder.withOrigin(zippopotamusClient.validateZipCode(params.get(ORIGIN_PARAM)[0]));

    if (params.get(MAX_DISTANCE_PARAM) != null)
      builder = builder.withMaxDistance(parseMaxDistance(params.get(MAX_DISTANCE_PARAM)[0]));

    return builder.build();
  }

  private int parseNumberOfNights(String numberOfNights) {
    return parsePositiveInteger(numberOfNights, new InvalidNumberOfNightsException());
  }

  private int parseMaxDistance(String maxDistance) {
    return parsePositiveInteger(maxDistance, new InvalidMaxDistanceException());
  }

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
