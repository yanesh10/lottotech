package mu.yanesh.lottoextractor.extractor;

import mu.yanesh.lottoextractor.models.Ticket;

import java.time.LocalDate;

public interface IExtractor {

    Ticket getTirage(LocalDate date);

    void extract();
}
