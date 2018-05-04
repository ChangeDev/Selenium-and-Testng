package com.taoqi;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest {
    static WebDriver driver = null;

    static {
        // firefox driver
        System.setProperty("webdriver.gecko.driver", "F:\\browser driver\\geckodriver.exe");
        driver = new FirefoxDriver();
    }

    public static void main(String[] args) throws Exception {
        driver.get("http://www.baidu.com");
        driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MILLISECONDS);
        driver.getWindowHandle();
        ((JavascriptExecutor)driver).executeScript("window.alert(\"alert\");");
        //WebElement element = driver.findElement(By.cssSelector(".extra > a:nth-child(1)"));
        //.sendKeys("Selenium");
        // Thread.sleep(1000);
        //driver.findElement(By.cssSelector("a.mnav:nth-child(3)")).sendKeys(Keys.CONTROL, "w");

        new Actions(driver).clickAndHold().perform();
        //actions.perform();
    }
}