package com.tan.fx.service;

import com.tan.fx.MainController;
import com.tan.fx.StageManager;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;

import java.io.UnsupportedEncodingException;

/**
 * SubscribeService
 *
 * @Author XZ.Tan
 * @Date: 2020/12/25 19:19
 * @Version 1.0
 */
public class SubscribeService {

    /**
     * 订阅
     */
    public void subscribe(){
        MainController mainController = StageManager.getInstance().getMainController();

        String ip = mainController.ip.getText();
        String port = mainController.port.getText();
        String userName = mainController.userName.getText();
        String password = mainController.password.getText();
        // 连接
        getConsumer(ip, port, userName, password);


    }

    private void getConsumer(String ip, String port, String userName, String password) {

        DefaultMQPushConsumer consumer = StageManager.getInstance().getConsumer();

        consumer.setNamesrvAddr(ip + ":" + port);
        //消费模式:一个新的订阅组第一次启动从队列的最后位置开始消费 后续再启动接着上次消费的进度开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        //订阅主题和 标签（ * 代表所有标签)下信息

        MainController mainController = StageManager.getInstance().getMainController();
        String text = mainController.receiveTopic.getText();
        String[] split = text.split(",");
        for (String top : split) {
            try {
                consumer.subscribe(top.trim(), "*");
                start(consumer);
                StageManager.log("订阅成功：" + top.trim());
            } catch (MQClientException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 通过构造函数 实例化对象
     */
    public void start(DefaultMQPushConsumer consumer) {
        try {
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