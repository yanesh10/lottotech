package mu.yanesh.lotto.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("mu.yanesh.lotto")
public class LottoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LottoApiApplication.class, args);
    }

}
