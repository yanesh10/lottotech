package mu.yanesh.lotto.library.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;

@Slf4j
public abstract class AbstractDataRepository<V, K> extends BasicMongoCrudRepository<V> {

    AbstractDataRepository(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

}
