package ca.ulaval.glo2003.beds.converters;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.exceptions.ExceedingAccommodationCapacityException;
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
  private final BloodTypeConverter bloodTypeConverter;
  private final PackageConverter packageConverter;

  @Inject
  public BedConverter(
      BedNumberConverter bedNumberConverter,
      PublicKeyConverter publicKeyConverter,
      BloodTypeConverter bloodTypeConverter,
      PackageConverter packageConverter) {
    this.bedNumberConverter = bedNumberConverter;
    this.publicKeyConverter = publicKeyConverter;
    this.bloodTypeConverter = bloodTypeConverter;
    this.packageConverter = packageConverter;
  }

  public Bed fromRequest(BedRequest request) {

    PublicKey ownerPublicKey = publicKeyConverter.fromString(request.getOwnerPublicKey());
    List<BloodTypes> bloodTypes = bloodTypeConverter.fromStrings(request.getBloodTypes());
    validateCapacity(request.getBedType(), request.getCapacity());
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
    List<String> bloodTypes = bloodTypeConverter.toStrings(bed.getBloodTypes());
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

  public void validateCapacity(String bedType, int capacity) {
    if (capacity < 1) throw new InvalidCapacityException();

    int maxCapacity = BedTypesCapacities.get(BedTypes.get(bedType));

    if (capacity > maxCapacity) throw new ExceedingAccommodationCapacityException();
  }
}
