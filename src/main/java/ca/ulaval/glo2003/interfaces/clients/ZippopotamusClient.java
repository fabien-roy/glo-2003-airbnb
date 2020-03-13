package ca.ulaval.glo2003.interfaces.clients;

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
    } catch (IOException ex) {
      // TODO
    }
  }
}
