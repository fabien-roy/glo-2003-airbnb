package ca.ulaval.glo2003.beds.converters.validators;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.exceptions.SweetToothDependencyException;
import java.util.Arrays;
import java.util.List;

public class SweetToothValidator implements PackageValidator {

  @Override
  public boolean isPackage(Packages packages) {
    return packages.equals(Packages.SWEET_TOOTH);
  }

  @Override
  public List<Packages> get() {
    return Arrays.asList(Packages.BLOODTHIRSTY, Packages.ALL_YOU_CAN_DRINK);
  }

  @Override
  public void throwException() {
    throw new SweetToothDependencyException();
  }
}
