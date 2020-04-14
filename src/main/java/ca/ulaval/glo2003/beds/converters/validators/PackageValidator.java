package ca.ulaval.glo2003.beds.converters.validators;

import ca.ulaval.glo2003.beds.domain.Packages;
import java.util.List;

public abstract class PackageValidator {

  public abstract boolean isForPackage(Packages packages);

  protected abstract List<Packages> dependencies();

  protected abstract void throwException();

  public void validate(List<Packages> packages) {
    if (!packages.containsAll(dependencies())) throwException();
  }
}
