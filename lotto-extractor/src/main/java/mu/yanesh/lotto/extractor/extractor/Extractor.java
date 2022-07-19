package mu.yanesh.lotto.extractor.extractor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.yanesh.lotto.extractor.exception.MissingExtractionLogException;
import mu.yanesh.lotto.extractor.publish.TicketMessagePublisher;
import mu.yanesh.lotto.extractor.utils.CalendarUtils;
import mu.yanesh.lotto.library.models.ExtractionLog;
import mu.yanesh.lotto.library.models.Ticket;
import mu.yanesh.lotto.library.repository.ExtractionLogDataRepository;
import mu.yanesh.lotto.library.repository.IExtractionLogDataRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class Extractor implements IExtractor {

    private static final String NUM_GAGNANTS = "num-gagnants";
    private static final String SEPARATOR = ",";
    private static final String MOZILLA = "Mozilla";
    private static final String DATE_FORMAT = "dd+MMM+yyyy";

    @Value("${lottotech.tirage.url}")
    private String rootUrl;

    @Value("${lottotech.tirage.param}")
    private String queryParam;

    @Value("${lottotech.extractor.extract.weekends}")
    private boolean extractWeekend;

    @Value("${lottotech.extractor.extract.weekdays}")
    private boolean extractWeekdays;

    private final ExtractionLogDataRepository extractionLogDataRepository;

    private final TicketMessagePublisher publisher;

    @Override
    public Ticket getTirage(LocalDate date) {
        try {
            Document doc = Jsoup.connect(getURL(date.format(DateTimeFormatter.ofPattern(DATE_FORMAT))).toString())
                    .userAgent(MOZILLA)
                    .get();
            return getResult(doc, date);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void extract(boolean full) {
        log.info("******* EXTRACTION - START ******");
        LocalTime startTime = LocalTime.now();
        List<LocalDate> dates = new ArrayList<>();
        if (full) {
            if (extractWeekend) {
                dates.addAll(CalendarUtils.getWeekendsTirage(2009));
            }

            if (extractWeekdays) {
                dates.addAll(CalendarUtils.getWeekDaysTirage(2018));
            }
        } else {
            ExtractionLog extractionLog = extractionLogDataRepository.getLatestExtractionLog().orElse(null);
            if (Objects.isNull(extractionLog)) {
                log.warn("No extraction log found. Please run the full");
                throw new MissingExtractionLogException("No extraction log. Please run a full extraction instead");
            }
            if (extractWeekend) {
                dates.addAll(CalendarUtils.getWeekendsTirageByStartDate(extractionLog.getDateTime().toLocalDate()));
            }

            if (extractWeekdays) {
                dates.addAll(CalendarUtils.getWeekDaysTirageByStartDate(extractionLog.getDateTime().toLocalDate()));
            }
            if (CollectionUtils.isEmpty(dates)) {
                log.info("Latest tirage is already present");
                return;
            }
        }

        dates.parallelStream().map(this::getTirage).filter(Objects::nonNull).forEach(publisher::publish);

        ExtractionLog extractionLog = new ExtractionLog(null, ZonedDateTime.now());
        extractionLogDataRepository.save(extractionLog);
        Duration duration = Duration.between(startTime, LocalTime.now());
        log.info("Extracted {} with an Execution time: {}", dates.size(), duration.get(ChronoUnit.SECONDS));
        log.info("******* EXTRACTION - END ******");
    }

    private Ticket getResult(Document doc, LocalDate resultDate) {
        Element numGagnats = doc.getElementById(NUM_GAGNANTS);
        if (Objects.isNull(numGagnats)) {
            log.warn("No tirage for {}", resultDate.toString());
            return null;
        }

        List<Integer> resultats = Arrays
                .stream(Objects.requireNonNull(numGagnats)
                        .childNodes().get(0)
                        .toString()
                        .replace("\n", "")
                        .split(SEPARATOR))
                .map(String::trim)
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        return new Ticket(resultDate.format(DateTimeFormatter.ISO_DATE), resultats.get(0), resultats.get(1),
                resultats.get(2),
                resultats.get(3), resultats.get(4),
                resultats.get(5), resultDate);
    }

    private StringBuilder getURL(String date) {
        StringBuilder apiEndpoint = new StringBuilder(rootUrl);
        apiEndpoint.append("?");
        apiEndpoint.append(queryParam);
        apiEndpoint.append("=");
        apiEndpoint.append(date);
        return apiEndpoint;
    }
}
