package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.domain.BloodTypes;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BloodTypesQueryParamAssembler implements BedQueryParamAssembler {

  public static final String BLOOD_TYPES_PARAM = "bloodTypes";

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, String[]> params) {
    return params.get(BLOOD_TYPES_PARAM) != null
        ? builder.withBloodTypes(parseBloodTypes(params.get(BLOOD_TYPES_PARAM)))
        : builder;
  }

  private List<BloodTypes> parseBloodTypes(String[] bloodTypes) {
    String[] repairedBloodTypes = repairArray(bloodTypes[0]);
    return Arrays.stream(repairedBloodTypes).map(BloodTypes::get).collect(Collectors.toList());
  }

  // TODO : Solve this horrid fix
  private String[] repairArray(String bloodTypes) {
    return bloodTypes.replace(" ", "+").split(",");
  }
}
