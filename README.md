# BaseTestFramework

![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)

BaseTestFrameWork 是一个基于 **Selenium** 和 **TestNG** 构建的自动化测试框架，采用 **POM（Page Object Model）** 设计模式，集成 **Allure** 测试报告和 **Maven** 依赖管理，旨在提供高可维护性、可扩展性的自动化测试基础框架。

## 技术栈

- **Selenium**：用于浏览器自动化操作
- **TestNG**：测试框架，支持数据驱动、并行测试、测试分组
- **Allure**：生成美观的测试报告
- **Maven**：项目构建与依赖管理

## 项目结构

```shell
BaseTestFrameWork/
├─.idea
├─allure-results
│  └─html
│      ├─data
│      │  ├─attachments
│      │  └─test-cases
│      ├─export
│      ├─history
│      ├─plugins
│      │  ├─behaviors
│      │  ├─jira
│      │  ├─junit
│      │  ├─packages
│      │  ├─screen-diff
│      │  ├─trx
│      │  ├─xctest
│      │  ├─xray
│      │  └─xunit-xml
│      └─widgets
├─screenshot
│  └─fail
├─src
│  └─test
│      ├─java
│      │  └─com
│      │      └─base
│      │          ├─base
│      │          ├─cases
│      │          ├─constant
│      │          ├─enums
│      │          ├─handlers
│      │          ├─pages
│      │          └─utils
│      └─resources
```

## 项目特点

1. **POM 分层设计**
   - `pages/`：获取页面元素
   - `handlers/`：封装页面操作逻辑
   - `cases/`：编写 TestNG 测试用例
2. **Allure 集成**
   - 通过 `Allure.step("")` 方法记录操作步骤
   - 生成 HTML 可视化报告
3. **Maven 管理**
   - 一键依赖安装（Selenium、TestNG、Allure）
   - 支持 `mvn test` 运行测试套件
4. **模块化设计**
   - 项目结构清晰，分为 `base`、`cases`、`constant`、`enums`、`handlers`、`pages`、`utils` 和 `resources` 等模块，便于扩展和维护。
5. **配置文件支持**
   - 使用 `element.properties` 文件管理页面元素定位信息，便于统一修改和维护。

## 快速开始

### 环境要求

- JDK 17+
- Maven 3.9+
- Chrome/Firefox 浏览器

### 安装依赖

```bash
mvn clean install
```

### 运行测试

1. **运行所有测试**

   ```bash
   mvn test
   ```

2. **生成可视化 Allure 报告**

   `位于项目根目录下`

   ```bash
   allure generate ./allure-results -o ./allure-results/html --clean
   ```

3. **打开 Allure 可视化报告**

   `位于项目根目录下`

   ```bash
   allure open ./allure-resultes/html
   ```

## 许可证

[MIT License](https://chat.deepseek.com/a/chat/s/LICENSE) | © 2025 programmerChenYu
