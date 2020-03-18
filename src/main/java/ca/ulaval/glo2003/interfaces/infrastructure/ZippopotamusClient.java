package ca.ulaval.glo2003.interfaces.infrastructure;

import ca.ulaval.glo2003.interfaces.domain.ZipCode;
import ca.ulaval.glo2003.interfaces.rest.exceptions.InvalidZipCodeException;
import ca.ulaval.glo2003.interfaces.rest.exceptions.NonExistingZipCodeException;
import ca.ulaval.glo2003.interfaces.rest.exceptions.UnreachableZippopotamusServerException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ZippopotamusClient {

  private String zipCodeValue;
  private ZipCode zipCode;
  int responseStatusCode;

  public ZippopotamusClient() {}

  public ZipCode getZipCode() {
    return zipCode;
  }

  public void initiate(String zipCode) {
    this.zipCodeValue = zipCode;
    String host = "http://api.zippopotam.us/us/";
    try {
      URL urlForGetRequest = new URL(host + zipCode);
      HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
      connection.setRequestMethod("GET");
      this.responseStatusCode = connection.getResponseCode();
    } catch (IOException ex) {
      throw new UnreachableZippopotamusServerException();
    }
    // TODO le nouveau ZipCode devrait être créé avec les données en réponse
    this.zipCode = new ZipCode(zipCodeValue);
  }

  public void validateZipCode() {
    validateZipCodeFormat(zipCodeValue);
    if (this.responseStatusCode != HttpURLConnection.HTTP_OK) {
      throw new NonExistingZipCodeException();
    }
  }

  private static void validateZipCodeFormat(String zipCode) {
    if (zipCode.length() != 5) {
      throw new InvalidZipCodeException();
    }
    try {
      Double.parseDouble(zipCode);
    } catch (NumberFormatException e) {
      throw new InvalidZipCodeException();
    }
  }
}
