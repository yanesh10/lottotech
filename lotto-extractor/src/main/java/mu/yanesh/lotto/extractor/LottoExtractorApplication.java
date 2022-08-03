package mu.yanesh.lotto.extractor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("mu.yanesh.lotto")
@EnableDiscoveryClient
public class LottoExtractorApplication {

    public static void main(String[] args) {
        SpringApplication.run(LottoExtractorApplication.class, args);
    }

}
