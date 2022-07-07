package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordPage {

    @FindBy(how = How.LINK_TEXT, using = "Войти")
    private SelenideElement enterLinkButton;

    @Step("Проверка перехода на страницу авторизации")
    public ForgotPasswordPage goToTheEnterAccountButton() {
        enterLinkButton.shouldHave(Condition.exactText("Войти")).click();
        return this;
    }
}
