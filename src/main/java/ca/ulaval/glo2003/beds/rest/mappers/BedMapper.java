package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedTypes;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BedMapper {

  public Bed fromRequest(BedRequest request) {
    BedTypes bedType = BedTypes.get(request.getBedType());

    // TODO : Add other values in BedMapper.fromRequest
    return new Bed(bedType, null, new ArrayList<>(), 0, new ArrayList<>());
  }

  public BedResponse toResponse(Bed bed, int stars) {
    // TODO
    UUID bedNumber = null;
    String zipCode = null;
    String bedType = null;
    String cleaningFrequency = null;
    List<String> bloodTypes = null;
    int capacity = 0;
    List<PackageResponse> packages = null;

    return new BedResponse(
        bedNumber, zipCode, bedType, cleaningFrequency, bloodTypes, capacity, packages, stars);
  }
}
