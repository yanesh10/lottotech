package mu.yanesh.lotto.extractor.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

public class CalendarUtils {

    /*
     * All weekends (Sat & Sun) of the given year and the month
     */
    public static List<LocalDate> getWeekends(int year) {
        LocalDate firstDateOfTheMonth = Year.of(year).atDay(1);

        return firstDateOfTheMonth
                .datesUntil(firstDateOfTheMonth.plusMonths(1))
                .filter(date -> date.getDayOfWeek() == DayOfWeek.SATURDAY && date.isBefore(LocalDate.now()))
                .collect(Collectors.toList());
    }
}
