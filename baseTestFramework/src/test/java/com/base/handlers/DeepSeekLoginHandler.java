package com.base.handlers;

import com.base.base.BaseHandler;
import com.base.pages.DeepSeekLoginPage;

/**
 * Description: deepSeek 登陆页面操作
 * Created by cy on 2025-02-08 19:50
 * Created with IntelliJ IDEA.
 */
public class DeepSeekLoginHandler extends BaseHandler {

    private DeepSeekLoginPage deepSeekLoginPage;

    public DeepSeekLoginHandler(String browser) {
        deepSeekLoginPage = new DeepSeekLoginPage(browser);
    }

    /**
     * 点击 登录页面密码登录 按钮
     */
    public void clickPasswordLoginButton() {
        if (!deepSeekLoginPage.findAgreeButton().isSelected()) {
            deepSeekLoginPage.findPasswordLoginButton().click();
        }
    }

    /**
     * 点击账号输入框
     */
    public void clickAccountNumberInput() {
        deepSeekLoginPage.findAccountNumberInput().click();
    }

    /**
     * 向 手机号/邮箱 输入框输入账号
     * @param accountNumber
     */
    public void inputAccountNumberInput(String accountNumber) {
        deepSeekLoginPage.findAccountNumberInput().clear();
        deepSeekLoginPage.findAccountNumberInput().sendKeys(accountNumber);
    }

    /**
     * 点击密码输入框
     */
    public void clickPasswordInput() {
        deepSeekLoginPage.findPasswordInput().click();
    }

    /**
     * 向 密码 输入框输入密码
     * @param password
     */
    public void inputPasswordInput(String password) {
        deepSeekLoginPage.findPasswordInput().clear();
        deepSeekLoginPage.findPasswordInput().sendKeys(password);
    }

    /**
     * 点击 同意用户协议 确认 按钮
     */
    public void clickAgreeButton() {
        deepSeekLoginPage.findAgreeButton().click();
    }

    /**
     * 点击 登录按钮
     */
    public void clickLonginButton() {
        deepSeekLoginPage.findLonginButton().click();
    }

}
