package ca.ulaval.glo2003.beds.domain;

public interface BedQueryBuilder {

  BedQueryBuilder aBedQuery();

  BedQueryBuilder withBedType(BedTypes bedType);

  BedQuery build();
}
