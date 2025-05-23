package service;

import com.islandesapp.dao.PalabraDAOMock;
import com.islandesapp.model.Palabra;
import com.islandesapp.service.PalabraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitarios para la clase PalabraService,
 * asegurando la creación y búsqueda correcta de palabras.
 */
public class PalabraServiceTest {

    private PalabraService palabraService;

    /**
     * Inicializa un servicio de palabra con un DAO mock antes de cada test.
     */
    @BeforeEach
    public void setUp() {
        palabraService = new PalabraService(new PalabraDAOMock());
    }

    /**
     * Test para crear una palabra y buscarla por categoría,
     * comprobando que se guarda y recupera correctamente.
     */
    @Test
    public void testCrearYBuscarPalabra() {
        // Crear una nueva palabra con datos de ejemplo
        Palabra p = new Palabra("hundur", "perro", "animales", "El hundur es amigable.", "leccion1");
        palabraService.crearPalabra(p);

        // Buscar palabras por categoría 'animales'
        List<Palabra> resultados = palabraService.buscarPorCategoria("animales");

        // Comprobar que la lista no está vacía y que la palabra buscada está presente
        assertFalse(resultados.isEmpty(), "La lista de palabras no debería estar vacía");
        assertEquals("hundur", resultados.get(0).getIslandes(), "La palabra islandesa debe coincidir");
    }
    
    /**
     * Test para asegurar que se lanza excepción si la palabra en islandés es nula o vacía.
     */
    @Test
    public void testCrearOPactualizarPalabraSinIslandes() {
        Palabra palabraSinIslandes = new Palabra(null, "pez", "animales", "Un pez nada.", "leccion3");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            palabraService.crearOPactualizarPalabra(palabraSinIslandes);
        });

        assertEquals("La palabra en islandés es obligatoria", exception.getMessage());
    }

    /**
     * Test para asegurar que se lanza excepción si el ID de lección es nulo o vacío.
     */
    @Test
    public void testCrearOPactualizarPalabraSinLeccionId() {
        Palabra palabraSinLeccion = new Palabra("fiskur", "pez", "animales", "Un pez nada.", null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            palabraService.crearOPactualizarPalabra(palabraSinLeccion);
        });

        assertEquals("La palabra debe pertenecer a una lección válida", exception.getMessage());
    }
}