package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.time2.domain.TimeDate;
import java.util.List;

public interface BedQueryBuilder<Q extends BedQuery> {

  BedQueryBuilder<Q> aBedQuery();

  BedQueryBuilder<Q> withBedType(BedTypes bedType);

  BedQueryBuilder<Q> withCleaningFrequency(CleaningFrequencies cleaningFrequency);

  BedQueryBuilder<Q> withBloodTypes(List<BloodTypes> parseBloodTypes);

  BedQueryBuilder<Q> withPackage(Packages packageNames);

  BedQueryBuilder<Q> withMinCapacity(int minCapacity);

  BedQueryBuilder<Q> withArrivalDate(TimeDate arrivalDate);

  BedQueryBuilder<Q> withNumberOfNights(int numberOfNights);

  BedQueryBuilder<Q> withLodgingMode(LodgingModes lodgingMode);

  BedQueryBuilder<Q> withOrigin(Location origin);

  BedQueryBuilder<Q> withMaxDistance(double maxDistance);

  Q build();
}
