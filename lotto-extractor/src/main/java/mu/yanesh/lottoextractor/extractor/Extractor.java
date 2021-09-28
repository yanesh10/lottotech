package mu.yanesh.lottoextractor.extractor;

import lombok.extern.slf4j.Slf4j;
import mu.yanesh.lottoextractor.models.Ticket;
import mu.yanesh.lottoextractor.utils.CalendarUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class Extractor implements IExtractor{

    private static final String NUM_GAGNANTS = "num-gagnants";
    private static final String SEPARATOR = ",";
    private static final String MOZILLA = "Mozilla";

    @Value("${lottotech.tirage.url}")
    private String rootUrl;

    @Value("${lottotech.tirage.param}")
    private String queryParam;

    @Override
    public Ticket getTirage(LocalDate date){
        try {
            Document doc = Jsoup.connect(getURL(date.toString()).toString())
                    .userAgent(MOZILLA)
                    .get();
            return getResult(doc);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void extract() {
        List<LocalDate> dates = CalendarUtils.getWeekends(2021);
        dates.forEach(this::getTirage);
    }

    private Ticket getResult(Document doc) {
        List<Integer> resultats = Arrays
                .stream(Objects.requireNonNull(doc.getElementById(NUM_GAGNANTS)).text().replaceAll("\\s", "")
                        .split(SEPARATOR)).map(Integer::getInteger).collect(Collectors.toList());
        return new Ticket(resultats.get(0), resultats.get(1), resultats.get(2), resultats.get(3), resultats.get(4),
                resultats.get(5));
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
