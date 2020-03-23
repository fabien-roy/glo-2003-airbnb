package ca.ulaval.glo2003.locations.infrastructure;

import ca.ulaval.glo2003.locations.domain.ZipCode;
import ca.ulaval.glo2003.locations.exceptions.InvalidZipCodeException;
import ca.ulaval.glo2003.locations.exceptions.NonExistingZipCodeException;
import ca.ulaval.glo2003.locations.exceptions.UnreachableZippopotamusServerException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ZippopotamusClient {

  private static final String ZIPPOPOTAMUS_URL = "http://api.zippopotam.us/us/";

  public ZipCode validateZipCode(String zipCodeValue) {
    validateZipCodeFormat(zipCodeValue);
    validateZipCodeExistence(zipCodeValue);
    return new ZipCode(zipCodeValue);
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

  private int getResponseForZipCode(String zipCodeValue) throws IOException {
    HttpURLConnection connection = buildUrlConnection(zipCodeValue);
    connection.setRequestMethod("GET");
    return connection.getResponseCode();
  }

  protected HttpURLConnection buildUrlConnection(String zipCodeValue) throws IOException {
    URL url = new URL(ZIPPOPOTAMUS_URL + zipCodeValue);
    return (HttpURLConnection) url.openConnection();
  }
}
