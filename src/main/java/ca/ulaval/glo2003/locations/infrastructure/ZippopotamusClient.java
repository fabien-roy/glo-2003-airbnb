package ca.ulaval.glo2003.locations.infrastructure;

import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.domain.LocationClient;
import ca.ulaval.glo2003.locations.domain.ZipCode;
import ca.ulaval.glo2003.locations.exceptions.NonExistingZipCodeException;
import ca.ulaval.glo2003.locations.exceptions.UnreachableZippopotamusServerException;
import ca.ulaval.glo2003.locations.mappers.LocationMapper;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.inject.Inject;

public class ZippopotamusClient implements LocationClient {

  private static final String ZIPPOPOTAMUS_URL = "http://api.zippopotam.us/us/";

  private final LocationMapper locationMapper;

  @Inject
  public ZippopotamusClient(LocationMapper locationMapper) {
    this.locationMapper = locationMapper;
  }

  @Override
  public Location getLocation(ZipCode zipCode) {
    LocationResponse locationResponse = tryGetLocationFromServer(zipCode.getValue());
    return locationMapper.fromResponse(locationResponse);
  }

  private LocationResponse tryGetLocationFromServer(String zipCode) {
    try {
      return getLocationFromServer(zipCode);
    } catch (IOException ex) {
      throw new UnreachableZippopotamusServerException();
    }
  }

  private LocationResponse getLocationFromServer(String zipCode) throws IOException {
    HttpURLConnection connection = buildUrlConnection(zipCode);
    connection.setRequestMethod("GET");
    connection.connect();

    if (connection.getResponseCode() != HttpURLConnection.HTTP_OK)
      throw new NonExistingZipCodeException();

    return readResponse(connection);
  }

  private LocationResponse readResponse(HttpURLConnection connection) throws IOException {
    return new Gson()
        .fromJson(
            new InputStreamReader((InputStream) connection.getContent()), LocationResponse.class);
  }

  protected HttpURLConnection buildUrlConnection(String zipCode) throws IOException {
    URL url = new URL(ZIPPOPOTAMUS_URL + zipCode);
    return (HttpURLConnection) url.openConnection();
  }
}
