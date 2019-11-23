package com.neo.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author :  yuxuenan 2019年11月04日
 */
@Data
public class Message {
    /**
     * id
     */
    private Long id;
    /**
     * 消息
     */
    private String msg;
    /**
     * 时间戳
     */
    private Date sendTime;
}
