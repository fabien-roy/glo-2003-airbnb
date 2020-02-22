package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedTypes;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import java.util.ArrayList;

public class BedMapper {

  public Bed fromRequest(BedRequest request) {
    BedTypes bedType = BedTypes.get(request.getBedType());

    // TODO : Add other values in BedMapper.fromRequest
    return new Bed(bedType, null, new ArrayList<>(), 0, new ArrayList<>());
  }

  public BedResponse toResponse(Bed bed) {
    // TODO
    return new BedResponse();
  }
}
