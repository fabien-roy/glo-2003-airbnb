package ca.ulaval.glo2003.beds.rest.serializers;

import ca.ulaval.glo2003.beds.exceptions.InvalidPackagesException;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import ca.ulaval.glo2003.interfaces.rest.serializers.ListDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PackagesDeserializer
    extends ListDeserializer<PackageRequest, InvalidPackagesException> {

  List<PackageRequest> packages = new ArrayList<>();

  protected PackagesDeserializer() {
    super();
  }

  protected PackagesDeserializer(BeanProperty property) {
    super(property);
  }

  @Override
  public void throwException() throws InvalidPackagesException {
    throw new InvalidPackagesException();
  }

  @Override
  protected void resetCollection() {
    packages = new ArrayList<>();
  }

  @Override
  protected void addElement(JsonParser jsonParser) throws IOException {
    packages.add(jsonParser.readValuesAs(PackageRequest.class).next());
  }

  @Override
  protected List<PackageRequest> buildDeserializedCollection() {
    return packages;
  }

  @Override
  public JsonDeserializer<?> createContextual(
      DeserializationContext deserializationContext, BeanProperty property) {
    return new PackagesDeserializer(property);
  }
}
