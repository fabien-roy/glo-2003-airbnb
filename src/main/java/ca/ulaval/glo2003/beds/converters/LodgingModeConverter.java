package ca.ulaval.glo2003.beds.converters;

import ca.ulaval.glo2003.beds.domain.*;
import javax.inject.Inject;

public class LodgingModeConverter {

  private final LodgingModeBuilder lodgingModeBuilder;

  @Inject
  public LodgingModeConverter(LodgingModeBuilder lodgingModeBuilder) {
    this.lodgingModeBuilder = lodgingModeBuilder;
  }

  public LodgingMode fromString(String nullableLodgingMode) {
    return nullableLodgingMode == null
        ? lodgingModeBuilder.aLodgingMode().build()
        : lodgingModeBuilder.aLodgingMode().withType(LodgingModes.get(nullableLodgingMode)).build();
  }

  public String toString(LodgingMode lodgingMode) {
    return lodgingMode.getName().toString();
  }
}
