package ca.ulaval.glo2003.locations.clients;

import static ca.ulaval.glo2003.locations.clients.helpers.LocationResponseBuilder.aLocationResponse;
import static ca.ulaval.glo2003.locations.domain.helpers.LocationBuilder.aLocation;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.locations.converters.LocationConverter;
import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.exceptions.NonExistingZipCodeException;
import ca.ulaval.glo2003.locations.exceptions.UnreachableZippopotamusServerException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ZippopotamusClientTest {

  private static ZippopotamusClient zippopotamusClient;
  private static LocationMapper locationMapper = mock(LocationMapper.class);
  private static LocationConverter locationConverter = mock(LocationConverter.class);
  private static HttpURLConnection fakeUrlConnection = mock(HttpURLConnection.class);

  private static final Location location = aLocation().build();
  private static final LocationResponse locationResponse = aLocationResponse().build();

  @BeforeAll
  public static void setUpClient() {
    zippopotamusClient =
        new FakeZippopotamusClient(locationMapper, locationConverter, fakeUrlConnection);
  }

  @BeforeEach
  public void setUpMocks() throws IOException {
    InputStream inputStream = new ByteArrayInputStream("inputStream".getBytes());
    when(fakeUrlConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
    when(fakeUrlConnection.getInputStream()).thenReturn(inputStream);
    when(locationMapper.readValue(inputStream, LocationResponse.class))
        .thenReturn(locationResponse);
    when(locationConverter.fromResponse(locationResponse)).thenReturn(location);
  }

  @Test
  public void getLocation_shouldGetLocationFromServer() {
    Location actualLocation = zippopotamusClient.getLocation(location.getZipCode());

    assertEquals(location, actualLocation);
  }

  @Test
  public void getLocation_withNonExistingZipCode_shouldThrowNonExistingZipCodeException()
      throws IOException {
    when(fakeUrlConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_NOT_FOUND);

    assertThrows(
        NonExistingZipCodeException.class,
        () -> zippopotamusClient.getLocation(location.getZipCode()));
  }

  @Test
  public void
      getLocation_withUnreachableZippopotamusServer_shouldThrowUnreachableZippopotamusServerException()
          throws IOException {
    when(fakeUrlConnection.getResponseCode())
        .thenThrow(new IOException())
        .thenReturn(HttpURLConnection.HTTP_OK);

    assertThrows(
        UnreachableZippopotamusServerException.class,
        () -> zippopotamusClient.getLocation(location.getZipCode()));
  }
}
