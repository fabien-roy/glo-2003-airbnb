package ca.ulaval.glo2003.beds.rest.serializers;

import ca.ulaval.glo2003.beds.exceptions.InvalidPackagesException;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import ca.ulaval.glo2003.parsers.domain.serializers.AbstractDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.util.ArrayList;
import java.util.List;

public class PackagesDeserializer
    extends AbstractDeserializer<PackageRequest[], InvalidPackagesException> {

  public PackagesDeserializer() {
    super(PackageRequest[].class);
  }

  @Override
  public Class<?> getType() {
    return PackageRequest[].class;
  }

  @Override
  public void throwException() throws InvalidPackagesException {
    throw new InvalidPackagesException();
  }

  // TODO : This is duplicated in BloodTypesDeserializer. How about an abstract class to check if
  // not empty
  @Override
  public PackageRequest[] deserialize(
      JsonParser jsonParser, DeserializationContext deserializationContext)
      throws InvalidPackagesException {
    if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
      List<PackageRequest> packages = new ArrayList<>();

      try {
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
          packages.add(jsonParser.readValuesAs(PackageRequest.class).next());
        }
      } catch (Exception e) {
        throwException();
        return new PackageRequest[0]; // TODO : Return nothing
      }

      return packages.toArray(new PackageRequest[0]);
    }

    throwException();
    return new PackageRequest[0]; // TODO : Return nothing
  }
}
