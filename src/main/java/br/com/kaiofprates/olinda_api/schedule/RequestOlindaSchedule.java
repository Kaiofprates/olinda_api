package br.com.kaiofprates.olinda_api.schedule;

import br.com.kaiofprates.olinda_api.client.OlindaHttpRequest;
import br.com.kaiofprates.olinda_api.models.AgenteSaque;
import br.com.kaiofprates.olinda_api.models.redisModels.Parceiro;
import br.com.kaiofprates.olinda_api.models.redisModels.ParceiroRedisHash;
import br.com.kaiofprates.olinda_api.repository.ParceiroRedisHashRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component @EnableScheduling
public class RequestOlindaSchedule {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OlindaHttpRequest client;

    @Autowired
    private ParceiroRedisHashRepository repository;

    @Scheduled(cron="${schedule.cron}")
    private void request () {
        try {
            log.info("Inicia requisição");

            var response = client.getPontos(1,100);
            var parceiros = response.getAgenteSaques();
            saveParceiro(parceiros);

            int totalPages = response.getMeta().getTotalPaginas();

            for (int i = 2; i <= totalPages; i++) {

                response = client.getPontos(i, 100);
                parceiros = response.getAgenteSaques();
                saveParceiro(parceiros);

            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

    private void saveParceiro( List<AgenteSaque> parceiros) {

        parceiros.stream().forEach(p ->
                repository.save(agenteToParceiro(p))
                );

    }

    private ParceiroRedisHash agenteToParceiro(AgenteSaque agenteSaque) {

        String id = UUID.randomUUID().toString();

        ParceiroRedisHash parceiroRedisHash = new ParceiroRedisHash();
        Parceiro parceiro = new Parceiro();
        parceiro.setNome(agenteSaque.getNome());
        parceiro.setCnpj(agenteSaque.getCnpj());

        parceiroRedisHash.setId(id);
        parceiroRedisHash.setParceiro(parceiro);

        log.info("[PERSISTINDO] parceiro: " + parceiro.getNome() + " cnpj: " + parceiro.getCnpj());

        return parceiroRedisHash;

    }

}
