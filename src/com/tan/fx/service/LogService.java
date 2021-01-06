package com.tan.fx.service;

import com.tan.fx.MainController;
import com.tan.fx.StageManager;
import com.tan.fx.utils.DateUtils;
import javafx.scene.control.TextArea;

import java.util.Date;

/**
 * 日志处理
 *
 * @Author XZ.Tan
 * @Date: 2020/12/25 15:37
 * @Version 1.0
 */
public class LogService {

    /**
     * 消息打印
     * @param msg
     */
    public void print(String msg) {
        MainController mainController = StageManager.getInstance().getMainController();
        TextArea msgTextArea = mainController.logTextArea;
        System.out.println(DateUtils.formatDateToStringByDefaultFormat(new Date()) + " -- " + msg + "\n");
        msgTextArea.appendText(DateUtils.formatDateToStringByDefaultFormat(new Date()) + " -- " + msg + "\n");
    }
}