package mu.yanesh.lotto.api.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.yanesh.lotto.library.models.Ticket;
import mu.yanesh.lotto.library.repository.TicketDataRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class TirageServiceImpl implements TirageService {

    private final TicketDataRepository ticketDataRepository;

    public static final String COLLECTION_NAME = "raw_tirage";

    @Override
    public List<Ticket> getAllTickets() {
        return ticketDataRepository.findAll(Ticket.class, COLLECTION_NAME);
    }

    @Override
    public Optional<Ticket> findTicket(LocalDate resultDate) {
        Query query = new Query(Criteria.where("tirageDate").is(resultDate));
        return ticketDataRepository.findOne(query, Ticket.class);
    }

    @Override
    public List<Integer> frequentNumber(int limit, LocalDate dateLimit, boolean reverse) {
        List<Ticket> ticketList = getTicketList(dateLimit);
        HashMap<Integer, Integer> mapCount = generateMap();
        for (Ticket ticket : ticketList) {
            mapCount.put(ticket.getNumber1(), mapCount.get(ticket.getNumber1()) + 1);
            mapCount.put(ticket.getNumber2(), mapCount.get(ticket.getNumber2()) + 1);
            mapCount.put(ticket.getNumber3(), mapCount.get(ticket.getNumber3()) + 1);
            mapCount.put(ticket.getNumber4(), mapCount.get(ticket.getNumber4()) + 1);
            mapCount.put(ticket.getNumber5(), mapCount.get(ticket.getNumber5()) + 1);
            mapCount.put(ticket.getNumber6(), mapCount.get(ticket.getNumber6()) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new LinkedList<>(mapCount.entrySet());
        Collections.sort(list, Map.Entry.comparingByValue());
        if (reverse) {
            Collections.reverse(list);
        }
        return list.stream().limit(limit).map(Map.Entry::getKey).sorted().collect(Collectors.toList());
    }

    @Override
    public List<Integer> leastFrequentNumber(int limit, LocalDate dateLimit) {
        return frequentNumber(limit, dateLimit, false);
    }

    @Override
    public List<Integer> getRandomNumber() {
        List<Integer> numbers = frequentNumber(10, null, true);
        Collections.shuffle(numbers);
        return numbers.stream().limit(6).sorted().collect(Collectors.toList());
    }

    private List<Ticket> getTicketList(LocalDate dateLimit) {
        List<Ticket> ticketList = getAllTickets();
        if (Objects.nonNull(dateLimit)) {
            ticketList = getAllTickets().stream().filter(ticket -> ticket.getTirageDate().isAfter(dateLimit)).collect(
                    Collectors.toList());
        }
        return ticketList;
    }

    private HashMap<Integer, Integer> generateMap() {
        HashMap<Integer, Integer> mapCount = new HashMap<>();
        for (int i = 1; i <= 40; i++) {
            mapCount.put(i, 0);
        }
        return mapCount;
    }

}
