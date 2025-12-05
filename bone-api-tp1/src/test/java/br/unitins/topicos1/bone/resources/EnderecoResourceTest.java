package br.unitins.topicos1.bone.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*; // Import correto para is(), anyOf(), greaterThanOrEqualTo, etc.

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import br.unitins.topicos1.bone.dto.EnderecoDTO;
import br.unitins.topicos1.bone.service.HashService;
import br.unitins.topicos1.bone.service.JwtService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import jakarta.inject.Inject;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EnderecoResourceTest {

    @Inject
    JwtService jwtService;

    @Inject
    HashService hashService;

    @Test
    public void testBuscarTodos() {
        given()
        .when()
            .get("/enderecos")
        .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    public void testBuscarPorCep() {
        given()
            .pathParam("cep", "12345678")
        .when()
            .get("/enderecos/find/{cep}")
        .then()
            .statusCode(anyOf(is(200), is(204)));
    }

    @Test
    public void testBuscarPorId() {
        given()
        .when()
            .get("/enderecos/1")
        .then()
            .statusCode(anyOf(is(200), is(404)));
    }

    @Test
    public void testIncluirEndereco() {
        EnderecoDTO dto = new EnderecoDTO(
            "12345678",
            "Rua Teste",
            "10",
            1L,
            1L
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
        .when()
            .post("/enderecos")
        .then()
            .statusCode(anyOf(is(201), is(200)))
            .body("logradouro", is("Rua Teste"));
    }

    @Test
    public void testAlterarEndereco() {
        EnderecoDTO dto = new EnderecoDTO(
            "87654321",
            "Rua Atualizada",
            "15",
            2L,
            2L
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
        .when()
            .put("/enderecos/1")
        .then()
            .statusCode(anyOf(is(204), is(404)));
    }

    @Test
    public void testDeletarEndereco() {
        given()
        .when()
            .delete("/enderecos/3")
        .then()
            .statusCode(anyOf(is(204), is(404)));
    }
}
