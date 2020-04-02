package ca.ulaval.glo2003.beds.converters.validators;

import ca.ulaval.glo2003.beds.domain.Packages;
import java.util.List;
import java.util.Set;

public abstract class AbstractPackageValidator implements PackageValidator {

  protected abstract List<Packages> dependencies();

  protected abstract void throwException();

  @Override
  public void validate(Set<Packages> packages) {
    if (!packages.containsAll(dependencies())) throwException();
  }
}
