package ca.ulaval.glo2003.transactions.rest.serializers;

import ca.ulaval.glo2003.interfaces.domain.serializers.AbstractSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;

public class ServiceFeeSerializer extends AbstractSerializer<BigDecimal> {

  private BigDecimal serviceFee;

  public ServiceFeeSerializer() {
    super(BigDecimal.class);
  }

  @Override
  public Class<?> getType() {
    return BigDecimal.class;
  }

  @Override
  public void serialize(
      BigDecimal value, JsonGenerator generator, SerializerProvider serializerProvider)
      throws IOException {
    generator.writeRawValue(serviceFee.toString());
  }
}
