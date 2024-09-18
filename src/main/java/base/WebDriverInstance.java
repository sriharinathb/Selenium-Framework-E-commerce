package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverInstance {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            try {
                driver.set(createDriver());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return driver.get();
    }

    public static WebDriver createDriver() throws IOException {
        WebDriver driver = null;

        Properties prop = new Properties();
        FileInputStream data = new FileInputStream(
                System.getProperty("user.dir") + "\\src\\main\\java\\resources\\config.properties");
        prop.load(data);

        if (prop.getProperty("browser").equals("chrome")) {
           // ChromeOptions options = new ChromeOptions();
          //  driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),options);
        	driver=new ChromeDriver();
        } else if (prop.getProperty("browser").equals("firefox")){
            FirefoxOptions options=new FirefoxOptions();
            driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),options);
        }
        else if(prop.getProperty("browser").equals("edge")){
            EdgeOptions options=new EdgeOptions();
            driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),options);
        }
        else{
            System.exit(0);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }

    public static void cleanupDriver() {
        driver.get().quit();
        driver.remove();
    }

}
