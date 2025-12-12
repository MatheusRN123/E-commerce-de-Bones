package br.unitins.topicos1.bone.resources;

import br.unitins.topicos1.bone.dto.PagamentoDTOResponse;
import br.unitins.topicos1.bone.resource.PagamentoResource;
import br.unitins.topicos1.bone.service.PagamentoService;
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
class PagamentoResourceUnitTest {

    @Inject
    PagamentoResource pagamentoResource;

    @InjectMock
    PagamentoService pagamentoService;

    @Test
    void testBuscarTodos() {
        PagamentoDTOResponse p1 = mock(PagamentoDTOResponse.class);
        PagamentoDTOResponse p2 = mock(PagamentoDTOResponse.class);

        when(pagamentoService.findAll()).thenReturn(List.of(p1, p2));

        Response response = pagamentoResource.buscarTodos();

        assertEquals(200, response.getStatus());
        assertNotNull(response.getEntity());
        verify(pagamentoService).findAll();
    }

    @Test
    void testBuscarPorIdExistente() {
        PagamentoDTOResponse p = mock(PagamentoDTOResponse.class);
        when(pagamentoService.findById(1L)).thenReturn(p);

        Response response = pagamentoResource.buscarPorId(1L);

        assertEquals(200, response.getStatus());
        assertEquals(p, response.getEntity());
        verify(pagamentoService).findById(1L);
    }

    @Test
    void testBuscarPorIdInexistente() {
        when(pagamentoService.findById(99L)).thenReturn(null);

        Response response = pagamentoResource.buscarPorId(99L);

        assertEquals(404, response.getStatus());
        verify(pagamentoService).findById(99L);
    }
}
