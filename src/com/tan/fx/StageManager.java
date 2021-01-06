package com.tan.fx;

import com.tan.fx.mqtt.consumer.RocketMQAPIConsumer;
import com.tan.fx.mqtt.producer.RocketmqSendService;
import com.tan.fx.service.LinkServerService;
import com.tan.fx.service.LogService;
import com.tan.fx.service.SubscribeService;
import lombok.Data;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;


import java.util.HashMap;

/**
 * StageManager
 *
 * @Author XZ.Tan
 * @Date: 2020/12/25 16:26
 * @Version 1.0
 */
@Data
public class StageManager {

    private static StageManager instance = new StageManager();;

    private StageManager (){}

    public static synchronized StageManager getInstance() {
        if (instance == null) {
            instance = new StageManager();
        }
        return instance;
    }

    private LogService logService;

    private DefaultMQProducer producer;

    private DefaultMQPushConsumer consumer;

    private MainController mainController;

    private LinkServerService linkServerService;

    private RocketmqSendService rocketmqSendService;

    private RocketMQAPIConsumer rocketMQAPIConsumer;

    private SubscribeService subscribeService;

    public static void log(String msg) {
        StageManager.getInstance().getLogService().print(msg);
    }

}