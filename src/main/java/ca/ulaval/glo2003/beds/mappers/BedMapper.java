package ca.ulaval.glo2003.beds.mappers;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.exceptions.*;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class BedMapper {

  private final PublicKeyMapper publicKeyMapper;
  private final PackageMapper packageMapper;

  @Inject
  public BedMapper(PublicKeyMapper publicKeyMapper, PackageMapper packageMapper) {
    this.publicKeyMapper = publicKeyMapper;
    this.packageMapper = packageMapper;
  }

  public Bed fromRequest(BedRequest request) {
    validateRequest(request);

    PublicKey ownerPublicKey = publicKeyMapper.fromString(request.getOwnerPublicKey());
    BedTypes bedType = BedTypes.get(request.getBedType());
    CleaningFrequencies cleaningFrequencies =
        CleaningFrequencies.get(request.getCleaningFrequency());
    List<BloodTypes> bloodTypes = parseBloodTypes(request.getBloodTypes());
    LodgingModes mode =
        request.getLodgingMode() == null
            ? LodgingModes.PRIVATE
            : LodgingModes.get(request.getLodgingMode());
    Map<Packages, Price> pricesPerNight = packageMapper.fromRequests(request.getPackages());

    return new Bed(
        ownerPublicKey,
        bedType,
        cleaningFrequencies,
        bloodTypes,
        request.getCapacity(),
        mode,
        pricesPerNight);
  }

  public BedResponse toResponseWithoutNumber(Bed bed, int stars) {
    List<String> bloodTypes =
        bed.getBloodTypes().stream().map(BloodTypes::toString).collect(Collectors.toList());

    List<PackageResponse> packageResponses = packageMapper.toResponses(bed.getPricesPerNight());

    return new BedResponse(
        bed.getLocation().getZipCode(),
        bed.getBedType().toString(),
        bed.getCleaningFrequency().toString(),
        bloodTypes,
        bed.getCapacity(),
        bed.getLodgingMode().toString(),
        packageResponses,
        stars);
  }

  public BedResponse toResponseWithNumber(Bed bed, int stars) {
    BedResponse bedResponse = toResponseWithoutNumber(bed, stars);
    bedResponse.setBedNumber(bed.getNumber().toString());
    return bedResponse;
  }

  private void validateRequest(BedRequest request) {
    if (request.getBloodTypes().isEmpty()) throw new InvalidBloodTypesException();

    if (request.getCapacity() <= 0) throw new InvalidCapacityException();
  }

  private List<BloodTypes> parseBloodTypes(List<String> bloodTypes) {
    return bloodTypes.stream().map(BloodTypes::get).collect(Collectors.toList());
  }
}
