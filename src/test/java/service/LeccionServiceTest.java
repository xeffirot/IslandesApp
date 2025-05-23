package service;

import com.islandesapp.dao.LeccionDAOMock;
import com.islandesapp.model.Leccion;
import com.islandesapp.service.LeccionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitarios para la clase LeccionService,
 * verificando la creación, búsqueda, actualización y eliminación de lecciones.
 */
class LeccionServiceTest {

    private LeccionService leccionService;

    @BeforeEach
    void setUp() {
        leccionService = new LeccionService(new LeccionDAOMock());
    }

    @Test
    void testCrearYBuscarLeccion() {
        Leccion leccion = new Leccion("Saludos básicos", "Lección para aprender saludos");
        leccionService.crearLeccion(leccion);

        Leccion encontrada = leccionService.buscarPorTitulo("Saludos básicos");
        assertNotNull(encontrada, "La lección debería existir después de ser creada");
        assertEquals("Saludos básicos", encontrada.getTitulo());
        assertEquals("Lección para aprender saludos", encontrada.getDescripcion());
    }

    @Test
    void testBuscarLeccionPorId() {
        Leccion leccion = new Leccion("Colores", "Lección sobre colores");
        leccionService.crearLeccion(leccion);

        Leccion creada = leccionService.buscarPorTitulo("Colores");
        assertNotNull(creada);

        // Aquí usamos el método correcto del servicio:
        Leccion encontradaPorId = leccionService.obtenerLeccionPorId(creada.getId());
        assertNotNull(encontradaPorId);
        assertEquals(creada.getId(), encontradaPorId.getId());
    }

    @Test
    void testActualizarLeccion() {
        Leccion leccion = new Leccion("Números", "Lección de números");
        leccionService.crearLeccion(leccion);

        Leccion creada = leccionService.buscarPorTitulo("Números");
        assertNotNull(creada);

        creada.setDescripcion("Lección actualizada de números");
        leccionService.actualizarLeccion(creada);

        Leccion actualizada = leccionService.obtenerLeccionPorId(creada.getId());
        assertEquals("Lección actualizada de números", actualizada.getDescripcion());
    }

    @Test
    void testEliminarLeccion() {
        Leccion leccion = new Leccion("Frutas", "Lección sobre frutas");
        leccionService.crearLeccion(leccion);

        Leccion creada = leccionService.buscarPorTitulo("Frutas");
        assertNotNull(creada);

        leccionService.eliminarLeccion(creada.getId());

        Leccion eliminada = leccionService.buscarPorTitulo("Frutas");
        assertNull(eliminada, "La lección debería haber sido eliminada");
    }

    @Test
    void testObtenerTodas() {
        leccionService.crearLeccion(new Leccion("L1", "Desc 1"));
        leccionService.crearLeccion(new Leccion("L2", "Desc 2"));

        // Aquí usamos el método correcto:
        List<Leccion> todas = leccionService.listarTodasLasLecciones();
        assertNotNull(todas);
        assertTrue(todas.size() >= 2, "Debería haber al menos dos lecciones");
    }

    @Test
    void testBuscarPorTituloNoExistente() {
        Leccion leccion = leccionService.buscarPorTitulo("NoExiste");
        assertNull(leccion, "La búsqueda por título inexistente debería devolver null");
    }

    @Test
    void testBuscarPorIdNoExistente() {
        Leccion leccion = leccionService.obtenerLeccionPorId("id_inexistente");
        assertNull(leccion, "La búsqueda por id inexistente debería devolver null");
    }
}