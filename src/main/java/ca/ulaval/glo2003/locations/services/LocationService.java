package ca.ulaval.glo2003.locations.services;

import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.exceptions.InvalidZipCodeException;
import ca.ulaval.glo2003.locations.exceptions.NonExistingZipCodeException;
import ca.ulaval.glo2003.locations.exceptions.UnreachableZippopotamusServerException;
import ca.ulaval.glo2003.locations.infrastructure.LocationResponse;
import ca.ulaval.glo2003.locations.infrastructure.mappers.LocationMapper;
import com.google.gson.Gson;
import com.google.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LocationService {

  private static final String ZIPPOPOTAMUS_URL = "http://api.zippopotam.us/us/";
  private final LocationMapper locationMapper;

  @Inject
  public LocationService(LocationMapper locationMapper) {
    this.locationMapper = locationMapper;
  }

  public Location getLocation(String zipCode) {
    try {
      return locationMapper.fromResponse(getByZipCode(zipCode));
    } catch (IOException e) {
      return null; // TODO : This is why ZippopotamusClient is important.
    }
  }

  public LocationResponse getByZipCode(String zipCode) throws IOException {
    validateZipCodeFormat(zipCode);
    validateZipCodeExistence(zipCode);
    HttpURLConnection connection = buildUrlConnection(zipCode);
    connection.connect();
    Gson gson = new Gson();
    return gson.fromJson(
        new InputStreamReader((InputStream) connection.getContent()), LocationResponse.class);
  }

  protected HttpURLConnection buildUrlConnection(String zipCodeValue) throws IOException {
    URL url = new URL(ZIPPOPOTAMUS_URL + zipCodeValue);
    return (HttpURLConnection) url.openConnection();
  }

  private void validateZipCodeFormat(String zipCodeValue) {
    if (zipCodeValue.length() != 5) {
      throw new InvalidZipCodeException();
    }
    try {
      Double.parseDouble(zipCodeValue);
    } catch (NumberFormatException e) {
      throw new InvalidZipCodeException();
    }
  }

  private void validateZipCodeExistence(String zipCodeValue) {
    int response;

    try {
      response = getResponseForZipCode(zipCodeValue);
    } catch (IOException ex) {
      throw new UnreachableZippopotamusServerException();
    }

    if (response != HttpURLConnection.HTTP_OK) throw new NonExistingZipCodeException();
  }

  private int getResponseForZipCode(String zipCode) throws IOException {
    HttpURLConnection connection = buildUrlConnection(zipCode);
    connection.setRequestMethod("GET");
    return connection.getResponseCode();
  }
}
