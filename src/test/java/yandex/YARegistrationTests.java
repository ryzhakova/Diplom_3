import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
import org.openqa.selenium.chrome.ChromeDriver;
import restClients.UserRestClient;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.driver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static org.junit.Assert.assertTrue;

@DisplayName("Яндекс браузер")
@Story("Тесты на регистрацию в Яндексе")
public class YARegistrationTests extends BaseYATest{
    public String nameForRegistration;
    public String emailForRegistration;
    public String passwordForRegistration;
    private MainPage mainPage;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private User user;

    @Before
    @DisplayName("Создание рандомных данных, вход на сайт")
    public void setUp() {
        nameForRegistration = RandomStringUtils.randomAlphabetic(10);
        emailForRegistration = String.format("%s@%s.ru", RandomStringUtils.randomAlphabetic(6), RandomStringUtils.randomAlphabetic(6)).toLowerCase(Locale.ROOT);
        passwordForRegistration = RandomStringUtils.randomAlphabetic(7);
        user = User.builder()
                .email(emailForRegistration)
                .name(nameForRegistration)
                .password(passwordForRegistration)
                .build();

        mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        mainPage.clickToTheEnterButton();
    }

    @After
    @DisplayName("Очистка данных")
    public void tearDown() {
        UserRestClient.deleteUser(user.getToken());
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Тест успешной регистрации пользователя (Yandex)")
    public void successfulRegistrationTestYandex() {
        loginPage = open("https://stellarburgers.nomoreparties.site/login", LoginPage.class);
        loginPage.clickToTheRegistrationButton();
        registrationPage = open("https://stellarburgers.nomoreparties.site/register", RegistrationPage.class);
        registrationPage.registrationNewUser(nameForRegistration, emailForRegistration, passwordForRegistration);

        loginPage = open("https://stellarburgers.nomoreparties.site/login", LoginPage.class);
        user.setToken(UserRestClient.authorizationUser(user).extract().jsonPath().get("accessToken"));

        assertTrue(loginPage.getTitleFromTheLoginPage());
    }

    @Test
    @DisplayName("Тест провальной регистрации (из-за пароля < 6 символов) (Yandex)")
    public void unSuccessfulRegistrationTestYandex() {
        passwordForRegistration = RandomStringUtils.randomAlphabetic(5);
        loginPage = open("https://stellarburgers.nomoreparties.site/login", LoginPage.class);
        loginPage.clickToTheRegistrationButton();
        registrationPage = open("https://stellarburgers.nomoreparties.site/register", RegistrationPage.class);
        registrationPage.registrationNewUser(nameForRegistration, emailForRegistration, passwordForRegistration);

        assertTrue(registrationPage.showRegistrationErrorText());
    }
}
