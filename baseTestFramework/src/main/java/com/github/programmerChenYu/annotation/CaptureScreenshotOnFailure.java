package com.github.programmerChenYu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 测试失败时自动截图 注解
 * Created by cy on 2025-02-10 12:44
 * Created with IntelliJ IDEA.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CaptureScreenshotOnFailure {
    String caseName();
}
