package com.tan.fx.mqtt.producer;

import com.tan.fx.MainController;
import com.tan.fx.StageManager;
import com.tan.fx.config.JmsConfig;
import com.tan.fx.dto.MqttFileManager;
import javafx.scene.control.TextArea;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * Rocketmq 发送器
 *
 * @title: RocketmqSendService
 * @Author Tan
 * @Date: 2020/11/25 11:08
 * @Version 1.0
 */
@Slf4j
@Data
public class RocketmqSendService<T> {

    /**
     * 发送消息到Rocketmq
     * 调用之前需要先设置 topic
     *
     * @param messageObj
     */
    public void sendMessage(final T messageObj, String topic) {
        // 构造消息
        Message message = new Message(topic, messageObj.toString().getBytes());
        try {
            StageManager.getInstance().getProducer().send(message);
            StageManager.log("成功发送消息：" + message);
        } catch (MQClientException e) {
            StageManager.log("服务未连接！！" + e.getErrorMessage());
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 发送消息到Rocketmq
     * 调用之前需要先设置 topic
     *
     */
    public void sendMessage() {
        MainController mainController = StageManager.getInstance().getMainController();
        String msg = mainController.sandMsg.getText();
        String topicText = mainController.sendTopic.getText();
        if (!StringUtils.isEmpty(msg.trim())) {
            String[] split = topicText.split(",");
            for (String top : split) {
                sendMessage((T) msg, top.trim());
            }
        }else {
            StageManager.log("发送数据为空");
        }
    }

}