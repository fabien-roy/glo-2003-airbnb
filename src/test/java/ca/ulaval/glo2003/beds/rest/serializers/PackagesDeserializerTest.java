package ca.ulaval.glo2003.beds.rest.serializers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.exceptions.InvalidPackagesException;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PackagesDeserializerTest {

  private static PackagesDeserializer packagesDeserializer;
  private static JsonParser jsonParser = mock(JsonParser.class);
  private static DeserializationContext deserializationContext = mock(DeserializationContext.class);

  private static PackageRequest firstPackage = mock(PackageRequest.class);
  private static PackageRequest secondPackage = mock(PackageRequest.class);
  private static Iterator iterator = mock(Iterator.class);

  @BeforeAll
  public static void setUpDeserializer() {
    packagesDeserializer = new PackagesDeserializer();
  }

  @BeforeEach
  public void setUpMocks() throws IOException {
    reset(jsonParser, iterator);
    when(jsonParser.getCurrentToken()).thenReturn(JsonToken.START_ARRAY);
    when(jsonParser.nextToken())
        .thenReturn(JsonToken.VALUE_EMBEDDED_OBJECT)
        .thenReturn(JsonToken.VALUE_EMBEDDED_OBJECT)
        .thenReturn(JsonToken.END_ARRAY);
    when(jsonParser.readValuesAs(PackageRequest.class)).thenReturn(iterator);
    when(iterator.next()).thenReturn(firstPackage).thenReturn(secondPackage);
  }

  @Test
  public void deserialize_withSinglePackage_shouldGetPackage() throws IOException {
    when(jsonParser.nextToken())
        .thenReturn(JsonToken.VALUE_EMBEDDED_OBJECT)
        .thenReturn(JsonToken.END_ARRAY);

    List<PackageRequest> packages =
        packagesDeserializer.deserialize(jsonParser, deserializationContext);

    assertEquals(1, packages.size());
    assertEquals(firstPackage, packages.get(0));
  }

  @Test
  public void deserialize_withMultiplePackages_shouldGetPackage() {
    List<PackageRequest> packages =
        packagesDeserializer.deserialize(jsonParser, deserializationContext);

    assertEquals(2, packages.size());
    assertTrue(packages.contains(firstPackage));
    assertTrue(packages.contains(secondPackage));
  }

  @Test
  public void deserialize_withInvalidString_shouldInvalidPackagesException() throws IOException {
    when(jsonParser.readValuesAs(PackageRequest.class)).thenThrow(new InvalidPackagesException());

    assertThrows(
        InvalidPackagesException.class,
        () -> packagesDeserializer.deserialize(jsonParser, deserializationContext));
  }
}
