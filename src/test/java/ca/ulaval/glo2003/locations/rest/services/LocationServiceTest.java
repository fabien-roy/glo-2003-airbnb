package ca.ulaval.glo2003.locations.rest.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.exceptions.InvalidZipCodeException;
import ca.ulaval.glo2003.locations.exceptions.NonExistingZipCodeException;
import ca.ulaval.glo2003.locations.exceptions.UnreachableZippopotamusServerException;
import java.io.IOException;
import java.net.HttpURLConnection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LocationServiceTest {

  private static LocationService locationService;
  private static HttpURLConnection fakeURLConnection;

  private static final Location LOCATION = new Location("12345", "42.8333", "-74.058");

  @BeforeAll
  public static void setUpService() {
    locationService = mock(LocationService.class);
    fakeURLConnection = mock(HttpURLConnection.class);
  }

  @BeforeEach
  public void setUpMocks() throws IOException {
    when(fakeURLConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
  }

  @Test
  public void getLocation_withValidZipCode_shouldReturnLocation() throws IOException {
    Location actualLocation = locationService.getLocation(LOCATION.getZipCode());

    assertEquals(LOCATION, actualLocation);
  }

  @Test
  public void getLocation_withNonExistingZipCode_shouldThrowNonExistingZipCodeException()
      throws IOException {
    when(fakeURLConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_NOT_FOUND);

    assertThrows(
        NonExistingZipCodeException.class,
        () -> locationService.getLocation(LOCATION.getZipCode()));
  }

  @Test
  public void
      getLocation_withUnreachableZippopotamusServer_shouldThrowUnreachableZippopotamusServerException()
          throws IOException {
    when(fakeURLConnection.getResponseCode())
        .thenThrow(new IOException())
        .thenReturn(HttpURLConnection.HTTP_OK);

    assertThrows(
        UnreachableZippopotamusServerException.class,
        () -> locationService.getLocation(LOCATION.getZipCode()));
  }

  @Test
  void getLocation_withInvalidZipCodeLength_shouldThrowNonExistingZipCodeException() {
    String invalidZipCode = "0000";

    assertThrows(InvalidZipCodeException.class, () -> locationService.getLocation(invalidZipCode));
  }

  @Test
  void getLocation_withNotAllNumericZipCode_shouldThrowNonExistingZipCodeException() {
    String invalidZipCode = "00A2E";

    assertThrows(InvalidZipCodeException.class, () -> locationService.getLocation(invalidZipCode));
  }
}
