package mu.yanesh.lotto.extractor.publish;

import lombok.extern.slf4j.Slf4j;
import mu.yanesh.lotto.extractor.exception.MissingBeanJmsTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.support.JmsGatewaySupport;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class ExtractionLogMessagePublisher extends JmsGatewaySupport {

    @Value("${messaging.extraction-publisher.queue-name}")
    private String batchQueue;

    public ExtractionLogMessagePublisher(@Qualifier("jmsTemplatePersistentQueue") JmsTemplate jmsTemplate) {
        setJmsTemplate(jmsTemplate);
    }

    public void publish(Object response) {
        log.trace("Sending Ticket message to JMS Queue {}", batchQueue);
        Optional.ofNullable(getJmsTemplate()).orElseThrow(MissingBeanJmsTemplate::new).convertAndSend(batchQueue, response);
    }
}
