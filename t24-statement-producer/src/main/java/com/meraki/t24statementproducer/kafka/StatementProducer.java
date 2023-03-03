package com.meraki.t24statementproducer.kafka;

import com.meraki.t24statementproducer.entity.StatementModel;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class StatementProducer {

    private static final Logger logger = LoggerFactory.getLogger(StatementProducer.class);

    private NewTopic newTopic;

    private KafkaTemplate<String, StatementModel> kafkaTemplate;

    public StatementProducer(NewTopic newTopic, KafkaTemplate<String, StatementModel> kafkaTemplate) {
        this.newTopic = newTopic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendStatement(StatementModel statementModel){
        logger.info("Statement Send........"+ statementModel);

        //create
        Message<StatementModel> message = MessageBuilder
                .withPayload(statementModel)
                .setHeader(KafkaHeaders.TOPIC, newTopic.name())
                .build();

        kafkaTemplate.send(message);
    }
}
