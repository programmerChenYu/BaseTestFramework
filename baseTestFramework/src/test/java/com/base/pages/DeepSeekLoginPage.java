package com.base.pages;

import com.base.base.BasePage;
import org.openqa.selenium.WebElement;

/**
 * Description: 文件头
 * Created by 爱吃小鱼的橙子 on 2025-02-08 19:31
 * Created with IntelliJ IDEA.
 */
public class DeepSeekLoginPage extends BasePage {

    /**
     * 登录页面密码登录按钮
     * @return
     */
    public WebElement findPasswordLoginButton() {
        return getElement("dSPwButton");
    }

    /**
     * 登录页面 手机号/邮箱 输入框
     * @return
     */
    public WebElement findAccountNumberInput() {
        return getElement("accountNumberInput");
    }

    /**
     * 登录页面 密码 输入框
     * @return
     */
    public WebElement findPasswordInput() {
        return getElement("passwordInput");
    }

    /**
     * 同意用户协议 确认 按钮
     * @return
     */
    public WebElement findAgreeButton() {
        return getElement("agreeButton");
    }

    /**
     * 登录按钮
     * @return
     */
    public WebElement findLonginButton() {
        return getElement("loginButton");
    }
}
