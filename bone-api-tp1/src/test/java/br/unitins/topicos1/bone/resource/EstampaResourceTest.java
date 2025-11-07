package br.unitins.topicos1.bone.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.anything;

import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class EstampaResourceTest {

    @Test
    public void testFindAll() {
        given()
            .when().get("/estampas")
            .then()
            .statusCode(200)
            .body(anything());
    }

    @Test
    public void testFindByNome() {
        given()
            .pathParam("nome", "Floral")
            .when().get("/estampas/find/{nome}")
            .then()
            .statusCode(anyOf(is(200), is(204)));
    }

    @Test
    public void testFindById() {
        given()
            .pathParam("id", 1)
            .when().get("/estampas/{id}")
            .then()
            .statusCode(anyOf(is(200), is(404)));
    }
}
