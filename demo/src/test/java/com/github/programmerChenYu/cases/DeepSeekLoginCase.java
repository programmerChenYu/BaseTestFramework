package com.github.programmerChenYu.cases;

import com.github.programmerChenYu.annotation.CaptureScreenshotOnFailure;
import com.github.programmerChenYu.handlers.DeepSeekLoginHandler;
import com.github.programmerChenYu.listener.ScreenshotListener;
import com.github.programmerChenYu.utils.WebDriverUtil;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Description: deepSeek 登录测试
 * Created by cy on 2025-02-08 20:00
 * Created with IntelliJ IDEA.
 */
@Listeners({ScreenshotListener.class})
public class DeepSeekLoginCase {

    private DeepSeekLoginHandler handler;

    @BeforeClass
    @Parameters({"browser"})
    public void init(String browser) {
        handler = new DeepSeekLoginHandler(browser);
    }

    @Test
    @Parameters({"accountNumber", "password"})
    @CaptureScreenshotOnFailure(caseName = "deepSeek 登录测试用例")
    public void loginCase(String accountNumber, String password) {
        Allure.step("step1: 点击密码登录按钮");
        handler.clickPasswordLoginButton();
        Allure.step("step2: 点击账号输入框");
        handler.clickAccountNumberInput();
        Allure.step("step3: 输入账号【" + accountNumber + "】");
        handler.inputAccountNumberInput(accountNumber);
        Allure.step("step4: 点击密码输入框");
        handler.clickPasswordInput();
        Allure.step("step5: 输入密码【" + password + "】");
        handler.inputPasswordInput(password);
        Allure.step("step6: 点击同意用户协议");
        handler.clickAgreeButton();
        Allure.step("step7: 点击登录");
        handler.clickLonginButton();
        WebDriver driver = handler.getDriver();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(driver.getCurrentUrl(), "https://chat.deepseek.com/");

    }

    @AfterClass
    public void destroy() {
        WebDriverUtil.quitDriver();
    }
}
