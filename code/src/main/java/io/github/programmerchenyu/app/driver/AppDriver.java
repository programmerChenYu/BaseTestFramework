package io.github.programmerchenyu.app.driver;

import io.appium.java_client.AppiumDriver;
import io.github.programmerchenyu.beans.factory.annotation.Autowired;
import io.github.programmerchenyu.beans.factory.annotation.Component;
import io.github.programmerchenyu.beans.factory.annotation.InitAfter;
import io.github.programmerchenyu.constant.FilePathConstant;
import io.github.programmerchenyu.utils.ReadPropertiesUtil;

/**
 * @author 爱吃小鱼的橙子
 */
@Component
public class AppDriver {

    private AppiumDriver driver;

    @Autowired
    private AndroidDriver androidDriver;

    @Autowired
    private IosDriver iosDriver;

    public AppDriver() {}

    @InitAfter
    public void initAfter() {
        ReadPropertiesUtil propertiesUtil = new ReadPropertiesUtil(FilePathConstant.CONFIG_APP_PROPERTIES);
        String type = propertiesUtil.getProperty("platformName");
        switch (type) {
            case "android":
                this.driver = androidDriver.getDriver();
                break;
            case "ios":
                this.driver = iosDriver.getDriver();
                break;
            default:
                throw new RuntimeException("The specified driver is abnormal. Please select the driver for ios or android");
        }
    }

    public AppiumDriver getDriver() {
        return this.driver;
    }
}
