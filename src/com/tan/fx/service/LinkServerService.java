package com.tan.fx.service;

import com.tan.fx.MainController;
import com.tan.fx.StageManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

import java.io.IOException;
import java.util.Properties;

/**
 * 服务连接处理
 *
 * @Author XZ.Tan
 * @Date: 2020/12/25 15:37
 * @Version 1.0
 */
@Slf4j
public class LinkServerService {

    public LinkServerService() {

    }

    public void link() {
        MainController mainController = StageManager.getInstance().getMainController();

        String ip = mainController.ip.getText();
        String port = mainController.port.getText();
        String userName = mainController.userName.getText();
        String password = mainController.password.getText();
        // 连接
        getProducer(ip, port, userName, password);

    }

    public void getProducer(String ip, String port , String userName, String password) {
        DefaultMQProducer producer = StageManager.getInstance().getProducer();

        if (!producer.getInstanceName().equals("DEFAULT")) {
            StageManager.log(" 服务已连接，请不要重复连！！");
            return;
        }

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("连接信息：");
        stringBuffer.append("ip：" + ip);
        stringBuffer.append(", port：" + port);
        stringBuffer.append(", userName：" + userName);
        stringBuffer.append(", password：" + password);
        StageManager.log(stringBuffer.toString());

        //不开启vip通道 开通口端口会减2
        producer.setVipChannelEnabled(false);
        //绑定name server
        producer.setNamesrvAddr(ip + ":" + port);
        try {
            StageManager.log((super.getClass().getName() + "topic: " + producer.getCreateTopicKey()));
            StageManager.log((super.getClass().getName() + "group: " + producer.getProducerGroup()));
            producer.start();
            log.error(super.getClass().getName() + " Producer 启动成功=======");
            StageManager.log(super.getClass().getName() + " Producer 启动成功=======");
        } catch (MQClientException e) {
            log.error(super.getClass().getName() + " Producer 启动异常=======");
            StageManager.log(super.getClass().getName() + " Producer 启动异常=======");
        }
    }

}