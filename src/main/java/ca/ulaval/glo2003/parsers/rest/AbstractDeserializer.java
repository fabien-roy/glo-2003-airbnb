package ca.ulaval.glo2003.parsers.rest;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public abstract class AbstractDeserializer<T, E extends RuntimeException>
    extends StdDeserializer<T> {

  protected AbstractDeserializer(Class<T> t) {
    super(t);
  }

  public abstract Class<?> getType();

  public abstract void throwException() throws E;
}
