package mu.yanesh.lottoextractor.controller;

import lombok.AllArgsConstructor;
import mu.yanesh.lottoextractor.extractor.IExtractor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/${api.version}/extractor")
public class TirageExtractorController {

    private final IExtractor extractor;

    @GetMapping("/extract")
    public ResponseEntity<Boolean> launchExtraction() {
        extractor.extract();
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
