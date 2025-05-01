package io.github.programmerchenyu.web.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.programmerchenyu.beans.factory.annotation.Component;
import io.github.programmerchenyu.beans.factory.annotation.Destroy;
import io.github.programmerchenyu.constant.FilePathConstant;
import io.github.programmerchenyu.enums.BrowserTypeEnum;
import io.github.programmerchenyu.utils.ReadPropertiesUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Description: WebDriver 工具类，单例
 * Created by cy on 2025-02-08 13:25
 * Created with IntelliJ IDEA.
 */
@Component
public class WebDriverUtil {

    private WebDriver driver;

    public WebDriverUtil() {
        ReadPropertiesUtil propertiesUtil = new ReadPropertiesUtil(FilePathConstant.CONFIG_WEB_PROPERTIES);
        String browserType = propertiesUtil.getProperty("browserType");
        switch (browserType) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("不支持的浏览器类型：" + browserType);
        }
        String env = propertiesUtil.getProperty("curEnvironment");
        String url = propertiesUtil.getProperty(env + "Url");
        driver.get(url);
        driver.manage().window().maximize();
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    @Destroy
    public void destroy() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
