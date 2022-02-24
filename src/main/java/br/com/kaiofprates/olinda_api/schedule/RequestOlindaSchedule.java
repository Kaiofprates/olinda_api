package br.com.kaiofprates.olinda_api.schedule;

import br.com.kaiofprates.olinda_api.client.OlindaHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component @EnableScheduling
public class RequestOlindaSchedule {


    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OlindaHttpRequest client;

    @Scheduled(fixedDelayString="${schedule.time}")
    private void request () {
        try {
            log.info("Inicia requisição");

            var response = client.getPontos(1,100);
            System.out.printf(response.getAgenteSaques().toString());

            int totalPages = response.getMeta().getTotalPaginas();

            for (int i = 2; i <= totalPages; i++) {
                response = client.getPontos(i, 100);
                System.out.printf("dentro do for");
                System.out.printf(response.getAgenteSaques().toString());
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }



}
