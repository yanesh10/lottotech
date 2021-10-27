package mu.yanesh.lotto.api.service;

import mu.yanesh.lotto.library.models.Ticket;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ITirageService {

    List<Ticket> getAllTickets();

    Optional<Ticket> findTicket(LocalDate resultDate);

}
