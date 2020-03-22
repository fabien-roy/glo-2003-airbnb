package ca.ulaval.glo2003.beds.domain;

import java.util.List;

public interface BedFilter {

  List<Bed> filter(List<Bed> beds);
}
