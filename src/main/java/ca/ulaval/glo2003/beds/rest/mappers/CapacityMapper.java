package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.BedTypes;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidBedTypeException;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidMaximalCapacityException;

public class CapacityMapper {

  public static void validateCapacity(BedRequest bedRequest) {
    BedTypes bedType = BedTypes.get(bedRequest.getBedType());
    if (bedRequest.getCapacity() > getMaxCapacity(bedType)) {
      throw new InvalidMaximalCapacityException();
    }
  }

  public static int getMaxCapacity(BedTypes bedType) {
    int maxCapacity;
    switch (bedType) {
      case LATEX:
        maxCapacity = 400;
        break;
      case MEMORY_FOAM:
        maxCapacity = 700;
        break;
      case SPRINGS:
        maxCapacity = 1000;
        break;
      default:
        throw new InvalidBedTypeException();
    }
    return maxCapacity;
  }
}
