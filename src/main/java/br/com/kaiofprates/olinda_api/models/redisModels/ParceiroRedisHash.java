package br.com.kaiofprates.olinda_api.models.redisModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "parceiro", timeToLive = 60)
public class ParceiroRedisHash {

    private String id;
    private Parceiro parceiro;

}
