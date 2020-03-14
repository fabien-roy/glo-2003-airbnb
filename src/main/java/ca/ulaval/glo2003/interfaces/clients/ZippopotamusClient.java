package ca.ulaval.glo2003.interfaces.clients;

import ca.ulaval.glo2003.interfaces.clients.exceptions.InvalidZipCodeException;
import ca.ulaval.glo2003.interfaces.clients.exceptions.NonExistingZipCodeException;
import ca.ulaval.glo2003.interfaces.clients.exceptions.UnreachableZippopotamusServerException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ZippopotamusClient {

  private String zipCode;
  int responseStatusCode;

  public ZippopotamusClient(String zipCode) {
    this.zipCode = zipCode;
    String host = "http://api.zippopotam.us/us/";
    try {
      URL urlForGetRequest = new URL(host + zipCode);
      HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
      conection.setRequestMethod("GET");
      this.responseStatusCode = conection.getResponseCode();
    } catch (IOException ex) {
      throw new UnreachableZippopotamusServerException();
    }
  }

  public void validateZipCode() {
    validateZipCodeFormat(zipCode);
    if (this.responseStatusCode != HttpURLConnection.HTTP_OK) {
      throw new NonExistingZipCodeException();
    }
  }

  private static void validateZipCodeFormat(String zipCode) {
    try {
      Double.parseDouble(zipCode);
    } catch (NumberFormatException e) {
      throw new InvalidZipCodeException();
    }
    if (zipCode.length() != 5) {
      throw new InvalidZipCodeException();
    }
  }
}
