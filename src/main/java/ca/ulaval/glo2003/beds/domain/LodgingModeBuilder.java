package ca.ulaval.glo2003.beds.domain;

public class LodgingModeBuilder {

  private static LodgingModes DEFAULT_LODGING_MODE_TYPE = LodgingModes.PRIVATE;

  private LodgingModes lodgingModeType = DEFAULT_LODGING_MODE_TYPE;

  public LodgingModeBuilder aLodgingMode() {
    return new LodgingModeBuilder();
  }

  public LodgingModeBuilder withType(LodgingModes lodgingModeType) {
    this.lodgingModeType = lodgingModeType;
    return this;
  }

  public LodgingMode build() {
    switch (lodgingModeType) {
      case COHABITATION:
        return new CohabitationLodgingMode();
      default:
      case PRIVATE:
        return new PrivateLodgingMode();
    }
  }
}
