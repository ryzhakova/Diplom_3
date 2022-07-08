import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.MainPage;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.driver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static org.junit.Assert.assertTrue;

@DisplayName("Яндекс браузер")
@Story("Тесты на работу с конструктором в Яндексе")
public class YAConstructorTests extends BaseYATest{
    private MainPage mainPage;

    @Before
    @Step("Общая для selenium тестов подготовка данных")
    public void setUp() {
        mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
    }

    @After
    public void tearDown(){
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Тест перехода к разделу начинок (Yandex)")
    public void goToTheFillingChapterTestYandex() {
        assertTrue(mainPage.goToFillingChapter());
    }

    @Test
    @DisplayName("Тест перехода к разделу соусов (Yandex)")
    public void goToTheSauceChapterTestYandex() {
        assertTrue(mainPage.goToSauceChapter());
    }

    @Test
    @DisplayName("Тест перехода к разделу булок (Yandex)")
    public void goToTheBreadChapterTestYandex() {
        assertTrue(mainPage.goToBreadChapter());
    }

}
