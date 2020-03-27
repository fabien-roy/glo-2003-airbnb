package ca.ulaval.glo2003.beds.rest.serializers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.exceptions.InvalidPackagesException;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PackagesDeserializerTest {

  private static PackagesDeserializer packagesDeserializer;
  private static JsonParser jsonParser = mock(JsonParser.class);
  private static DeserializationContext deserializationContext = mock(DeserializationContext.class);

  private static Iterator<PackageRequest> jsonIterator = mock(Iterator.class);
  private static PackageRequest firstPackage = mock(PackageRequest.class);
  private static PackageRequest secondPackage = mock(PackageRequest.class);

  @BeforeAll
  public static void setUpDeserializer() {
    packagesDeserializer = new PackagesDeserializer();
  }

  @BeforeEach
  public void setUpMocks() throws IOException {
    reset(jsonParser, jsonIterator);
    when(jsonParser.getCurrentToken()).thenReturn(JsonToken.START_ARRAY);
    when(jsonParser.nextToken())
        .thenReturn(JsonToken.VALUE_EMBEDDED_OBJECT)
        .thenReturn(JsonToken.VALUE_EMBEDDED_OBJECT)
        .thenReturn(JsonToken.END_ARRAY);
    when(jsonParser.readValuesAs(PackageRequest.class)).thenReturn(jsonIterator);
    when(jsonIterator.next()).thenReturn(firstPackage).thenReturn(secondPackage);
  }

  @Test
  public void deserialize_shouldReturnPackageArray() {
    List<PackageRequest> packages =
        Arrays.asList(packagesDeserializer.deserialize(jsonParser, deserializationContext));

    assertEquals(2, packages.size());
    assertTrue(packages.contains(firstPackage));
    assertTrue(packages.contains(secondPackage));
  }

  @Test
  public void deserialize_withInvalidPackage_shouldThrowInvalidPackagesException() {
    when(jsonParser.getCurrentToken()).thenReturn(JsonToken.NOT_AVAILABLE);

    assertThrows(
        InvalidPackagesException.class,
        () -> packagesDeserializer.deserialize(jsonParser, deserializationContext));
  }

  @Test
  public void deserialize_withInvalidValue_shouldThrowInvalidPackagesException()
      throws IOException {
    when(jsonParser.readValuesAs(PackageRequest.class)).thenThrow(new IOException());

    assertThrows(
        InvalidPackagesException.class,
        () -> packagesDeserializer.deserialize(jsonParser, deserializationContext));
  }

  @Test
  public void deserialize_withEmptyArray_shouldThrowInvalidPackagesException() throws IOException {
    when(jsonParser.getCurrentToken()).thenReturn(JsonToken.START_ARRAY);
    when(jsonParser.nextToken()).thenReturn(JsonToken.END_ARRAY);

    assertThrows(
        InvalidPackagesException.class,
        () -> packagesDeserializer.deserialize(jsonParser, deserializationContext));
  }
}
