package mu.yanesh.lotto.persister.receiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.yanesh.lotto.library.models.Ticket;
import mu.yanesh.lotto.library.repository.TicketDataRepository;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Slf4j
@Component
@RequiredArgsConstructor
public class TicketMessageReceiver {

    private final ObjectMapper objectMapper;
    private final TicketDataRepository ticketDataRepository;

    @JmsListener(destination = "${messaging.ticket-receiver.queue-name}")
    public void receiveTicketMessage(final Message message) throws JMSException, JsonProcessingException {
        final String data = ((ActiveMQTextMessage) message).getText();
        log.info("Received message with content: {}", data);

        processMessage(data);
    }

    private void processMessage(final String jsonMessage) throws JsonProcessingException {
        Ticket ticket = objectMapper.readValue(jsonMessage, Ticket.class);
        ticketDataRepository.save(ticket);
        log.info("Persisted data to mongo");
    }
}
