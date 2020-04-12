package ca.ulaval.glo2003.beds.domain.helpers;

import static ca.ulaval.glo2003.beds.domain.helpers.LodgingModeObjectMother.createLodgingModeName;

import ca.ulaval.glo2003.beds.domain.CohabitationLodgingMode;
import ca.ulaval.glo2003.beds.domain.LodgingMode;
import ca.ulaval.glo2003.beds.domain.LodgingModes;
import ca.ulaval.glo2003.beds.domain.PrivateLodgingMode;

public class LodgingModeBuilder {

  private LodgingModeBuilder() {}

  private LodgingModes DEFAULT_LODGING_MODE_TYPE = createLodgingModeName();
  private LodgingModes lodgingModeType = DEFAULT_LODGING_MODE_TYPE;

  public static LodgingModeBuilder aLodgingMode() {
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
