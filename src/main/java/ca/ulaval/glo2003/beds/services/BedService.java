package ca.ulaval.glo2003.beds.services;

import ca.ulaval.glo2003.beds.converters.BedConverter;
import ca.ulaval.glo2003.beds.converters.BedNumberConverter;
import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.services.LocationService;
import com.google.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BedService {

  private final BedFactory bedFactory;
  private final BedQueryFactory bedQueryFactory;
  private final BedConverter bedConverter;
  private final BedNumberConverter bedNumberConverter;
  private final BedRepository bedRepository;
  private final BedStarsCalculator bedStarsCalculator;
  private final LocationService locationService;

  @Inject
  public BedService(
      BedFactory bedFactory,
      BedQueryFactory bedQueryFactory,
      BedConverter bedConverter,
      BedNumberConverter bedNumberConverter,
      BedRepository bedRepository,
      BedStarsCalculator bedStarsCalculator,
      LocationService locationService) {
    this.bedFactory = bedFactory;
    this.bedQueryFactory = bedQueryFactory;
    this.bedConverter = bedConverter;
    this.bedNumberConverter = bedNumberConverter;
    this.bedRepository = bedRepository;
    this.bedStarsCalculator = bedStarsCalculator;
    this.locationService = locationService;
  }

  public String add(BedRequest request) {
    Bed bed = bedConverter.fromRequest(request);
    Location location = locationService.getLocation(request.getZipCode());

    bed = bedFactory.create(bed, location);

    bedRepository.add(bed);

    return bed.getNumber().toString();
  }

  public BedResponse getResponse(String number) {
    Bed bed = get(number);

    return bedConverter.toResponseWithoutNumber(bed, bedStarsCalculator.calculateStars(bed));
  }

  public Bed get(String number) {
    BedNumber bedNumber = bedNumberConverter.fromString(number);

    return bedRepository.getByNumber(bedNumber);
  }

  // TODO : Test BedService.getAll()
  public List<Bed> getAll() {
    return bedRepository.getAll();
  }

  // TODO : Test BedService.getAll(...)
  private List<Bed> getAll(Map<String, List<String>> params) {
    BedQuery bedQuery = bedQueryFactory.create(params);

    return bedRepository.getAll(bedQuery);
  }

  public List<BedResponse> getAllResponses(Map<String, List<String>> params) {
    List<Bed> beds = getAll(params);

    return orderByStars(toResponses(beds));
  }

  public void update(Bed bed) {
    bedRepository.update(bed);
  }

  private List<BedResponse> toResponses(List<Bed> beds) {
    return beds.stream()
        .map(
            matchingBed ->
                bedConverter.toResponseWithNumber(
                    matchingBed, bedStarsCalculator.calculateStars(matchingBed)))
        .collect(Collectors.toList());
  }

  private List<BedResponse> orderByStars(List<BedResponse> beds) {
    return beds.stream()
        .sorted(Comparator.comparingInt(BedResponse::getStars).reversed())
        .collect(Collectors.toList());
  }
}
