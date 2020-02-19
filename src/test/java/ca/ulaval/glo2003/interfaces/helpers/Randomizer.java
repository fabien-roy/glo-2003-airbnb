package ca.ulaval.glo2003.interfaces.helpers;

import java.util.Random;

public class Randomizer {

  // TODO : This was built during testing for Bed.matches(...)
  // TODO : Might turn out irrelevant

  private static final Random RANDOM = new Random();

  private Randomizer() {}

  public static <T extends Enum<?>> T randomEnum(Class<T> enumToRandomize) {
    int x = RANDOM.nextInt(enumToRandomize.getEnumConstants().length);
    return enumToRandomize.getEnumConstants()[x];
  }
}
