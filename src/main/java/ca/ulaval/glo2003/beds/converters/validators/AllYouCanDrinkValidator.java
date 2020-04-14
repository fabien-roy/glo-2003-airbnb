package ca.ulaval.glo2003.beds.converters.validators;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.exceptions.AllYouCanDrinkDependencyException;
import java.util.Collections;
import java.util.List;

public class AllYouCanDrinkValidator extends PackageValidator {

  @Override
  public boolean isForPackage(Packages packages) {
    return packages.equals(Packages.ALL_YOU_CAN_DRINK);
  }

  @Override
  protected List<Packages> dependencies() {
    return Collections.singletonList(Packages.BLOODTHIRSTY);
  }

  @Override
  public void throwException() {
    throw new AllYouCanDrinkDependencyException();
  }
}
