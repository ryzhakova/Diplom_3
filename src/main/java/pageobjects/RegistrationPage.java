package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationPage {

    @FindBy(how = How.XPATH, using = ".//p[contains(text(),'Некорректный пароль')]")
    private SelenideElement registrationErrorText;

    @FindBy(how = How.XPATH, using = ".//a[contains(text(),'Войти')]")
    private SelenideElement enterToAccountButtonOnTheRegistrationForm;

    @FindBy(how = How.XPATH, using = ".//button[contains(text(),'Зарегистрироваться')]")
    private SelenideElement registrationButton;

    @FindBy(how = How.NAME, using = "name")
    private SelenideElement nameBox;

    @FindBy(how = How.XPATH, using = ".//label[text() ='Email']/following-sibling::input")
    private SelenideElement emailBox;

    @FindBy(how = How.NAME, using = "Пароль")
    private SelenideElement passwordBox;

    @Step("Регистрация нового пользователя")
    public void registrationNewUser(String nameForRegistration, String emailForRegistration, String passwordForRegistration) {
        nameBox.shouldBe(Condition.exist).setValue(nameForRegistration);
        emailBox.shouldBe(Condition.exist).setValue(emailForRegistration);
        passwordBox.shouldBe(Condition.exist).setValue(passwordForRegistration);
        registrationButton.click();
    }

    @Step("Взять текст от ошибочной регистрации")
    public boolean showRegistrationErrorText() {
        registrationErrorText.shouldHave(Condition.exactText("Некорректный пароль"));
        return true;
    }

    @Step("Нажать на кнопку входа в аккаунт")
    public RegistrationPage clickToEnterToAccountButton() {
        enterToAccountButtonOnTheRegistrationForm.shouldBe(Condition.visible).click();
        return this;
    }

}
