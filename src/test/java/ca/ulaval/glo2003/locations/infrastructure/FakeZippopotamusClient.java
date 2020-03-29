package ca.ulaval.glo2003.locations.infrastructure;

import ca.ulaval.glo2003.locations.converters.LocationConverter;
import java.net.HttpURLConnection;

public class FakeZippopotamusClient extends ZippopotamusClient {

  private final HttpURLConnection fakeUrlConnection;

  public FakeZippopotamusClient(
      LocationConverter locationConverter, HttpURLConnection fakeUrlConnection) {
    super(locationConverter);
    this.fakeUrlConnection = fakeUrlConnection;
  }

  @Override
  protected HttpURLConnection buildUrlConnection(String zipCode) {
    return fakeUrlConnection;
  }
}
