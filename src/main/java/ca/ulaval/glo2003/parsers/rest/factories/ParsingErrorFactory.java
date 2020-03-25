package ca.ulaval.glo2003.parsers.rest.factories;

import ca.ulaval.glo2003.errors.rest.factories.AbstractErrorFactory;
import com.fasterxml.jackson.databind.JsonMappingException;

public abstract class ParsingErrorFactory extends AbstractErrorFactory<JsonMappingException> {}
