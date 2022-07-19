package mu.yanesh.lotto.library.repository;

import mu.yanesh.lotto.library.models.ExtractionLog;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.Optional;


@Repository
public class ExtractionLogDataRepository extends BasicMongoCrudRepository<ExtractionLog> implements IExtractionLogDataRepository {

    public ExtractionLogDataRepository(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

    @Override
    public Optional<ExtractionLog> getLatestExtractionLog() {
        return this.mongoTemplate.findAll(ExtractionLog.class).parallelStream().max(Comparator.comparing(ExtractionLog::getDateTime));
    }
}
