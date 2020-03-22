package ca.ulaval.glo2003.beds.domain;

import java.util.List;

public interface BedQuery {

  List<Bed> execute();
}
