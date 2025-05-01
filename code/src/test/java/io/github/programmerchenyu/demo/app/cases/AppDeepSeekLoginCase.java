package io.github.programmerchenyu.demo.app.cases;

import io.appium.java_client.android.AndroidDriver;
import io.github.programmerchenyu.annotation.CaptureScreenshotOnFailure;
import io.github.programmerchenyu.app.base.AppBaseCase;
import io.github.programmerchenyu.app.driver.AppDriver;
import io.github.programmerchenyu.base.ITestNexusContext;
import io.github.programmerchenyu.base.annotion.TestNexus;
import io.github.programmerchenyu.beans.factory.annotation.Autowired;
import io.github.programmerchenyu.demo.configuration.AndroidConfiguration;
import io.github.programmerchenyu.demo.app.handler.AppDeepSeekLoginHandler;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Description: 文件头
 * Created by 爱吃小鱼的橙子 on 2025-05-01 14:43
 * Created with IntelliJ IDEA.
 */
@TestNexus(classes = {AndroidConfiguration.class})
public class AppDeepSeekLoginCase extends AppBaseCase implements ITestNexusContext {

    @Autowired
    private AppDeepSeekLoginHandler appDeepSeekLoginHandler;
    @Autowired
    private AppDriver appDriver;

    @Test
    @CaptureScreenshotOnFailure(caseName = "deepSeek app端登录测试用例")
    public void loginCase() {
        String accountNumber = "12345678900";
        String password = "123456";
        Allure.step("step1: 点击协议的同意按钮");
        appDeepSeekLoginHandler.clickIntoAgreeButton();
        Allure.step("step2: 点击密码登录按钮");
        appDeepSeekLoginHandler.clickPasswordLoginButton();
        Allure.step("step3: 点击账号输入框");
        appDeepSeekLoginHandler.clickAccountNumberInput();
        Allure.step("step4: 输入账号【" + accountNumber + "】");
        appDeepSeekLoginHandler.inputAccountNumberInput(accountNumber);
        Allure.step("step5: 点击密码输入框");
        appDeepSeekLoginHandler.clickPasswordInput();
        Allure.step("step6: 输入密码【" + password + "】");
        appDeepSeekLoginHandler.inputPasswordInput(password);
        Allure.step("step7: 点击登录");
        appDeepSeekLoginHandler.clickLonginButton();
        AndroidDriver androidDriver = (AndroidDriver) appDriver.getDriver();
        String currentActivity = androidDriver.currentActivity();
        Assert.assertEquals(currentActivity, "com.deepseek.chat.HomeActivity");
    }
}
