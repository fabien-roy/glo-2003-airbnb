package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.domain.BloodTypes;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BloodTypesQueryParamAssembler implements BedQueryParamAssembler {

  public static final String BLOOD_TYPES_PARAM = "bloodTypes";

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, List<String>> params) {
    List<String> bloodTypes = params.get(BLOOD_TYPES_PARAM);

    return bloodTypes != null ? builder.withBloodTypes(parseBloodTypes(bloodTypes)) : builder;
  }

  private List<BloodTypes> parseBloodTypes(List<String> bloodTypes) {
    return bloodTypes.stream().map(BloodTypes::get).collect(Collectors.toList());
  }
}
