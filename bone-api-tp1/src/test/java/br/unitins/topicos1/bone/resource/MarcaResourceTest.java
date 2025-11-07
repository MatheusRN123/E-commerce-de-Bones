package br.unitins.topicos1.bone.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import br.unitins.topicos1.bone.dto.MarcaDTO;

@QuarkusTest
public class MarcaResourceTest {

    @Test
    public void testFindAll() {
        given()
            .when().get("/marcas")
            .then()
            .statusCode(200)
            .body("$.size()", greaterThan(0));
    }

    @Test
    public void testFindByNome() {
        given()
            .pathParam("nome", "Nike")
            .when().get("/marcas/find/{nome}")
            .then()
            .statusCode(anyOf(is(200), is(204)));
    }

    @Test
    public void testFindById() {
        given()
            .when().get("/marcas/1")
            .then()
            .statusCode(200)
            .body("id", equalTo(1));
    }

    @Test
    public void testCreate() {
        MarcaDTO dto = new MarcaDTO("Puma");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/marcas")
            .then()
            .statusCode(201)
            .body("nome", equalTo("Puma"));
    }

    @Test
    public void testUpdate() {
        MarcaDTO dto = new MarcaDTO("Nike Atualizada");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().put("/marcas/1")
            .then()
            .statusCode(204);
    }

    @Test
    public void testDelete() {
        given()
            .when().delete("/marcas/3")
            .then()
            .statusCode(anyOf(is(204), is(200)));
    }
}
