package mu.yanesh.lotto.extractor.utils;

import lombok.experimental.UtilityClass;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CalendarUtils {

    public static final LocalDate FIRST_WEEKEND_TIRAGE_DATE = LocalDate.of(2009, Month.NOVEMBER, 8);
    public static final LocalDate FIRST_WEEKDAY_TIRAGE_DATE = LocalDate.of(2018, Month.SEPTEMBER, 4);

    /*
     * All weekends (Sat) of the given year
     */
    public static List<LocalDate> getWeekendsTirage(int year) {
        LocalDate firstDateOfTheMonth = Year.of(year).atDay(1);

        return firstDateOfTheMonth
                .datesUntil(LocalDate.now())
                .filter(date -> date.getDayOfWeek() == DayOfWeek.SATURDAY && date.isAfter(FIRST_WEEKEND_TIRAGE_DATE))
                .collect(Collectors.toList());
    }

    /*
     * All weekdays (Wed) of the given year
     */
    public static List<LocalDate> getWeekDaysTirage(int year) {
        LocalDate firstDateOfTheMonth = Year.of(year).atDay(1);

        return firstDateOfTheMonth
                .datesUntil(LocalDate.now())
                .filter(date -> date.getDayOfWeek() == DayOfWeek.WEDNESDAY && date.isAfter(FIRST_WEEKDAY_TIRAGE_DATE))
                .collect(Collectors.toList());
    }

    /*
     * All weekdays (Wed) of the given date
     */
    public static List<LocalDate> getWeekDaysTirageByStartDate(LocalDate startDate) {

        return startDate
                .datesUntil(LocalDate.now())
                .filter(date -> date.getDayOfWeek() == DayOfWeek.WEDNESDAY && date.isAfter(FIRST_WEEKDAY_TIRAGE_DATE))
                .collect(Collectors.toList());
    }

    /*
     * All weekends (Sat) of the given date
     */
    public static List<LocalDate> getWeekendsTirageByStartDate(LocalDate startDate) {

        return startDate
                .datesUntil(LocalDate.now())
                .filter(date -> date.getDayOfWeek() == DayOfWeek.SATURDAY && date.isAfter(FIRST_WEEKEND_TIRAGE_DATE))
                .collect(Collectors.toList());
    }

}
