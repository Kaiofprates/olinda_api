package br.com.kaiofprates.olinda_api.schedule;

import br.com.kaiofprates.olinda_api.consumer.SqsConsumer;
import br.com.kaiofprates.olinda_api.elasticache.ParceiroRedis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component @EnableScheduling
@ConditionalOnExpression(value = "${schedule.enabled}")
public class RequestOlindaSchedule {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SqsConsumer sqsConsumer;

    @Scheduled(cron="${schedule.cron}")
    private void request () {
        log.info("Inicia consumo sqs");
        sqsConsumer.listenner();
    }
}
