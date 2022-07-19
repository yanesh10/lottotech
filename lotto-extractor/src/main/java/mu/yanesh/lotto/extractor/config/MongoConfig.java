package mu.yanesh.lotto.extractor.config;

import mu.yanesh.lotto.extractor.converter.ZonedDateTimeReadConverter;
import mu.yanesh.lotto.extractor.converter.ZonedDateTimeWriteConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;

@Configuration
public class MongoConfig {

    @Bean
    public MongoCustomConversions mongoCustomConversions() {

        return new MongoCustomConversions(
                Arrays.asList(
                        new ZonedDateTimeReadConverter(),
                        new ZonedDateTimeWriteConverter()));
    }
}
