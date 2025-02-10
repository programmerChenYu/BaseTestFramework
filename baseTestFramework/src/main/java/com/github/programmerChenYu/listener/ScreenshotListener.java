package com.github.programmerChenYu.listener;

import com.github.programmerChenYu.annotation.CaptureScreenshotOnFailure;
import com.github.programmerChenYu.base.BaseHandler;
import lombok.extern.slf4j.Slf4j;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.lang.reflect.Field;

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
            Field field = instance.getClass().getDeclaredField("handler");
            field.setAccessible(true);
            BaseHandler handler = (BaseHandler) field.get(instance);
            handler.takeScreenshot(testResult.getTestClass().getName());
        } catch (Exception e) {
            log.error("用例【{}】的失败截图抛出异常，原因如下：{}", testResult.getMethod().getConstructorOrMethod().getMethod().getName(), e.getMessage());
            e.printStackTrace();
        }
    }
}
