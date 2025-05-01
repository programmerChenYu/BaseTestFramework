package io.github.programmerchenyu.demo.app.handler;

import io.github.programmerchenyu.app.base.AppBaseHandler;
import io.github.programmerchenyu.beans.factory.annotation.Autowired;
import io.github.programmerchenyu.beans.factory.annotation.Component;
import io.github.programmerchenyu.demo.app.page.AppDeepSeekLoginPage;

/**
 * Description: 文件头
 * Created by 爱吃小鱼的橙子 on 2025-05-01 14:40
 * Created with IntelliJ IDEA.
 */
@Component
public class AppDeepSeekLoginHandler {

    @Autowired
    private AppBaseHandler appBaseHandler;
    @Autowired
    private AppDeepSeekLoginPage appDeepSeekLoginPage;

    /**
     * 点击同意须知按钮
     */
    public void clickIntoAgreeButton() {
        appDeepSeekLoginPage.findIntoAgreeButton().click();
    }

    /**
     * 点击 登录页面密码登录 按钮
     */
    public void clickPasswordLoginButton() {
        appDeepSeekLoginPage.findPasswordLoginButton().click();
    }

    /**
     * 点击账号输入框
     */
    public void clickAccountNumberInput() {
        appDeepSeekLoginPage.findAccountNumberInput().click();
    }

    /**
     * 向 手机号/邮箱 输入框输入账号
     * @param accountNumber
     */
    public void inputAccountNumberInput(String accountNumber) {
        appDeepSeekLoginPage.findAccountNumberInput().clear();
        appDeepSeekLoginPage.findAccountNumberInput().sendKeys(accountNumber);
    }

    /**
     * 点击密码输入框
     */
    public void clickPasswordInput() {
        appDeepSeekLoginPage.findPasswordInput().click();
    }

    /**
     * 向 密码 输入框输入密码
     * @param password
     */
    public void inputPasswordInput(String password) {
        appDeepSeekLoginPage.findPasswordInput().clear();
        appDeepSeekLoginPage.findPasswordInput().sendKeys(password);
    }

    /**
     * 点击 登录按钮
     */
    public void clickLonginButton() {
        appDeepSeekLoginPage.findLonginButton().click();
    }
}
