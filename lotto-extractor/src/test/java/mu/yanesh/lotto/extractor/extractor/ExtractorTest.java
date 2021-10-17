package mu.yanesh.lotto.extractor.extractor;

import mu.yanesh.lotto.library.models.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
class ExtractorTest {

    @Autowired
    IExtractor extractor;

    @Test
    void getTirageTest_WithNoTirage() {
        LocalDate tirageDate = LocalDate.of(2020, Month.FEBRUARY, 13);
        Ticket ticket = extractor.getTirage(tirageDate);

        assertThat(ticket, nullValue());
    }

    @Test
    void getTirageTest_WithTirage() {
        LocalDate tirageDate = LocalDate.of(2010, Month.FEBRUARY, 13);
        Ticket ticket = extractor.getTirage(tirageDate);

        assertThat(ticket.getTirageDate(), is(tirageDate));
        assertThat(ticket.getNumber1(), is(1));
        assertThat(ticket.getNumber2(), is(6));
        assertThat(ticket.getNumber3(), is(12));
        assertThat(ticket.getNumber4(), is(14));
        assertThat(ticket.getNumber5(), is(18));
        assertThat(ticket.getNumber6(), is(40));
    }
}