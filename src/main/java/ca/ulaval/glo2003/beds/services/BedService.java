package ca.ulaval.glo2003.beds.services;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.beds.rest.mappers.BedMapper;
import ca.ulaval.glo2003.beds.rest.mappers.BedNumberMapper;
import java.util.*;
import java.util.stream.Collectors;

public class BedService {

  private final BedMapper bedMapper;
  private final BedNumberMapper bedNumberMapper;
  private final BedRepository bedRepository;

  public BedService(
      BedMapper bedMapper, BedNumberMapper bedNumberMapper, BedRepository bedRepository) {
    this.bedMapper = bedMapper;
    this.bedNumberMapper = bedNumberMapper;
    this.bedRepository = bedRepository;
  }

  public String add(BedRequest request) {
    // TODO

    return "";
  }

  public List<BedResponse> getAll(Map<String, String> params) {
    Bed requestedBed = bedMapper.fromRequestParams(params);

    List<Bed> beds = bedRepository.getAll();

    List<Bed> matchingBeds =
        beds.stream().filter(bed -> bed.matches(requestedBed)).collect(Collectors.toList());

    return matchingBeds.stream().map(bedMapper::toResponse).collect(Collectors.toList());
  }

  // TODO : Tests for bedService.getByNumber
  public BedResponse getByNumber(String number) {
    UUID bedNumber = bedNumberMapper.fromString(number);

    Bed bed = bedRepository.getByNumber(bedNumber);

    return bedMapper.toResponse(bed);
  }
}
