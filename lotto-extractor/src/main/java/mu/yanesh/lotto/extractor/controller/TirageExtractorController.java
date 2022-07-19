package mu.yanesh.lotto.extractor.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.yanesh.lotto.extractor.extractor.IExtractor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("api/${api.version}/extractor")
public class TirageExtractorController {

    private final IExtractor extractor;

    @GetMapping("/extract/full")
    public ResponseEntity<Boolean> launchExtractionFull() {
        log.info("Request for extraction full: {}", LocalDateTime.now());
        extractor.extract(true);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @GetMapping("/extract/update")
    public ResponseEntity<Boolean> launchExtractionUpdate() {
        log.info("Request for extraction: {}", LocalDateTime.now());
        extractor.extract(false);
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
