package io.github.programmerchenyu.app.base;

import io.appium.java_client.AppiumDriver;
import io.github.programmerchenyu.app.driver.AppDriver;
import io.github.programmerchenyu.beans.factory.BeanFactory;
import io.github.programmerchenyu.listener.ScreenshotListener;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Description: 文件头
 * Created by 爱吃小鱼的橙子 on 2025-04-27 0:46
 * Created with IntelliJ IDEA.
 */
@Listeners({ScreenshotListener.class})
public class AppBaseCase {

    private AppiumDriver driver;

    @BeforeClass
    public void initAppBaseCase(ITestContext context) throws NoSuchFieldException, IllegalAccessException {
        BeanFactory beanFactory = (BeanFactory) context.getAttribute("beanFactory");
        Field field = beanFactory.getClass().getDeclaredField("singletonObjects");
        field.setAccessible(true);
        Map<String, Object> singletonObjects = (Map<String, Object>) field.get(beanFactory);
        AppDriver appDriver = (AppDriver) singletonObjects.get("appDriver");
        this.driver = appDriver.getDriver();
    }
}
