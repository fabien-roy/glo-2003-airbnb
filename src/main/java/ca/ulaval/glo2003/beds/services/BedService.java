package ca.ulaval.glo2003.beds.services;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.beds.rest.mappers.BedMapper;
import ca.ulaval.glo2003.beds.rest.mappers.BedNumberMapper;
import ca.ulaval.glo2003.locations.domain.ZipCode;
import ca.ulaval.glo2003.locations.infrastructure.ZippopotamusClient;
import com.google.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

public class BedService {

  private final BedFactory bedFactory;
  private final BedQueryFactory bedQueryFactory;
  private final BedMapper bedMapper;
  private final BedNumberMapper bedNumberMapper;
  private final BedRepository bedRepository;
  private final BedStarsCalculator bedStarsCalculator;
  private final ZippopotamusClient zippopotamusClient;

  @Inject
  public BedService(
      BedFactory bedFactory,
      BedQueryFactory bedQueryFactory,
      BedMapper bedMapper,
      BedNumberMapper bedNumberMapper,
      BedRepository bedRepository,
      BedStarsCalculator bedStarsCalculator,
      ZippopotamusClient zippopotamusClient) {
    this.bedFactory = bedFactory;
    this.bedQueryFactory = bedQueryFactory;
    this.bedMapper = bedMapper;
    this.bedNumberMapper = bedNumberMapper;
    this.bedRepository = bedRepository;
    this.bedStarsCalculator = bedStarsCalculator;
    this.zippopotamusClient = zippopotamusClient;
  }

  public String add(BedRequest request) {
    Bed bed = bedMapper.fromRequest(request);
    ZipCode zipCode = getValidatedZipCode(request.getZipCode());

    bed = bedFactory.create(bed, zipCode);

    bedRepository.add(bed);

    return bed.getNumber().toString();
  }

  public List<BedResponse> getAll(Map<String, String[]> params) {
    BedQuery bedQuery = bedQueryFactory.create(params);

    List<Bed> beds = bedRepository.getAll(bedQuery);

    return orderByStars(toResponses(beds));
  }

  public BedResponse getByNumber(String number) {
    UUID bedNumber = bedNumberMapper.fromString(number);

    Bed bed = bedRepository.getByNumber(bedNumber);

    return bedMapper.toResponseWithoutNumber(bed, bedStarsCalculator.calculateStars(bed));
  }

  private ZipCode getValidatedZipCode(String zipCodeValue) {
    return zippopotamusClient.validateZipCode(zipCodeValue);
  }

  private List<BedResponse> toResponses(List<Bed> beds) {
    return beds.stream()
        .map(
            matchingBed ->
                bedMapper.toResponseWithNumber(
                    matchingBed, bedStarsCalculator.calculateStars(matchingBed)))
        .collect(Collectors.toList());
  }

  private List<BedResponse> orderByStars(List<BedResponse> beds) {
    return beds.stream()
        .sorted(Comparator.comparingInt(BedResponse::getStars).reversed())
        .collect(Collectors.toList());
  }
}
