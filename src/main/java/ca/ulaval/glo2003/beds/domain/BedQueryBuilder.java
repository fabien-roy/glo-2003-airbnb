package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.locations.domain.ZipCode;
import java.util.List;

public interface BedQueryBuilder {

  BedQueryBuilder aBedQuery();

  BedQueryBuilder withBedType(BedTypes bedType);

  BedQueryBuilder withCleaningFrequency(CleaningFrequencies cleaningFrequency);

  BedQueryBuilder withBloodTypes(List<BloodTypes> parseBloodTypes);

  BedQueryBuilder withPackage(Packages packageNames);

  BedQueryBuilder withMinCapacity(int minCapacity);

  BedQueryBuilder withArrivalDate(BookingDate arrivalDate);

  BedQueryBuilder withNumberOfNights(int numberOfNights);

  BedQueryBuilder withLodgingMode(LodgingModes lodgingMode);

  BedQueryBuilder withOrigin(ZipCode origin);

  BedQueryBuilder withMaxDistance(int maxDistance);

  BedQuery build();
}
