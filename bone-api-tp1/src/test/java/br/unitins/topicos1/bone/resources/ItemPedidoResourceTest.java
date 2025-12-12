package br.unitins.topicos1.bone.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.bone.dto.ItemPedidoDTO;
import io.restassured.http.ContentType;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;

@QuarkusTest
@TestSecurity(authorizationEnabled = false)
public class ItemPedidoResourceTest {

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
            .pathParam("id", 1)
        .when().get("/itens/{id}")
        .then()
            .statusCode(anyOf(is(200), is(404)));
    }

    @Test
    public void testAlterarItemPedido() {
        ItemPedidoDTO dto = new ItemPedidoDTO(
            1L, 3, 55.0
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .pathParam("id", 1)
        .when().put("/itens/{id}")
        .then()
            .statusCode(anyOf(is(204), is(404))); // 204 quando atualizado com sucesso
    }

    @Test
    public void testDeletarItemPedido() {
        given()
            .pathParam("id", 3)
        .when().delete("/itens/{id}")
        .then()
            .statusCode(anyOf(is(204), is(404)));
    }
}
