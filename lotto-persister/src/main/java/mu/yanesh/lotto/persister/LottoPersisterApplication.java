package mu.yanesh.lotto.persister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("mu.yanesh.lotto")
public class LottoPersisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(LottoPersisterApplication.class, args);
    }

}
