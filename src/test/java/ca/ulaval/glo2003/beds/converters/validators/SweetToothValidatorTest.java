package ca.ulaval.glo2003.beds.converters.validators;

import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.exceptions.SweetToothDependencyException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SweetToothValidatorTest {

  private static PackageValidator packageValidator;

  @BeforeAll
  public static void setUpValidator() {
    packageValidator = new SweetToothValidator();
  }

  @Test
  public void isForPackage_withSweetToothPackage_shouldReturnTrue() {
    boolean isForPackage = packageValidator.isForPackage(Packages.SWEET_TOOTH);

    assertTrue(isForPackage);
  }

  @Test
  public void isForPackage_withAllYouCanEatPackage_shouldReturnFalse() {
    boolean isForPackage = packageValidator.isForPackage(Packages.ALL_YOU_CAN_DRINK);

    assertFalse(isForPackage);
  }

  @Test
  public void isForPackage_withBloodthirstyPackage_shouldReturnFalse() {
    boolean isForPackage = packageValidator.isForPackage(Packages.BLOODTHIRSTY);

    assertFalse(isForPackage);
  }

  @Test
  public void getDependencies_shouldReturnBloodthirstyAndAllYouCanDrink() {
    List<Packages> expectedDependencies =
        Arrays.asList(Packages.BLOODTHIRSTY, Packages.ALL_YOU_CAN_DRINK);

    List<Packages> dependencies = packageValidator.getDependencies();

    assertEquals(expectedDependencies, dependencies);
  }

  @Test
  public void throwException_shouldThrowSweetToothDependencyException() {
    assertThrows(SweetToothDependencyException.class, () -> packageValidator.throwException());
  }
}
