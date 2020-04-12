package ca.ulaval.glo2003.beds.infrastructure;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.exceptions.ArrivalDateWithoutMinimalCapacityException;
import ca.ulaval.glo2003.beds.exceptions.MaxDistanceWithoutOriginException;
import ca.ulaval.glo2003.beds.exceptions.NumberOfNightsWithoutMinimalCapacityException;
import ca.ulaval.glo2003.beds.infrastructure.filters.*;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.locations.domain.Location;
import java.util.ArrayList;
import java.util.List;

public class InMemoryBedQueryBuilder implements BedQueryBuilder<InMemoryBedQuery> {

  private List<InMemoryBedFilter> filters = new ArrayList<>();

  private static final int UNSET_NUMERIC = 0;
  static final int DEFAULT_NUMBER_OF_NIGHTS = 3;
  static final int DEFAULT_MAX_DISTANCE = 10;

  private int minCapacity = UNSET_NUMERIC;
  private BookingDate arrivalDate = null;
  private int numberOfNights = UNSET_NUMERIC;
  private Location origin = null;
  private double maxDistance = UNSET_NUMERIC;

  @Override
  public InMemoryBedQueryBuilder aBedQuery() {
    return new InMemoryBedQueryBuilder();
  }

  @Override
  public InMemoryBedQueryBuilder withBedType(BedTypes bedType) {
    this.filters.add(new InMemoryBedTypeFilter(bedType));
    return this;
  }

  @Override
  public InMemoryBedQueryBuilder withCleaningFrequency(CleaningFrequencies cleaningFrequency) {
    this.filters.add(new InMemoryCleaningFrequencyFilter(cleaningFrequency));
    return this;
  }

  @Override
  public InMemoryBedQueryBuilder withBloodTypes(List<BloodTypes> bloodTypes) {
    this.filters.add(new InMemoryBloodTypesFilter(bloodTypes));
    return this;
  }

  @Override
  public InMemoryBedQueryBuilder withPackage(Packages packageName) {
    this.filters.add(new InMemoryPackageFilter(packageName));
    return this;
  }

  @Override
  public InMemoryBedQueryBuilder withMinCapacity(int minCapacity) {
    this.minCapacity = minCapacity;
    return this;
  }

  @Override
  public InMemoryBedQueryBuilder withArrivalDate(BookingDate arrivalDate) {
    this.arrivalDate = arrivalDate;
    return this;
  }

  @Override
  public InMemoryBedQueryBuilder withNumberOfNights(int numberOfNights) {
    this.numberOfNights = numberOfNights;
    return this;
  }

  @Override
  public InMemoryBedQueryBuilder withLodgingMode(LodgingModes lodgingMode) {
    this.filters.add(new InMemoryLodgingModeFilter(lodgingMode));
    return this;
  }

  @Override
  public InMemoryBedQueryBuilder withOrigin(Location origin) {
    this.origin = origin;
    return this;
  }

  @Override
  public InMemoryBedQueryBuilder withMaxDistance(double maxDistance) {
    this.maxDistance = maxDistance;
    return this;
  }

  @Override
  public InMemoryBedQuery build() {
    addAvailabilityFilter();
    addDistanceFilter();
    return new InMemoryBedQuery(filters);
  }

  private void addAvailabilityFilter() {
    if (minCapacity != UNSET_NUMERIC) {
      filters.add(
          new InMemoryAvailabilityFilter(minCapacity, getArrivalDate(), getNumberOfNights()));
    } else {
      if (arrivalDate != null) throw new ArrivalDateWithoutMinimalCapacityException();

      if (numberOfNights != UNSET_NUMERIC)
        throw new NumberOfNightsWithoutMinimalCapacityException();
    }
  }

  private void addDistanceFilter() {
    if (origin != null) {
      filters.add(new InMemoryDistanceFilter(origin, getMaxDistance()));
    } else {
      if (maxDistance != UNSET_NUMERIC) throw new MaxDistanceWithoutOriginException();
    }
  }

  private BookingDate getArrivalDate() {
    return arrivalDate == null ? BookingDate.now() : arrivalDate;
  }

  private int getNumberOfNights() {
    return numberOfNights == UNSET_NUMERIC ? DEFAULT_NUMBER_OF_NIGHTS : numberOfNights;
  }

  private double getMaxDistance() {
    return maxDistance == UNSET_NUMERIC ? DEFAULT_MAX_DISTANCE : maxDistance;
  }
}
