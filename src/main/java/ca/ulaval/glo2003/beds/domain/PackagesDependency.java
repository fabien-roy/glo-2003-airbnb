package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.rest.exceptions.AllYouCanDrinkDependencyException;
import ca.ulaval.glo2003.beds.rest.exceptions.SweetToothDependencyException;
import java.util.HashMap;
import java.util.Map;

public class PackagesDependency {

  private static class DependencyObject {
    private Packages dependency;
    private Exception exception;

    public DependencyObject(Packages dependency, Exception exception) {
      this.dependency = dependency;
      this.exception = exception;
    }

    public Exception getException() {
      return exception;
    }

    public Packages getDependency() {
      return dependency;
    }
  }

  private static final Map<Packages, DependencyObject> dependencies;

  static {
    Map<Packages, DependencyObject> modifiableBaseRates = new HashMap<Packages, DependencyObject>();
    DependencyObject BTDependencies = new DependencyObject(null, null);
    DependencyObject AYCDDependencies =
        new DependencyObject(Packages.BLOODTHIRSTY, new AllYouCanDrinkDependencyException());
    DependencyObject STDependencies =
        new DependencyObject(Packages.ALL_YOU_CAN_DRINK, new SweetToothDependencyException());
    modifiableBaseRates.put(null, null);
    modifiableBaseRates.put(Packages.BLOODTHIRSTY, BTDependencies);
    modifiableBaseRates.put(Packages.ALL_YOU_CAN_DRINK, AYCDDependencies);
    modifiableBaseRates.put(Packages.SWEET_TOOTH, STDependencies);
    dependencies = modifiableBaseRates;
  }

  public static DependencyObject get(Packages packages) {
    return dependencies.get(packages);
  }

  public static Packages getDependency(Packages packages) {
    return dependencies.get(packages).getDependency();
  }

  public static Exception getException(Packages packages) {
    return dependencies.get(packages).getException();
  }
}
