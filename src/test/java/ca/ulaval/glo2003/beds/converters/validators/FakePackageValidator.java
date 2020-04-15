package ca.ulaval.glo2003.beds.converters.validators;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.errors.exceptions.TestingException;
import java.util.List;

public class FakePackageValidator extends PackageValidator {

  private final List<Packages> dependencies;

  public FakePackageValidator(List<Packages> dependencies) {
    this.dependencies = dependencies;
  }

  @Override
  public boolean isForPackage(Packages packages) {
    return true;
  }

  @Override
  protected List<Packages> getDependencies() {
    return dependencies;
  }

  @Override
  public void throwException() {
    throw new TestingException();
  }
}
