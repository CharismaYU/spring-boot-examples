package com.neo.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ConfigurationProperties 将类中的所有属性和配置文件中相关的配置进行绑定。 prefix : 配置文件中的属性值
 */
@ConfigurationProperties(prefix = "com.yyy")
public class ConfigBean {

    private String name;

    private String want;

    private String yearHope;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWant() {
        return want;
    }

    public void setWant(String want) {
        this.want = want;
    }

    public String getYearHope() {
        return yearHope;
    }

    public void setYearHope(String yearHope) {
        this.yearHope = yearHope;
    }
}
