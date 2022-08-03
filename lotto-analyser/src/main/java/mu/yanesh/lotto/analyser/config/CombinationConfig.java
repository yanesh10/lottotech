package mu.yanesh.lotto.analyser.config;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.paukov.combinatorics3.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class CombinationConfig {

    @Autowired
    private Environment env;

    @Bean
    public List<List<Integer>> combinations() {
        log.info("Creating combination bean");
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            numbers.add(i);
        }
        int combination = env.getProperty("combinations", Integer.class);

        combinations.addAll(Generator.combination(numbers).simple(combination).stream().toList());
//        combinations.addAll(Generator.combination(numbers).simple(3).stream().toList());
//        combinations.addAll(Generator.combination(numbers).simple(4).stream().toList());
//        combinations.addAll(Generator.combination(numbers).simple(5).stream().toList());
//        combinations.addAll(Generator.combination(numbers).simple(6).stream().toList());

        log.info("Combination bean created with {}", combinations.size());

        return combinations;
    }

}
