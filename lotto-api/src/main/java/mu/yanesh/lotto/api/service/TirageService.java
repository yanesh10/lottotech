package mu.yanesh.lotto.api.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.yanesh.lotto.library.models.Ticket;
import mu.yanesh.lotto.library.repository.TicketDataRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class TirageService implements ITirageService {

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

}
