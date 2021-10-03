package mu.yanesh.lotto.extractor.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

class CalendarUtilsTest {

    @Test
    void testGetWeekendsTirage() {
        List<LocalDate> dates = CalendarUtils.getWeekendsTirage(2020);

        assertThat(dates, hasSize(52));
    }
}