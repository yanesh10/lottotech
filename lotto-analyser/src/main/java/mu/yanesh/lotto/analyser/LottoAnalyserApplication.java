package mu.yanesh.lotto.analyser;

import mu.yanesh.lotto.analyser.service.IAnalyser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import static java.lang.System.exit;

@SpringBootApplication
@ComponentScan("mu.yanesh.lotto")
public class LottoAnalyserApplication implements CommandLineRunner {

    @Autowired
    private IAnalyser analyser;

    public static void main(String[] args) {
        SpringApplication.run(LottoAnalyserApplication.class, args);
    }

    @Override
    public void run(String... args) {
        analyser.analysis();

        exit(0);
    }
}
