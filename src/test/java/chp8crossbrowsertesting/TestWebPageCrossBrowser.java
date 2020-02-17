package chp8crossbrowsertesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//Class must be run through the XML file, cannot be executed from class

public class TestWebPageCrossBrowser {

    private WebDriver driver;

    @Test
    @Parameters ({"URL", "BrowserType"})
    public void verifyWebPage(String url, String browserType) {

        if (browserType.equalsIgnoreCase("Internet Explorer"))
        {
            System.setProperty("webdriver.ie.driver", "resources/IEDriverServer.exe");
            //IE security settings - all zones must together have Protected Mode checked or unchecked
            driver = new InternetExplorerDriver();
        }
        else if (browserType.equalsIgnoreCase("Firefox"))
        {
            System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        else if (browserType.equalsIgnoreCase("Chrome"))
        {
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
            driver = new ChromeDriver();
        }

        driver.get(url);

        System.out.println("\n" + "Open " + browserType);
        System.out.println("  " + driver.getTitle());
        System.out.println("Close " + browserType + "\n");

        driver.quit();
    }
}
