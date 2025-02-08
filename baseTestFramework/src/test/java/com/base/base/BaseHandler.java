package com.base.base;

import com.base.enums.BrowserTypeEnum;
import com.base.utils.WebDriverUtil;
import io.qameta.allure.Allure;
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
import java.util.logging.Logger;

/**
 * Description: 基操
 * Created by cy on 2025-02-08 13:44
 * Created with IntelliJ IDEA.
 */
public class BaseHandler {

    private final WebDriver driver;
    private static final Logger logger = Logger.getLogger(BaseHandler.class.getName());

    public BaseHandler() {
        this.driver = WebDriverUtil.getDriver(BrowserTypeEnum.CHROME);
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
        logger.info("Clicked on the element：" + element);
    }

    /**
     * 输入文字
     * @param element
     * @param content
     */
    public void inputText(WebElement element, String content) {
        element.clear();
        element.sendKeys(content);
        logger.info("Input text：" + content);
    }

    /**
     * 切换 iframe
     * @param element
     */
    public void switchToIframe(WebElement element) {
        this.driver.switchTo().frame(element);
        logger.info("Switched to iframe：" + element);
    }

    /**
     * 切换回原来界面
     */
    public void switchToDefaultContent() {
        this.driver.switchTo().defaultContent();
        logger.info("Switched back to default content");
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
        logger.info("Executed JavaScript：" + script);
        return res;
    }

    /**
     * 切换窗口
     * @param handle
     */
    public void switchToWindow(String handle) {
        this.driver.switchTo().window(handle);
        logger.info("Switched to window with handle：" + handle);
    }

    /**
     * 切换到最近打开的窗口
     */
    public void switchToLastWindow() {
        Set<String> handlesSet = this.driver.getWindowHandles();
        List<String> handles = handlesSet.stream().toList();
        this.driver.switchTo().window(handles.get(handles.size()-1));
        logger.info("Switched to the last opened window");
    }

    /**
     * 将 element 滑动到可视区域
     * @param element
     */
    public void scrollToElement(WebElement element) {
        String script = "arguments[0].scrollIntoView({block: 'center', inline: 'center'});";
        this.executeJavaScript(script, element);
        logger.info("Scrolled to element：" + element);
    }

    /**
     * 获取当前窗口的 handle
     * @return
     */
    public String getCurrentWindowHandle() {
        String handle = this.driver.getWindowHandle();
        logger.info("Current window handle：" + handle);
        return handle;
    }

    /**
     * 关闭当前窗口，再在新窗口打开
     */
    public void closeCurrentWindowAndSwitchBack() {
        String handle = this.driver.getWindowHandle();
        this.driver.close();
        logger.info("Closed the current window");
        this.driver.switchTo().window(handle);
        logger.info("Switched back to the main window");
    }

    /**
     * 鼠标移动到 element 元素上
     * @param element
     */
    public void hoverOverElement(WebElement element) {
        Actions actions = new Actions(this.driver);
        actions.moveToElement(element).perform();
        logger.info("Hovered over element：" + element);
    }

    /**
     * 将元素 source 拖到 target 处放下
     * @param source
     * @param target
     */
    public void dragAndDrop(WebElement source, WebElement target) {
        Actions actions = new Actions(this.driver);
        actions.dragAndDrop(source, target).perform();
        logger.info("Dragged element：" + source + " and dropped onto：" + target);
    }

    /**
     * 根据 visibleText 选择下拉框中元素
     * @param element
     * @param visibleText
     */
    public void selectFromDropdownByVisibleText(WebElement element, String visibleText) {
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
        logger.info("Selected value from dropdown：" + visibleText);
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
            logger.info("Screenshot saved to：" + fullPath);
            byte[] screenshotBytes = FileUtils.readFileToByteArray(screenshot);
            Allure.step("添加失败截图");
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshotBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
