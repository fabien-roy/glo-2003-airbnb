package ca.ulaval.glo2003.beds.domain.validator;

import ca.ulaval.glo2003.beds.domain.Packages;
import java.util.List;

public interface PackageValidator {

  boolean validate(Packages packages);

  List<Packages> get();

  void throwException();
}
