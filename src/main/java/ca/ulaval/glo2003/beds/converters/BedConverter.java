package ca.ulaval.glo2003.beds.converters;

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

  private final BedNumberConverter bedNumberConverter;
  private final PublicKeyConverter publicKeyConverter;
  private final BloodTypesConverter bloodTypesConverter;
  private final PackageConverter packageConverter;

  @Inject
  public BedConverter(
      BedNumberConverter bedNumberConverter,
      PublicKeyConverter publicKeyConverter,
      BloodTypesConverter bloodTypesConverter,
      PackageConverter packageConverter) {
    this.bedNumberConverter = bedNumberConverter;
    this.publicKeyConverter = publicKeyConverter;
    this.bloodTypesConverter = bloodTypesConverter;
    this.packageConverter = packageConverter;
  }

  public Bed fromRequest(BedRequest request) {
    if (request.getCapacity() < 0) throw new InvalidCapacityException();

    PublicKey ownerPublicKey = publicKeyConverter.fromString(request.getOwnerPublicKey());
    List<BloodTypes> bloodTypes = bloodTypesConverter.fromStrings(request.getBloodTypes());
    LodgingModes mode =
        request.getLodgingMode() == null
            ? LodgingModes.PRIVATE
            : LodgingModes.get(request.getLodgingMode());
    Map<Packages, Price> pricesPerNight = packageConverter.fromRequests(request.getPackages());

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
    List<String> bloodTypes = bloodTypesConverter.toStrings(bed.getBloodTypes());
    List<PackageResponse> packageResponses = packageConverter.toResponses(bed.getPricesPerNight());

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
    String bedNumber = bedNumberConverter.toString(bed.getNumber());
    bedResponse.setBedNumber(bedNumber);
    return bedResponse;
  }
}
