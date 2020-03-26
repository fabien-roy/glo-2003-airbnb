package ca.ulaval.glo2003.beds.mappers;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.exceptions.InvalidCapacityException;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class BedConverter {

  private final PublicKeyMapper publicKeyMapper;
  private final BloodTypesMapper bloodTypesMapper;
  private final PackageMapper packageMapper;

  @Inject
  public BedConverter(
      PublicKeyMapper publicKeyMapper,
      BloodTypesMapper bloodTypesMapper,
      PackageMapper packageMapper) {
    this.publicKeyMapper = publicKeyMapper;
    this.bloodTypesMapper = bloodTypesMapper;
    this.packageMapper = packageMapper;
  }

  public Bed fromRequest(BedRequest request) {
    if (request.getCapacity() < 0) throw new InvalidCapacityException();

    PublicKey ownerPublicKey = publicKeyMapper.fromString(request.getOwnerPublicKey());
    List<BloodTypes> bloodTypes = bloodTypesMapper.fromStrings(request.getBloodTypes());
    LodgingModes mode =
        request.getLodgingMode() == null
            ? LodgingModes.PRIVATE
            : LodgingModes.get(request.getLodgingMode());
    Map<Packages, Price> pricesPerNight = packageMapper.fromRequests(request.getPackages());

    return new Bed(
        ownerPublicKey,
        BedTypes.get(request.getBedType()),
        CleaningFrequencies.get(request.getCleaningFrequency()),
        bloodTypes,
        request.getCapacity(),
        mode,
        pricesPerNight);
  }

  public BedResponse toResponseWithoutNumber(Bed bed, int stars) {
    List<String> bloodTypes = bloodTypesMapper.toStrings(bed.getBloodTypes());
    List<PackageResponse> packageResponses = packageMapper.toResponses(bed.getPricesPerNight());

    return new BedResponse(
        bed.getLocation().getZipCode().getValue(),
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
}
