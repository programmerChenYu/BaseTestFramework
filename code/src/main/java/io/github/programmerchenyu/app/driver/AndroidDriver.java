package io.github.programmerchenyu.app.driver;

import io.github.programmerchenyu.beans.factory.annotation.Component;
import io.github.programmerchenyu.beans.factory.annotation.Destroy;
import io.github.programmerchenyu.utils.ReadPropertiesUtil;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author 爱吃小鱼的橙子
 */
@Component
public class AndroidDriver {

    private io.appium.java_client.android.AndroidDriver driver;

    public AndroidDriver() {
        ReadPropertiesUtil propertiesUtil = new ReadPropertiesUtil("config-app.properties");
        String platformName = propertiesUtil.getProperty("platformName");
        if ("android".equals(platformName)) {
            String deviceName = propertiesUtil.getProperty("deviceName");
            String appPackage = propertiesUtil.getProperty("appPackage");
            String appActivity = propertiesUtil.getProperty("appActivity");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "android");
            capabilities.setCapability("deviceName", deviceName);
            capabilities.setCapability("appPackage", appPackage);
            capabilities.setCapability("appActivity", appActivity);
            try {
                URL url = new URL("http://127.0.0.1:4723/wd/hub");
                driver = new io.appium.java_client.android.AndroidDriver(url, capabilities);
            } catch (MalformedURLException e) {
                throw new RuntimeException("android 驱动加载异常");
            }
        }
    }

    public io.appium.java_client.android.AndroidDriver getDriver() {
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
