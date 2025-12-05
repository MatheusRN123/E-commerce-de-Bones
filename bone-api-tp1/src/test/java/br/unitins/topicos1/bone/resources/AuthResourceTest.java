package br.unitins.topicos1.bone.resources;

import br.unitins.topicos1.bone.dto.AuthDTO;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestSecurity(authorizationEnabled = false)
public class AuthResourceTest {

    @Test
    public void testLoginSucesso() {
        AuthDTO authDTO = new AuthDTO("matheus", "12345");

        // Faz login e extrai o token do header "Authorization"
        String token = given()
            .contentType("application/json")
            .body(authDTO)
        .when()
            .post("/auth")
        .then()
            .statusCode(200)
            .header("Authorization", notNullValue())
            .extract()
            .header("Authorization");

        System.out.println("Token gerado: " + token);
    }
}
