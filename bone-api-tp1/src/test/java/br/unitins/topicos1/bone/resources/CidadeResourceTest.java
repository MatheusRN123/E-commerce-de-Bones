package br.unitins.topicos1.bone.resources;

import br.unitins.topicos1.bone.dto.CidadeDTO;
import br.unitins.topicos1.bone.dto.CidadeDTOResponse;
import br.unitins.topicos1.bone.resource.CidadeResource;
import br.unitins.topicos1.bone.service.CidadeService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
@TestSecurity(authorizationEnabled = false)
class CidadeResourceTest {

    @Inject
    CidadeResource cidadeResource;

    @InjectMock
    CidadeService cidadeService;

    @Test
    void testBuscarTodos() {
        CidadeDTOResponse dto1 = mock(CidadeDTOResponse.class);
        CidadeDTOResponse dto2 = mock(CidadeDTOResponse.class);
        when(cidadeService.findAll()).thenReturn(List.of(dto1, dto2));

        Response response = cidadeResource.buscarTodos();

        assertEquals(200, response.getStatus());
        verify(cidadeService).findAll();
    }

    @Test
    void testBuscarPorIdExistente() {
        Long id = 1L;
        CidadeDTOResponse dto = mock(CidadeDTOResponse.class);
        when(cidadeService.findById(id)).thenReturn(dto);

        Response response = cidadeResource.buscarPorId(id);

        assertEquals(200, response.getStatus());
        assertEquals(dto, response.getEntity());
        verify(cidadeService).findById(id);
    }

    @Test
    void testBuscarPorIdInexistente() {
        Long id = 999L;
        when(cidadeService.findById(id)).thenReturn(null);

        Response response = cidadeResource.buscarPorId(id);

        assertEquals(404, response.getStatus());
        verify(cidadeService).findById(id);
    }

    @Test
    void testBuscarPorNome() {
        String nome = "São Paulo";
        CidadeDTOResponse dto = mock(CidadeDTOResponse.class);
        when(cidadeService.findByNome(nome)).thenReturn(List.of(dto));

        Response response = cidadeResource.buscarPorNome(nome);

        assertEquals(200, response.getStatus());
        verify(cidadeService).findByNome(nome);
    }

    @Test
    void testIncluirCidade() {
        CidadeDTO dto = new CidadeDTO("Cidade Teste", 1L);
        CidadeDTOResponse dtoResponse = mock(CidadeDTOResponse.class);

        when(cidadeService.create(dto)).thenReturn(dtoResponse);

        Response response = cidadeResource.incluirCidade(dto);

        assertEquals(201, response.getStatus());
        assertEquals(dtoResponse, response.getEntity());
        verify(cidadeService).create(dto);
    }

    @Test
    void testAlterarCidade() {
        Long id = 1L;
        CidadeDTO dto = new CidadeDTO("Cidade Atualizada", 1L);

        doNothing().when(cidadeService).update(id, dto);

        Response response = cidadeResource.alterarCidade(id, dto);

        assertEquals(204, response.getStatus());
        verify(cidadeService).update(id, dto);
    }

    @Test
    void testDeletarCidade() {
        Long id = 1L;

        doNothing().when(cidadeService).delete(id);

        Response response = cidadeResource.deletarCidade(id);

        assertEquals(204, response.getStatus());
        verify(cidadeService).delete(id);
    }
}
