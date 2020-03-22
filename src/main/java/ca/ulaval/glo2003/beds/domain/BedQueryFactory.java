package ca.ulaval.glo2003.beds.domain;

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

    if (params.get(BLOOD_TYPES_PARAM) != null)
      builder = builder.withBloodTypes(parseBloodTypes(params.get(BLOOD_TYPES_PARAM)));

    return builder.build();
  }

  private List<BloodTypes> parseBloodTypes(String[] bloodTypes) {
    return Arrays.stream(bloodTypes).map(BloodTypes::get).collect(Collectors.toList());
  }
}
