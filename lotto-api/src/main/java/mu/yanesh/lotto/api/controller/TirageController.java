package mu.yanesh.lotto.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.yanesh.lotto.api.exception.NoDataFoundException;
import mu.yanesh.lotto.api.service.TirageService;
import mu.yanesh.lotto.library.models.Ticket;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping("frequently")
    public ResponseEntity<List<Integer>> frequentNumber(@RequestParam(required = false) Integer limit,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateLimit) {
        return ResponseEntity.ok(tirageService.frequentNumber(Optional.ofNullable(limit).orElse(6), dateLimit, true));
    }

    @GetMapping("leastFrequently")
    public ResponseEntity<List<Integer>> leastFrequentNumber(@RequestParam(required = false) Integer limit,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateLimit) {
        return ResponseEntity.ok(tirageService.frequentNumber(Optional.ofNullable(limit).orElse(6), dateLimit, false));
    }

    @GetMapping("play")
    public ResponseEntity<List<Integer>> getRandomNumber() {
        return ResponseEntity.ok(tirageService.getRandomNumber());
    }
}
