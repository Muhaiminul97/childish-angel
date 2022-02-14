package appiumcal;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Deprecated

public class TestApk {
    AppiumDriver driver;

    @BeforeTest
    public void test() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        caps.setCapability("platformName", "android");
        caps.setCapability("appPackage", "com.android.calculator2");
        caps.setCapability("appActivity", ".Calculator");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
    }

    @Test
    public void sum() {
        //1st way to validate
        driver.findElement(By.id("digit_1")).click();
        driver.findElement(By.id("op_add")).click();
        driver.findElement(By.id("digit_6")).click();
        WebElement first = driver.findElement(By.id("result"));
        String fy = first.getText();
        driver.findElement(By.id("eq")).click();
        String v = String.valueOf(9);

        if (fy.equals(v)) {
            System.out.println("Output:: " + driver.findElement(By.id("result")).getText());
            System.out.println("Valid Result");

        } else {
            System.out.println("Found error");
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("clr")).click();

        //2nd Way to validate
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("digit_5")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("op_mul")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("digit_6")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("eq")).click();
        System.out.println("Output:: " + driver.findElement(By.id("result")).getText());
        Assert.assertEquals(driver.findElement(By.id("result")).getText(), "30");
        System.out.println("Test Completed Successfully");


    }
@AfterTest
    public void close() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.quit();

    }

}
