package mu.yanesh.lotto.extractor.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CalendarUtilsTest {

    @BeforeAll
    static void setup() {
        LocalDate mockDate = LocalDate.of(2021, 10, 17);
        Clock clock = Clock.fixed(mockDate.atStartOfDay().toInstant(ZoneOffset.UTC), ZoneOffset.UTC);
        LocalDate.now(clock);
    }

    @Test
    void testGetWeekendsTirage() {
        List<LocalDate> dates = CalendarUtils.getWeekendsTirage(2020);

        assertThat(dates, hasSize(94));
    }

    @Test
    void getWeekDaysTirage() {
        List<LocalDate> dates = CalendarUtils.getWeekDaysTirage(2020);

        assertThat(dates, hasSize(94));
    }
}