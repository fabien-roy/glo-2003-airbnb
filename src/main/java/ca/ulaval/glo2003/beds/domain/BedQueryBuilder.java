package ca.ulaval.glo2003.beds.domain;

import java.util.List;

public interface BedQueryBuilder {

  BedQueryBuilder aBedQuery();

  BedQueryBuilder withBedType(BedTypes bedType);

  BedQueryBuilder withCleaningFrequency(CleaningFrequencies cleaningFrequency);

  BedQueryBuilder withBloodTypes(List<BloodTypes> parseBloodTypes);

  BedQuery build();
}
