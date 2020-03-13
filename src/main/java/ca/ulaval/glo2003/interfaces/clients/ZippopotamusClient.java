package ca.ulaval.glo2003.interfaces.clients;

import ca.ulaval.glo2003.interfaces.clients.exceptions.NonExistingZipCodeException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ZippopotamusClient {

  public ZippopotamusClient() {}

  public static void validateZipCode(String zipCode) {
    String host = "http://api.zippopotam.us/us/";
    try {
      URL urlForGetRequest = new URL(host + zipCode);
      String readLine = null;
      HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
      conection.setRequestMethod("GET");
      int responseCode = conection.getResponseCode();
      if (responseCode != HttpURLConnection.HTTP_OK) {
        throw new NonExistingZipCodeException();
      }
    } catch (IOException ex) {
      // TODO
    }
  }
}
