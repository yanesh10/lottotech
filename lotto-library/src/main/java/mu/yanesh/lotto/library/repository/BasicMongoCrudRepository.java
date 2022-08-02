package mu.yanesh.lotto.library.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.yanesh.lotto.library.exception.MongoDataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class BasicMongoCrudRepository<V> implements MongoCrudRepository<V> {

    protected final MongoTemplate mongoTemplate;

    @Override
    public void save(V entity) {
        try {
            mongoTemplate.save(entity);
        } catch (DataAccessResourceFailureException e) {
            throw new MongoDataAccessException("Entity could not be stored:", entity.toString(), e);
        }
    }

    @Override
    public void save(V entity, String collectionName) {
        mongoTemplate.save(entity, collectionName);
    }

    @Override
    public Optional<V> findById(Object id, Class<V> entityType) {
        final V result = mongoTemplate.findById(id, entityType);
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<V> findOne(Query query, Class<V> entityType) {
        V result = mongoTemplate.findOne(query, entityType);
        return Optional.ofNullable(result);
    }

    @Override
    public List<V> findAll(Class<V> entityType, String collectionName) {
        return mongoTemplate.findAll(entityType, collectionName);
    }

    @Override
    public Optional<List<V>> findAll(Query query, Class<V> entityType) {
        List<V> result = mongoTemplate.find(query, entityType);
        return Optional.of(result);
    }

    @Override
    public void deleteAll(String collectionName) {
        mongoTemplate.dropCollection(collectionName);
    }
}
