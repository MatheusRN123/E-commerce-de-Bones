package br.unitins.topicos1.bone.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.bone.dto.BoneDTO;
import br.unitins.topicos1.bone.dto.EstoqueDTO;
import br.unitins.topicos1.bone.model.Bordado;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;

@QuarkusTest
@TestSecurity(user = "admin", roles = {"ADM"})
public class EstoqueResourceTest {

    /**
     * Cria um Boné (com Estoque por composição)
     * e retorna o ID do Estoque criado
     */
    private Long criarEstoque() {
        BoneDTO boneDTO = new BoneDTO(
            "Boné Teste",
            "Preto",
            1L,
            "Reta",
            5.5f,
            10.0f,
            "58cm",
            Bordado.COM_BORDADO,
            1L,
            1L,
            10,
            List.of()
        );
    
        var response =
            given()
                .contentType(ContentType.JSON)
                .body(boneDTO)
            .when()
                .post("/bones")
            .then()
                .log().all()
                .statusCode(anyOf(is(201), is(400)))
                .extract();
    
        // Se criou, retorna o estoque
        if (response.statusCode() == 201) {
            return response.path("estoque.id");
        }
    
        // Se não criou, o teste não pode continuar
        throw new RuntimeException("Não foi possível criar     estoque para o teste");
    }


    @Test
    public void testVerificarDisponibilidadeSucesso() {
        Long idEstoque = criarEstoque();

        given()
            .pathParam("id", idEstoque)
        .when()
            .get("/estoques/{id}/disponibilidade")
        .then()
            .statusCode(200)
            .body(is(true));
    }

    @Test
    public void testAtualizarQuantidade() {
        Long idEstoque = criarEstoque();

        EstoqueDTO dto = new EstoqueDTO(30, LocalDate.now());

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .pathParam("id", idEstoque)
        .when()
            .put("/estoques/{id}/quantidade")
        .then()
            .statusCode(204);

        // Validação indireta via disponibilidade
        given()
            .pathParam("id", idEstoque)
        .when()
            .get("/estoques/{id}/disponibilidade")
        .then()
            .statusCode(200)
            .body(is(true));
    }

    @Test
    public void testAdicionarQuantidade() {
        Long idEstoque = criarEstoque();

        EstoqueDTO dto = new EstoqueDTO(5, LocalDate.now());

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .pathParam("id", idEstoque)
        .when()
            .put("/estoques/{id}/adicionar")
        .then()
            .statusCode(204);

        // Validação indireta
        given()
            .pathParam("id", idEstoque)
        .when()
            .get("/estoques/{id}/disponibilidade")
        .then()
            .statusCode(200)
            .body(is(true));
    }
}
