package mu.yanesh.lotto.library.repository;

import mu.yanesh.lotto.library.models.Stats;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StatsRepository extends BasicMongoCrudRepository<Stats> {

    public StatsRepository(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }
}
