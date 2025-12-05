package br.unitins.topicos1.bone.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.anyOf;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.bone.dto.BoletoDTO;
import br.unitins.topicos1.bone.dto.CartaoDTO;
import br.unitins.topicos1.bone.dto.PixDTO;
import br.unitins.topicos1.bone.service.JwtService;

import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestSecurity(authorizationEnabled = false)
public class PagamentoResourceTest {


    @Inject
    JwtService jwtService;

    @Test
    public void testBuscarTodos() {
        given()
        .when().get("/pagamentos")
        .then()
            .statusCode(200)
            .body(anything());
    }

    @Test
    public void testBuscarPorId() {
        given()
        .when().get("/pagamentos/1")
        .then()
            .statusCode(anyOf(is(200), is(404)));
    }

    @Test
    public void testIncluirPix() {
        PixDTO dto = new PixDTO("chavepix@example.com", "EMAIL");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
        .when().post("/pagamentos/pix/1")
        .then()
            .statusCode(anyOf(is(201), is(200)));
    }

    @Test
    public void testIncluirBoleto() {
        BoletoDTO dto = new BoletoDTO("12345678901234567890", LocalDate.now().plusDays(7));

        given()
            .contentType(ContentType.JSON)
            .body(dto)
        .when().post("/pagamentos/boleto/1")
        .then()
            .statusCode(anyOf(is(201), is(200)));
    }

    @Test
    public void testIncluirCartao() {
        CartaoDTO dto = new CartaoDTO("Titular Teste", "1234567890123456", "12/25", "123");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
        .when().post("/pagamentos/cartao/1")
        .then()
            .statusCode(anyOf(is(201), is(200)));
    }

    @Test
    public void testAlterarPix() {
        PixDTO dto = new PixDTO("novachave@example.com", "EMAIL");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
        .when().put("/pagamentos/pix/1")
        .then()
            .statusCode(anyOf(is(204), is(404)));
    }

    @Test
    public void testAlterarBoleto() {
        BoletoDTO dto = new BoletoDTO("09876543210987654321", LocalDate.now().plusDays(10));

        given()
            .contentType(ContentType.JSON)
            .body(dto)
        .when().put("/pagamentos/boleto/1")
        .then()
            .statusCode(anyOf(is(204), is(404)));
    }

    @Test
    public void testAlterarCartao() {
        CartaoDTO dto = new CartaoDTO("Titular Atualizado", "6543210987654321", "01/26", "321");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
        .when().put("/pagamentos/cartao/1")
        .then()
            .statusCode(anyOf(is(204), is(404)));
    }

    @Test
    public void testDeletarPagamento() {
        given()
        .when().delete("/pagamentos/3")
        .then()
            .statusCode(anyOf(is(204), is(404)));
    }
}
