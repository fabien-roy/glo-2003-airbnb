package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
import ca.ulaval.glo2003.beds.rest.exceptions.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BedMapper {

  public static final String ZIP_CODE_PATTERN = "([0-9]){5}";

  private final PublicKeyMapper publicKeyMapper;
  private final PackageMapper packageMapper;

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
    Map<Packages, Price> pricesPerNight = packageMapper.fromRequests(request.getPackages());
    LodgingModes mode = LodgingModes.PRIVATE;

    if (request.getLodgingMode() == null) request.setLodgingMode("PRIVATE");
    else if (request.getLodgingMode().equals(LodgingModes.COHABITATION.toString()))
      mode = LodgingModes.COHABITATION;
    else if (request.getLodgingMode().equals(LodgingModes.PRIVATE.toString()))
      mode = LodgingModes.PRIVATE;

    return new Bed(
        ownerPublicKey,
        request.getZipCode(),
        bedType,
        cleaningFrequencies,
        bloodTypes,
        request.getCapacity(),
        pricesPerNight,
        mode);
  }

  public BedResponse toResponseWithoutNumber(Bed bed, int stars) {
    List<String> bloodTypes =
        bed.getBloodTypes().stream().map(BloodTypes::toString).collect(Collectors.toList());

    List<PackageResponse> packageResponses = packageMapper.toResponses(bed.getPricesPerNight());

    return new BedResponse(
        bed.getZipCode(),
        bed.getBedType().toString(),
        bed.getCleaningFrequency().toString(),
        bloodTypes,
        bed.getCapacity(),
        packageResponses,
        stars);
  }

  public BedResponse toResponseWithNumber(Bed bed, int stars) {
    BedResponse bedResponse = toResponseWithoutNumber(bed, stars);
    bedResponse.setBedNumber(bed.getNumber());
    return bedResponse;
  }

  private void validateRequest(BedRequest request) {
    if (request.getBloodTypes().isEmpty()) throw new InvalidBloodTypesException();

    if (request.getCapacity() <= 0) throw new InvalidCapacityException();

    if (request.getLodgingMode() != null) validateLodgingMode(request.getLodgingMode());

    validateZipCode(request.getZipCode());
  }

  // TODO : FLG : Only validate not null
  private void validateZipCode(String zipCode) {
    if (zipCode == null || !zipCode.matches(ZIP_CODE_PATTERN)) throw new InvalidZipCodeException();
  }

  private void validateLodgingMode(String lodgingMode) {

    if ((!lodgingMode.equals(LodgingModes.PRIVATE.toString()))
        && (!lodgingMode.equals(LodgingModes.COHABITATION.toString())))
      throw new InvalidLodgingModeException();
  }

  private List<BloodTypes> parseBloodTypes(List<String> bloodTypes) {
    return bloodTypes.stream().map(BloodTypes::get).collect(Collectors.toList());
  }
}
