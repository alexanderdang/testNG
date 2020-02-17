package chp7datadriventesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class LoginOrangeHRM {

    private WebDriver driver;

    @Test(dataProviderClass = LogInOrangeHRMDataProvider.class, dataProvider = "login-provider")
    public void signIn (String username, String password, boolean success) {

        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com");

        driver.findElement(By.id("txtUsername")).sendKeys(username);
        driver.findElement(By.id("txtPassword")).sendKeys(password);
        driver.findElement(By.id("btnLogin")).click();

        System.out.println("Sign In Credentials: " + "\n" +
                        " Username = " + username + "\n" +
                        " Password = " + password + "\n" +
                        " Successful Sign In = " + success + "\n");

        String actualResult = driver.findElement(By.id("welcome")).getText();
        String expectedResult = "Welcome Admin";
        Assert.assertEquals(actualResult, expectedResult, "The Actual & Expected Results Do Not Match");

        driver.quit();

    }

}
