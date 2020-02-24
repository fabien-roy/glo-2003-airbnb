package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedTypes;
import ca.ulaval.glo2003.beds.domain.BloodTypes;
import ca.ulaval.glo2003.beds.domain.Package;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
import java.util.ArrayList;
import java.util.List;

public class BedMapper {

  public Bed fromRequest(BedRequest request) {
    BedTypes bedType = BedTypes.get(request.getBedType());

    // TODO : Add other values in BedMapper.fromRequest
    return new Bed(bedType, null, new ArrayList<>(), 0, new ArrayList<>());
  }

  public BedResponse toResponse(Bed bed, int stars) {
    List<String> bloodTypes = new ArrayList<>();
    List<PackageResponse> packageResponses = new ArrayList<>();

    for (BloodTypes bloodType : bed.getBloodTypes()) {
      bloodTypes.add(bloodType.toString());
    }
    for (Package pack : bed.getPackages()) {
      PackageResponse packageResponse =
          new PackageResponse(pack.getName().toString(), pack.getPricePerNight().doubleValue());
      packageResponses.add(packageResponse);
    }
    BedResponse bedResponse =
        new BedResponse(
            bed.getNumber(),
            bed.getZipCode(),
            bed.getBedType().toString(),
            bed.getCleaningFrequency().toString(),
            bloodTypes,
            bed.getCapacity(),
            packageResponses,
            stars);
    return bedResponse;
  }
}
