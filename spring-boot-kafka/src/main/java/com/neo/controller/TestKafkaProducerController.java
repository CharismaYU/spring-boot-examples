package com.neo.controller;

import com.neo.com.neo.component.KafkaSender;
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
}
