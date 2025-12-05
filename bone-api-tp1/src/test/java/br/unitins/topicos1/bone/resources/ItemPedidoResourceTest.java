package br.unitins.topicos1.bone.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.anyOf;

import org.junit.jupiter.api.Test;
import br.unitins.topicos1.bone.dto.ItemPedidoDTO;
import br.unitins.topicos1.bone.service.JwtService;
import io.restassured.http.ContentType;

import jakarta.inject.Inject;

import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestSecurity(authorizationEnabled = false)
public class ItemPedidoResourceTest {

    @Inject
    JwtService jwtService;

    @Test
    public void testBuscarTodos() {
        given()
        .when().get("/itens")
        .then()
            .statusCode(200)
            .body(anything());
    }

    @Test
    public void testBuscarPorId() {
        given()
        .when().get("/itens/1")
        .then()
            .statusCode(anyOf(is(200), is(404)));
    }

    @Test
    public void testIncluirItemPedido() {
        ItemPedidoDTO dto = new ItemPedidoDTO(
            1L, 2, 50.0
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
        .when().post("/itens")
        .then()
            .statusCode(anyOf(is(201), is(200)))
            .body("idBone", is(1));
    }

    @Test
    public void testAlterarItemPedido() {
        ItemPedidoDTO dto = new ItemPedidoDTO(
            1L, 3, 55.0
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
        .when().put("/itens/1")
        .then()
            .statusCode(anyOf(is(204), is(404)));
    }

    @Test
    public void testDeletarItemPedido() {
        given()
        .when().delete("/itens/3")
        .then()
            .statusCode(anyOf(is(204), is(404)));
    }
}
