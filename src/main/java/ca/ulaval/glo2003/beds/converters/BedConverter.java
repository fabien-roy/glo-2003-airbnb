package ca.ulaval.glo2003.beds.converters;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedTypes;
import ca.ulaval.glo2003.beds.domain.CleaningFrequencies;
import ca.ulaval.glo2003.beds.exceptions.ExceedingAccommodationCapacityException;
import ca.ulaval.glo2003.beds.exceptions.InvalidCapacityException;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import javax.inject.Inject;

public class BedConverter {

  private final PublicKeyConverter publicKeyConverter;
  private final BloodTypeConverter bloodTypeConverter;
  private final LodgingModeConverter lodgingModeConverter;
  private final PackageConverter packageConverter;

  @Inject
  public BedConverter(
      PublicKeyConverter publicKeyConverter,
      BloodTypeConverter bloodTypeConverter,
      LodgingModeConverter lodgingModeConverter,
      PackageConverter packageConverter) {
    this.publicKeyConverter = publicKeyConverter;
    this.bloodTypeConverter = bloodTypeConverter;
    this.lodgingModeConverter = lodgingModeConverter;
    this.packageConverter = packageConverter;
  }

  public Bed fromRequest(BedRequest request) {
    BedTypes bedType = BedTypes.get(request.getBedType());
    validateCapacity(bedType, request.getCapacity());

    return new Bed(
        publicKeyConverter.fromString(request.getOwnerPublicKey()),
        bedType,
        CleaningFrequencies.get(request.getCleaningFrequency()),
        bloodTypeConverter.fromStrings(request.getBloodTypes()),
        request.getCapacity(),
        lodgingModeConverter.fromString(request.getLodgingMode()),
        packageConverter.fromRequests(request.getPackages()));
  }

  public BedResponse toResponseWithoutNumber(Bed bed, int stars) {
    return new BedResponse(
        bed.getLocation().getZipCode().toString(),
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
    bedResponse.setBedNumber(bed.getNumber().toString());
    return bedResponse;
  }

  public void validateCapacity(BedTypes bedType, int capacity) {
    if (capacity < 1) throw new InvalidCapacityException();

    int maxCapacity = BedTypesCapacities.get(bedType);

    if (capacity > maxCapacity) throw new ExceedingAccommodationCapacityException();
  }
}
