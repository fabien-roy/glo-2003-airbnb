package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedTypes;
import ca.ulaval.glo2003.beds.domain.CleaningFrequencies;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import java.util.Map;

public class BedMapper {

  public static final String BED_TYPE_PARAM = "bedType";
  public static final String CLEANING_FREQUENCY = "cleaningFrequency";

  public Bed fromRequest(BedRequest request) {
    // TODO
    return new Bed();
  }

  public Bed fromRequestParams(Map<String, String> params) {
    Bed bed = new Bed();

    if (params.get(BED_TYPE_PARAM) != null) {
      bed.setBedType(BedTypes.get(params.get(BED_TYPE_PARAM)));
    }
    if (params.get(CLEANING_FREQUENCY) != null) {
      bed.setCleaningFrequency(CleaningFrequencies.get(params.get(CLEANING_FREQUENCY)));
    }

    return bed;
  }

  public BedResponse toResponse(Bed bed) {
    // TODO
    return new BedResponse();
  }
}
