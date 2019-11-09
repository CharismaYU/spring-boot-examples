package com.neo.com.neo.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author :  yuxuenan 2019年11月09日
 */
//@Component
@Slf4j
public class KafkaConsumer {

    //@KafkaListener(topics = {"yyy"})
    public void listen(ConsumerRecord record) {
        Optional kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            log.info("----------------- record =" + record);
            log.info("------------------ message =" + message);
        }
    }
}