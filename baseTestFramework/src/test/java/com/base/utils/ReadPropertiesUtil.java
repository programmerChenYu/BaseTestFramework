package com.base.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Description: 读取 properties 文件工具类
 * Created by cy on 2025-02-08 15:06
 * Created with IntelliJ IDEA.
 */
public class ReadPropertiesUtil {

    private Properties properties;
    private static final Logger logger = Logger.getLogger(ReadPropertiesUtil.class.getName());

    public ReadPropertiesUtil(String filePath) {
        this.initProperties(filePath);
    }

    private Properties initProperties(String filePath) {
        properties = new Properties();
        try {
            InputStream fileInputStream = this.getClass().getClassLoader().getResourceAsStream(filePath);
//            FileInputStream fileInputStream = new FileInputStream(filePath);
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
            logger.warning("The key does not exist");
        }
        return value;
    }

}
