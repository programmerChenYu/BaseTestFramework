package io.github.programmerchenyu.web.base;

import io.github.programmerchenyu.beans.factory.BeanFactory;
import io.github.programmerchenyu.listener.ScreenshotListener;
import io.github.programmerchenyu.web.driver.WebDriverUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Description: 文件头
 * Created by 爱吃小鱼的橙子 on 2025-02-10 22:26
 * Created with IntelliJ IDEA.
 */
@Listeners({ScreenshotListener.class})
public class WebBaseCase {

    private WebDriver driver;

    @BeforeClass
    public void initWebBaseCase(ITestContext context) throws NoSuchFieldException, IllegalAccessException {
        BeanFactory beanFactory = (BeanFactory) context.getAttribute("beanFactory");
        Field field = beanFactory.getClass().getDeclaredField("singletonObjects");
        field.setAccessible(true);
        Map<String, Object> singletonObjects = (Map<String, Object>) field.get(beanFactory);
        WebDriverUtil webDriverUtil = (WebDriverUtil) singletonObjects.get("webDriverUtil");
        this.driver = webDriverUtil.getDriver();
    }
}
