package ca.ulaval.glo2003.beds.converters;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.LodgingMode;
import ca.ulaval.glo2003.beds.domain.LodgingModeBuilder;
import ca.ulaval.glo2003.beds.domain.LodgingModes;
import ca.ulaval.glo2003.beds.exceptions.InvalidLodgingModeException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LodgingModeConverterTest {

  private static LodgingModeConverter lodgingModeConverter;
  private static LodgingModeBuilder lodgingModeBuilder = mock(LodgingModeBuilder.class);
  private static LodgingModeBuilder nonDefaultLodgingModeBuilder = mock(LodgingModeBuilder.class);
  private static LodgingMode defaultLodgingMode = mock(LodgingMode.class);
  private static LodgingMode nonDefaultLodgingMode = mock(LodgingMode.class);
  private static LodgingModes nonDefaultLodgingModeType = LodgingModes.COHABITATION;

  @BeforeAll
  public static void setUpConverter() {
    lodgingModeConverter = new LodgingModeConverter(lodgingModeBuilder);
  }

  @BeforeEach
  public void setUpMocks() {
    when(lodgingModeBuilder.aLodgingMode()).thenReturn(lodgingModeBuilder);
    when(lodgingModeBuilder.build()).thenReturn(defaultLodgingMode);
    when(lodgingModeBuilder.withType(nonDefaultLodgingModeType))
        .thenReturn(nonDefaultLodgingModeBuilder);
    when(nonDefaultLodgingModeBuilder.build()).thenReturn(nonDefaultLodgingMode);
  }

  @Test
  public void fromString_shouldConvertLodgingMode() {
    String lodgingModeType = nonDefaultLodgingModeType.toString();

    LodgingMode lodgingMode = lodgingModeConverter.fromString(lodgingModeType);

    assertSame(nonDefaultLodgingMode, lodgingMode);
  }

  @Test
  public void fromString_withNullValue_shouldConvertDefaultLodgingMode() {
    LodgingMode lodgingMode = lodgingModeConverter.fromString(null);

    assertSame(defaultLodgingMode, lodgingMode);
  }

  @Test
  public void fromRequest_withInvalidLodgingMode_shouldThrowInvalidLodgingModeException() {
    String invalidLodgingMode = "invalidLodgingMode";

    assertThrows(
        InvalidLodgingModeException.class,
        () -> lodgingModeConverter.fromString(invalidLodgingMode));
  }
}
