package mu.yanesh.lotto.analyser.config;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import mu.yanesh.lotto.analyser.exception.InvalidCombinationValue;
import org.paukov.combinatorics3.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
@Slf4j
public class CombinationConfig {

    @Bean
    public List<List<Integer>> combinations(@Autowired Environment env) {
        log.info("Creating combination bean");
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            numbers.add(i);
        }
        int combination =
                Optional.ofNullable(env.getProperty("combinations", Integer.class))
                        .orElseThrow(InvalidCombinationValue::new);

        List<List<Integer>> combinations = (Generator.combination(numbers).simple(combination).stream().toList());
        log.info("Combination bean created with {} ", combinations.size());

        return combinations;
    }

}
