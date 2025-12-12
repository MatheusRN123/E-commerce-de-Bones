package br.unitins.topicos1.bone.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.unitins.topicos1.bone.model.EstampaBordada;
import br.unitins.topicos1.bone.repository.EstampaBordadaRepository;
import br.unitins.topicos1.bone.service.JwtService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestSecurity(authorizationEnabled = false)
public class EstampaResourceTest {

    @Inject
    JwtService jwtService;

    @Inject
    EstampaBordadaRepository bordadaRepo;

    private Long idEstampa;

    @BeforeEach
    @Transactional
    public void setup() {
        bordadaRepo.deleteAll();

        EstampaBordada e = new EstampaBordada(
            "Floral",
            "Frente",
            "Estampa floral de teste",
            "Vermelho",
            2
        );
        bordadaRepo.persistAndFlush(e); // persiste e garante   que o ID seja gerado

        // guarda o ID para usar no teste
        this.idEstampa  = e.getId();
    }

@Test
    public void testFindAll() {
        given()
        .when().get("/estampas")
        .then()
            .statusCode(200) // Verifica se o status code é 200 OK
            .body("size()", greaterThan(0)); // Garante que há ao menos uma estampa
    }

    @Test
    public void testFindByNome() {
        given()
        .when().get("/estampas/find/Floral")
        .then()
            .statusCode(200)
            .body("[0].nome", is("Floral"));
    }

    @Test
    public void testFindById() {
        given()
            .pathParam("id", idEstampa)
        .when().get("/estampas/{id}")
        .then()
            .statusCode(anyOf(is(200), is(404)))
            .body("id", equalTo(idEstampa.intValue  ())) // converte para int, pois RestAssured   compara com JSON numbers
            .body("nome", notNullValue());
}
}
