package br.com.kaiofprates.olinda_api.consumer;

import br.com.kaiofprates.olinda_api.elasticache.ParceiroRedis;
import br.com.kaiofprates.olinda_api.models.sqsparceiros.SqsParceirosMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.regions.Region;

import java.io.IOException;
import java.net.URI;
import java.util.Objects;

@Service
@ConditionalOnExpression(value = "${consumer.enabled}")
public class SqsConsumer {

    @Autowired
    private ParceiroRedis redis;

    public void listenner() {

        SqsClient client = SqsClient.builder()
                .region(Region.US_EAST_1)
                .endpointOverride(URI.create("http://localhost:4566"))
                .build();

        var queueUrl = "http://localhost:4566/000000000000/teste";

        ObjectMapper objectMapper = new ObjectMapper();

        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .waitTimeSeconds(5)
                .maxNumberOfMessages(10)
                .build();

        while (true) {
            client.receiveMessage(receiveMessageRequest)
                    .messages()
                    .forEach( message -> {
                        try {
                             SqsParceirosMessage sqsParceirosMessage = objectMapper.readValue(message.body(),
                                     SqsParceirosMessage.class);
                             if (Objects.nonNull(sqsParceirosMessage)) {

                                 String urlApi = sqsParceirosMessage.getUrl();
                                 int page = sqsParceirosMessage.getPage();
                                 int size = sqsParceirosMessage.getSize();
                                 redis.request(urlApi, page, size);
                             }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }

    }


}
