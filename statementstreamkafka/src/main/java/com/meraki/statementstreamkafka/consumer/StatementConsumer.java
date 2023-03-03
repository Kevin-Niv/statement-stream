package com.meraki.statementstreamkafka.consumer;

import com.google.gson.Gson;
import com.meraki.statementstreamkafka.entity.StatementEntity;
import com.meraki.statementstreamkafka.entity.StatementEntityWrapper;
import com.meraki.statementstreamkafka.exception.ApiRequestException;
import com.meraki.statementstreamkafka.repository.StatementRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatementConsumer {

    private static final Logger logger = LoggerFactory.getLogger(StatementConsumer.class);

    private StatementRepository statementRepository;

    public StatementConsumer(StatementRepository statementRepository) {
        this.statementRepository = statementRepository;
    }


    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")

    public void consume(String payload) {
        try {
            if (payload != null && payload.length() > 0) {
                logger.info("Statement Info received..." + payload);
                System.out.println("message = " + payload);

  /*          Gson gson = new Gson();

            StatementEntity statement = gson.fromJson(payload, StatementEntity.class);

            String recId = statement.getRecId();
            String xmlRecord = statement.getXmlRecord();*/

                JSONObject obj = new JSONObject(payload);

                StatementEntity statement = new StatementEntity();
                String recId = obj.getString("RECID");
                String xmlRecord = obj.getString("XMLRECORD");
                if (recId != null && recId.length() > 0 && xmlRecord != null && xmlRecord.length() > 0) {

                    logger.info("RecId: {}", recId);
                    logger.info("XmlRecord: {}", xmlRecord);

                    statement.setRecId(recId);
                    statement.setXmlRecord(xmlRecord);

                    Optional<StatementEntity> inDB = statementRepository.findByRecId(recId);
                    if (inDB.isEmpty()) {
                        StatementEntity record = statementRepository.save(statement);

                        logger.info("consume | Record inserted successfully..." + recId);
                        //throw new ApiRequestException("consume | Record failed insert ...");

                    } else {
                        //throw new ApiRequestException("consume | Record already exists...");
                        logger.error("consume | Record already exists..." + recId);
                    }

                } else
                    //throw new ApiRequestException("Empty payload in RecId or XmlRecord field");
                    logger.error("Empty payload in RecId or XmlRecord field");

            } else {
                //throw new ApiRequestException("No Payload consumed | found in topic...");
                logger.error("No Payload consumed | found in topic...");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
