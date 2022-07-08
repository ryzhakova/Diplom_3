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

@DisplayName("Яндекс браузер")
@Story("Тесты в личном кабинете в Яндексе")
public class YAPersonalAccountTests extends BaseYATest{

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
    @DisplayName("Очиcтка данных")
    public void tearDown() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Тест перехода к разделу конструктора (Yandex)")
    public void goToConstructorTestYandex() {
        assertTrue(mainPage.goToTheConstructor());
    }

    @Test
    @DisplayName("Тест перехода через логотип (Yandex)")
    public void goToStellarBurgerLogoTest() {
        assertTrue(mainPage.goToTheLogoButton());
    }

    @Test
    @DisplayName("Тест выхода из аккаунта (Yandex)")
    public void exitFromAccountTestYandex() {
        personalAccountPage = open("https://stellarburgers.nomoreparties.site/account", PersonalAccountPage.class);
        personalAccountPage.exitFromAccount();
        assertTrue(loginPage.getTitleFromTheLoginPage());
    }

    @Test
    @DisplayName("Тест входа в аккаунт (Yandex)")
    public void enterToAccountTestYandex() {
        personalAccountPage = open("https://stellarburgers.nomoreparties.site/account", PersonalAccountPage.class);
        assertTrue(personalAccountPage.getTextFromPersonalProfile());
    }

}
