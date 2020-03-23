package ca.ulaval.glo2003.locations.services;

import ca.ulaval.glo2003.locations.domain.Location;

public interface LocationClient {

  Location getLocation(String zipCode);
}
