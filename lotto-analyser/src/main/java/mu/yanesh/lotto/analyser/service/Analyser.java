package mu.yanesh.lotto.analyser.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.yanesh.lotto.analyser.client.LottoApiClient;
import mu.yanesh.lotto.analyser.publish.StatsMessagePublisher;
import mu.yanesh.lotto.library.models.Stats;
import mu.yanesh.lotto.library.models.Ticket;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@AllArgsConstructor
@Slf4j
public class Analyser implements IAnalyser {

    private final List<List<Integer>> combinations;
    private final StatsMessagePublisher statsMessagePublisher;
    private final LottoApiClient lottoApiClient;

    @Override
    public void analysis() {
        log.info("Analysing tirage data");
        AtomicInteger counter = new AtomicInteger();
        List<Ticket> ticketList = lottoApiClient.findAll();
        BigInteger totalCount = BigInteger.valueOf(ticketList.size()).multiply(BigInteger.valueOf(combinations.size()));
        log.info("Number of tickets retrieved: {}", ticketList.size());

        ticketList.parallelStream().forEach(ticket -> combinations.parallelStream().forEach(combination -> {
            log.info("{} of {}", counter.get(), totalCount);
            log.info("Percentage completed: {}" ,
                    BigInteger.valueOf(counter.get()).divide(totalCount).multiply(BigInteger.valueOf(100)));
            int count = 0;
            List<Integer> numberPresent = new ArrayList<>();
            count = checkCount(ticket, combination, count, numberPresent);
            if (count >= 2) {
                Stats stats = new Stats();
                stats.setDate(new Date());
                stats.setFrequency(stats.getFrequency() + 1);
                stats.setNumbers(numberPresent);
                stats.setCombination(numberPresent.size());
                statsMessagePublisher.publish(stats);
            }
            counter.getAndIncrement();
        }));
    }

    private int checkCount(Ticket ticket, List<Integer> combination, int count, List<Integer> numberPresent) {
        if (combination.contains(ticket.getNumber1())) {
            count++;
            numberPresent.add(ticket.getNumber1());
        }

        if (combination.contains(ticket.getNumber2())) {
            count++;
            numberPresent.add(ticket.getNumber2());
        }

        if (combination.contains(ticket.getNumber3())) {
            count++;
            numberPresent.add(ticket.getNumber3());
        }

        if (combination.contains(ticket.getNumber4())) {
            count++;
            numberPresent.add(ticket.getNumber4());
        }

        if (combination.contains(ticket.getNumber5())) {
            count++;
            numberPresent.add(ticket.getNumber5());
        }

        if (combination.contains(ticket.getNumber6())) {
            count++;
            numberPresent.add(ticket.getNumber6());
        }
        return count;
    }
}
