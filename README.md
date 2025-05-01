# BaseTestFramework - 快速自动化测试基础框架 🚀

[![Java 17](https://img.shields.io/badge/Java-17-%23ED8B00?logo=openjdk)](https://openjdk.org/projects/jdk/17/) [![Maven 3.9.6](https://img.shields.io/badge/Maven-3.9.6-%23C71A36?logo=apachemaven)](https://maven.apache.org/docs/3.9.6/release-notes.html) [![License](https://img.shields.io/badge/License-MIT-%2300ADD8)](https://opensource.org/licenses/MIT) [![TestNexus Powered](https://img.shields.io/badge/Test--Nexus-1.0.1-%2300C4CC)](https://github.com/programmerChenYu/test-nexus) 

> **让测试代码比春天的新芽更优雅，让多端测试如呼吸般自然！**

BaseTestFramework 是一个基于 **test-nexus** 的自动化测试框架，深度融合 **Selenium** 与 **Appium**，通过 IOC 容器实现 Web/移动端（iOS/Android）双驱动测试，采用 **POM（Page Object Model）** 设计模式，集成 **Allure** 测试报告和 **Maven** 依赖管理，旨在提供高可维护性、可扩展性的自动化测试基础框架。


## 🌟 项目亮点
- **灵活的元素定位策略**：使用「策略模式+配置驱动」元素定位方案，无需硬编码即可动态切换定位方式
- **经典 POM 设计**：严格遵循 Page Object Model 模式，实现测试逻辑与页面对象的完美解耦
- **设计模式引入**：单例模式WebDriver管理 × 注解式失败处理 × 多维度封装，打造更灵活的测试框架
- **零侵入扩展**：自定义`@CaptureScreenshotOnFailure`注解实现优雅的失败处理机制
- **自动生命周期管理**：通过 `@Component` + `@Autowired` 注解实现 WebDriver/AppiumDriver 的自动创建与销毁  
- **依赖注入革命**：支持构造器/方法/属性三种注入方式，彻底告别手动初始化  
- **环境智能感知**：根据配置自动切换 Web/移动端测试模式 
- **高效调试支持**：集成SLF4J + Log4j2日志系统，实时追踪测试执行过程
- **多浏览器矩阵**：通过枚举实现浏览器类型安全校验，支持Chrome/Firefox/Edge等全平台适配
- **Allure 报告集成**：自动生成美观的测试报告


## 🛠️ 架构蓝图
```bash
📦 BaseTestFramework
├── 📂 src/main/java/io/github/programmerchenyu
│   ├── 📂 annotation
│   │   └── CaptureScreenshotOnFailure.java # 失败时截图注解
│   ├── 📂 app
│   │   ├── 📂 base
│   │   │   ├── AppBaseCase.java		# 注册监听器并初始化指定使用的浏览器类型           
│   │   │   ├── AppBaseHandler.java		# 原子操作封装库（点击/输入/滚动等）
│   │   │   └── AppBasePage.java         # 策略模式驱动的元素定位中心
│   │   └── 📂 driver
│   │   │   ├── AndroidDriver.java		# 安卓驱动         
│   │   │   ├── AppDriver.java		   	# 封装安卓/IOS的驱动类，根据配置自动使用对应的驱动
│   │   │   └── IOSDriver.java			# IOS驱动
│   ├── 📂 constant
│   │   └── FilePathConstant.java        # 配置文件路径管理
│   ├── 📂 enums
│   │   └── BrowserTypeEnum.java         # 类型安全的浏览器枚举
│   ├── 📂 listeners
│   │   └── CaptureListener.java         # 注解驱动的智能监听器
│   ├── 📂 web
│   │   ├── 📂 base
│   │   │   ├── WebBaseCase.java		# 注册监听器并初始化指定使用的浏览器类型           
│   │   │   ├── WebBaseHandler.java		# 原子操作封装库（点击/输入/滚动等）
│   │   │   └── WebBasePage.java         # 策略模式驱动的元素定位中心
│   │   └── 📂 driver
│   │   │   └── WebDriverUtil.java       # Web驱动
│   └── 📂 utils
│       └── ReadPropertiesUtil.java      # 配置文件读取工具
└── 📂 resources
    ├── config-app.properties			#移动端的相关配置
    ├── config-web.properties			#web端的相关配置
    ├── element.properties				#定位元素
    └── log4j2.xml                       # Log4j2配置文件
```

## 🔥 核心优势对比

| 特性         | 1.0 版本     | 2.0 版本             |
| ------------ | ------------ | -------------------- |
| **驱动管理** | 手动单例模式 | IOC 自动生命周期管理 |
| **多端支持** | 仅Web        | Web + iOS/Android    |
| **维护成本** | 高           | 低                   |

## 🛠️ 技术栈要求

| 组件           | 版本要求       | 验证命令          |
|----------------|---------------|-------------------|
| JDK            | 17+           | `java -version`   |
| Maven          | 3.9.6+        | `mvn -v`          |
| Allure         | 2.20+         | `allure --version`|

> **🚨 注意：若使用旧版本可能导致不可预知的行为！**


## 🚀 快速上手指南

### 1. 环境准备
```bash
# 打开 BaseTestFramework 项目，执行如下命令
mvn install -DskipTests

# 打开自己的项目，在pom.xml中添加如下依赖
<dependency>
  <groupId>io.github.programmerchenyu</groupId>
  <artifactId>baseTestFramework</artifactId>
  <version>2.0.0</version>
  <scope>test</scope>
</dependency>

# 在pom.xml中添加如下插件
<build>
  <defaultGoal>compile</defaultGoal>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-compiler-plugin</artifactId>
      <version>3.11.0</version>
      <configuration>
        <encoding>UTF-8</encoding>
        <source>17</source>
        <target>17</target>
      </configuration>
    </plugin>

    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-surefire-plugin</artifactId>
      <version>3.0.0-M5</version>
      <configuration>
        <testFailureIgnore>true</testFailureIgnore>
        <suiteXmlFiles>
          <suiteXmlFile>testng.xml</suiteXmlFile>
        </suiteXmlFiles>
      </configuration>
    </plugin>
  </plugins>
</build>
```

### 2. 创建配置文件

- config-web.properties

  ```properties
  # 在src/test/resource目录下创建element.properties文件，内容格式如下（定位方式和定位用 > 分隔）
  loginButton=xpath>//*[@id="root"]/div/div[2]/div/div/div[7]

  # 在src/test/resource目录下创建config.properties文件，基本内容如下
  curEnvironment=test            # 表示当前环境，有 prod、test、dev 三种可供选择
  prodUrl=https://www.prod.com   # 生产环境下的访问路径
  testUrl=https://www.test.com   # 测试环境下的访问路径
  devUrl=https://www.dev.com     # 开发环境下的访问路径
  browserType=chrome			  # 指定浏览器
  ```

- config-app.properties

  ```properties
  platformName=android		 			    # 指定设备类型（ios/android）
  deviceName=emulator-5554				    # 指定设备名
  appPackage=com.deepseek.chat				# 指定要使用的应用
  appActivity=com.deepseek.chat.MainActivity	 # 指定进入的页面
  ```

- element.properties

  ```properties
  # 在src/test/resource目录下创建element.properties文件，内容格式如下（定位方式和定位用 > 分隔）
  appDsAgreeButton=xpath>/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.Button
  appDsPwButton=xpath>/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/L0.q0/android.view.View/android.view.View/android.widget.ScrollView/i1.o[1]/android.widget.HorizontalScrollView/android.widget.EditText
  appAccountNumberInput=xpath>/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/L0.q0/android.view.View/android.view.View/android.widget.ScrollView/i1.o[1]/android.widget.HorizontalScrollView/android.widget.EditText
  appPasswordInput=xpath>/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/L0.q0/android.view.View/android.view.View/android.widget.ScrollView/i1.o[2]/android.widget.HorizontalScrollView/android.widget.EditText
  appLoginButton=xpath>/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/L0.q0/android.view.View/android.view.View/android.widget.ScrollView/android.view.View[4]/android.widget.Button

  dSPwButton=xpath>//*[@id="root"]/div/div/div[2]/div/div/div[2]/div[1]/div[1]/div[2]/div[1]
  accountNumberInput=xpath>//*[@id="root"]/div/div/div[2]/div/div/div[2]/div[1]/div[3]/div[1]/div/input
  passwordInput=xpath>//*[@id="root"]/div/div/div[2]/div/div/div[2]/div[1]/div[4]/div[1]/div/input
  loginButton=xpath>//*[@id="root"]/div/div/div[2]/div/div/div[2]/div[1]/div[6]

  ```

  ​

### 3. 编写你的第一个Web自动化测试用例

#### 3.1 创建要操作的页面类
```java
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
```

#### 3.2 创建页面的操作类
```java
@Component
public class WebDeepSeekLoginHandler {

    @Autowired
    private WebBaseHandler webBaseHandler;

    @Autowired
    private WebDeepSeekLoginPage webDeepSeekLoginPage;

    /**
     * 点击 登录页面密码登录 按钮
     */
    public void clickPasswordLoginButton() {
        webDeepSeekLoginPage.findPasswordLoginButton().click();
    }

    /**
     * 点击账号输入框
     */
    public void clickAccountNumberInput() {
        webDeepSeekLoginPage.findAccountNumberInput().click();
    }

    /**
     * 向 手机号/邮箱 输入框输入账号
     * @param accountNumber
     */
    public void inputAccountNumberInput(String accountNumber) {
        webDeepSeekLoginPage.findAccountNumberInput().clear();
        webDeepSeekLoginPage.findAccountNumberInput().sendKeys(accountNumber);
    }

    /**
     * 点击密码输入框
     */
    public void clickPasswordInput() {
        webDeepSeekLoginPage.findPasswordInput().click();
    }

    /**
     * 向 密码 输入框输入密码
     * @param password
     */
    public void inputPasswordInput(String password) {
        webDeepSeekLoginPage.findPasswordInput().clear();
        webDeepSeekLoginPage.findPasswordInput().sendKeys(password);
    }

    /**
     * 点击 登录按钮
     */
    public void clickLonginButton() {
        webDeepSeekLoginPage.findLonginButton().click();
    }

}
```

#### 3.3 创建登录测试用例类
```java
@TestNexus(classes = {WebConfiguration.class})
public class WebDeepSeekLoginCase extends WebBaseCase implements ITestNexusContext {

    @Autowired
    private WebDeepSeekLoginHandler webDeepSeekLoginHandler;

    @Autowired
    private WebDriverUtil webDriverUtil;

    @Test
    @CaptureScreenshotOnFailure(caseName = "deepSeek web端登录测试用例")
    public void loginCase() {
        String accountNumber = "12345678900";
        String password = "123456";
        Allure.step("step1: 点击密码登录按钮");
        webDeepSeekLoginHandler.clickPasswordLoginButton();
        Allure.step("step2: 点击账号输入框");
        webDeepSeekLoginHandler.clickAccountNumberInput();
        Allure.step("step3: 输入账号【" + accountNumber + "】");
        webDeepSeekLoginHandler.inputAccountNumberInput(accountNumber);
        Allure.step("step4: 点击密码输入框");
        webDeepSeekLoginHandler.clickPasswordInput();
        Allure.step("step5: 输入密码【" + password + "】");
        webDeepSeekLoginHandler.inputPasswordInput(password);
        Allure.step("step6: 点击登录");
        webDeepSeekLoginHandler.clickLonginButton();
        WebDriver driver = this.webDriverUtil.getDriver();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(driver.getCurrentUrl(), "https://chat.deepseek.com/");

    }
}
```

#### 3.4 在项目根目录下创建testng.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="All Test Suite">
    <test verbose="2" preserve-order="true"
          name="测试" skipfailedinvocationcounts="true">
        <classes>
            <class name="测试类的完整类路径">
                <methods>
                    <include name="loginCase" />
                </methods>
            </class>
        </classes>
    </test>
</suite>
```



### 4. 编写你的第一个App自动化测试用例

#### 4.1 创建要操作的页面类

```java
@Component
public class AppDeepSeekLoginPage {

    @Autowired
    private AppBasePage appBasePage;

    /**
     * 打开软件时同意须知的同意按钮
     * @return
     */
    public WebElement findIntoAgreeButton() {
        return this.appBasePage.getElement("appDsAgreeButton");
    }

    /**
     * 登录页面密码登录按钮
     * @return
     */
    public WebElement findPasswordLoginButton() {
        return this.appBasePage.getElement("appDsPwButton");
    }

    /**
     * 登录页面 手机号/邮箱 输入框
     * @return
     */
    public WebElement findAccountNumberInput() {
        return this.appBasePage.getElement("appAccountNumberInput");
    }

    /**
     * 登录页面 密码 输入框
     * @return
     */
    public WebElement findPasswordInput() {
        return this.appBasePage.getElement("appPasswordInput");
    }

    /**
     * 登录按钮
     * @return
     */
    public WebElement findLonginButton() {
        return this.appBasePage.getElement("appLoginButton");
    }
}
```

#### 4.2 创建页面的操作类

```java
@Component
public class AppDeepSeekLoginHandler {

    @Autowired
    private AppBaseHandler appBaseHandler;
    @Autowired
    private AppDeepSeekLoginPage appDeepSeekLoginPage;

    /**
     * 点击同意须知按钮
     */
    public void clickIntoAgreeButton() {
        appDeepSeekLoginPage.findIntoAgreeButton().click();
    }

    /**
     * 点击 登录页面密码登录 按钮
     */
    public void clickPasswordLoginButton() {
        appDeepSeekLoginPage.findPasswordLoginButton().click();
    }

    /**
     * 点击账号输入框
     */
    public void clickAccountNumberInput() {
        appDeepSeekLoginPage.findAccountNumberInput().click();
    }

    /**
     * 向 手机号/邮箱 输入框输入账号
     * @param accountNumber
     */
    public void inputAccountNumberInput(String accountNumber) {
        appDeepSeekLoginPage.findAccountNumberInput().clear();
        appDeepSeekLoginPage.findAccountNumberInput().sendKeys(accountNumber);
    }

    /**
     * 点击密码输入框
     */
    public void clickPasswordInput() {
        appDeepSeekLoginPage.findPasswordInput().click();
    }

    /**
     * 向 密码 输入框输入密码
     * @param password
     */
    public void inputPasswordInput(String password) {
        appDeepSeekLoginPage.findPasswordInput().clear();
        appDeepSeekLoginPage.findPasswordInput().sendKeys(password);
    }

    /**
     * 点击 登录按钮
     */
    public void clickLonginButton() {
        appDeepSeekLoginPage.findLonginButton().click();
    }
}
```

#### 4.3 创建登录测试用例类

```java
@TestNexus(classes = {AndroidConfiguration.class})
public class AppDeepSeekLoginCase extends AppBaseCase implements ITestNexusContext {

    @Autowired
    private AppDeepSeekLoginHandler appDeepSeekLoginHandler;
    @Autowired
    private AppDriver appDriver;

    @Test
    @CaptureScreenshotOnFailure(caseName = "deepSeek app端登录测试用例")
    public void loginCase() {
        String accountNumber = "12345678900";
        String password = "123456";
        Allure.step("step1: 点击协议的同意按钮");
        appDeepSeekLoginHandler.clickIntoAgreeButton();
        Allure.step("step2: 点击密码登录按钮");
        appDeepSeekLoginHandler.clickPasswordLoginButton();
        Allure.step("step3: 点击账号输入框");
        appDeepSeekLoginHandler.clickAccountNumberInput();
        Allure.step("step4: 输入账号【" + accountNumber + "】");
        appDeepSeekLoginHandler.inputAccountNumberInput(accountNumber);
        Allure.step("step5: 点击密码输入框");
        appDeepSeekLoginHandler.clickPasswordInput();
        Allure.step("step6: 输入密码【" + password + "】");
        appDeepSeekLoginHandler.inputPasswordInput(password);
        Allure.step("step7: 点击登录");
        appDeepSeekLoginHandler.clickLonginButton();
        AndroidDriver androidDriver = (AndroidDriver) appDriver.getDriver();
        String currentActivity = androidDriver.currentActivity();
        Assert.assertEquals(currentActivity, "com.deepseek.chat.HomeActivity");
    }
}
```

#### 4.4 在项目根目录下创建testng.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="All Test Suite">
    <test verbose="2" preserve-order="true"
          name="测试" skipfailedinvocationcounts="true">
        <classes>
            <class name="测试类的完整类路径">
                <methods>
                    <include name="loginCase" />
                </methods>
            </class>
        </classes>
    </test>
</suite>
```



## 📊 执行与报告

### 测试执行命令
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

### 🧩 Allure 报告

![image](https://github.com/user-attachments/assets/c0be74d9-9dc1-44c3-98fc-cb483594c5fa)

![image](https://github.com/user-attachments/assets/6ed1cf2a-3700-460d-83a6-a358fba0e0ae)


## 🤝 贡献者公约
欢迎提交PR！特别期待：
- 可视化测试报告模块
- 分布式执行解决方案


## 📜 开源许可
MIT License | 为创新而生，因共享而美

---

**✨ 现在，是时候开始体验了！**
