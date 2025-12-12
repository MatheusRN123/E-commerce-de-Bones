package br.unitins.topicos1.bone.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.anyOf;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.bone.dto.MarcaDTO;
import br.unitins.topicos1.bone.service.JwtService;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestSecurity(authorizationEnabled = false)
public class MarcaResourceTest {

    @Inject
    JwtService jwtService;

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
            .pathParam("id", 1)
        .when().get("/marcas/{id}")
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
            .pathParam("id", 1)
        .when().put("/marcas/{id}")
        .then()
            .statusCode(anyOf(is(204), is(404)));
    }

    @Test
    public void testDelete() {
        given()
            .pathParam("id", 3)
        .when().delete("/marcas/{id}")
        .then()
            .statusCode(anyOf(is(204), is(404)));
    }
}
