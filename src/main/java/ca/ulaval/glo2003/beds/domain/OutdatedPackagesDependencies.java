package ca.ulaval.glo2003.beds.domain;

import ca.ulaval.glo2003.beds.exceptions.AllYouCanDrinkDependencyException;
import ca.ulaval.glo2003.beds.exceptions.SweetToothDependencyException;
import java.util.HashMap;
import java.util.Map;

public class OutdatedPackagesDependencies {

  private static class DependencyObject {
    private Packages dependency;
    private RuntimeException exception;

    public DependencyObject(Packages dependency, RuntimeException exception) {
      this.dependency = dependency;
      this.exception = exception;
    }

    public RuntimeException getException() {
      return exception;
    }

    public Packages getDependency() {
      return dependency;
    }
  }

  private static final Map<Packages, DependencyObject> dependencies;

  static {
    Map<Packages, DependencyObject> dependenciesObject = new HashMap<Packages, DependencyObject>();
    DependencyObject BTDependencies = new DependencyObject(null, null);
    DependencyObject AYCDDependencies =
        new DependencyObject(Packages.BLOODTHIRSTY, new AllYouCanDrinkDependencyException());
    DependencyObject STDependencies =
        new DependencyObject(Packages.ALL_YOU_CAN_DRINK, new SweetToothDependencyException());
    dependenciesObject.put(null, null);
    dependenciesObject.put(Packages.BLOODTHIRSTY, BTDependencies);
    dependenciesObject.put(Packages.ALL_YOU_CAN_DRINK, AYCDDependencies);
    dependenciesObject.put(Packages.SWEET_TOOTH, STDependencies);
    dependencies = dependenciesObject;
  }

  public static DependencyObject get(Packages packages) {
    return dependencies.get(packages);
  }

  public static Packages getDependency(Packages packages) {
    return dependencies.get(packages).getDependency();
  }

  public static RuntimeException getException(Packages packages) {
    return dependencies.get(packages).getException();
  }

  public static void throwException(Packages packageName) {
    throw dependencies.get(packageName).getException();
  }
}
