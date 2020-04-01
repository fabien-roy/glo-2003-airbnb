package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.domain.CleaningFrequencies;
import java.util.List;
import java.util.Map;

public class CleaningFrequencyQueryParamAssembler implements BedQueryParamAssembler {

  public static final String CLEANING_FREQUENCY_PARAM = "cleaningFreq";

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, List<String>> params) {
    List<String> cleaningFrequencies = params.get(CLEANING_FREQUENCY_PARAM);

    return cleaningFrequencies != null
        ? builder.withCleaningFrequency(CleaningFrequencies.get(cleaningFrequencies.get(0)))
        : builder;
  }
}
