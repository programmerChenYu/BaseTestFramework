package io.github.programmerchenyu.app.driver;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.github.programmerchenyu.beans.factory.annotation.Component;
import io.github.programmerchenyu.beans.factory.annotation.Destroy;
import io.github.programmerchenyu.utils.ReadPropertiesUtil;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author 爱吃小鱼的橙子
 */
@Component
public class IosDriver {

    private IOSDriver driver;

    public IosDriver() {
        ReadPropertiesUtil propertiesUtil = new ReadPropertiesUtil("config-app.properties");
        String platformName = propertiesUtil.getProperty("platformName");
        if ("ios".equals(platformName)) {
            String deviceName = propertiesUtil.getProperty("deviceName");
            String bundleId = propertiesUtil.getProperty("bundleId");
            String udid = propertiesUtil.getProperty("udid");

            XCUITestOptions options = new XCUITestOptions();
            options.setCapability("deviceName", deviceName);
            options.setCapability("bundleId", bundleId);
            options.setCapability("automationName", "XCUITest");
            options.setCapability("udid", udid);
            try {
                URL url = new URL("http://127.0.0.1:4723/wd/hub");
                driver = new IOSDriver(url, options);
            } catch (MalformedURLException e) {
                throw new RuntimeException("android 驱动加载异常");
            }
        }
    }

    public IOSDriver getDriver() {
        return this.driver;
    }

    @Destroy
    public void destroy() {
        if (driver != null) {
            System.out.println("销毁驱动");
            driver.quit();
        }
    }
}
