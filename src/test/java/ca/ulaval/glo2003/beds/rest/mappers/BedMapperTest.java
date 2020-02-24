package ca.ulaval.glo2003.beds.rest.mappers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.domain.Package;
import ca.ulaval.glo2003.beds.helpers.BedBuilder;
import ca.ulaval.glo2003.beds.rest.BedRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import ca.ulaval.glo2003.beds.rest.BedResponse;
import ca.ulaval.glo2003.beds.rest.exceptions.BedNotFoundException;
import ca.ulaval.glo2003.beds.services.BedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BedMapperTest {

  private BedMapper bedMapper;

  @BeforeEach
  public void setUpMapper() {
    bedMapper = new BedMapper();
  }

  @Test
  public void fromRequest_withSingleBloodType_shouldReturnBedWithSingleBloodType() {
    /*
    BloodTypes expectedBloodType = BloodTypes.O_MINUS;
    List<String> bloodTypes = Collections.singletonList(expectedBloodType.toString());
    BedRequest bedRequest = mock(BedRequest.class);
    when(bedRequest.getBloodTypes()).thenReturn(bloodTypes);

    Bed bed = bedMapper.fromRequest(bedRequest);

    assertEquals(1, bed.getBloodTypes().size());
    assertEquals(expectedBloodType, bed.getBloodTypes().get(0));
    */
  }

  @Test
  public void fromRequest_withMultipleBloodTypes_shouldReturnBedWithMultipleBloodTypes() {
    /*
    BloodTypes expectedBloodType = BloodTypes.O_MINUS;
    BloodTypes otherExpectedBloodType = BloodTypes.O_PLUS;
    List<String> bloodTypes =
        Arrays.asList(expectedBloodType.toString(), otherExpectedBloodType.toString());
    BedRequest bedRequest = mock(BedRequest.class);
    when(bedRequest.getBloodTypes()).thenReturn(bloodTypes);

    Bed bed = bedMapper.fromRequest(bedRequest);

    assertEquals(2, bed.getBloodTypes().size());
    assertTrue(bed.getBloodTypes().contains(expectedBloodType));
    assertTrue(bed.getBloodTypes().contains(otherExpectedBloodType));
    */
  }

  @Test
  public void fromRequest_withInvalidBloodType_shouldThrowInvalidBloodTypeException() {
    String invalidBloodType = "invalidBloodType";
    List<String> bloodTypes = Collections.singletonList(invalidBloodType);
    BedRequest bedRequest = mock(BedRequest.class);
    when(bedRequest.getBloodTypes()).thenReturn(bloodTypes);

    // assertThrows(InvalidBloodTypeException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void toResponse_shouldReturnTheRightBed_whenProvidedWithBedNumber() {

    BedBuilder bedBuilder = BedBuilder.aBed();
    Bed bed = bedBuilder.build();

    BedResponse bedResponse = bedMapper.toResponse(bed);

    assertEquals(bed.getNumber(), bedResponse.getBedNumber());

  }

  @Test
  public void toResponse_shouldKeepTheCorrectAmountOfPackages_whenProvidedWithBed() {

    List<BloodTypes> listBloodTypes = new ArrayList<>();
    listBloodTypes.add(BloodTypes.A_MINUS);

    List<Package> listPackages = new ArrayList<>();
    listPackages.add(new Package(PackageNames.BLOODTHIRSTY, BigDecimal.valueOf(45.45)));
    listPackages.add(new Package(PackageNames.ALL_YOU_CAN_DRINK, BigDecimal.valueOf(78.78)));

    Bed bed = new Bed("5A5832M7QP888887G4P45MP88888885A5832M7QP888887G4P45MP8888888",
            "11111", BedTypes.LATEX, CleaningFrequencies.ANNUAL, listBloodTypes, 122, listPackages);

    BedResponse bedResponse = bedMapper.toResponse(bed);

    assertEquals(bed.getPackages().size(), bedResponse.getPackages().size());

  }

  @Test
  public void toResponse_shouldKeepTheCorrectAmountOfBloodTypes_whenProvidedWithBed() {

    List<BloodTypes> listBloodTypes = new ArrayList<>();
    listBloodTypes.add(BloodTypes.A_MINUS);
    listBloodTypes.add(BloodTypes.O_MINUS);
    listBloodTypes.add(BloodTypes.AB_MINUS);

    List<Package> listPackages = new ArrayList<>();
    listPackages.add(new Package(PackageNames.BLOODTHIRSTY, BigDecimal.valueOf(45.45)));

    Bed bed = new Bed("5A5832M7QP888887G4P45MP88888885A5832M7QP888887G4P45MP8888888",
            "11111", BedTypes.LATEX, CleaningFrequencies.ANNUAL, listBloodTypes, 122, listPackages);

    BedResponse bedResponse = bedMapper.toResponse(bed);

    assertEquals(bed.getBloodTypes().size(), bedResponse.getBloodTypes().size());

  }




}
