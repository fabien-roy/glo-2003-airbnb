package ca.ulaval.glo2003.errors;

import ca.ulaval.glo2003.errors.rest.handlers.CatchallExceptionHandler;
import ca.ulaval.glo2003.errors.rest.mappers.ErrorMapper;
import com.google.inject.AbstractModule;

public class ErrorModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(CatchallExceptionHandler.class);
    bind(ErrorMapper.class);
  }
}
