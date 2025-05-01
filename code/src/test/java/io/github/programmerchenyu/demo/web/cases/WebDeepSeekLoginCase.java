package io.github.programmerchenyu.demo.web.cases;

import io.github.programmerchenyu.annotation.CaptureScreenshotOnFailure;
import io.github.programmerchenyu.base.ITestNexusContext;
import io.github.programmerchenyu.base.annotion.TestNexus;
import io.github.programmerchenyu.beans.factory.annotation.Autowired;
import io.github.programmerchenyu.demo.configuration.WebConfiguration;
import io.github.programmerchenyu.demo.web.handler.WebDeepSeekLoginHandler;
import io.github.programmerchenyu.web.driver.WebDriverUtil;
import io.github.programmerchenyu.web.base.WebBaseCase;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Description: deepSeek 登录测试
 * Created by cy on 2025-02-08 20:00
 * Created with IntelliJ IDEA.
 */
@TestNexus(classes = {WebConfiguration.class})
public class WebDeepSeekLoginCase extends WebBaseCase implements ITestNexusContext {

    @Autowired
    private WebDeepSeekLoginHandler webDeepSeekLoginHandler;

    @Autowired
    private WebDriverUtil webDriverUtil;

    @Test
    @CaptureScreenshotOnFailure(caseName = "deepSeek web端登录测试用例")
    public void loginCase() {
        String accountNumber = "12345678900";
        String password = "123456";
        Allure.step("step1: 点击密码登录按钮");
        webDeepSeekLoginHandler.clickPasswordLoginButton();
        Allure.step("step2: 点击账号输入框");
        webDeepSeekLoginHandler.clickAccountNumberInput();
        Allure.step("step3: 输入账号【" + accountNumber + "】");
        webDeepSeekLoginHandler.inputAccountNumberInput(accountNumber);
        Allure.step("step4: 点击密码输入框");
        webDeepSeekLoginHandler.clickPasswordInput();
        Allure.step("step5: 输入密码【" + password + "】");
        webDeepSeekLoginHandler.inputPasswordInput(password);
        Allure.step("step6: 点击登录");
        webDeepSeekLoginHandler.clickLonginButton();
        WebDriver driver = this.webDriverUtil.getDriver();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(driver.getCurrentUrl(), "https://chat.deepseek.com/");

    }
}
