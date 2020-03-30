package ca.ulaval.glo2003.locations.clients;

import ca.ulaval.glo2003.interfaces.domain.AbstractMapper;
import ca.ulaval.glo2003.locations.clients.serializers.LocationDeserializingModule;
import ca.ulaval.glo2003.locations.clients.serializers.LocationSerializingModule;
import javax.inject.Inject;

public class LocationMapper extends AbstractMapper {

  @Inject
  public LocationMapper(
      LocationSerializingModule serializingModule,
      LocationDeserializingModule deserializingModule) {
    super(serializingModule, deserializingModule);
  }
}
