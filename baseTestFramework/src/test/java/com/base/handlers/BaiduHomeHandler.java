package com.base.handlers;

import com.base.base.BaseHandler;
import com.base.pages.BaiduHomePage;

/**
 * Description: 百度首页操作
 * Created by cy on 2025-02-08 14:21
 * Created with IntelliJ IDEA.
 */
public class BaiduHomeHandler extends BaseHandler {

    private final BaiduHomePage baiduHomePage;

    public BaiduHomeHandler(String browser) {
        this.baiduHomePage = new BaiduHomePage(browser);
    }

    /**
     * 向输入框输入文字
     * @param content
     */
    public void inputContent(String content) {
        inputText(baiduHomePage.findInputContentElement(), content);
    }

    public void clickSearchButton() {
        clickElement(baiduHomePage.findSearchButtonElement());
    }

}
