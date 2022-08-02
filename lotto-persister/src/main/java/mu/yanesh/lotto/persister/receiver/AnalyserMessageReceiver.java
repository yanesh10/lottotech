package mu.yanesh.lotto.persister.receiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.yanesh.lotto.library.models.Stats;
import mu.yanesh.lotto.library.models.Ticket;
import mu.yanesh.lotto.library.repository.StatsRepository;
import mu.yanesh.lotto.library.repository.TicketDataRepository;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class AnalyserMessageReceiver {

    public static final String STATS_COLLECTION_NAME = "stats";

    private final ObjectMapper objectMapper;
    private final StatsRepository statsRepository;

    @JmsListener(destination = "${messaging.stats-receiver.queue-name}")
    public void receiveStatsMessage(final Message message) throws JMSException, JsonProcessingException {
        final String data = ((ActiveMQTextMessage) message).getText();
        log.info("Received message with content: {}", data);

        processMessage(data);
    }

    @JmsListener(destination = "${messaging.stats-receiver-clear.queue-name}")
    public void receiveStatsClearMessage(final Message message) throws JMSException {
        final String data = ((ActiveMQTextMessage) message).getText();
        log.info("Received message with content: {}", data);

        statsRepository.deleteAll(STATS_COLLECTION_NAME);
    }

    private void processMessage(final String jsonMessage) throws JsonProcessingException {
        Stats stats = objectMapper.readValue(jsonMessage, Stats.class);
        List<Stats> statsList = statsRepository.findAll(Stats.class, STATS_COLLECTION_NAME);
        Stats mongoStats =
                statsList.stream().filter(s -> s.getNumbers().containsAll(stats.getNumbers())).findFirst().orElse(new Stats());
        mongoStats.setDate(Optional.ofNullable(stats.getDate()).orElse(new Date()));
        mongoStats.setFrequency(Optional.of(mongoStats.getFrequency()).orElse(0) + 1);
        mongoStats.setNumbers(stats.getNumbers());
        statsRepository.save(mongoStats);
        log.info("Persisted data to mongo");
    }

}
