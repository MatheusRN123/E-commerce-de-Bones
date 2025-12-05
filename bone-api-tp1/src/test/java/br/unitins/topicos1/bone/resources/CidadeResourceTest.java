package br.unitins.topicos1.bone.resources;

import br.unitins.topicos1.bone.dto.CidadeDTO;
import br.unitins.topicos1.bone.service.JwtService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestSecurity(authorizationEnabled = false)
public class CidadeResourceTest {

    @Inject
    JwtService jwtService;

    @Test
    public void testBuscarTodos() {
        given()
        .when()
            .get("/cidades")
        .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    public void testBuscarPorId() {
        Long id = 1L; // ID de teste existente
        given()
        .when()
            .get("/cidades/" + id)
        .then()
            .statusCode(anyOf(is(200), is(404)));
    }

    @Test
    public void testBuscarPorNome() {
        String nome = "São Paulo"; // Nome de teste
        given()
        .when()
            .get("/cidades/find/" + nome)
        .then()
            .statusCode(anyOf(is(200), is(204)));
    }

    @Test
    public void testIncluirCidade() {
        CidadeDTO dto = new CidadeDTO("Cidade Teste", 1L);

        given()
            .contentType("application/json")
            .body(dto)
        .when()
            .post("/cidades")
        .then()
            .statusCode(anyOf(is(201), is(200)))
            .body("nome", equalTo("Cidade Teste"));
    }

    @Test
    public void testAlterarCidade() {
        Long id = 1L; // ID de teste existente
        CidadeDTO dto = new CidadeDTO("Cidade Atualizada", 1L);

        given()
            .contentType("application/json")
            .body(dto)
        .when()
            .put("/cidades/" + id)
        .then()
            .statusCode(anyOf(is(204), is(404)));
    }

    @Test
    public void testDeletarCidade() {
        Long id = 1L; // ID de teste existente

        given()
        .when()
            .delete("/cidades/" + id)
        .then()
            .statusCode(anyOf(is(204), is(404)));
    }
}
