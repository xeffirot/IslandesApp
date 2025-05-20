package islandesapp.service;

import com.islandesapp.dao.LeccionDAO;
import com.islandesapp.model.Leccion;
import com.islandesapp.service.LeccionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class LeccionServiceTest {

    private LeccionDAO leccionDAO;
    private LeccionService leccionService;

    @BeforeEach
    public void setup() {
        // Usamos un mock para simular la DAO y evitar conectar con la BD real
        leccionDAO = mock(LeccionDAO.class);
        leccionService = new LeccionService(leccionDAO);
    }

    @Test
    public void testCrearLeccion_Valida() {
        Leccion leccion = new Leccion();
        leccion.setTitulo("Introducción al islandés");

        // No esperamos retorno, solo que llame insertarLeccion una vez
        leccionService.crearLeccion(leccion);

        verify(leccionDAO, times(1)).insertarLeccion(leccion);
    }

    @Test
    public void testCrearLeccion_NullTitulo_DeberiaLanzarExcepcion() {
        Leccion leccion = new Leccion();
        leccion.setTitulo(null);

        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> leccionService.crearLeccion(leccion)
        );

        assertEquals("La lección debe tener un título válido", thrown.getMessage());
    }

    @Test
    public void testListarTodasLasLecciones() {
        List<Leccion> mockLista = new ArrayList<>();
        mockLista.add(new Leccion());
        when(leccionDAO.obtenerTodas()).thenReturn(mockLista);

        List<Leccion> resultado = leccionService.listarTodasLasLecciones();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(leccionDAO, times(1)).obtenerTodas();
    }
}