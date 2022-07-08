import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.PersonalAccountPage;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;

@DisplayName("Chrome")
@Story("Тесты в личном кабинете")
public class PersonalAccountTests extends BaseChromeTest{

    private MainPage mainPage;
    private PersonalAccountPage personalAccountPage;
    private LoginPage loginPage;

    @Before
    @DisplayName("Создание логопассов, авторизация")
    public void setUp() {
        String email = "kate@ya.ru";
        String password = "qweasd";

        mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        mainPage.clickToTheEnterButton();

        loginPage = open("https://stellarburgers.nomoreparties.site/login", LoginPage.class);
        loginPage.loginNewUser(email, password);

        mainPage.goToThePersonalAccount();
    }

    @After
    @DisplayName("Очистка данных")
    public void tearDown() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Тест перехода к разделу конструктора")
    public void goToConstructorTest() {
        assertTrue(mainPage.goToTheConstructor());
    }

    @Test
    @DisplayName("Тест перехода через логотип")
    public void goToStellarBurgerLogoTest() {
        assertTrue(mainPage.goToTheLogoButton());
    }

    @Test
    @DisplayName("Тест выхода из аккаунта")
    public void exitFromAccountTest() {
        personalAccountPage = open("https://stellarburgers.nomoreparties.site/account", PersonalAccountPage.class);
        personalAccountPage.exitFromAccount();
        assertTrue(loginPage.getTitleFromTheLoginPage());
    }

    @Test
    @DisplayName("Тест входа в аккаунт")
    public void enterToAccountTest() {
        personalAccountPage = open("https://stellarburgers.nomoreparties.site/account", PersonalAccountPage.class);
        assertTrue(personalAccountPage.getTextFromPersonalProfile());
    }

}
