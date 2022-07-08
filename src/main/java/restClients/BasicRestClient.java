package restClients;

import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;

@Story("Главная страница сайта")
public abstract class BasicRestClient {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    @DisplayName("Взаимодействие с главной страницей")
    public static RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder().setContentType(JSON).setBaseUri(BASE_URL).build();
    }

}
