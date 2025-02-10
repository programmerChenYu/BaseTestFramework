package com.github.programmerChenYu.base;

import com.github.programmerChenYu.listener.ScreenshotListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

/**
 * Description: 文件头
 * Created by 爱吃小鱼的橙子 on 2025-02-10 22:26
 * Created with IntelliJ IDEA.
 */
@Listeners({ScreenshotListener.class})
public class BaseCase {

    private String browser;

    @BeforeClass
    @Parameters({"browser"})
    public void initBaseCase(String browser) {
        this.browser = browser;
    }
}
