package com.github.programmerChenYu.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Description: 读取 properties 文件工具类
 * Created by cy on 2025-02-08 15:06
 * Created with IntelliJ IDEA.
 */
@Slf4j
public class ReadPropertiesUtil {

    private Properties properties;

    public ReadPropertiesUtil(String filePath) {
        this.initProperties(filePath);
    }

    private Properties initProperties(String filePath) {
        properties = new Properties();
        try {
            InputStream fileInputStream = this.getClass().getClassLoader().getResourceAsStream(filePath);
            BufferedInputStream inputStream = new BufferedInputStream(fileInputStream);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public String getProperty(String key) {
        String value = "";
        if (properties.containsKey(key)) {
            value = properties.getProperty(key);
        } else {
            log.warn("键【{}】不存在", key);
        }
        return value;
    }

}
