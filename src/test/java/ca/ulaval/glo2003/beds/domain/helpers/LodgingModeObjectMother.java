package ca.ulaval.glo2003.beds.domain.helpers;

import ca.ulaval.glo2003.beds.domain.LodgingModes;

import static ca.ulaval.glo2003.interfaces.domain.helpers.Randomizer.randomEnum;

public class LodgingModeObjectMother {

  private LodgingModeObjectMother() {}

  public static LodgingModes createLodgingModeName() {
    return randomEnum(LodgingModes.class);
  }
}
