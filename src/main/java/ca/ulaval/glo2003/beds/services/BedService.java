package ca.ulaval.glo2003.beds.services;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.infrastructure.InMemoryBedQuery;
import ca.ulaval.glo2003.beds.mappers.BedMapper;
import ca.ulaval.glo2003.beds.mappers.BedNumberMapper;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.services.LocationService;
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
  private final LocationService locationService;

  @Inject
  public BedService(
      BedFactory bedFactory,
      BedQueryFactory bedQueryFactory,
      BedMapper bedMapper,
      BedNumberMapper bedNumberMapper,
      BedRepository bedRepository,
      BedStarsCalculator bedStarsCalculator,
      LocationService locationService) {
    this.bedFactory = bedFactory;
    this.bedQueryFactory = bedQueryFactory;
    this.bedMapper = bedMapper;
    this.bedNumberMapper = bedNumberMapper;
    this.bedRepository = bedRepository;
    this.bedStarsCalculator = bedStarsCalculator;
    this.locationService = locationService;
  }

  public String add(BedRequest request) {
    Bed bed = bedMapper.fromRequest(request);
    Location location = locationService.getLocation(request.getZipCode());

    bed = bedFactory.create(bed, location);

    bedRepository.add(bed);

    return bed.getNumber().toString();
  }

  public List<BedResponse> getAll(Map<String, String[]> params) {
    InMemoryBedQuery bedQuery = bedQueryFactory.create(params);

    List<Bed> beds = bedRepository.getAll(bedQuery);

    return orderByStars(toResponses(beds));
  }

  public BedResponse getResponse(String number) {
    Bed bed = get(number);

    return bedMapper.toResponseWithoutNumber(bed, bedStarsCalculator.calculateStars(bed));
  }

  public Bed get(String number) {
    UUID bedNumber = bedNumberMapper.fromString(number);

    return bedRepository.getByNumber(bedNumber);
  }

  public void update(Bed bed) {
    bedRepository.update(bed);
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
