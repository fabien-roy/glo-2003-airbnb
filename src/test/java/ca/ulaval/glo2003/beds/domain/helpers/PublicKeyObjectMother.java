package ca.ulaval.glo2003.beds.domain.helpers;

import ca.ulaval.glo2003.beds.domain.PublicKey;
import ca.ulaval.glo2003.beds.mappers.PublicKeyMapper;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import java.util.Locale;

public class PublicKeyObjectMother {

  private static final FakeValuesService fakeValuesService =
      new FakeValuesService(new Locale("en-US"), new RandomService());

  public static PublicKey createPublicKey() {
    String value = fakeValuesService.regexify(PublicKeyMapper.PUBLIC_KEY_PATTERN);
    return new PublicKey(value);
  }
}
