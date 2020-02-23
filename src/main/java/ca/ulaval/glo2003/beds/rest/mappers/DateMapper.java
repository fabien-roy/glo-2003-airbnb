package ca.ulaval.glo2003.beds.rest.mappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class DateMapper {

  public static boolean isNotAValidDate(String date) {
    return !DATE_PATTERN.matcher(date).matches();
  }

  public static Date fromString(String date) throws ParseException {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    return formatter.parse(date);
  }

  private static Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

  public static boolean isBeforeToday(String dateToCompare) {
    Date date = null;
    try {
      date = fromString(dateToCompare);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    assert date != null;
    return date.before(new Date());
  }
}
