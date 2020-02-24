package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.interfaces.rest.exceptions.InvalidFormatException;
import java.util.ArrayList;

public class BedMapper {

  public static final String OWNER_PUBLIC_KEY_PATTERN = "([A-Z]|[0-9]){64}";
  public static final String ZIP_CODE_PATTERN = "([0-9]){5}"; // TODO : Will be useful.

  public Bed fromRequest(BedRequest request) {
    validateFormat(request);

    BedTypes bedType = BedTypes.get(request.getBedType());
    CleaningFrequencies cleaningFrequencies =
        CleaningFrequencies.get(request.getCleaningFrequency());

    return new Bed(bedType, cleaningFrequencies, new ArrayList<>(), 0, new ArrayList<>());
  }

  public BedResponse toResponse(Bed bed) {
    // TODO
    return new BedResponse();
  }

  private void validateFormat(BedRequest request) {
    if (request.getBedType() == null) {
      throw new InvalidFormatException();
    }
  }
}
