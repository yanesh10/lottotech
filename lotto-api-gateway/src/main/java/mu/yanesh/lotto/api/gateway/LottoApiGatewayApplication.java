package mu.yanesh.lotto.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LottoApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LottoApiGatewayApplication.class, args);
    }

}
