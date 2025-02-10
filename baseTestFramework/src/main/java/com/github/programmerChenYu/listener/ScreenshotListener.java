package com.github.programmerChenYu.listener;

import com.github.programmerChenYu.annotation.CaptureScreenshotOnFailure;
import com.github.programmerChenYu.utils.WebDriverUtil;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Description: 失败截图的监听器
 * Created by cy on 2025-02-10 15:20
 * Created with IntelliJ IDEA.
 */
@Slf4j
public class ScreenshotListener implements IInvokedMethodListener {
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // 检查测试方法或类是否带有注解
        boolean hasAnnotation = method.getTestMethod()
                .getConstructorOrMethod()
                .getMethod()
                .isAnnotationPresent(CaptureScreenshotOnFailure.class);
        if (!hasAnnotation) {
            // 检查类级别注解
            hasAnnotation = testResult.getTestClass().getRealClass().isAnnotationPresent(CaptureScreenshotOnFailure.class);
        }

        if (hasAnnotation && testResult.getStatus() == ITestResult.FAILURE) {
            log.warn("用例【{}】执行失败", method.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(CaptureScreenshotOnFailure.class).caseName());
            captureScreenshotAndAttach(testResult);
        }
    }

    private void captureScreenshotAndAttach(ITestResult testResult) {
        try {
            Object instance = testResult.getInstance();
            Field field = instance.getClass().getSuperclass().getDeclaredField("browser");
            field.setAccessible(true);
            String browser = (String) field.get(instance);
            WebDriver driver = WebDriverUtil.getDriver(browser);
            String time = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss"));
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            StringBuilder sb = new StringBuilder();
            String fullPath = sb.append("screenshot\\fail")
                    .append(File.separator)
                    .append(testResult.getTestClass().getRealClass().getName())
                    .append("_")
                    .append(testResult.getMethod().getConstructorOrMethod().getMethod().getName())
                    .append("_")
                    .append(time)
                    .append(".png")
                    .toString();
            FileUtils.copyFile(screenshot, new File(fullPath));
            log.info("失败截图保存至【{}】", fullPath);
            byte[] screenshotBytes = FileUtils.readFileToByteArray(screenshot);
            Allure.step("添加失败截图");
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshotBytes));
        } catch (Exception e) {
            log.error("用例【{}】的失败截图抛出异常，原因如下：{}", testResult.getMethod().getConstructorOrMethod().getMethod().getName(), e.getMessage());
            e.printStackTrace();
        }
    }
}
