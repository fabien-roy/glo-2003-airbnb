package ca.ulaval.glo2003.beds.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LodgingModeBuilderTest {

  private static LodgingModeBuilder lodgingModeBuilder;

  @BeforeAll
  public static void setUpBuilder() {
    lodgingModeBuilder = new LodgingModeBuilder();
  }

  @Test
  public void build_withoutType_shouldBuildPrivateLodgingMode() {
    LodgingMode lodgingMode = lodgingModeBuilder.build();

    assertTrue(lodgingMode instanceof PrivateLodgingMode);
  }

  @ParameterizedTest
  @MethodSource("provideTypes")
  public void build_withType_shouldBuildAppropriateLodgingMode(
      LodgingModes type, LodgingMode appropriateLodgingMode) {
    LodgingMode lodgingMode = lodgingModeBuilder.withType(type).build();

    assertEquals(appropriateLodgingMode.getClass(), lodgingMode.getClass());
  }

  private static Stream<Arguments> provideTypes() {
    return Stream.of(
        Arguments.of(LodgingModes.PRIVATE, new PrivateLodgingMode()),
        Arguments.of(LodgingModes.COHABITATION, new CohabitationLodgingMode()));
  }
}
