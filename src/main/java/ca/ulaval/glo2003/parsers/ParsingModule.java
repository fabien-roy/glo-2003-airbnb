package ca.ulaval.glo2003.parsers;

import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import ca.ulaval.glo2003.parsers.rest.factories.CapacityParsingErrorFactory;
import ca.ulaval.glo2003.parsers.rest.factories.JsonParsingErrorFactory;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

public class ParsingModule extends AbstractModule {

  @Override
  protected void configure() {
    configureErrorFactories();
  }

  private void configureErrorFactories() {
    Multibinder<ErrorFactory<JsonMappingException>> multibinder =
        Multibinder.newSetBinder(
            binder(), new TypeLiteral<ErrorFactory<JsonMappingException>>() {});
    multibinder.addBinding().to(CapacityParsingErrorFactory.class);
    multibinder.addBinding().to(JsonParsingErrorFactory.class);
  }
}
