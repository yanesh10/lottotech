package mu.yanesh.lotto.analyser.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.yanesh.lotto.analyser.publish.StatsMessagePublisher;
import mu.yanesh.lotto.library.models.Stats;
import mu.yanesh.lotto.library.models.Ticket;
import mu.yanesh.lotto.library.repository.TicketDataRepository;
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

    private final TicketDataRepository ticketDataRepository;

    private final StatsMessagePublisher statsMessagePublisher;

    public static final String COLLECTION_NAME = "raw_tirage";

    @Override
    public void analysis() {
        log.info("Analysing tirage data");
        AtomicInteger counter = new AtomicInteger();
        List<Ticket> ticketList = ticketDataRepository.findAll(Ticket.class, COLLECTION_NAME);
        BigInteger totalCount = BigInteger.valueOf(ticketList.size()).multiply(BigInteger.valueOf(combinations.size()));
        log.info("Number of tickets retrived: {}", ticketList.size());

        statsMessagePublisher.clear("clear");

        ticketList.parallelStream().forEach(ticket -> combinations.parallelStream().forEach(combination -> {
            log.info("{} of {}", counter.get(), totalCount);
            log.info("Percentage completed: {}" ,
                    BigInteger.valueOf(counter.get()).divide(totalCount).multiply(BigInteger.valueOf(100)));
            int count = 0;
            List<Integer> numberPresent = new ArrayList<>();
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
}
