package ca.ulaval.glo2003.locations.infrastructure;

import static ca.ulaval.glo2003.locations.domain.helpers.LocationBuilder.aLocation;
import static ca.ulaval.glo2003.locations.infrastructure.helpers.LocationResponseBuilder.aLocationResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.mappers.LocationMapper;
import com.google.gson.Gson;
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
  private static HttpURLConnection fakeUrlConnection = mock(HttpURLConnection.class);

  private static final Location location = aLocation().build();
  private static final LocationResponse locationResponse = aLocationResponse().build();

  @BeforeAll
  public static void setUpClient() {
    zippopotamusClient = new FakeZippopotamusClient(locationMapper, fakeUrlConnection);
  }

  @BeforeEach
  public void setUpMocks() throws IOException {
    String content = new Gson().toJson(locationResponse);
    InputStream inputStream = new ByteArrayInputStream(content.getBytes());
    when(fakeUrlConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
    when(fakeUrlConnection.getContent()).thenReturn(inputStream);
    when(locationMapper.fromResponse(any())).thenReturn(location);
  }

  @Test
  public void getLocation_shouldGetLocationFromServer() {
    Location actualLocation = zippopotamusClient.getLocation(location.getZipCode());

    assertEquals(location, actualLocation);
  }

  // TODO : Other tests for ZippopotamusClient
}
