package ca.ulaval.glo2003.locations.infrastructure;

import java.net.HttpURLConnection;

public class FakeZippopotamusClient extends ZippopotamusClient {

  private final HttpURLConnection fakeUrlConnection;

  public FakeZippopotamusClient(HttpURLConnection fakeUrlConnection) {
    this.fakeUrlConnection = fakeUrlConnection;
  }

  @Override
  protected HttpURLConnection buildUrlConnection(String zipCode) {
    return fakeUrlConnection;
  }
}
