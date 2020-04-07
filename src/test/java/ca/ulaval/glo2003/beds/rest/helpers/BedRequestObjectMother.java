package ca.ulaval.glo2003.beds.rest.helpers;

import static ca.ulaval.glo2003.beds.domain.helpers.PublicKeyObjectMother.createPublicKey;
import static ca.ulaval.glo2003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo2003.beds.domain.BedTypes;
import ca.ulaval.glo2003.beds.domain.BloodTypes;
import ca.ulaval.glo2003.beds.domain.CleaningFrequencies;
import ca.ulaval.glo2003.beds.domain.LodgingModes;
import com.github.javafaker.Faker;
import java.util.Collections;
import java.util.List;

public class BedRequestObjectMother {

  private static int minMaximalCapacity = 400;

  private BedRequestObjectMother() {}

  public static String createOwnerPublicKey() {
    return createPublicKey().getValue();
  }

  public static String createZipCode() {
    return Faker.instance().address().zipCode();
  }

  public static String createBedType() {
    return randomEnum(BedTypes.class).toString();
  }

  public static String createCleaningFrequency() {
    return randomEnum(CleaningFrequencies.class).toString();
  }

  public static List<String> createBloodTypes() {
    return Collections.singletonList(randomEnum(BloodTypes.class).toString());
  }

  public static int createCapacity() {
    return Faker.instance().number().numberBetween(1, minMaximalCapacity);
  }

  public static String createLodgingMode() {
    return randomEnum(LodgingModes.class).toString();
  }
}
