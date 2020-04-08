package ca.ulaval.glo2003.locations.clients;

import ca.ulaval.glo2003.locations.converters.LocationConverter;
import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.domain.LocationClient;
import ca.ulaval.glo2003.locations.domain.ZipCode;
import ca.ulaval.glo2003.locations.exceptions.NonExistingZipCodeException;
import ca.ulaval.glo2003.locations.exceptions.UnreachableZippopotamusServerException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.inject.Inject;

public class ZippopotamusClient implements LocationClient {

  private static final String ZIPPOPOTAMUS_URL = "http://api.zippopotam.us/us/";

  private final LocationMapper locationMapper;
  private final LocationConverter locationConverter;

  @Inject
  public ZippopotamusClient(LocationMapper locationMapper, LocationConverter locationConverter) {
    this.locationMapper = locationMapper;
    this.locationConverter = locationConverter;
  }

  @Override
  public Location getLocation(ZipCode zipCode) {
    LocationResponse locationResponse = tryGetLocationFromServer(zipCode.toString());
    return locationConverter.fromResponse(locationResponse);
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
    return locationMapper.readValue(connection.getInputStream(), LocationResponse.class);
  }

  protected HttpURLConnection buildUrlConnection(String zipCode) throws IOException {
    URL url = new URL(ZIPPOPOTAMUS_URL + zipCode);
    return (HttpURLConnection) url.openConnection();
  }
}
