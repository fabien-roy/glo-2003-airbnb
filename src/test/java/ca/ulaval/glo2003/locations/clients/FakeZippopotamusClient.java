package ca.ulaval.glo2003.locations.clients;

import ca.ulaval.glo2003.locations.converters.LocationConverter;
import java.net.HttpURLConnection;

public class FakeZippopotamusClient extends ZippopotamusClient {

  private final HttpURLConnection fakeUrlConnection;

  public FakeZippopotamusClient(
      LocationMapper locationMapper,
      LocationConverter locationConverter,
      HttpURLConnection fakeUrlConnection) {
    super(locationMapper, locationConverter);
    this.fakeUrlConnection = fakeUrlConnection;
  }

  @Override
  protected HttpURLConnection buildUrlConnection(String zipCode) {
    return fakeUrlConnection;
  }
}
