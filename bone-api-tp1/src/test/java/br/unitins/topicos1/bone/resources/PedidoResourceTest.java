package br.unitins.topicos1.bone.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.bone.dto.PedidoDTO;
import br.unitins.topicos1.bone.dto.ItemPedidoDTO;
import br.unitins.topicos1.bone.dto.PagamentoDTO;
import br.unitins.topicos1.bone.dto.PixDTO;
import br.unitins.topicos1.bone.service.JwtService;

import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestSecurity(authorizationEnabled = false)
public class PedidoResourceTest {


    @Inject
    JwtService jwtService;


    @Test
    public void testBuscarTodos() {
        given()
        .when().get("/pedidos")
        .then()
            .statusCode(200)
            .body(anything());
    }

    @Test
    public void testBuscarPorId() {
        given()
        .when().get("/pedidos/1")
        .then()
            .statusCode(anyOf(is(200), is(404)));
    }

    @Test
    public void testIncluirPedido() {
        ItemPedidoDTO item = new ItemPedidoDTO(1L, 2, 50.0);
        PagamentoDTO pagamento = new PagamentoDTO("PIX", new PixDTO("chavepix@example.com", "EMAIL"), null, null);

        PedidoDTO dto = new PedidoDTO(
            1L,          // idUsuario
            1L,          // idEndereco
            List.of(item),
            pagamento
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
        .when().post("/pedidos")
        .then()
            .statusCode(anyOf(is(201), is(200)));
    }

    @Test
    public void testAlterarPedido() {
        ItemPedidoDTO item = new ItemPedidoDTO(1L, 3, 55.0);
        PagamentoDTO pagamento = new PagamentoDTO("PIX", new PixDTO("novachave@example.com", "EMAIL"), null, null);

        PedidoDTO dto = new PedidoDTO(
            1L,
            1L,
            List.of(item),
            pagamento
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
        .when().put("/pedidos/1")
        .then()
            .statusCode(anyOf(is(204), is(404)));
    }

    @Test
    public void testDeletarPedido() {
        given()
        .when().delete("/pedidos/3")
        .then()
            .statusCode(anyOf(is(204), is(404)));
    }
}
