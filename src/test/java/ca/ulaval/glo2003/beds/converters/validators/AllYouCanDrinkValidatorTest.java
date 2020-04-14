package ca.ulaval.glo2003.beds.converters.validators;

import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.exceptions.AllYouCanDrinkDependencyException;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AllYouCanDrinkValidatorTest {

  private static PackageValidator packageValidator;

  @BeforeAll
  public static void setUpValidator() {
    packageValidator = new AllYouCanDrinkValidator();
  }

  @Test
  public void isForPackage_withAllYouCanEatPackage_shouldReturnTrue() {
    boolean isForPackage = packageValidator.isForPackage(Packages.ALL_YOU_CAN_DRINK);

    assertTrue(isForPackage);
  }

  @Test
  public void isForPackage_withBloodthirstyPackage_shouldReturnFalse() {
    boolean isForPackage = packageValidator.isForPackage(Packages.BLOODTHIRSTY);

    assertFalse(isForPackage);
  }

  @Test
  public void isForPackage_withSweetToothPackage_shouldReturnFalse() {
    boolean isForPackage = packageValidator.isForPackage(Packages.SWEET_TOOTH);

    assertFalse(isForPackage);
  }

  @Test
  public void getDependencies_shouldReturnBloodthirsty() {
    List<Packages> expectedDependencies = Collections.singletonList(Packages.BLOODTHIRSTY);

    List<Packages> dependencies = packageValidator.getDependencies();

    assertEquals(expectedDependencies, dependencies);
  }

  @Test
  public void throwException_shouldThrowAllYouCanDrinkDependencyException() {
    assertThrows(AllYouCanDrinkDependencyException.class, () -> packageValidator.throwException());
  }
}
