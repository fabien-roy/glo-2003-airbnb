package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidBedTypeException;
import ca.ulaval.glo2003.interfaces.rest.exceptions.InvalidFormatException;
import java.util.ArrayList;

public class BedMapper {

  public Bed fromRequest(BedRequest request) {
    try {
      BedTypes bedType = BedTypes.get(request.getBedType());
      return new Bed(bedType, null, new ArrayList<>(), 0, new ArrayList<>());
    } catch (InvalidBedTypeException e) {
      throw new InvalidFormatException();
    }
  }

  public BedResponse toResponse(Bed bed) {
    // TODO
    return new BedResponse();
  }
}
