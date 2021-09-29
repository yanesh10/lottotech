package mu.yanesh.lotto.library.repository;

import lombok.extern.slf4j.Slf4j;
import mu.yanesh.lotto.library.models.Ticket;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class TicketDataRepository extends BasicMongoCrudRepository<Ticket>  {

    public TicketDataRepository(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }
}
