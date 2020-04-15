package ca.ulaval.glo2003.beds.converters.validators;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.errors.exceptions.TestingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PackageValidatorTest {

  private static PackageValidator packageValidator;
  private static Packages firstDependency = Packages.BLOODTHIRSTY;
  private static Packages secondDependency = Packages.ALL_YOU_CAN_DRINK;
  private static List<Packages> dependencies = Arrays.asList(firstDependency, secondDependency);

  @BeforeAll
  public static void setUpValidator() {
    packageValidator = new FakePackageValidator(dependencies);
  }

  @Test
  public void validate_withAllDependencies_shouldNotThrowException() {
    List<Packages> packages = new ArrayList<>(dependencies);

    assertDoesNotThrow(() -> packageValidator.validate(packages));
  }

  @Test
  public void validate_withNotAllDependencies_shouldThrowException() {
    List<Packages> packages = Collections.singletonList(firstDependency);

    assertThrows(TestingException.class, () -> packageValidator.validate(packages));
  }

  @Test
  public void validate_withoutDependencies_shouldThrowException() {
    List<Packages> packages = Collections.emptyList();

    assertThrows(Exception.class, () -> packageValidator.validate(packages));
  }
}
