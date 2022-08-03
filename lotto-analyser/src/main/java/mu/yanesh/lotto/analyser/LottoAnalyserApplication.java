package mu.yanesh.lotto.analyser;

import mu.yanesh.lotto.analyser.service.IAnalyser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import static java.lang.System.exit;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = "mu.yanesh.lotto",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value=Repository.class)})
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
