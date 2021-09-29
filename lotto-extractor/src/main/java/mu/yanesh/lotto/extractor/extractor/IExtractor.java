package mu.yanesh.lotto.extractor.extractor;

import mu.yanesh.lotto.extractor.models.Ticket;

import java.time.LocalDate;

public interface IExtractor {

    Ticket getTirage(LocalDate date);

    void extract();

}
