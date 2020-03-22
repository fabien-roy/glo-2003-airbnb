package ca.ulaval.glo2003.beds.domain;

import java.util.Map;
import javax.inject.Inject;

public class BedQueryFactory {

  // TODO : With BedQueryMapBuilder, we will have the correct params
  public static final String BED_TYPE_PARAM = "bedType";
  public static final String CLEANING_FREQUENCY_PARAM = "cleaningFreq";

  private final BedQueryBuilder bedQueryBuilder;

  @Inject
  public BedQueryFactory(BedQueryBuilder bedQueryBuilder) {
    this.bedQueryBuilder = bedQueryBuilder;
  }

  public BedQuery create(Map<String, String[]> params) {
    BedQueryBuilder builder = bedQueryBuilder.aBedQuery();

    if (params.get(BED_TYPE_PARAM) != null)
      builder = builder.withBedType(BedTypes.get(params.get(BED_TYPE_PARAM)[0]));

    if (params.get(CLEANING_FREQUENCY_PARAM) != null)
      builder =
          builder.withCleaningFrequency(
              CleaningFrequencies.get(params.get(CLEANING_FREQUENCY_PARAM)[0]));

    return builder.build();
  }
}
