package ca.ulaval.glo2003.beds.converters;

import ca.ulaval.glo2003.beds.domain.BloodTypes;
import ca.ulaval.glo2003.beds.exceptions.InvalidBloodTypesException;
import java.util.List;
import java.util.stream.Collectors;

public class BloodTypeConverter {

  public List<BloodTypes> fromStrings(List<String> bloodTypes) {
    if (bloodTypes.isEmpty()) throw new InvalidBloodTypesException();

    return bloodTypes.stream().map(BloodTypes::get).collect(Collectors.toList());
  }

  public List<String> toStrings(List<BloodTypes> bloodTypes) {
    return bloodTypes.stream().map(BloodTypes::toString).collect(Collectors.toList());
  }
}
