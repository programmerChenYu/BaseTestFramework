package com.base.cases;

import com.base.handlers.BaiduHomeHandler;
import com.base.utils.WebDriverUtil;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Description: 百度搜索测试用例
 * Created by cy on 2025-02-08 14:23
 * Created with IntelliJ IDEA.
 */
public class BaiduSearchCase {

    private BaiduHomeHandler baiduHomeHandler;

    @BeforeClass
    @Parameters({"browser"})
    public void init(String browser) {
        baiduHomeHandler = new BaiduHomeHandler(browser);
    }

    @Test
    @Parameters({"content"})
    public void testBaiduSearchCase(String content) {
        try {
            Allure.step("step1: 输入搜索内容【" + content + "】");
            baiduHomeHandler.inputContent(content);
            Allure.step("step2: 点击搜索按钮");
            baiduHomeHandler.clickSearchButton();
            Thread.sleep(3000);
            Assert.assertEquals(1, 2);
        } catch (AssertionError e) {
            baiduHomeHandler.takeScreenshot("testBaiduSearchContent");
            throw e;
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    @AfterClass
    public void destroy() {
        WebDriverUtil.quitDriver();
    }

}
