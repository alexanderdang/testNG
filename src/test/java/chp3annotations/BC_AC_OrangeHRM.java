package chp3annotations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Highlighter;


public class BC_AC_OrangeHRM {

    private WebDriver driver;
    private By menuAdminButton = By.id("menu_admin_viewAdminModule");

    @BeforeClass
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
        driver = new ChromeDriver();
        //driver.manage().window().maximize();

        driver.get("https://opensource-demo.orangehrmlive.com/");
        System.out.println("1. Open Chrome and Application");
    }

    @Test
    public void signIn() {

        WebElement textUsername = driver.findElement(By.id("txtUsername"));
        textUsername.sendKeys("Admin");

        WebElement textPassword = driver.findElement(By.id("txtPassword"));
        textPassword.sendKeys("admin123");

        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        System.out.println("2. Sign In");
    }

    @Test
    public void userSearch() throws InterruptedException {

        //WebDriverWait wait = new WebDriverWait(driver, 10);
        //wait.until(ExpectedConditions.presenceOfElementLocated(menuAdminButton));
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Thread.sleep(3000);

        WebElement menuAdmin = driver.findElement(menuAdminButton);
        menuAdmin.click();

        WebElement textUserName = driver.findElement(By.id("searchSystemUser_userName"));
        textUserName.sendKeys("Admin");

        WebElement buttonSearch = driver.findElement(By.id("searchBtn"));
        buttonSearch.click();

        System.out.println("3. Search For User");
    }

    @Test
    public void userSignOut() throws InterruptedException {
        Thread.sleep(1000);

        WebElement linkWelcome = driver.findElement(By.id("welcome"));
        Highlighter.highlightElement(driver, linkWelcome);
        linkWelcome.click();

        WebElement linkLogout = driver.findElement(By.xpath("//div[@id='welcome-menu']/descendant::a[contains(@href,'logout')]"));
        Highlighter.highlightElement(driver, linkLogout);
        Thread.sleep(1000);
        linkLogout.click();
    }

    @AfterClass
    public void tearDown() {
        System.out.println("5. Close Chrome and Application");
        driver.quit();
    }


}
