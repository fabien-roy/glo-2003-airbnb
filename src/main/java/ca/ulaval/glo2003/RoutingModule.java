package ca.ulaval.glo2003;

import com.google.inject.AbstractModule;

public class RoutingModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(Server.class);
    bind(Router.class);
  }
}
