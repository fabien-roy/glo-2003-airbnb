package ca.ulaval.glo2003.interfaces.domain.serializers;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public abstract class AbstractDeserializer<T, E extends Exception> extends StdDeserializer<T> {

  protected AbstractDeserializer(Class<?> t) {
    super(t);
  }

  protected AbstractDeserializer(JavaType t) {
    super(t);
  }

  public abstract Class<?> getType();

  public abstract void throwException() throws E;
}
