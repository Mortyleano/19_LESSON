package api.authtorization;

import data.AuthorizationData;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static specs.AuthorizationUserSpec.authorizationRequestSpec;
import static specs.AuthorizationUserSpec.authorizationResponseSpec;

public class AuthorizationApi {

    public Response getAuthorizationResponse() {
        AuthorizationData authData = new AuthorizationData();

        return given(authorizationRequestSpec)
                .body(authData)
                .when()
                .post()
                .then()
                .spec(authorizationResponseSpec)
                .extract().response();
    }
}