package mu.yanesh.lotto.api.service;

import mu.yanesh.lotto.library.models.Ticket;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TirageService {

    List<Ticket> getAllTickets();

    Optional<Ticket> findTicket(LocalDate resultDate);

}
