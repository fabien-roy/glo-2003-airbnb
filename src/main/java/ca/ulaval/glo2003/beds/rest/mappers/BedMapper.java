package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedTypes;
import ca.ulaval.glo2003.beds.domain.BloodTypes;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BedMapper {

  public Bed fromRequest(BedRequest request) {
    BedTypes bedType = BedTypes.get(request.getBedType());

    // TODO : Add other values in BedMapper.fromRequest
    return new Bed(bedType, null, new ArrayList<>(), 0, new ArrayList<>());
  }

  // TODO : toResponse should only set bedNumber for getAll, not for get
  public BedResponse toResponse(Bed bed, int stars) {
    List<String> bloodTypes =
        bed.getBloodTypes().stream().map(BloodTypes::toString).collect(Collectors.toList());

    List<PackageResponse> packageResponses =
        bed.getPackages().stream()
            .map(
                bedPackage ->
                    new PackageResponse(
                        bedPackage.getName().toString(),
                        bedPackage.getPricePerNight().doubleValue()))
            .collect(Collectors.toList());

    return new BedResponse(
        bed.getNumber(),
        bed.getZipCode(),
        bed.getBedType().toString(),
        bed.getCleaningFrequency().toString(),
        bloodTypes,
        bed.getCapacity(),
        packageResponses,
        stars);
  }
}
