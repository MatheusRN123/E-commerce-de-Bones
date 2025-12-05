package br.unitins.topicos1.bone.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.anyOf;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import br.unitins.topicos1.bone.dto.MaterialDTO;
import br.unitins.topicos1.bone.service.JwtService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
@TestInstance(Lifecycle.PER_CLASS)
public class MaterialResourceTest {


    @Inject
    JwtService jwtService;


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
            .statusCode(anyOf(is(200), is(204), is(404)));
    }

    @Test
    public void testDelete() {
        given()
        .when().delete("/materiais/3")
        .then()
            .statusCode(anyOf(is(204), is(200), is(404)));
    }
}
