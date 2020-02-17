package chp4priority;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Highlighter;

public class PriorityExecutionOrder {

    private WebDriver driver;

    @BeforeClass
    public void setUp() throws Exception {

        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("http://automationpractice.com/index.php");
    }

    @Test (priority = 1)
    public void signIn() {

        WebElement headerSignIn = driver.findElement(By.xpath("//div[@class='header_user_info']/a"));
        Highlighter.highlightElement(driver, headerSignIn);
        headerSignIn.click();

        WebElement emailAddress = driver.findElement(By.id("email"));
        emailAddress.sendKeys("TestNG@Framework.com");

        WebElement password = driver.findElement(By.id("passwd"));
        password.sendKeys("TestNG1234");

        WebElement buttonSignIn = driver.findElement(By.id("SubmitLogin"));
        buttonSignIn.click();

        System.out.println("1. Sign In");
    }

    @Test (priority = 2)
    public void searchTShirt() {

        WebElement menuLinkTshirtSearch = driver.findElement(By.xpath("//div[@id='block_top_menu']/ul/li/a[text()='T-shirts']"));
        Highlighter.highlightElement(driver, menuLinkTshirtSearch);
        menuLinkTshirtSearch.click();

        WebElement searchBox = driver.findElement(By.id("search_query_top"));
        searchBox.sendKeys("Blue");

        WebElement buttonSearch = driver.findElement(By.xpath("//*[@id='searchbox']/button"));
        Highlighter.highlightElement(driver, buttonSearch);
        buttonSearch.click();

        System.out.println("2. Search for T-Shirt");
    }

    @Test (priority = 3)
    public void signOut() {

        WebElement linkSignOut = driver.findElement(By.className("logout"));
        Highlighter.highlightElement(driver, linkSignOut);
        linkSignOut.click();

        System.out.println("3. Sign Out");
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }
}
