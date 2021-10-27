package mu.yanesh.lotto.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.yanesh.lotto.api.exception.NoDataFoundException;
import mu.yanesh.lotto.api.service.TirageService;
import mu.yanesh.lotto.library.models.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/${api.version}/tirage/")
public class TirageController {

    private final TirageService tirageService;

    @GetMapping("full")
    public ResponseEntity<List<Ticket>> getAllResults() {
        return ResponseEntity.ok(tirageService.getAllTickets());
    }

    @GetMapping("search/{resultDate}")
    public ResponseEntity<Ticket> findByDate(LocalDate resultDate) {
        return ResponseEntity.ok(tirageService.findTicket(resultDate).orElseThrow(NoDataFoundException::new));
    }
}
