package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

    @FindBy(how = How.XPATH, using = ".//a[contains(text(),'Зарегистрироваться')]")
    private SelenideElement registrationLinkButton;

    @FindBy(how = How.XPATH, using = ".//button[contains(text(),'Войти')]")
    private SelenideElement enterToAccountButton;

    @FindBy(how = How.XPATH, using = ".//a[contains(text(),'Восстановить пароль')]")
    private SelenideElement changePasswordButton;

    @FindBy(how = How.XPATH, using = ".//h2[contains(text(),'Вход')]")
    private SelenideElement loginPageTitle;

    @FindBy(how = How.NAME, using = "name")
    private SelenideElement nameBox;

    @FindBy(how = How.NAME, using = "Пароль")
    private SelenideElement passwordBox;

    @Step("Нажать на кнопку регистрации")
    public LoginPage clickToTheRegistrationButton() {
        registrationLinkButton.click();
        return this;
    }

    @Step("Нажать на кнопку смены пароля")
    public LoginPage clickToTheChangePasswordButton() {
        changePasswordButton.click();
        return this;
    }

    @Step("Получить заглавие со страницы авторизации")
    public boolean getTitleFromTheLoginPage() {
        loginPageTitle.shouldHave(Condition.exactText("Вход"));
        return true;
    }

    @Step("Авторизовать нового пользователя")
    public void loginANewUser(String emailForLogin, String passwordForLogin){
        nameBox.shouldBe(Condition.exist).setValue(emailForLogin);
        passwordBox.shouldBe(Condition.exist).setValue(passwordForLogin);
        enterToAccountButton.click();
    }


}
