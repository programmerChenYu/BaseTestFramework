package io.github.programmerchenyu.demo.app.page;

import io.github.programmerchenyu.app.base.AppBasePage;
import io.github.programmerchenyu.beans.factory.annotation.Autowired;
import io.github.programmerchenyu.beans.factory.annotation.Component;
import org.openqa.selenium.WebElement;

/**
 * Description: 文件头
 * Created by 爱吃小鱼的橙子 on 2025-05-01 14:29
 * Created with IntelliJ IDEA.
 */
@Component
public class AppDeepSeekLoginPage {

    @Autowired
    private AppBasePage appBasePage;

    /**
     * 打开软件时同意须知的同意按钮
     * @return
     */
    public WebElement findIntoAgreeButton() {
        return this.appBasePage.getElement("appDsAgreeButton");
    }

    /**
     * 登录页面密码登录按钮
     * @return
     */
    public WebElement findPasswordLoginButton() {
        return this.appBasePage.getElement("appDsPwButton");
    }

    /**
     * 登录页面 手机号/邮箱 输入框
     * @return
     */
    public WebElement findAccountNumberInput() {
        return this.appBasePage.getElement("appAccountNumberInput");
    }

    /**
     * 登录页面 密码 输入框
     * @return
     */
    public WebElement findPasswordInput() {
        return this.appBasePage.getElement("appPasswordInput");
    }

    /**
     * 登录按钮
     * @return
     */
    public WebElement findLonginButton() {
        return this.appBasePage.getElement("appLoginButton");
    }
}
