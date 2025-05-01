package io.github.programmerchenyu.app.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.github.programmerchenyu.beans.factory.annotation.Autowired;
import io.github.programmerchenyu.beans.factory.annotation.Component;
import io.github.programmerchenyu.utils.ReadPropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Description: 文件头
 * Created by 爱吃小鱼的橙子 on 2025-04-26 16:33
 * Created with IntelliJ IDEA.
 */
@Slf4j
@Component
public class AppBaseHandler {

    @Autowired
    private AppBasePage basePage;

    /**
     * 返回驱动
     * @return
     */
    public AppiumDriver getDriver() {
        return basePage.getDriver();
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
     * 切换到 webview 的上下文环境
     */
    public void switchWebViewContext() {
        ReadPropertiesUtil propertiesUtil = new ReadPropertiesUtil("config-app.properties");
        String platformName = propertiesUtil.getProperty("platformName");
        switch (platformName) {
            case "ios":
                IOSDriver iosDriver = (IOSDriver) getDriver();
                Set<String> contextHandlesIOS = iosDriver.getContextHandles();
                for (String contextHandle : contextHandlesIOS) {
                    if (contextHandle.startsWith("WEBVIEW")) {
                        iosDriver.context(contextHandle);
                        break;
                    }
                }
                break;
            case "android":
                AndroidDriver androidDriver = (AndroidDriver) getDriver();
                Set<String> contextHandlesAndroid = androidDriver.getContextHandles();
                for (String contextHandle : contextHandlesAndroid) {
                    if (contextHandle.startsWith("WEBVIEW")) {
                        androidDriver.context(contextHandle);
                        break;
                    }
                }
                break;
            default:
                throw new RuntimeException("The specified driver is abnormal. Please select the driver for ios or android");
        }
    }

    /**
     * 切换 iframe
     * @param element
     */
    public void switchToIframe(WebElement element) {
        getDriver().switchTo().frame(element);
        log.info("切换到【{}】嵌套页面", element);
    }

    /**
     * 切换回 webview 的顶层页面
     */
    public void switchToWebViewDefaultContent() {
        getDriver().switchTo().defaultContent();
        log.info("切换回顶层页面");
    }

    /**
     * 切换回原生环境
     */
    public void switchNativeAppContext() {
        ReadPropertiesUtil propertiesUtil = new ReadPropertiesUtil("config-app.properties");
        String platformName = propertiesUtil.getProperty("platformName");
        switch (platformName) {
            case "ios":
                IOSDriver iosDriver = (IOSDriver) getDriver();
                iosDriver.context("NATIVE_APP");
                break;
            case "android":
                AndroidDriver androidDriver = (AndroidDriver) getDriver();
                androidDriver.context("NATIVE_APP");
                break;
            default:
                throw new RuntimeException("The specified driver is abnormal. Please select the driver for ios or android");
        }
        log.info("切换回原生环境");
    }

    /**
     * 执行 JavaScript 脚本
     * @param script
     * @param args
     * @return
     */
    public Object executeJavaScript(String script, Object... args) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        Object res = jsExecutor.executeScript(script, args);
        log.info("执行 JavaScript 脚本：【{}】", script);
        return res;
    }

    /**
     * 将 element 滑动到可视区域
     * @param element
     */
    public void scrollToElement(WebElement element) {
        ReadPropertiesUtil propertiesUtil = new ReadPropertiesUtil("config-app.properties");
        String platformName = propertiesUtil.getProperty("platformName");
        switch (platformName) {
            case "ios":
                IOSDriver iosDriver = (IOSDriver) getDriver();
                if ("NATIVE_APP".equals(iosDriver.getContext())) {
                    touchAction(platformName);
                } else {
                    jsScrollToElement(element);
                }
                break;
            case "android":
                AndroidDriver androidDriver = (AndroidDriver) getDriver();
                if ("NATIVE_APP".equals(androidDriver.getContext())) {
                    touchAction(platformName);
                } else {
                    jsScrollToElement(element);
                }
                break;
            default:
                throw new RuntimeException("The specified driver is abnormal. Please select the driver for ios or android");
        }
    }

    /**
     * app 中滑动屏幕
     * @param platformName
     */
    private void touchAction(String platformName) {
        // 获取屏幕尺寸
        Dimension size = getDriver().manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        // 模拟从下往上滑动
        TouchAction action;
        if ("ios".equals(platformName)) {
            action = new TouchAction((IOSDriver)getDriver());
        } else {
            action = new TouchAction((AndroidDriver)getDriver());
        }
        action.press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }

    /**
     * webview 中滑动屏幕
     * @param element
     */
    private void jsScrollToElement(WebElement element) {
        String script = "arguments[0].scrollIntoView({block: 'center', inline: 'center'});";
        this.executeJavaScript(script, element);
        log.info("滑动到元素【{}】所在处", element);
    }

    /**
     * 将元素 source 拖到 target 处放下
     * @param source
     * @param target
     */
    public void dragAndDrop(WebElement source, WebElement target) {
        Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();

        if (getDriver().getCapabilities().getCapability("platformName").equals("ios")) {
            Map<String, Object> deviceInfo = (Map<String, Object>) getDriver().executeScript("mobile: getDeviceInfo");
            Map<String, Number> viewportRect = (Map<String, Number>) deviceInfo.get("viewportRect");
            int safeAreaTop = viewportRect.get("top").intValue();
            sourceLocation = new Point(sourceLocation.x, sourceLocation.y - safeAreaTop);
            targetLocation = new Point(targetLocation.x, targetLocation.y - safeAreaTop);
        }

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 0)
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), sourceLocation.x, sourceLocation.y))
                .addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), targetLocation.x, targetLocation.y))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        getDriver().perform(Arrays.asList(sequence));
    }

    /**
     * 根据 visibleText 选择下拉框中元素
     * @param element
     * @param visibleText
     */
    public void selectFromDropdownByVisibleText(WebElement element, String visibleText) {
        if (getDriver().getCapabilities().getCapability("platformName").equals("ios")) {
            IOSDriver iosDriver = (IOSDriver) getDriver();
            if ("NATIVE_APP".equals(iosDriver.getContext())) {
                Map<String, Object> params = new HashMap<>();
                params.put("order", "next");
                // 滚动偏移量
                params.put("offset", 0.15);
                getDriver().executeScript("mobile: selectPickerWheelValue", params);
            } else {
                selectFromDropdownByVisibleTextWebView(element, visibleText);
            }
        } else if (getDriver().getCapabilities().getCapability("platformName").equals("android")) {
            AndroidDriver androidDriver = (AndroidDriver) getDriver();
            if ("NATIVE_APP".equals(androidDriver.getContext())) {
                element.click();
                // 使用 UiScrollable 滚动查找文本
                getDriver().findElement(MobileBy.AndroidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))" +
                                ".scrollIntoView(new UiSelector().text(\"" + visibleText + "\"))"
                )).click();
            } else {
                selectFromDropdownByVisibleTextWebView(element, visibleText);
            }
        } else {
            throw new RuntimeException("The specified driver is abnormal. Please select the driver for ios or android");
        }
    }

    private void selectFromDropdownByVisibleTextWebView(WebElement element, String visibleText) {
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

}
