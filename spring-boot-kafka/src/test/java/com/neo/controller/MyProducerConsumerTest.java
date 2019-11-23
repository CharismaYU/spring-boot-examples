package com.neo.controller;

import com.neo.KafkaProperties;
import com.neo.consumer.MyConsumer;
import com.neo.producer.MyProducer;

/**
 * @author :  yuxuenan 2019年11月12日
 */
public class MyProducerConsumerTest {

    public static void main(String[] args) {
        boolean isAsync = args.length == 0 || !args[0].trim().equalsIgnoreCase("sync");

        MyProducer producer = new MyProducer(KafkaProperties.TOPIC, isAsync);
        producer.start();

        MyConsumer consumer = new MyConsumer(KafkaProperties.TOPIC);
        consumer.start();
    }
}
