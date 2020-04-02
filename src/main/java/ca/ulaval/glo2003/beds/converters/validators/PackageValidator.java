package ca.ulaval.glo2003.beds.converters.validators;

import ca.ulaval.glo2003.beds.domain.Packages;
import java.util.Set;

public interface PackageValidator {

  boolean isForPackage(Packages packages);

  void validate(Set<Packages> packages);
}
