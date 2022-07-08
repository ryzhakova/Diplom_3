import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

import pageobjects.ForgotPasswordPage;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.RegistrationPage;
import entity.User;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import restClients.UserRestClient;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;

@DisplayName("Яндекс браузер")
@Story("Тесты на авторизацию в Яндексе")
public class YALoginTests extends BaseYATest{

    public String emailForLogin;
    public String passwordForLogin;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private ForgotPasswordPage forgotPasswordPage;
    private String nameForRegistration;
    private User user;

    @Before
    @DisplayName("Создание логопассов, авторизация")
    public void setUp() {
        emailForLogin = String.format("%s@%s.ru", RandomStringUtils.randomAlphabetic(6), RandomStringUtils.randomAlphabetic(6)).toLowerCase(Locale.ROOT);
        passwordForLogin = RandomStringUtils.randomAlphabetic(7);
        nameForRegistration = RandomStringUtils.randomAlphabetic(10);

        user = User.builder()
                .email(emailForLogin)
                .name(nameForRegistration)
                .password(passwordForLogin)
                .build();
        user.setToken(UserRestClient.createUser(user).extract().jsonPath().get("accessToken"));

        mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
    }

    @After
    @DisplayName("Очистка данных")
    public void tearDown() {
        UserRestClient.deleteUser(user.getToken());
    }

    @Test
    @DisplayName("Тест авторизации через кнопку входа (Yandex)")
    public void loginUserWithTheEnterToAccountButtonYandex() {
        mainPage.clickToTheEnterButton();

        loginPage = open("https://stellarburgers.nomoreparties.site/login", LoginPage.class);
        loginPage.loginNewUser(emailForLogin, passwordForLogin);

        assertTrue(mainPage.getTitleFromTheCreateOrderButton());
    }

    @Test
    @DisplayName("Тест авторизации через личный кабинет (Yandex)")
    public void loginUserWithThePersonalAccountButtonYandex() {
        mainPage.goToThePersonalAccount();

        loginPage = open("https://stellarburgers.nomoreparties.site/login", LoginPage.class);
        loginPage.loginNewUser(emailForLogin, passwordForLogin);

        assertTrue(mainPage.getTitleFromTheCreateOrderButton());
    }

    @Test
    @DisplayName("Тест авторизации через окно регистрации (Yandex)")
    public void loginUserWithTheRegistrationFormYandex() {
        mainPage.clickToTheEnterButton();

        loginPage = open("https://stellarburgers.nomoreparties.site/login", LoginPage.class);
        loginPage.clickToTheRegistrationButton();
        registrationPage = open("https://stellarburgers.nomoreparties.site/register", RegistrationPage.class);
        registrationPage.clickToEnterToAccountButton();
        loginPage.loginNewUser(emailForLogin, passwordForLogin);

        assertTrue(mainPage.getTitleFromTheCreateOrderButton());
    }

    @Test
    @DisplayName("Тест авторизации через форму восстановления пароля (Yandex)")
    public void loginUserWithTheChangePasswordButtonYandex() {
        mainPage.clickToTheEnterButton();

        loginPage = open("https://stellarburgers.nomoreparties.site/login", LoginPage.class);
        loginPage.clickToTheChangePasswordButton();

        forgotPasswordPage = open("https://stellarburgers.nomoreparties.site/forgot-password", ForgotPasswordPage.class);
        forgotPasswordPage.goToTheEnterAccountButton();

        loginPage.loginNewUser(emailForLogin, passwordForLogin);

        assertTrue(mainPage.getTitleFromTheCreateOrderButton());
    }

}
