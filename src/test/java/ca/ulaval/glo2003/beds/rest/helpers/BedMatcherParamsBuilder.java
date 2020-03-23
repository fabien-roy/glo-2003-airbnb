package ca.ulaval.glo2003.beds.rest.helpers;

import static ca.ulaval.glo2003.beds.rest.BedQueryMapBuilder.*;

import java.util.HashMap;
import java.util.Map;

public class BedMatcherParamsBuilder {

  private Map<String, String[]> queryMap = new HashMap<>();

  public BedMatcherParamsBuilder() {}

  public static BedMatcherParamsBuilder aBedMatcherParams() {
    return new BedMatcherParamsBuilder();
  }

  public BedMatcherParamsBuilder withBedType(String[] bedTypeParam) {
    this.queryMap.put(BED_TYPE_PARAM, bedTypeParam);
    return this;
  }

  public BedMatcherParamsBuilder withCleaningFrequency(String[] cleaningFrequency) {
    this.queryMap.put(CLEANING_FREQUENCY_PARAM, cleaningFrequency);
    return this;
  }

  public BedMatcherParamsBuilder withBloodType(String[] bloodType) {
    this.queryMap.put(BLOOD_TYPES_PARAM, bloodType);
    return this;
  }

  public BedMatcherParamsBuilder withMinCapacity(String[] minCapacity) {
    this.queryMap.put(MIN_CAPACITY_PARAM, minCapacity);
    return this;
  }

  public BedMatcherParamsBuilder withPackageName(String[] packageName) {
    this.queryMap.put(PACKAGE_NAME_PARAM, packageName);
    return this;
  }

  public BedMatcherParamsBuilder withOrigin(String[] origin) {
    this.queryMap.put(ORIGIN_PARAM, origin);
    return this;
  }

  public BedMatcherParamsBuilder withMaxDistance(String[] maxDistance) {
    this.queryMap.put(MAX_DISTANCE_PARAM, maxDistance);
    return this;
  }

  public BedMatcherParamsBuilder withArrivalDate(String[] arrivalDate) {
    this.queryMap.put(ARRIVAL_DATE_PARAM, arrivalDate);
    return this;
  }

  public BedMatcherParamsBuilder withNumberOfNights(String[] numberOfNights) {
    this.queryMap.put(NUMBER_OF_NIGHTS_PARAM, numberOfNights);
    return this;
  }

  public BedMatcherParamsBuilder withLodgingMode(String[] lodgingMode) {
    this.queryMap.put(LODGING_MODE_PARAM, lodgingMode);
    return this;
  }

  public Map<String, String[]> build() {
    return queryMap;
  }
}
