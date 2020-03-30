package ca.ulaval.glo2003.beds.domain.validator;

import ca.ulaval.glo2003.beds.domain.Packages;
import java.util.Collections;
import java.util.List;

public class BloodthirstyValidator implements PackageValidator {

  @Override
  public boolean validate(Packages packages) {
    return packages.equals(Packages.BLOODTHIRSTY);
  }

  @Override
  public List<Packages> get() {
    return Collections.emptyList();
  }

  @Override
  public void throwException() {
    ;
  }
}
