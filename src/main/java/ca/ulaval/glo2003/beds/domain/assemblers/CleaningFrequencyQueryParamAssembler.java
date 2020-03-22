package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.domain.CleaningFrequencies;
import java.util.Map;

public class CleaningFrequencyQueryParamAssembler implements BedQueryParamAssembler {

  public static final String CLEANING_FREQUENCY_PARAM = "cleaningFreq";

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, String[]> params) {
    return params.get(CLEANING_FREQUENCY_PARAM) != null
        ? builder.withCleaningFrequency(getCleaningFrequency(params))
        : builder;
  }

  private CleaningFrequencies getCleaningFrequency(Map<String, String[]> params) {
    return CleaningFrequencies.get(params.get(CLEANING_FREQUENCY_PARAM)[0]);
  }
}
