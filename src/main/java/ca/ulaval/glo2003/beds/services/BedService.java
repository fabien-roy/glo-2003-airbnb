package ca.ulaval.glo2003.beds.services;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.beds.rest.mappers.BedMapper;
import ca.ulaval.glo2003.beds.rest.mappers.BedMatcherMapper;
import ca.ulaval.glo2003.beds.rest.mappers.BedNumberMapper;
import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.rest.services.LocationService;
import com.google.inject.Inject;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BedService {

  private final BedFactory bedFactory;
  private final BedMapper bedMapper;
  private final BedNumberMapper bedNumberMapper;
  private final BedMatcherMapper bedMatcherMapper;
  private final BedRepository bedRepository;
  private final BedStarsCalculator bedStarsCalculator;
  private final LocationService locationService;

  @Inject
  public BedService(
      BedFactory bedFactory,
      BedMapper bedMapper,
      BedNumberMapper bedNumberMapper,
      BedMatcherMapper bedMatcherMapper,
      BedRepository bedRepository,
      BedStarsCalculator bedStarsCalculator,
      LocationService locationService) {
    this.bedFactory = bedFactory;
    this.bedMapper = bedMapper;
    this.bedNumberMapper = bedNumberMapper;
    this.bedMatcherMapper = bedMatcherMapper;
    this.bedRepository = bedRepository;
    this.bedStarsCalculator = bedStarsCalculator;
    this.locationService = locationService;
  }

  public String add(BedRequest request) throws IOException {
    Bed bed = bedMapper.fromRequest(request);
    Location location = locationService.getLocation(request.getLocation().getZipCode());

    bed = bedFactory.create(bed, location);

    bedRepository.add(bed);

    return bed.getNumber().toString();
  }

  public List<BedResponse> getAll(Map<String, String[]> params) throws IOException {
    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    if (bedMatcher.getOrigin() != null) {
      Location location = locationService.getLocation(bedMatcher.getOrigin().getZipCode());
      bedMatcher.setOrigin(location);
    }

    List<Bed> beds = bedRepository.getAll();
    List<Bed> matchingBeds = beds.stream().filter(bedMatcher::matches).collect(Collectors.toList());

    return matchingBeds.stream()
        .map(
            matchingBed ->
                bedMapper.toResponseWithNumber(
                    matchingBed, bedStarsCalculator.calculateStars(matchingBed)))
        .sorted(Comparator.comparingInt(BedResponse::getStars).reversed())
        .collect(Collectors.toList());
  }

  public BedResponse getByNumber(String number) {
    UUID bedNumber = bedNumberMapper.fromString(number);

    Bed bed = bedRepository.getByNumber(bedNumber);

    return bedMapper.toResponseWithoutNumber(bed, bedStarsCalculator.calculateStars(bed));
  }
}
