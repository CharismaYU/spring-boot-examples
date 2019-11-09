package com.neo.com.neo.component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.neo.com.neo.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @author :  yuxuenan 2019年11月04日
 */
@Component
@Slf4j
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        //message.setMsg(UUID.randomUUID().toString());
        message.setMsg("123467");
        message.setSendTime(new Date());
        log.info("=========================message = {}", gson.toJson(message));
        kafkaTemplate.send("yyy", gson.toJson(message));
    }
}
