package br.unitins.topicos1.bone.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.anyOf;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import br.unitins.topicos1.bone.dto.ModeloDTO;
import br.unitins.topicos1.bone.service.JwtService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
@TestInstance(Lifecycle.PER_CLASS)
public class ModeloResourceTest {


    @Inject
    JwtService jwtService;

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
            .statusCode(anyOf(is(200), is(204), is(404)));
    }

    @Test
    public void testDelete() {
        given()
        .when().delete("/modelos/3")
        .then()
            .statusCode(anyOf(is(204), is(200), is(404)));
    }
}
