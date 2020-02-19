package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import java.util.Map;

public class BedMapper {

  public static final String BED_TYPE_PARAM = "bedType";

  public Bed fromRequest(BedRequest request) {
    // TODO
    return new Bed();
  }

  public Bed fromRequestParams(Map<String, String> params) {
    // TODO
    return new Bed();
  }

  public BedResponse toRequest(Bed bed) {
    // TODO
    return new BedResponse();
  }
}
