# BaseTestFramework - 快速自动化测试基础框架 🚀

[![Java 17](https://img.shields.io/badge/Java-17-%23ED8B00?logo=openjdk)](https://openjdk.org/projects/jdk/17/)
[![Maven 3.9.6](https://img.shields.io/badge/Maven-3.9.6-%23C71A36?logo=apachemaven)](https://maven.apache.org/docs/3.9.6/release-notes.html)
[![License](https://img.shields.io/badge/License-MIT-%2300ADD8)](https://opensource.org/licenses/MIT)

> **让测试代码比春天的新芽更优雅！**

BaseTestFrameWork 是一个基于 **Selenium** 和 **TestNG** 构建的自动化测试框架，采用 **POM（Page Object Model）** 设计模式，集成 **Allure** 测试报告和 **Maven** 依赖管理，旨在提供高可维护性、可扩展性的自动化测试基础框架。


## 🌟 项目亮点
- **灵活的元素定位策略**：使用「策略模式+配置驱动」元素定位方案，无需硬编码即可动态切换定位方式
- **经典 POM 设计**：严格遵循 Page Object Model 模式，实现测试逻辑与页面对象的完美解耦
- **设计模式引入**：单例模式WebDriver管理 × 注解式失败处理 × 多维度封装，打造更灵活的测试框架
- **零侵入扩展**：自定义`@CaptureScreenshotOnFailure`注解实现优雅的失败处理机制
- **高效调试支持**：集成SLF4J + Log4j2日志系统，实时追踪测试执行过程
- **多浏览器矩阵**：通过枚举实现浏览器类型安全校验，支持Chrome/Firefox/Edge等全平台适配
- **Allure 报告集成**：自动生成美观的测试报告


## 🛠️ 架构蓝图
```bash
📦 BaseTestFramework
├── 📂 src/main/java
│   ├── 📂 annotation
│   │   └── CaptureScreenshotOnFailure.java # 失败时截图注解
│   ├── 📂 base
│   │   ├── BaseHandler.java                # 原子操作封装库（点击/输入/滚动等）
│   │   └── BasePage.java                   # 策略模式驱动的智能元素定位中心
│   ├── 📂 constant
│   │   └── FilePathConstant.java           # 配置文件路径管理
│   ├── 📂 enums
│   │   └── BrowserTypeEnum.java            # 类型安全的浏览器枚举
│   ├── 📂 listeners
│   │   └── CaptureListener.java            # 注解驱动的智能监听器
│   └── 📂 utils
│       ├── ReadPropertiesUtil.java         # 配置文件读取工具
│       └── WebDriverUtil.java              # 单例模式WebDriver工厂
└── 📂 resources
    └── log4j2.xml                          # Log4j2配置文件
```


## 🛠️ 技术栈要求
| 组件           | 版本要求       | 验证命令          |
|----------------|---------------|-------------------|
| JDK            | 17+           | `java -version`   |
| Maven          | 3.9.6+        | `mvn -v`          |
| Allure         | 2.20+         | `allure --version`|

> **🚨 注意：本框架要求开发环境严格匹配JDK 17与Maven 3.9.6，使用旧版本可能导致不可预知的行为！**


## 🚀 快速上手指南

### 1. 环境准备
```bash
mvn install -DskipTests
```

### 2. 创建配置文件
```properties
# 在test/resource目录下创建element.properties文件，内容格式如下（定位方式和定位用 > 分隔）
loginButton=xpath>//*[@id="root"]/div/div[2]/div/div/div[7]

# 在test/resource目录下创建config.properties文件，基本内容如下
curEnvironment=test            # 表示当前环境，有 prod、test、dev 三种可供选择
prodUrl=https://www.prod.com   # 生产环境下的访问路径
testUrl=https://www.test.com   # 测试环境下的访问路径
devUrl=https://www.dev.com     # 开发环境下的访问路径
```

### 3. 编写你的第一个测试用例

#### 3.1 创建要操作的页面类
```java
public class LoginPage extends BasePage {

    public LoginPage(String browser) {
        super(browser);
    }

    /**
     * 登录页面密码登录按钮
     * @return
     */
    public WebElement findPasswordLoginButton() {
        return getElement("dSPwButton");
    }

    /**
     * 登录页面 手机号/邮箱 输入框
     * @return
     */
    public WebElement findAccountNumberInput() {
        return getElement("accountNumberInput");
    }

    /**
     * 登录页面 密码 输入框
     * @return
     */
    public WebElement findPasswordInput() {
        return getElement("passwordInput");
    }

    /**
     * 同意用户协议 确认 按钮
     * @return
     */
    public WebElement findAgreeButton() {
        return getElement("agreeButton");
    }

    /**
     * 登录按钮
     * @return
     */
    public WebElement findLonginButton() {
        return getElement("loginButton");
    }
}
```

#### 3.2 创建页面的操作类
```java
public class LoginHandler extends BaseHandler {

    private LoginPage loginPage;

    public LoginHandler(String browser) {
        loginPage = new LoginPage(browser);
    }

    /**
     * 点击 登录页面密码登录 按钮
     */
    public void clickPasswordLoginButton() {
        if (!loginPage.findAgreeButton().isSelected()) {
            loginPage.findPasswordLoginButton().click();
        }
    }

    /**
     * 点击账号输入框
     */
    public void clickAccountNumberInput() {
        loginPage.findAccountNumberInput().click();
    }

    /**
     * 向 手机号/邮箱 输入框输入账号
     * @param accountNumber
     */
    public void inputAccountNumberInput(String accountNumber) {
        loginPage.findAccountNumberInput().clear();
        loginPage.findAccountNumberInput().sendKeys(accountNumber);
    }

    /**
     * 点击密码输入框
     */
    public void clickPasswordInput() {
        loginPage.findPasswordInput().click();
    }

    /**
     * 向 密码 输入框输入密码
     * @param password
     */
    public void inputPasswordInput(String password) {
        loginPage.findPasswordInput().clear();
        loginPage.findPasswordInput().sendKeys(password);
    }

    /**
     * 点击 同意用户协议 确认 按钮
     */
    public void clickAgreeButton() {
        loginPage.findAgreeButton().click();
    }

    /**
     * 点击 登录按钮
     */
    public void clickLonginButton() {
        loginPage.findLonginButton().click();
    }

}
```

#### 3.3 创建登录测试用例类
```java
public class LoginCase {
    // 必须命名为 handler
    private LoginHandler handler;

    // 在testng.xml文件中指定使用什么浏览器
    @BeforeClass
    @Parameters({"browser"})
    public void init(String browser) {
        handler = new LoginHandler(browser);
    }

    @Test
    @Parameters({"accountNumber", "password"})
    @CaptureScreenshotOnFailure(caseName = "登录测试用例")
    public void loginCase(String accountNumber, String password) {
        Allure.step("step1: 点击密码登录按钮");
        handler.clickPasswordLoginButton();
        Allure.step("step2: 点击账号输入框");
        handler.clickAccountNumberInput();
        Allure.step("step3: 输入账号【" + accountNumber + "】");
        handler.inputAccountNumberInput(accountNumber);
        Allure.step("step4: 点击密码输入框");
        handler.clickPasswordInput();
        Allure.step("step5: 输入密码【" + password + "】");
        handler.inputPasswordInput(password);
        Allure.step("step6: 点击同意用户协议");
        handler.clickAgreeButton();
        Allure.step("step7: 点击登录");
        handler.clickLonginButton();
        WebDriver driver = handler.getDriver();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.test.com/");
    }

    @AfterClass
    public void destroy() {
        WebDriverUtil.quitDriver();
    }
}
```

#### 3.5 在项目根目录下创建testng.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="All Test Suite">
    <test verbose="2" preserve-order="true"
          name="测试" skipfailedinvocationcounts="true">
        <classes>
            <class name="com.github.programmerChenYu.cases.LoginCase">
                <parameter name="browser" value="chrome"></parameter>
                <parameter name="accountNumber" value="12345678901"></parameter>
                <parameter name="password" value="ds123456"></parameter>
                <methods>
                    <include name="loginCase" />
                </methods>
            </class>
        </classes>
    </test>
</suite>
```


## 📊 执行与报告

### 1. 测试执行命令
```bash
# 测试用例执行
mvn clean test

# 在项目根某目录下，进入命令行界面，使用如下命令生成Allure可视化报告
allure generate ./allure-results -o ./allure-results/html --clean

# 在项目根某目录下，进入命令行界面，使用如下命令打开可视化报告
allure open ./allure-results/html
```


## 🎯 Demo 展示
**在项目中有一个demo目录，其中编写了用BaseTestFramwork快速构建自动化测试代码的案例，以 DeepSeek 登录成功功能为例**

### 🔍 功能测试点分析
![Image](https://github.com/user-attachments/assets/0bd7dbfd-8a32-420a-a49a-e3b7af3e3229)

### 📸 测试用例
![Image](https://github.com/user-attachments/assets/00e9d7ff-6161-487c-a22b-471e254284d3)


## 🤝 贡献者公约
欢迎提交PR！特别期待：
- 可视化测试报告模块
- 移动端自动化测试集成
- 分布式执行解决方案


## 📜 开源许可
MIT License | 为创新而生，因共享而美

---

**✨ 现在，是时候开始体验了！**
