package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PersonalAccountPage {

    @FindBy(how = How.XPATH, using = ".//a[contains(text(),'История заказов')]")
    private SelenideElement ordersHistory;

    @FindBy(how = How.XPATH, using = ".//p[contains(text(),'В этом разделе вы можете изменить свои персональны')]")
    private SelenideElement personalAccountMessage;

    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement saveAccountButton;

    @FindBy(how = How.XPATH, using = ".//button[contains(text(),'Выход')]")
    private SelenideElement exitButton;

    @FindBy(how = How.XPATH, using = ".//a[contains(text(),'Профиль')]")
    private SelenideElement profileTitle;

    @Step("Выйти из аккаунта")
    public PersonalAccountPage exitFromAccount() {
        exitButton.shouldBe(Condition.exist).click();
        return this;
    }

    @Step("Взять текст с заглавия личного кабинета")
    public boolean getTextFromPersonalProfile() {
        profileTitle.shouldHave(Condition.exactText("Профиль"));
        return true;
    }

}
