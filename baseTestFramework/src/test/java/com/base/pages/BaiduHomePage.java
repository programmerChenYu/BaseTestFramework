package com.base.pages;

import com.base.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Description: 百度首页
 * Created by cy on 2025-02-08 14:16
 * Created with IntelliJ IDEA.
 */
public class BaiduHomePage extends BasePage {

    /**
     * 返回输入框元素
     * @return
     */
    public WebElement findInputContentElement() {
        return getElement("baiduInput");
    }

    /**
     * 返回搜索按钮元素
     * @return
     */
    public WebElement findSearchButtonElement() {
        return getElement("baiduSearchButton");
    }

}
