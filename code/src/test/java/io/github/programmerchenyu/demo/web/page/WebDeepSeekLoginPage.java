package io.github.programmerchenyu.demo.web.page;

import io.github.programmerchenyu.beans.factory.annotation.Autowired;
import io.github.programmerchenyu.beans.factory.annotation.Component;
import io.github.programmerchenyu.web.base.WebBasePage;
import org.openqa.selenium.WebElement;

/**
 * Description: deepSeek 登陆页面
 * Created by cy on 2025-02-08 19:31
 * Created with IntelliJ IDEA.
 */
@Component
public class WebDeepSeekLoginPage {

    @Autowired
    private WebBasePage webBasePage;

    /**
     * 登录页面密码登录按钮
     * @return
     */
    public WebElement findPasswordLoginButton() {
        return this.webBasePage.getElement("dSPwButton");
    }

    /**
     * 登录页面 手机号/邮箱 输入框
     * @return
     */
    public WebElement findAccountNumberInput() {
        return this.webBasePage.getElement("accountNumberInput");
    }

    /**
     * 登录页面 密码 输入框
     * @return
     */
    public WebElement findPasswordInput() {
        return this.webBasePage.getElement("passwordInput");
    }

    /**
     * 登录按钮
     * @return
     */
    public WebElement findLonginButton() {
        return this.webBasePage.getElement("loginButton");
    }
}
