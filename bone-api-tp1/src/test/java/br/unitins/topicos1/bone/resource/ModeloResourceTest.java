package br.unitins.topicos1.bone.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import br.unitins.topicos1.bone.dto.ModeloDTO;

@QuarkusTest
public class ModeloResourceTest {

    @Test
    public void testFindAll() {
        given()
            .when().get("/modelos")
            .then()
            .statusCode(200)
            .body("$.size()", greaterThan(0));
    }

    @Test
    public void testFindByNome() {
        given()
            .pathParam("nome", "Nike")
            .when().get("/modelos/find/{nome}")
            .then()
            .statusCode(anyOf(is(200), is(204)));
    }

    @Test
    public void testFindById() {
        given()
            .when().get("/modelos/1")
            .then()
            .statusCode(200)
            .body("id", equalTo(1));
    }

    @Test
    public void testCreate() {
        ModeloDTO dto = new ModeloDTO("Vintage", "Retrô", "Casual");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/modelos")
            .then()
            .statusCode(201)
            .body("nome", equalTo("Vintage"));
    }

    @Test
    public void testUpdate() {
        ModeloDTO dto = new ModeloDTO("Challenge Atualizado", "Streetwear", "Casual");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().put("/modelos/1")
            .then()
            .statusCode(204);
    }

    @Test
    public void testDelete() {
        given()
            .when().delete("/modelos/3")
            .then()
            .statusCode(anyOf(is(204), is(200)));
    }
}
