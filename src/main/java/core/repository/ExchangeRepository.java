package core.repository;

import core.data.entity.ExchangeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends CrudRepository<ExchangeEntity, Long> {
}
