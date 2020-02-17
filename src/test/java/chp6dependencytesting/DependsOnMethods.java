package chp6dependencytesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Highlighter;

public class DependsOnMethods {

    private WebDriver driver;
    private By menuAdminButton = By.id("menu_admin_viewAdminModule");

    @Test
    public void test1_SetUpChrome() throws Exception {
        System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
        driver = new ChromeDriver();
        //driver.manage().window().maximize();

        System.out.println("1. Set Up Chrome");
    }

    @Test (dependsOnMethods = "test1_SetUpChrome")
    public void test2_OpenOrangeHRM() {

        driver.get("https://opensource-demo.orangehrmlive.com/");
        System.out.println("2. Open OrangeHRM");
    }

    @Test (dependsOnMethods = "test2_OpenOrangeHRM")
    public void test3_SignIn() {

        WebElement textUsername = driver.findElement(By.id("txtUsername"));
        textUsername.sendKeys("Admin");

        WebElement textPassword = driver.findElement(By.id("txtPassword"));
        textPassword.sendKeys("admin123");

        WebElement buttonLogin = driver.findElement(By.id("btnLogin"));
        buttonLogin.click();

        System.out.println("3. Sign In");
    }

    @Test (dependsOnMethods = "test3_SignIn")
    public void test4_SearchUser() throws InterruptedException {

        //WebDriverWait wait = new WebDriverWait(driver, 10);
        //wait.until(ExpectedConditions.presenceOfElementLocated(menuAdminButton));
        Thread.sleep(1000);

        WebElement menuAdmin = driver.findElement(menuAdminButton);
        menuAdmin.click();

        WebElement textUserName = driver.findElement(By.id("searchSystemUser_userName"));
        textUserName.sendKeys("Admin");

        WebElement buttonSearch = driver.findElement(By.id("searchBtn"));
        buttonSearch.click();

        System.out.println("4. Search For User");
    }

    @Test (dependsOnMethods = {"test2_OpenOrangeHRM", "test3_SignIn"})
    public void test5_SearchEmployee() throws InterruptedException {

        Thread.sleep(1000);

        WebElement menuPIM = driver.findElement(By.xpath("//*[@id=\"menu_pim_viewPimModule\"]/b"));
        Highlighter.highlightElement(driver, menuPIM);
        menuPIM.click();

        driver.findElement(By.id("searchBtn")).click();

        System.out.println("5. Search For Employee");
    }

    @Test (dependsOnMethods = {"test2_OpenOrangeHRM", "test3_SignIn"})
    public void test6_SearchCandidate() throws InterruptedException {

        Thread.sleep(1000);

        WebElement menuRecruitment = driver.findElement(By.xpath("//*[@id=\"menu_recruitment_viewRecruitmentModule\"]/b"));
        Highlighter.highlightElement(driver, menuRecruitment);
        menuRecruitment.click();

        driver.findElement(By.id("btnSrch")).click();

        System.out.println("6. Search For Candidate");
    }

    @Test (dependsOnMethods = {"test2_OpenOrangeHRM", "test3_SignIn"})
    public void test7_SignOut() throws InterruptedException {
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
