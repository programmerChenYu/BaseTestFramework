package com.base.cases;

import com.base.handlers.DeepSeekLoginHandler;
import com.base.utils.WebDriverUtil;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Description: 文件头
 * Created by 爱吃小鱼的橙子 on 2025-02-08 20:00
 * Created with IntelliJ IDEA.
 */
public class DeepSeekLoginCase {

    private DeepSeekLoginHandler deepSeekLoginHandler;

    @BeforeClass
    public void init() {
        deepSeekLoginHandler = new DeepSeekLoginHandler();
    }

    @Test
    @Parameters({"accountNumber", "password"})
    public void loginCase(String accountNumber, String password) {
        try {
            Allure.step("step1: 点击密码登录按钮");
            deepSeekLoginHandler.clickPasswordLoginButton();
            Allure.step("step2: 点击账号输入框");
            deepSeekLoginHandler.clickAccountNumberInput();
            Allure.step("step3: 输入账号【" + accountNumber + "】");
            deepSeekLoginHandler.inputAccountNumberInput(accountNumber);
            Allure.step("step4: 点击密码输入框");
            deepSeekLoginHandler.clickPasswordInput();
            Allure.step("step5: 输入密码【" + password + "】");
            deepSeekLoginHandler.inputPasswordInput(password);
            Allure.step("step6: 点击同意用户协议");
            deepSeekLoginHandler.clickAgreeButton();
            Allure.step("step7: 点击登录");
            deepSeekLoginHandler.clickLonginButton();
            WebDriver driver = deepSeekLoginHandler.getDriver();
            Thread.sleep(5000);
            Assert.assertEquals(driver.getCurrentUrl(), "https://chat.deepseek.com/");
        } catch (AssertionError e) {
            deepSeekLoginHandler.takeScreenshot("DeepSeekLoginCase_loginCase");
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void destroy() {
        WebDriverUtil.quitDriver();
    }
}
