package ca.ulaval.glo2003.beds.converters.validators;

import ca.ulaval.glo2003.beds.domain.Packages;
import java.util.List;

public interface PackageValidator {

  boolean isPackage(Packages packages);

  List<Packages> get();

  void throwException();
}
