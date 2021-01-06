package com.tan.fx.dto;

import com.tan.fx.config.PlatformCst;
import org.springframework.core.io.ClassPathResource;

public class MqttFileManager {

private static MqttFileManager manager;
    
    private ClassPathResource resource = null;
    
    static{
        manager = new MqttFileManager();
    }
    
    public MqttFileManager(){
        String classfilepath = PlatformCst.MQTT_PEROPERTIES;
        resource = new ClassPathResource(classfilepath);
    }
    
    /**
     * 获取实例
     * @return
     */
    public static MqttFileManager getInstance(){
        return manager;
    }
    
    /**
     * 获取数据源
     * @return
     */
    public ClassPathResource getResource(){
        return resource;
    }
}
