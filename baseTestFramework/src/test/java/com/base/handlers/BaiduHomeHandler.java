package com.base.handlers;

import com.base.base.BaseHandler;
import com.base.pages.BaiduHomePage;

/**
 * Description: 百度首页操作
 * Created by cy on 2025-02-08 14:21
 * Created with IntelliJ IDEA.
 */
public class BaiduHomeHandler extends BaseHandler {

    private static final BaiduHomePage baiduHomePage;

    static {
        baiduHomePage = new BaiduHomePage();
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
