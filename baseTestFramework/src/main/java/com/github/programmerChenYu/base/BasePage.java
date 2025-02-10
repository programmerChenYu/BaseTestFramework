package com.github.programmerChenYu.base;

import com.github.programmerChenYu.constant.FilePathConstant;
import com.github.programmerChenYu.enums.BrowserTypeEnum;
import com.github.programmerChenYu.utils.ReadPropertiesUtil;
import com.github.programmerChenYu.utils.WebDriverUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Description: 基页
 * Created by cy on 2025-02-08 13:33
 * Created with IntelliJ IDEA.
 */
@Slf4j
public class BasePage {

    private WebDriver driver;

    /**
     * 构造函数，初始化 WebDriver 使用同一个浏览器驱动对象，防止多次创建对象
     */
    public BasePage(String browser) {
        switch (browser) {
            case "firefox":
                this.driver = WebDriverUtil.getDriver(BrowserTypeEnum.FIREFOX);
                break;
            case "edge":
                this.driver = WebDriverUtil.getDriver(BrowserTypeEnum.EDGE);
                break;
            default:
                this.driver = WebDriverUtil.getDriver(BrowserTypeEnum.CHROME);
        }
    }

    /**
     * 返回 WebDriver
     * @return
     */
    public WebDriver getDriver() {
        return this.driver;
    }

    /**
     * 显示等待获取单个元素
     * @param key
     * @param timeoutInSeconds
     * @return
     */
    public WebElement getElement(String key, Integer timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(timeoutInSeconds));
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(getByLocal(key)));
        log.info("已获取元素【{}】", webElement);
        return webElement;
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
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(timeoutInSeconds));
        List<WebElement> webElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocal(key)));
        log.info("以获取元素列表【{}】", webElements);
        return webElements;
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
