package mu.yanesh.lotto.extractor.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.yanesh.lotto.extractor.extractor.IExtractor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExtractorScheduler {

    private final IExtractor extractor;

    @Value("${extractor.scheduler.launch-on-startup}")
    private boolean launchOnStartup;

    @Scheduled(cron = "${extractor.scheduler.cron}")
    private void extract() {
        if (launchOnStartup) {
            log.info("****Launching extractor****");
            log.info("Starting Date and Time: {}", LocalDateTime.now());

            extractor.extract();

            log.info("****Completed extractor****");
        }
    }
}
