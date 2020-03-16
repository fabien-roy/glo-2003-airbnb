package ca.ulaval.glo2003.interfaces.clients;

import ca.ulaval.glo2003.interfaces.clients.exceptions.NonExistingZipCodeException;
import ca.ulaval.glo2003.interfaces.clients.exceptions.UnreachableZippopotamusServerException;
import ca.ulaval.glo2003.interfaces.domain.ZipCode;
import ca.ulaval.glo2003.interfaces.rest.exceptions.InvalidZipCodeException;
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
      HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
      conection.setRequestMethod("GET");
      this.responseStatusCode = conection.getResponseCode();
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
