package ca.ulaval.glo2003.beds.rest.mappers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BedMapperTest {

  private BedMapper bedMapper;

  @BeforeEach
  public void setUpMapper() {
    bedMapper = new BedMapper();
  }

  @Test
  public void fromRequest_withBedType_shouldReturnBedWithBedType() {
    BedTypes expectedBedType = BedTypes.LATEX;
    BedRequest bedRequest = mock(BedRequest.class);
    BedTypes bedType = BedTypes.LATEX;
    when(bedRequest.getBedType()).thenReturn(String.valueOf(bedType));

    Bed bed = bedMapper.fromRequest(bedRequest);

    assertEquals(expectedBedType, bed.getBedType());
  }
  /*
  @Test
  public void fromRequest_withInvalidBedType_shouldThrowInvalidFormatException() {
    String invalidBedType = "invalidBedType";
    BedRequest bedRequest = mock(BedRequest.class);
    when(bedRequest.getBedType()).thenReturn(invalidBedType);

    assertThrows(InvalidFormatException.class, () -> bedMapper.fromRequest(bedRequest));
  }

   @Test
   public void fromRequest_withCleaningFrequency_shouldReturnBedWithCleaningFrequency() {
     CleaningFrequencies expectedCleaningFrequency = CleaningFrequencies.ANNUAL;
     BedRequest bedRequest = mock(BedRequest.class);
     CleaningFrequencies cleaningFrequency = CleaningFrequencies.ANNUAL;
     when(bedRequest.getBedType()).thenReturn(String.valueOf(cleaningFrequency));

     Bed bed = bedMapper.fromRequest(bedRequest);

     assertEquals(expectedCleaningFrequency, bed.getCleaningFrequency());
   }

   @Test
   public void fromRequest_withInvalidCleaningFrequency_shouldThrowInvalidFormatException() {
     String invalidCleaningFrequency = "invalidCleaningFrequency";
     BedRequest bedRequest = mock(BedRequest.class);
     when(bedRequest.getCleaningFrequency()).thenReturn(invalidCleaningFrequency);

     assertThrows(InvalidFormatException.class, () -> bedMapper.fromRequest(bedRequest));
   }

   @Test
   public void fromRequest_withSingleBloodType_shouldReturnBedWithSingleBloodType() {
     BloodTypes expectedBloodType = BloodTypes.O_MINUS;
     List<String> bloodTypes = Collections.singletonList(expectedBloodType.toString());
     BedRequest bedRequest = mock(BedRequest.class);
     when(bedRequest.getBloodTypes()).thenReturn(bloodTypes);

     Bed bed = bedMapper.fromRequest(bedRequest);

     assertEquals(1, bed.getBloodTypes().size());
     assertEquals(expectedBloodType, bed.getBloodTypes().get(0));
   }

   @Test
   public void fromRequest_withMultipleBloodTypes_shouldReturnBedWithMultipleBloodTypes() {
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
   }

   @Test
   public void fromRequest_withoutBloodTypes_shouldThrowInvalidFormatException() {
     BedRequest bedRequest = mock(BedRequest.class);
     when(bedRequest.getBloodTypes()).thenReturn(null);

     assertThrows(InvalidFormatException.class, () -> bedMapper.fromRequest(bedRequest));
   }

   @Test
   public void fromRequest_withInvalidBloodType_shouldThrowInvalidBloodTypeException() {
     String invalidBloodType = "invalidBloodType";
     List<String> bloodTypes = Collections.singletonList(invalidBloodType);
     BedRequest bedRequest = mock(BedRequest.class);
     when(bedRequest.getBloodTypes()).thenReturn(bloodTypes);

     assertThrows(InvalidFormatException.class, () -> bedMapper.fromRequest(bedRequest));
   }

   @Test
   public void fromRequest_withCapacity_shouldReturnBedWithCapacity() {
     int expectedCapacity = 1000;
     BedRequest bedRequest = mock(BedRequest.class);
     int capacity = 1000;
     when(bedRequest.getBedType()).thenReturn(String.valueOf(1000));

     Bed bed = bedMapper.fromRequest(bedRequest);

     assertEquals(expectedCapacity, bed.getCapacity());
   }

   @Test
   public void fromRequest_withPackageName_shouldReturnBedWithPackageName(){
     Package expectedPackage = Package;
     BedRequest bedRequest = mock(BedRequest.class);
     PackageNames packageNames = PackageNames;
     when(bedRequest.getBedType()).thenReturn(String.valueOf(packageName));

     Bed bed = bedMapper.fromRequest(bedRequest);

     assertTrue(bed.getPackages().contains(expectedPackage));
   }

   @Test
   public void fromRequest_withInvalidCapacity_shouldThrowInvalidFormatException(){
     String invalidPackageName = "invalidPackageName";
     BedRequest bedRequest = mock(BedRequest.class);
     when(bedRequest.getPackagesName()).thenReturn(invalidPackageName);

     assertThrows(InvalidFormatException.class, () -> bedMapper.fromRequest(bedRequest));
   }
  */
}
