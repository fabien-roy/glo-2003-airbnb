package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.interfaces.rest.exceptions.InvalidFormatException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BedMapper {

  public static final String OWNER_PUBLIC_KEY_PATTERN = "([A-Z]|[0-9]){64}";
  public static final String ZIP_CODE_PATTERN = "([0-9]){5}"; // TODO : Will be useful.

  public Bed fromRequest(BedRequest request) {
    validateFormat(request);

    BedTypes bedType = BedTypes.get(request.getBedType());
    CleaningFrequencies cleaningFrequencies =
        CleaningFrequencies.get(request.getCleaningFrequency());
    List<BloodTypes> bloodTypes = parseBloodTypes(request.getBloodTypes().toArray(String[]::new));
    int capacity = request.getCapacity();

    return new Bed(bedType, cleaningFrequencies, bloodTypes, capacity, new ArrayList<>());
  }

  public BedResponse toResponse(Bed bed) {
    // TODO
    return new BedResponse();
  }

  private void validateFormat(BedRequest request) {
    if (request.getBedType() == null
        || request.getCleaningFrequency() == null
        || request.getBloodTypes().isEmpty()) {
      throw new InvalidFormatException();
    }
  }

  private List<BloodTypes> parseBloodTypes(String[] bloodTypes) {
    return Arrays.stream(bloodTypes).map(BloodTypes::get).collect(Collectors.toList());
  }
}
