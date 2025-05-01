package io.github.programmerchenyu.demo.web.handler;

import io.github.programmerchenyu.beans.factory.annotation.Autowired;
import io.github.programmerchenyu.beans.factory.annotation.Component;
import io.github.programmerchenyu.demo.web.page.WebDeepSeekLoginPage;
import io.github.programmerchenyu.web.base.WebBaseHandler;

/**
 * Description: deepSeek 登陆页面操作
 * Created by cy on 2025-02-08 19:50
 * Created with IntelliJ IDEA.
 */
@Component
public class WebDeepSeekLoginHandler {

    @Autowired
    private WebBaseHandler webBaseHandler;

    @Autowired
    private WebDeepSeekLoginPage webDeepSeekLoginPage;

    /**
     * 点击 登录页面密码登录 按钮
     */
    public void clickPasswordLoginButton() {
        webDeepSeekLoginPage.findPasswordLoginButton().click();
    }

    /**
     * 点击账号输入框
     */
    public void clickAccountNumberInput() {
        webDeepSeekLoginPage.findAccountNumberInput().click();
    }

    /**
     * 向 手机号/邮箱 输入框输入账号
     * @param accountNumber
     */
    public void inputAccountNumberInput(String accountNumber) {
        webDeepSeekLoginPage.findAccountNumberInput().clear();
        webDeepSeekLoginPage.findAccountNumberInput().sendKeys(accountNumber);
    }

    /**
     * 点击密码输入框
     */
    public void clickPasswordInput() {
        webDeepSeekLoginPage.findPasswordInput().click();
    }

    /**
     * 向 密码 输入框输入密码
     * @param password
     */
    public void inputPasswordInput(String password) {
        webDeepSeekLoginPage.findPasswordInput().clear();
        webDeepSeekLoginPage.findPasswordInput().sendKeys(password);
    }

    /**
     * 点击 登录按钮
     */
    public void clickLonginButton() {
        webDeepSeekLoginPage.findLonginButton().click();
    }

}
