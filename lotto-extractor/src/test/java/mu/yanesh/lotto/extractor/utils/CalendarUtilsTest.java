package mu.yanesh.lotto.extractor.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.powermock.api.mockito.PowerMockito;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalendarUtilsTest {

    @BeforeAll
    static void setup() {
        mock(LocalDate.class);
    }

    @Test
    @Disabled
    void testGetWeekendsTirage() {
        when(LocalDate.now()).thenReturn(LocalDate.of(2021, 10, 2));
        List<LocalDate> dates = CalendarUtils.getWeekendsTirage(2020);

        assertThat(dates, hasSize(92));
    }
}