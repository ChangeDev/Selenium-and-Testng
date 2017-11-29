<!-- $theme:gaia -->
<!-- $size: 16:9 -->
<!-- page_number: true -->
<!-- prerender: true -->
![bg](D:\Users\TQ-G153\Desktop\se+te\background.png)
# **Selenium** + TestNG
#### 自动化测试
###### Created by **长风**
###### Used [Marp](https://github.com/yhatt/marp)

---
![bg](D:\Users\TQ-G153\Desktop\se+te\background.png)
## **自动化测试及工具简述**
- *广义的自动化测试与狭义的自动化测试*
- *常见的自动化测试工具:*
	- UFT(Unified Functional Testing)
	- Robot Framework(基于python)
	- Watir(Ruby语言库)
	- Selenium(支持多平台、多浏览器、多语言)
---
![bg](D:\Users\TQ-G153\Desktop\se+te\background.png)
## **Selenium 工具介绍**
- **什么是 Selenium**
<small>Selenium 主要用于 Web 应用程序的自动化测试</small>
	- 开源，免费
    - 多浏览器支持：Firefox、Chrome、IE、Opera、Edge
    - 多平台支持：Linux 、Windows、MAC
    - 多语言支持：Java、Python、Ruby、C#、JavaScript、C++
    - 对 Web 页面有良好的支持
    - 简单（API 简单）、灵活（用开发语言驱动）
    - 支持分布式测试用例执行
 ---
 ![bg](D:\Users\TQ-G153\Desktop\se+te\background.png)
## **前端技术**
- **HTML**
- **JavaScript**
- **XML**
- **Chrome和FireFox的开发人员工具**
- **开发语言：Java**

---
![bg](D:\Users\TQ-G153\Desktop\se+te\background.png)
## **WebDriver API**
<small>&emsp;&emsp;WebDriver 属于 Selenium 体系中设计出来操作浏览器的一套 API，它支持多种编程语言。
&emsp;&emsp;站在Java的角度来看，Selenium WebDriver 只是 Java 的一个第三方框架，和 Spring开发框架属于同一性质，只是 Spring 只在 Java 语言中存在，其它语言也有用于 web 开发框架，但不叫 Spring。而 Selenium WebDriver 框架针对不同语言分别开发了该框架，所以，在不同的编程语言里它都叫 Selenium WebDriver。</small>

---
![bg](D:\Users\TQ-G153\Desktop\se+te\background.png)
## **环境搭建**
- Java
- Chrome或者Firefox
- IDE(IDEA)
- Maven
- WebDriver
<small>Chrome:[chromedriver](http://chromedriver.storage.googleapis.com/index.html)
Firefox:[geckodriver](https://github.com/mozilla/geckodriver/releases)</small>

---
![bg](D:\Users\TQ-G153\Desktop\se+te\background.png)
## **使用WebDriver定位元素**
- id
```
By.id()
```
- name
```
By.name()
```
- class name
```
By.className()
```

---
![bg](D:\Users\TQ-G153\Desktop\se+te\background.png)
## **使用WebDriver定位元素**
- tag name
```
By.className()
```
- link text
```
By.linkText()
```
- partial link text
```
By.partialLinkText()
```

---
![bg](D:\Users\TQ-G153\Desktop\se+te\background.png)
## **使用WebDriver定位元素**
- xpath
<small>&emsp;&emsp;XPath 是一种在 XML 文档中定位元素的语言。因为 HTML 可以看作 XML 的一种实现，所以 Selenium 用户可以使用这种强大的语言在 Web 应用中定位元素。</small>

```
By.xpath()
```

---
![bg](D:\Users\TQ-G153\Desktop\se+te\background.png)
## **使用WebDriver定位元素**
#### xpath定位元素
- 绝对路径定位

- 利用元素属性定位

- 层级与属性结合

- 使用逻辑运算符

> <small>Tips：可以使用浏览器的开发者工具直接复制元素的xpath语法来定位该元素</small>

---
![bg](D:\Users\TQ-G153\Desktop\se+te\background.png)
## **使用WebDriver定位元素**
#### css定位元素

|选择器|例子|描述|
|:-:|:-:|:-:|
|.class|.intro|class="intro"的所有元素|
|#id|#firstname| id="firstname"的所有元素|
|*|*|选择所有元素|
|element|p|元素所有```<p>```元素|
|element>element|div>a|父元素为```<div```的所有```<a>```元素>|
|```[attrubute=value]```|```[target=_blank]```|```target="_blank"```的所有元素|
> 可以使用开发者工具帮助生成css选择器语法。
---
![bg](D:\Users\TQ-G153\Desktop\se+te\background.png)
## **控制浏览器**
- 窗口大小
```
driver.manage().window.setSize(new Dimension(480,800));
```
- 前进后退
```
driver.navigate().back();
driver.navigate().forward();
```
- 刷新
```
driver.navigate().refresh();
```

---
![bg](D:\Users\TQ-G153\Desktop\se+te\background.png)
## **简单元素操作**
- 清除文本框内容
```
WebElement.clear();
```
- 模拟键盘输入
```
WebElement.sendKeys();
```
- 单击元素
```
WebElement.click();
```

---
![bg](D:\Users\TQ-G153\Desktop\se+te\background.png)
## **WebElement接口常用方法**
- 提交表单
```
submit();
```
- 获取元素信息
```
getSize();
```
```
getText();
```
```
getAttribute(name);
```
```
isDisplayed();
```

---
![bg](D:\Users\TQ-G153\Desktop\se+te\background.png)
## **鼠标事件**
<small>Actions类提供了鼠标操作的常用方法:</small>
```
contextClick() 右击
```
```
clickAndHold() 鼠标点击并控制
```
```
doubleClick() 双击
```
``` 
dragAndDrop() 拖动
```
```
release() 释放鼠标
```
```
perform()  执行所有Actions中存储的行为
```

---
![bg](D:\Users\TQ-G153\Desktop\se+te\background.png)
## **键盘事件**
<small>Keys类提供了键盘上几乎所有按键的方法。sendKeys()方法可以用来模拟键盘输入，除此之外，我们还可以用它来输入键盘上的按键，甚至是组合键，如 Ctrl+A、Ctrl+C 等。</small>
<small>*例如：*</small>
```
input.sendKeys(Keys.ENTER);
```

---
![bg](D:\Users\TQ-G153\Desktop\se+te\background.png)
## **设置元素等待**

**WebDriver 提供了几种方法来等待元素:**
- implicitlyWait。识别对象时的超时时间。
- setScriptTimeout。异步脚本的超时时间。
- pageLoadTimeout。页面加载时的超时时间。

---
![bg](D:\Users\TQ-G153\Desktop\se+te\background.png)
## **多窗口切换**
在页面操作过程中有时候点击某个链接会弹出新的窗口，这时就需要主机切换到新打开的窗口上进行操作。WebDriver 提供```switchTo().window()```方法可以实现在不同的窗口之间切换。

---
![bg](D:\Users\TQ-G153\Desktop\se+te\background.png)
## **操作Cookie**
<small>有时候我们需要验证浏览器中 cookie 是否正确，WebDriver 提供了操作 Cookie 的相关方法可以读取、添加和删除 cookie 信息。</small>
|方法|描述|
|:-:|:-:|
|getCookies()|获取所有coolie信息|
|getCookieNamed()|返回key为"name"的cookie信息|
|addCookie()|添加cookie|
|deleteCookieNamed()|删除cookie信息|
|deleteAllCookies()|删除所有cookie信息|

---
![bg](D:\Users\TQ-G153\Desktop\se+te\background.png)
## **执行Js脚本**
```
((JavascriptExecutor)driver).executeScript("window.alert();");
```
---
![bg](D:\Users\TQ-G153\Desktop\se+te\background.png)
## **TestNG**
#### **简介**
<small>JUnit 一直是一个单元测试框架,TestNG 则是用来解决更高级别的测试问题。</small>
- 依赖性测试
- 参数化测试
- 多线程测试

---
![bg](D:\Users\TQ-G153\Desktop\se+te\background.png)
## **TestNG**
#### **testng.xml配置**
- suite 定义一个测试套件，可包含多个测试用例或测试group
- test 定义一个测试用例,包含测试类或者包
- group 测试组
- classes 具体的测试类
- packages 测试包
- methods 测试方法
