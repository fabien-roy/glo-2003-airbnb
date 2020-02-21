package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedTypes;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import java.util.Map;

public class BedMapper {

  public static final String BED_TYPE_PARAM = "bedType";

  public Bed fromRequest(BedRequest request) {
    Bed bed = new Bed();

    BedTypes bedType = BedTypes.get(request.getBedType());
    bed.setBedType(bedType);

    return bed;
  }

  public Bed fromRequestParams(Map<String, String> params) {
    Bed bed = new Bed();

    if (params.get(BED_TYPE_PARAM) != null) {
      bed.setBedType(BedTypes.get(params.get(BED_TYPE_PARAM)));
    }

    return bed;
  }

  public BedResponse toResponse(Bed bed) {
    // TODO
    return new BedResponse();
  }
}
