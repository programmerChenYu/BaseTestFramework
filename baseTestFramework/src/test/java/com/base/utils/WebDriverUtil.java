package com.base.utils;

import com.base.enums.BrowserTypeEnum;
import com.base.enums.EnvironmentTypeEnum;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Description: WebDriver 工具类，单例
 * Created by cy on 2025-02-08 13:25
 * Created with IntelliJ IDEA.
 */
public class WebDriverUtil {

    private static volatile WebDriver driver;

    /**
     * 私有构造方法，防止外部实例化
     */
    private WebDriverUtil() {}

    /**
     * 使用 browserType 枚举来创建 WebDriver 实例
     * @param browserType
     * @return
     */
    public static WebDriver getDriver(BrowserTypeEnum browserType) {
        if (driver == null) {
            synchronized (WebDriverUtil.class) {
                if (driver == null) {
                    switch (browserType) {
                        case CHROME:
                            WebDriverManager.chromedriver().setup();
                            driver = new ChromeDriver();
                            break;
                        case FIREFOX:
                            WebDriverManager.firefoxdriver().setup();
                            driver = new FirefoxDriver();
                            break;
                        case EDGE:
                            WebDriverManager.edgedriver().setup();
                            driver = new EdgeDriver();
                            break;
                        default:
                            throw new IllegalArgumentException("不支持的浏览器类型：" + browserType);
                    }
                    driver.get(EnvironmentTypeEnum.TEST.getUrl());
                    driver.manage().window().maximize();
                }
            }
        }
        return driver;
    }

    /**
     * 关闭并释放资源
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
