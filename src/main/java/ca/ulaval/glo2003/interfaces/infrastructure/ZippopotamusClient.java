package ca.ulaval.glo2003.interfaces.infrastructure;

import ca.ulaval.glo2003.interfaces.domain.ZipCode;
import ca.ulaval.glo2003.interfaces.rest.exceptions.InvalidZipCodeException;
import ca.ulaval.glo2003.interfaces.rest.exceptions.NonExistingZipCodeException;
import ca.ulaval.glo2003.interfaces.rest.exceptions.UnreachableZippopotamusServerException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ZippopotamusClient {

  private static final String ZIPPOPOTAMUS_URL = "http://api.zippopotam.us/us/";

  private ZipCode center;

  public ZipCode getCenter() {
    return center;
  }

  public void setCenter(String zipCode) {
    validateZipCode(zipCode);
    this.center = new ZipCode(zipCode);
  }

  public void validateZipCode(String zipCode) {
    validateZipCodeFormat(zipCode);
    validateZipCodeExistence(zipCode);
  }

  private void validateZipCodeFormat(String zipCode) {
    if (zipCode.length() != 5) {
      throw new InvalidZipCodeException();
    }
    try {
      Double.parseDouble(zipCode);
    } catch (NumberFormatException e) {
      throw new InvalidZipCodeException();
    }
  }

  private void validateZipCodeExistence(String zipCode) {
    int response;

    try {
      response = getResponseForZipCode(zipCode);
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

  protected HttpURLConnection buildUrlConnection(String zipCode) throws IOException {
    URL url = new URL(ZIPPOPOTAMUS_URL + zipCode);
    return (HttpURLConnection) url.openConnection();
  }
}
