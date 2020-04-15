package ca.ulaval.glo2003.beds.rest.serializers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo2003.beds.exceptions.InvalidPackageException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PackageNameDeserializerTest {

  private static PackageNameDeserializer packageNameDeserializer;

  @BeforeAll
  public static void setUpDeserializer() {
    packageNameDeserializer = new PackageNameDeserializer();
  }

  @Test
  public void throwException_shouldThrowInvalidPackageException() {
    assertThrows(InvalidPackageException.class, () -> packageNameDeserializer.throwException());
  }
}
