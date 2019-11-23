package com.neo.producer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.neo.KafkaProperties;
import com.neo.domain.Message;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * @author :  yuxuenan 2019年11月11日
 */
public class MyProducer extends Thread {

    private final KafkaProducer<Integer, String> producer;
    private final String topic;
    private final Boolean isAsync;
    @Value("${spring.kafka.bootstrap-servers}")
    private static String KAFKA_SERVERS = "192.168.80.132:9092,192.168.80.128:9092,192.168.80.131:9092";
    private Gson gson = new GsonBuilder().create();

    public MyProducer(String topic, Boolean isAsync) {
        Properties properties = new Properties();
        // 用于建立与kafka集群的链接，这个list仅仅影响用于初始化的hosts，来发现全部的servers.
        System.out.println(KAFKA_SERVERS);
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVERS);
        // 当向server发出请求时，这个字符串会发送给server，目的是能够追踪请求源
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "DemoProducer");
        // key 序列化方式，类型为class，需实现Serializer interface
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        // value 序列化方式，类型为class，需实现Serializer interface
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 发生错误时，重传次数。当开启重传时，需要将`max.in.flight.requests.per.connection`设置为1，否则可能导致失序
        properties.put(ProducerConfig.RETRIES_CONFIG, 1);
        properties.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1);
        properties.put(ProducerConfig.ACKS_CONFIG, "1");
        producer = new KafkaProducer<>(properties);
        this.topic = topic;
        this.isAsync = isAsync;
    }

    public void run() {
        int messageNo = 1;

        while (true) {
            Message message = new Message();
            long startTime = System.currentTimeMillis();
            message.setId(startTime);
            message.setMsg(UUID.randomUUID().toString());
            message.setSendTime(new Date());
            if (isAsync) { //send asynchronously
                producer.send(new ProducerRecord<>(topic, messageNo, gson.toJson(message)), new DemoCallBack(startTime, messageNo, gson.toJson(message)));
            } else {
                try {
                    producer.send(new ProducerRecord<>(topic, messageNo, gson.toJson(message))).get();
                    System.out.println("Sent message: (" + messageNo + ", " + gson.toJson(message) + ")");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            ++messageNo;
            try {
                sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class DemoCallBack implements Callback {

    private final long startTime;
    private final int key;
    private final String message;

    public DemoCallBack(long startTime, int key, String message) {
        this.startTime = startTime;
        this.key = key;
        this.message = message;
    }

    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (metadata != null) {
            System.out.println("message(" + key + ", " + message + ") sent to partition(" + metadata.partition() +
                    "), " + "offset(" + metadata.offset() + ") in " + elapsedTime + " ms");
        } else {
            exception.printStackTrace();
        }

    }
}