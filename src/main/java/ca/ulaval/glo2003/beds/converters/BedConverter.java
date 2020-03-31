package ca.ulaval.glo2003.beds.converters;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedTypes;
import ca.ulaval.glo2003.beds.domain.CleaningFrequencies;
import ca.ulaval.glo2003.beds.exceptions.InvalidCapacityException;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import javax.inject.Inject;

public class BedConverter {

  private final BedNumberConverter bedNumberConverter;
  private final PublicKeyConverter publicKeyConverter;
  private final BloodTypeConverter bloodTypeConverter;
  private final LodgingModeConverter lodgingModeConverter;
  private final PackageConverter packageConverter;

  @Inject
  public BedConverter(
      BedNumberConverter bedNumberConverter,
      PublicKeyConverter publicKeyConverter,
      BloodTypeConverter bloodTypeConverter,
      LodgingModeConverter lodgingModeConverter,
      PackageConverter packageConverter) {
    this.bedNumberConverter = bedNumberConverter;
    this.publicKeyConverter = publicKeyConverter;
    this.bloodTypeConverter = bloodTypeConverter;
    this.lodgingModeConverter = lodgingModeConverter;
    this.packageConverter = packageConverter;
  }

  public Bed fromRequest(BedRequest request) {
    if (request.getCapacity() < 1) throw new InvalidCapacityException();

    return new Bed(
        publicKeyConverter.fromString(request.getOwnerPublicKey()),
        BedTypes.get(request.getBedType()),
        CleaningFrequencies.get(request.getCleaningFrequency()),
        bloodTypeConverter.fromStrings(request.getBloodTypes()),
        request.getCapacity(),
        lodgingModeConverter.fromString(request.getLodgingMode()),
        packageConverter.fromRequests(request.getPackages()));
  }

  public BedResponse toResponseWithoutNumber(Bed bed, int stars) {
    return new BedResponse(
        bed.getLocation().getZipCode().getValue(),
        bed.getBedType().toString(),
        bed.getCleaningFrequency().toString(),
        bloodTypeConverter.toStrings(bed.getBloodTypes()),
        bed.getCapacity(),
        lodgingModeConverter.toString(bed.getLodgingMode()),
        packageConverter.toResponses(bed.getPricesPerNight()),
        stars);
  }

  public BedResponse toResponseWithNumber(Bed bed, int stars) {
    BedResponse bedResponse = toResponseWithoutNumber(bed, stars);
    String bedNumber = bedNumberConverter.toString(bed.getNumber());
    bedResponse.setBedNumber(bedNumber);
    return bedResponse;
  }
}
