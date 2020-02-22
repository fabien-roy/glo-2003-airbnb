package ca.ulaval.glo2003.beds.services;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedFactory;
import ca.ulaval.glo2003.beds.domain.BedMatcher;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.beds.rest.mappers.BedMapper;
import ca.ulaval.glo2003.beds.rest.mappers.BedMatcherMapper;
import ca.ulaval.glo2003.beds.rest.mappers.BedNumberMapper;
import java.util.*;
import java.util.stream.Collectors;

public class BedService {

  private final BedFactory bedFactory;
  private final BedMapper bedMapper;
  private final BedNumberMapper bedNumberMapper;
  private final BedMatcherMapper bedMatcherMapper;
  private final BedRepository bedRepository;

  public BedService(
      BedFactory bedFactory,
      BedMapper bedMapper,
      BedNumberMapper bedNumberMapper,
      BedMatcherMapper bedMatcherMapper,
      BedRepository bedRepository) {
    this.bedFactory = bedFactory;
    this.bedMapper = bedMapper;
    this.bedNumberMapper = bedNumberMapper;
    this.bedMatcherMapper = bedMatcherMapper;
    this.bedRepository = bedRepository;
  }

  public String add(BedRequest request) {
    Bed bed = bedMapper.fromRequest(request);

    bed = bedFactory.create(bed);

    bedRepository.add(bed);

    return bed.getNumber().toString();
  }

  public List<BedResponse> getAll(Map<String, String> params) {
    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    List<Bed> beds = bedRepository.getAll();

    List<Bed> matchingBeds = beds.stream().filter(bedMatcher::matches).collect(Collectors.toList());

    return matchingBeds.stream().map(bedMapper::toResponse).collect(Collectors.toList());
  }

  // TODO : Tests for bedService.getByNumber
  public BedResponse getByNumber(String number) {
    UUID bedNumber = bedNumberMapper.fromString(number);

    Bed bed = bedRepository.getByNumber(bedNumber);

    return bedMapper.toResponse(bed);
  }
}
