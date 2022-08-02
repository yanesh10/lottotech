package mu.yanesh.lotto.analyser.publish;

import lombok.extern.slf4j.Slf4j;
import mu.yanesh.lotto.analyser.exception.MissingBeanJmsTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.support.JmsGatewaySupport;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class StatsMessagePublisher extends JmsGatewaySupport {

    @Value("${messaging.stats-publisher.queue-name}")
    private String statsQueue;

    @Value("${messaging.stats-publisher-clear.queue-name}")
    private String statsClearQueue;

    public StatsMessagePublisher(@Qualifier("jmsTemplatePersistentQueue") JmsTemplate jmsTemplate) {
        setJmsTemplate(jmsTemplate);
    }

    public void publish(Object response) {
        log.trace("Sending Ticket message to JMS Queue {}", statsQueue);
        Optional.ofNullable(getJmsTemplate()).orElseThrow(MissingBeanJmsTemplate::new).convertAndSend(statsQueue, response);
    }

    public void clear(Object response) {
        log.trace("Sending Ticket message to JMS Queue {}", statsQueue);
        Optional.ofNullable(getJmsTemplate()).orElseThrow(MissingBeanJmsTemplate::new).convertAndSend(statsClearQueue, response);
    }
}
