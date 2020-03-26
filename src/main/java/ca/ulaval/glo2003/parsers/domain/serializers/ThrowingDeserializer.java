package ca.ulaval.glo2003.parsers.domain.serializers;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public abstract class ThrowingDeserializer<T, E extends RuntimeException>
    extends StdDeserializer<T> {

  protected ThrowingDeserializer(Class<T> t) {
    super(t);
  }

  public abstract Class<?> getType();

  public abstract void throwException() throws E;
}
