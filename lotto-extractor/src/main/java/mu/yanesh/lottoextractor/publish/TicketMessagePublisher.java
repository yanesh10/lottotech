package mu.yanesh.lottoextractor.publish;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class TicketMessagePublisher {

    private final JmsTemplate jmsTemplateTopic;

    @Value("${messaging.ticket-publisher.topic-name}")
    private String ticketTopic;

    public void publish(Object response) {
        jmsTemplateTopic.convertAndSend(ticketTopic, response);
    }
}
