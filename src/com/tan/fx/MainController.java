package com.tan.fx;

import com.tan.fx.config.JmsConfig;
import com.tan.fx.dto.MqttFileManager;
import com.tan.fx.mqtt.consumer.RocketMQAPIConsumer;
import com.tan.fx.mqtt.producer.RocketmqSendService;
import com.tan.fx.service.LinkServerService;
import com.tan.fx.service.LogService;
import com.tan.fx.service.SubscribeService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Data;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;


/**
 * MainController
 *
 * @Author XZ.Tan
 * @Date: 2020/12/24 19:22
 * @Version 1.0
 */
@Data
public class MainController implements Initializable {

    /**
     * 连接信息
     */
    @FXML
    public TextField ip;
    @FXML
    public TextField port;
    @FXML
    public TextField status;
    @FXML
    public TextField userName;
    @FXML
    public TextField password;

    /**
     * 发送
     */
    @FXML
    public TextArea sendTopic;
    @FXML
    public TextArea sandMsg;

    /**
     * 订阅
     */
    @FXML
    public TextArea receiveTopic;
    @FXML
    public TextArea receiveMsg;

    /**
     * @FXML 表示从 javafx 映射组件
     */
    @FXML
    public TextArea logTextArea;

    public MainController() {
        StageManager.getInstance().setLogService(new LogService());
        StageManager.getInstance().setLinkServerService(new LinkServerService());

        StageManager.getInstance().setProducer(new DefaultMQProducer("outputActionCommandGroup"));
        StageManager.getInstance().setConsumer(new DefaultMQPushConsumer("tan"));

        StageManager.getInstance().setRocketmqSendService(new RocketmqSendService());
//        StageManager.getInstance().setRocketMQAPIConsumer(new RocketMQAPIConsumer());

        StageManager.getInstance().setMainController(this);
        StageManager.getInstance().setSubscribeService(new SubscribeService());


    }

    public void linkServerButton() {
        StageManager.getInstance().getLinkServerService().link();
    }

    public void sendMsg() {
        StageManager.getInstance().getRocketmqSendService().sendMessage();
    }

    public void subscribe() {
        StageManager.getInstance().getSubscribeService().subscribe();
    }

    /**
     * 清空消息
     */
    public void cleanMsg() {
        receiveMsg.clear();
    }

    /**
     * 清空消息
     */
    public void cleanLog() {
        logTextArea.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // 加载配置
        Object ip = null;
        Object port = null;
        Object username = null;
        Object password = null;
        try {
            Properties pro = new Properties();
            pro.load(MqttFileManager.getInstance().getResource().getInputStream());
            port = pro.get(JmsConfig.MQTT_PORT);
            ip = pro.get(JmsConfig.MQTT_IP);
            username = pro.get(JmsConfig.MQTT_USERNAME);
            password = pro.get(JmsConfig.MQTT_PASSWORD);
        } catch (IOException e1) {
            StageManager.log("文件读取失败 MqttFileManager: " + MqttFileManager.getInstance().getResource().getPath());
        }

        this.ip.setText((String) ip);
        this.port.setText((String) port);
        this.userName.setText((String) username);
        this.password.setText((String) password);
    }
}

