package mu.yanesh.lotto.extractor.publish;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TicketMessagePublisherTest {

    @Autowired
    private TicketMessagePublisher ticketMessagePublisher;

    @Test
    void publishTest() {
    }
}