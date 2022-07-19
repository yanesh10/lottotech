package mu.yanesh.lotto.library.repository;

import mu.yanesh.lotto.library.models.ExtractionLog;

import java.util.Optional;

public interface IExtractionLogDataRepository {

    Optional<ExtractionLog> getLatestExtractionLog();

}
