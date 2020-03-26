package ca.ulaval.glo2003.parsers.domain.serializers;

import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public abstract class ThrowingSerializer<T> extends StdSerializer<T> {

  protected ThrowingSerializer(Class<T> t) {
    super(t);
  }

  public abstract Class<?> getType();
}
