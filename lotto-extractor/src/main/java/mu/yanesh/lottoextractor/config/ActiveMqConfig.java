package mu.yanesh.lottoextractor.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;

@Configuration
@EnableJms
public class ActiveMqConfig {

    private final ConnectionFactory connectionFactory;
    private final ObjectMapper objectMapper;

    public ActiveMqConfig(@Qualifier("jmsConnectionFactory") ConnectionFactory connectionFactory,
            ObjectMapper objectMapper) {
        this.connectionFactory = connectionFactory;
        this.objectMapper = objectMapper;
    }

    @Bean
    MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        converter.setObjectMapper(objectMapper);
        return converter;
    }

    @Bean("jmsTemplateTopic")
    JmsTemplate jmsTemplateTopic() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setMessageConverter(messageConverter());
        jmsTemplate.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        jmsTemplate.setExplicitQosEnabled(true);
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }
}
