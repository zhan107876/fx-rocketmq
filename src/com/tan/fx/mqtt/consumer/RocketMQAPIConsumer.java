package com.tan.fx.mqtt.consumer;

import com.tan.fx.StageManager;
import com.tan.fx.config.JmsConfig;
import com.tan.fx.dto.MqttFileManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * RocketMQA 原生api 消费者基类
 *
 * @title: APIConsumer
 * @Author Tan
 * @Date: 2020/11/25 21:40
 * @Version 1.0
 */
@Slf4j
public class RocketMQAPIConsumer<T> {

    /**
     * 通过构造函数 实例化对象
     */
    public RocketMQAPIConsumer() {

        DefaultMQPushConsumer consumer = StageManager.getInstance().getConsumer();

        try {
//            log.info(super.getClass().getName() + "group: " + getGroup());
//            log.info(super.getClass().getName() + "subscribe-topic: " + getTopic());
            // //注册消费的监听 并在此监听中消费信息，并返回消费的状态信息
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                // msgs中只收集同一个topic，同一个tag，并且key相同的message
                // 会把不同的消息分别放置到不同的队列中
                try {
                    for (Message msg : msgs) {
                        //消费者获取消息 这里只输出 不做后面逻辑处理
                        String body = new String(msg.getBody(), "utf-8");
                        StageManager.log("Consumer-获取消息-主题topic为=" + msg.getTopic() + ", 消费消息为=" + body);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            consumer.start();
            System.out.println(super.getClass().getName() + "消费者 启动成功=======");

        } catch (MQClientException e) {
            e.printStackTrace();
        }

    }

}