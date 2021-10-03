package mu.yanesh.lotto.library.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateUtils {

    public String formatDate(LocalDate date, String dateFormat) {
        return date.format(DateTimeFormatter.ofPattern(dateFormat));
    }

}
