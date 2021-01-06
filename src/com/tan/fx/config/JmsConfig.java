package com.tan.fx.config;

/**
 * @title: JmsConfig
 * @Author Tan
 * @Date: 2020/11/25 21:38
 * @Version 1.0
 */
public interface JmsConfig {

    String MQTT_HOST = "mqttUri";
    String MQTT_PORT = "mqttPort";
    String MQTT_IP = "mqttIp";
    String MQTT_USERNAME = "mqttUserName";
    String MQTT_PASSWORD = "mqttPassword";
    String MQTT_DEFAULT_CLIENT = "CallbackServer";

    Integer DEAULT_QOS = 2;  //发布订阅消息质量控制服务，0表示最多一次，1表示至少一次，2表示恰好1次

    /**
     * Name Server 地址，因为是集群部署 所以有多个用 分号 隔开
     */
    public static final String NAME_SERVER = "192.168.3.133:9876";
    /**
     * 主题名称 主题一般是服务器设置好 而不能在代码里去新建topic（ 如果没有创建好，生产者往该主题发送消息 会报找不到topic错误）
     */
    public static final String TOPIC = "topic_family";

}