package com.github.programmerChenYu.base;

import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

/**
 * Description: 基操
 * Created by cy on 2025-02-08 13:44
 * Created with IntelliJ IDEA.
 */
@Slf4j
public class BaseHandler {

    private WebDriver driver;
    private BasePage basePage;

    public BaseHandler(String browser) {
        basePage = new BasePage(browser);
        this.driver = basePage.getDriver();
    }

    /**
     * 返回 WebDriver
     * @return
     */
    public WebDriver getDriver() {
        return this.driver;
    }

    /**
     * 点击元素
     * @param element
     */
    public void clickElement(WebElement element) {
        element.click();
        log.info("点击【{}】元素", element);
    }

    /**
     * 输入文字
     * @param element
     * @param content
     */
    public void inputText(WebElement element, String content) {
        element.clear();
        element.sendKeys(content);
        log.info("输入文本【{}】", content);
    }

    /**
     * 切换 iframe
     * @param element
     */
    public void switchToIframe(WebElement element) {
        this.driver.switchTo().frame(element);
        log.info("切换到【{}】嵌套页面", element);
    }

    /**
     * 切换回原来界面
     */
    public void switchToDefaultContent() {
        this.driver.switchTo().defaultContent();
        log.info("切换回顶层页面");
    }

    /**
     * 执行 JavaScript 脚本
     * @param script
     * @param args
     * @return
     */
    public Object executeJavaScript(String script, Object... args) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) this.driver;
        Object res = jsExecutor.executeScript(script, args);
        log.info("执行 JavaScript 脚本：【{}】", script);
        return res;
    }

    /**
     * 切换窗口
     * @param handle
     */
    public void switchToWindow(String handle) {
        this.driver.switchTo().window(handle);
        log.info("切换到【{}】标签页", handle);
    }

    /**
     * 切换到最近打开的窗口
     */
    public void switchToLastWindow() {
        Set<String> handlesSet = this.driver.getWindowHandles();
        List<String> handles = handlesSet.stream().toList();
        this.driver.switchTo().window(handles.get(handles.size()-1));
        log.info("切换到当前浏览器中的最后一个标签页");
    }

    /**
     * 将 element 滑动到可视区域
     * @param element
     */
    public void scrollToElement(WebElement element) {
        String script = "arguments[0].scrollIntoView({block: 'center', inline: 'center'});";
        this.executeJavaScript(script, element);
        log.info("滑动到元素【{}】所在处", element);
    }

    /**
     * 获取当前窗口的 handle
     * @return
     */
    public String getCurrentWindowHandle() {
        String handle = this.driver.getWindowHandle();
        log.info("当前所在标签页是【{}】", handle);
        return handle;
    }

    /**
     * 关闭当前窗口，再在新窗口打开
     */
    public void closeCurrentWindowAndSwitchBack() {
        String handle = this.driver.getWindowHandle();
        this.driver.close();
        log.info("关闭当前窗口");
        this.driver.switchTo().window(handle);
        log.info("打开窗口【{}】", handle);
    }

    /**
     * 鼠标移动到 element 元素上
     * @param element
     */
    public void hoverOverElement(WebElement element) {
        Actions actions = new Actions(this.driver);
        actions.moveToElement(element).perform();
        log.info("鼠标移动到元素【{}】上", element);
    }

    /**
     * 将元素 source 拖到 target 处放下
     * @param source
     * @param target
     */
    public void dragAndDrop(WebElement source, WebElement target) {
        Actions actions = new Actions(this.driver);
        actions.dragAndDrop(source, target).perform();
        log.info("拖动元素【{}】到【{}】元素上", source, target);
    }

    /**
     * 根据 visibleText 选择下拉框中元素
     * @param element
     * @param visibleText
     */
    public void selectFromDropdownByVisibleText(WebElement element, String visibleText) {
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
        log.info("在下拉菜单中选择【{}】", visibleText);
    }

    /**
     * 截图
     * @param clasName
     * @throws IOException
     */
    public void takeScreenshot(String clasName) {
        String time = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss"));
        File screenshot = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
        StringBuilder sb = new StringBuilder();
        String fullPath = sb.append("screenshot\\fail").append(File.separator).append(clasName).append("_").append(time).append(".png").toString();
        try {
            FileUtils.copyFile(screenshot, new File(fullPath));
            log.info("失败截图保存至【{}】", fullPath);
            byte[] screenshotBytes = FileUtils.readFileToByteArray(screenshot);
            Allure.step("添加失败截图");
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshotBytes));
        } catch (IOException e) {
            log.error("截图失败，原因如下：{}", e.getMessage());
            e.printStackTrace();
        }
    }

}
