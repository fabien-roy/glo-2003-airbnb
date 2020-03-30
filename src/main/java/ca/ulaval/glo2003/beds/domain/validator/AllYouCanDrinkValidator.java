package ca.ulaval.glo2003.beds.domain.validator;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.exceptions.AllYouCanDrinkDependencyException;
import java.util.Collections;
import java.util.List;

public class AllYouCanDrinkValidator implements PackageValidator {

  @Override
  public boolean validate(Packages packages) {
    return packages.equals(Packages.ALL_YOU_CAN_DRINK);
  }

  @Override
  public List<Packages> get() {
    return Collections.singletonList(Packages.BLOODTHIRSTY);
  }

  @Override
  public void throwException() {
    throw new AllYouCanDrinkDependencyException();
  }
}
