package ca.ulaval.glo2003.beds.domain.helpers;

import static ca.ulaval.glo2003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo2003.beds.domain.LodgingModes;

public class LodgingModeObjectMother {

  private LodgingModeObjectMother() {}

  public static LodgingModes createLodgingModeType() {
    return randomEnum(LodgingModes.class);
  }
}
