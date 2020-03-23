package ca.ulaval.glo2003.locations.infrastructure;

import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.exceptions.InvalidZipCodeException;
import ca.ulaval.glo2003.locations.exceptions.NonExistingZipCodeException;
import ca.ulaval.glo2003.locations.exceptions.UnreachableZippopotamusServerException;
import ca.ulaval.glo2003.locations.rest.LocationResponse;
import ca.ulaval.glo2003.locations.rest.PlaceResponse;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ZippopotamusClient {

  private static final String ZIPPOPOTAMUS_URL = "http://api.zippopotam.us/us/";

  public Location validateZipCode(String zipCode) throws IOException {
    validateZipCodeFormat(zipCode);
    validateZipCodeExistence(zipCode);
    PlaceResponse place = getLocationResponse(zipCode).getPlaces().get(0);
    return new Location(zipCode, place.getLatitude(), place.getLongitude());
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
      response = getResponseForLocation(zipCodeValue);
    } catch (IOException ex) {
      throw new UnreachableZippopotamusServerException();
    }

    if (response != HttpURLConnection.HTTP_OK) throw new NonExistingZipCodeException();
  }

  private int getResponseForLocation(String zipCodeValue) throws IOException {
    HttpURLConnection connection = buildUrlConnection(zipCodeValue);
    connection.setRequestMethod("GET");
    return connection.getResponseCode();
  }

  protected HttpURLConnection buildUrlConnection(String zipCodeValue) throws IOException {
    URL url = new URL(ZIPPOPOTAMUS_URL + zipCodeValue);
    return (HttpURLConnection) url.openConnection();
  }

  public LocationResponse getLocationResponse(String zipCodeValue) throws IOException {
    HttpURLConnection connection = buildUrlConnection(zipCodeValue);
    connection.connect();
    Gson gson = new Gson();
    return gson.fromJson(
        new InputStreamReader((InputStream) connection.getContent()), LocationResponse.class);
  }
}
