import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Epic;
import pageobjects.MainPage;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;

@DisplayName("Chrome")
@Story("Тесты на работу с конструктором")
public class ConstructorTests extends BaseChromeTest{

    private MainPage mainPage;

    @Before
    @DisplayName("Подготовка данных")
    public void setUp() {
        mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
    }

    @After
    @DisplayName("Очистка данных")
    public void tearDown() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Тест перехода к разделу начинок")
    public void goToTheFillingChapterTest() {
        assertTrue(mainPage.goToFillingChapter());
    }

    @Test
    @DisplayName("Тест перехода к разделу соусов")
    public void goToTheSauceChapterTest() {
        assertTrue(mainPage.goToSauceChapter());
    }

    @Test
    @DisplayName("Тест перехода к разделу булок")
    public void goToTheBreadChapterTest() {
        assertTrue(mainPage.goToBreadChapter());
    }

}
