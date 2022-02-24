package br.com.kaiofprates.olinda_api.repository;

import br.com.kaiofprates.olinda_api.models.redisModels.ParceiroRedisHash;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParceiroRedisHashRepository  extends CrudRepository<ParceiroRedisHash, String> {
}
