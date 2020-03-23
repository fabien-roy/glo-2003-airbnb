package ca.ulaval.glo2003.locations.infrastructure;

import ca.ulaval.glo2003.locations.mappers.LocationMapper;
import java.net.HttpURLConnection;

public class FakeZippopotamusClient extends ZippopotamusClient {

  private final HttpURLConnection fakeUrlConnection;

  public FakeZippopotamusClient(
      LocationMapper locationMapper, HttpURLConnection fakeUrlConnection) {
    super(locationMapper);
    this.fakeUrlConnection = fakeUrlConnection;
  }

  @Override
  protected HttpURLConnection buildUrlConnection(String zipCode) {
    return fakeUrlConnection;
  }
}
