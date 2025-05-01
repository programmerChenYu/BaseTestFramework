package io.github.programmerchenyu.app.base;

import io.appium.java_client.AppiumDriver;
import io.github.programmerchenyu.app.driver.AppDriver;
import io.github.programmerchenyu.beans.factory.annotation.Autowired;
import io.github.programmerchenyu.beans.factory.annotation.Component;
import io.github.programmerchenyu.constant.FilePathConstant;
import io.github.programmerchenyu.listener.ScreenshotListener;
import io.github.programmerchenyu.utils.ReadPropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;

import java.time.Duration;
import java.util.List;

/**
 * Description: 文件头
 * Created by 爱吃小鱼的橙子 on 2025-04-26 16:20
 * Created with IntelliJ IDEA.
 */
@Slf4j
@Component
public class AppBasePage {

    @Autowired
    private AppDriver driver;

    /**
     * 获取驱动 driver
     * @return
     */
    public AppiumDriver getDriver() {
        return driver.getDriver();
    }

    /**
     * 显示等待获取单个元素
     * @param key
     * @param timeoutInSeconds
     * @return
     */
    public WebElement getElement(String key, Integer timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(getByLocal(key)));
        log.info("以获取元素【{}】", element);
        return element;
    }

    /**
     * 默认 10s 超时的 getElement 方法
     * @param key
     * @return
     */
    public WebElement getElement(String key) {
        return getElement(key, 10);
    }

    /**
     * 显示等待获取多个元素
     * @param key
     * @param timeoutInSeconds
     * @return
     */
    public List<WebElement> getElements(String key, Integer timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocal(key)));
        log.info("以获取元素列表【{}】", elements);
        return elements;
    }

    /**
     * 默认 10s 超时的 getElements 方法
     * @param key
     * @return
     */
    public List<WebElement> getElements(String key) {
        return getElements(key, 10);
    }

    /**
     * 封装根据不同定位方式获取元素
     * @param key
     * @return
     */
    public By getByLocal(String key) {
        ReadPropertiesUtil propertiesUtil = new ReadPropertiesUtil(FilePathConstant.ELEMENT_PROPERTIES);
        String locator = propertiesUtil.getProperty(key);
        String locatorBy = locator.split(">")[0];
        String locatorValue = locator.split(">")[1];
        By by;
        if (locatorBy.equals("id")) {
            by = By.id(locatorValue);
        } else if (locatorBy.equals("name")) {
            by = By.name(locatorValue);
        } else if (locatorBy.equals("className")) {
            by = By.className(locatorValue);
        } else {
            by = By.xpath(locatorValue);
        }
        return by;
    }
}
