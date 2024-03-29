package mu.yanesh.lotto.library.repository;

import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

public interface MongoCrudRepository<V> {

    void save(V entity);
    void save(V entity, String collectionName);

    Optional<V> findById(Object id, Class<V> entityType);
    Optional<V> findOne(Query query, Class<V> entityType);

    List<V> findAll(Class<V> entityType, String collectionName);
    Optional<List<V>> findAll(Query query, Class<V> entityType);

    void deleteAll(String collectionName);
}
