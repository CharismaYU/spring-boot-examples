package com.neo.controller;

import com.neo.KafkaProperties;
import com.neo.component.KafkaSender;
import com.neo.consumer.MyConsumer;
import com.neo.producer.MyProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :  yuxuenan 2019年11月04日
 */
@RestController
@RequestMapping("kafka")
public class TestKafkaProducerController {

    @Autowired
    private KafkaSender kafkaSender;

    @RequestMapping("/send")
    public String send() {
        kafkaSender.send();
        return "success";
    }

    @RequestMapping("/sendTest")
    public String sendTest() {
        boolean isAsync = false;
        MyProducer producer = new MyProducer(KafkaProperties.TOPIC, isAsync);
        producer.start();
        MyConsumer consumer = new MyConsumer(KafkaProperties.TOPIC);
        consumer.start();
        return "success";
    }
}
