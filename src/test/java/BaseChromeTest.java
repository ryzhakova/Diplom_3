import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;

import org.junit.jupiter.api.BeforeAll;


public class BaseChromeTest {

    @Before
    public static void startUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = Browsers.CHROME;
        Configuration.startMaximized = true;
    }
}
