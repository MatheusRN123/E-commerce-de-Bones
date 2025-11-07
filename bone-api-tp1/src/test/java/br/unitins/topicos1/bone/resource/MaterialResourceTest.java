package br.unitins.topicos1.bone.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import br.unitins.topicos1.bone.dto.MaterialDTO;

@QuarkusTest
public class MaterialResourceTest {

    @Test
    public void testFindAll() {
        given()
            .when().get("/materiais")
            .then()
            .statusCode(200)
            .body("$.size()", greaterThan(0));
    }

    @Test
    public void testFindByNome() {
        given()
            .pathParam("nome", "Nike")
            .when().get("/materiais/find/{nome}")
            .then()
            .statusCode(anyOf(is(200), is(204)));
    }

    @Test
    public void testFindById() {
        given()
            .when().get("/materiais/1")
            .then()
            .statusCode(200)
            .body("id", equalTo(1));
    }

    @Test
    public void testCreate() {
        MaterialDTO dto = new MaterialDTO("Linho");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/materiais")
            .then()
            .statusCode(201)
            .body("nome", equalTo("Linho"));
    }

    @Test
    public void testUpdate() {
        MaterialDTO dto = new MaterialDTO("Couro Premium");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().put("/materiais/1")
            .then()
            .statusCode(204);
    }

    @Test
    public void testDelete() {
        given()
            .when().delete("/materiais/3")
            .then()
            .statusCode(anyOf(is(204), is(200)));
    }
}
