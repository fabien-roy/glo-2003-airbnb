package ca.ulaval.glo2003.beds.services;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BedService {

  private BedRepository bedRepository;

  public BedService(BedRepository bedRepository) {
    this.bedRepository = bedRepository;
  }

  public String add(BedRequest request) {
    // TODO

    return "";
  }

  public List<BedResponse> getAll(Set<String> params) {
    List<Bed> beds = bedRepository.getAll();

    // TODO : Gets all Bed that match using BedMatcher.matches(Bed, Bed)

    // TODO : Return List<BedResponse> using BedMapper

    return new ArrayList<>();
  }

  public BedResponse getByNumber(String number) {
    // TODO

    return new BedResponse();
  }
}
