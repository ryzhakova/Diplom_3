package restClients;

import entity.User;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.http.Header;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

@Story("Создание и авторизация пользователя")
public class UserRestClient extends BasicRestClient {
    public static final String REGISTER_PATH = "/api/auth/register";
    public static final String LOGIN_PATH = "/api/auth/login";
    public static final String USER_DATA_PATH = "/api/auth/user";

    @Step("Создание пользователя")
    public static ValidatableResponse createUser(User user) {
        return given()
                .spec(getBaseSpec())
                .and()
                .body(user)
                .when()
                .post(REGISTER_PATH)
                .then();
    }

    @Step("Регистрация пользователя")
    public static ValidatableResponse authorizationUser(User user) {
        return given()
                .spec(getBaseSpec())
                .and()
                .body(user)
                .when()
                .post(LOGIN_PATH)
                .then();
    }

    @Step("Удаление пользователя")
    public static ValidatableResponse deleteUser(String token) {
        return given()
                .spec(getBaseSpec())
                .and()
                .header(new Header("Authorization", token))
                .when()
                .delete(USER_DATA_PATH)
                .then();
    }
}
