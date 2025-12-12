package br.unitins.topicos1.bone.resources;

import br.unitins.topicos1.bone.dto.EstadoDTO;
import br.unitins.topicos1.bone.dto.EstadoDTOResponse;
import br.unitins.topicos1.bone.resource.EstadoResource;
import br.unitins.topicos1.bone.service.EstadoService;
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
class EstadoResourceUnitTest {

    @Inject
    EstadoResource estadoResource;

    @InjectMock
    EstadoService estadoService;

    @Test
    void testBuscarTodos() {
        EstadoDTOResponse dto1 = mock(EstadoDTOResponse.class);
        EstadoDTOResponse dto2 = mock(EstadoDTOResponse.class);
        when(estadoService.findAll()).thenReturn(List.of(dto1, dto2));

        Response response = estadoResource.buscarTodos();

        assertEquals(200, response.getStatus());
        verify(estadoService).findAll();
    }

    @Test
    void testBuscarPorIdExistente() {
        Long id = 1L;
        EstadoDTOResponse dto = mock(EstadoDTOResponse.class);
        when(estadoService.findById(id)).thenReturn(dto);

        Response response = estadoResource.buscarPorId(id);

        assertEquals(200, response.getStatus());
        assertEquals(dto, response.getEntity());
        verify(estadoService).findById(id);
    }

    @Test
    void testBuscarPorIdInexistente() {
        Long id = 999L;
        when(estadoService.findById(id)).thenReturn(null);

        Response response = estadoResource.buscarPorId(id);

        assertEquals(404, response.getStatus());
        verify(estadoService).findById(id);
    }

    @Test
    void testBuscarPorSigla() {
        String sigla = "SP";
        EstadoDTOResponse dto = mock(EstadoDTOResponse.class);
        when(estadoService.findBySigla(sigla)).thenReturn(List.of(dto));

        Response response = estadoResource.buscarPorSigla(sigla);

        assertEquals(200, response.getStatus());
        verify(estadoService).findBySigla(sigla);
    }

    @Test
    void testIncluirEstado() {
        EstadoDTO dto = new EstadoDTO("EstadoTeste", "ET", null);
        EstadoDTOResponse dtoResponse = mock(EstadoDTOResponse.class);

        when(estadoService.create(dto)).thenReturn(dtoResponse);

        Response response = estadoResource.incluirEstado(dto);

        assertEquals(201, response.getStatus());
        assertEquals(dtoResponse, response.getEntity());
        verify(estadoService).create(dto);
    }

    @Test
    void testAlterarEstado() {
        Long id = 1L;
        EstadoDTO dto = new EstadoDTO("EstadoAtualizado", "EA", null);

        doNothing().when(estadoService).update(id, dto);

        Response response = estadoResource.alterarEstado(id, dto);

        assertEquals(204, response.getStatus());
        verify(estadoService).update(id, dto);
    }

    @Test
    void testDeletarEstado() {
        Long id = 1L;

        doNothing().when(estadoService).delete(id);

        Response response = estadoResource.deletarEstado(id);

        assertEquals(204, response.getStatus());
        verify(estadoService).delete(id);
    }
}
