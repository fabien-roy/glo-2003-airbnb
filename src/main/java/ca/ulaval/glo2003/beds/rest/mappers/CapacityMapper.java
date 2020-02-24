package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.BedTypes;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidMaximalCapacityException;

public class CapacityMapper {

  static void validateCapacity(BedRequest bedRequest) {
    if (bedRequest.getBedType().equals(BedTypes.LATEX.toString())) {
      if (bedRequest.getCapacity() > 400) throw new InvalidMaximalCapacityException();
    }
    if (bedRequest.getBedType().equals(BedTypes.MEMORY_FOAM.toString())) {
      if (bedRequest.getCapacity() > 700) throw new InvalidMaximalCapacityException();
    }
    if (bedRequest.getBedType().equals(BedTypes.SPRINGS.toString())) {
      if (bedRequest.getCapacity() > 1000) throw new InvalidMaximalCapacityException();
    }
  }
}
