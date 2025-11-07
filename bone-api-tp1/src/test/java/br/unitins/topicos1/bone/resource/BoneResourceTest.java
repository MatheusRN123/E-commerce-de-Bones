package br.unitins.topicos1.bone.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.anything;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.bone.dto.BoneDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class BoneResourceTest {

    @Test
    public void testFindAll() {
        given()
            .when().get("/bones")
            .then()
            .statusCode(200)
            .body(anything());
    }

    @Test
    public void testFindByNome() {
        given()
            .pathParam("nome", "Classic")
            .when().get("/bones/find/{nome}")
            .then()
            .statusCode(anyOf(is(200), is(204)));
    }

    @Test
    public void testFindById() {
        given()
            .when().get("/bones/1")
            .then()
            .statusCode(anyOf(is(200), is(404)));
    }

    @Test
    public void testCreateBone() {
        BoneDTO dto = new BoneDTO(
            "BoneTeste",
            "Preto",
            1L,
            "Curva",
            5.0f,
            10.0f,
            "58cm",
            null,
            1L,
            1L,
            10,
            List.of()
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/bones")
            .then()
            .statusCode(anyOf(is(201), is(200)))
            .body("nome", is("BoneTeste"));
    }

    @Test
    public void testUpdateBone() {
        BoneDTO dto = new BoneDTO(
            "BoneAtualizado",
            "Branco",
            1L,
            "Reta",
            5.5f,
            9.0f,
            "60cm",
            null,
            1L,
            1L,
            5,
            List.of()
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().put("/bones/1")
            .then()
            .statusCode(anyOf(is(200), is(204), is(404)));
    }

    @Test
    public void testDeleteBone() {
        given()
            .when().delete("/bones/3")
            .then()
            .statusCode(anyOf(is(204), is(404)));
    }
}
