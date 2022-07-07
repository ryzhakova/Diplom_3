import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.WebDriverRunner.driver;

public class BaseYATest {

    @BeforeAll
    public static void startUp() {
        // для я.браузера
        System.setProperty("webdriver.chrome.driver","/Users/katebrz/YP/yandexdriver");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Users/katebrz/YP/yandexdriver");
        WebDriver driver =new ChromeDriver();
        WebDriverRunner.setWebDriver(driver);
        Configuration.timeout = 10000;
        WebDriverManager.chromedriver().setup();
    }

    // Нужно для Я.браузера потому что сам он у меня не закрывается
    @AfterAll
    public static void cleanUp(){
        driver().close();
    }
}
