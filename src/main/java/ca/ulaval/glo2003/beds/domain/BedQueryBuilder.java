package ca.ulaval.glo2003.beds.domain;

public interface BedQueryBuilder {

  BedQueryBuilder aBedQuery();

  BedQueryBuilder withBedType(BedTypes bedType);

  BedQueryBuilder withCleaningFrequency(CleaningFrequencies cleaningFrequency);

  BedQuery build();
}
