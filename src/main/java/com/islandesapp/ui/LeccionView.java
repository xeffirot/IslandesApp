package com.islandesapp.ui;

import com.islandesapp.model.Leccion;
import com.islandesapp.service.LeccionService;
import com.islandesapp.service.PalabraService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Panel que gestiona la interfaz para la gestión de Lecciones.
 */
public class LeccionView extends JPanel {

    /** Servicio para operaciones sobre Leccion */
    private final LeccionService leccionService;

    /** Servicio para operaciones sobre Palabra */
    private final PalabraService palabraService;

    /** Area de texto para mostrar resultados y mensajes al usuario */
    private final JTextArea textAreaResultados;

    /** Lista gráfica que muestra los títulos de las lecciones disponibles */
    private final JList<String> listaLecciones;

    /** Modelo de lista para manejar los elementos de la lista gráfica */
    private DefaultListModel<String> listModel;

    /** Lista interna de objetos Leccion cargados desde el servicio */
    private List<Leccion> lecciones;

    /** Campo de texto para entrada de título o ID */
    private final JTextField inputCampo;

    /**
     * Constructor principal que recibe los servicios y configura la UI.
     * @param leccionService servicio para lecciones
     * @param palabraService servicio para palabras
     */
    public LeccionView(LeccionService leccionService, PalabraService palabraService) {
        this.leccionService = leccionService;
        this.palabraService = palabraService;

        setLayout(new BorderLayout(15, 15));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // Panel superior con entrada de texto
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(new JLabel("Título o ID:"));
        inputCampo = new JTextField(25);
        inputPanel.add(inputCampo);

        // Panel izquierdo con botones de acciones
        JPanel accionesPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        accionesPanel.setBorder(new TitledBorder("Acciones"));
        JButton btnCrear = new JButton(" Crear");
        JButton btnListar = new JButton(" Listar");
        JButton btnBuscar = new JButton(" Buscar");
        JButton btnEliminar = new JButton(" Eliminar");

        accionesPanel.add(btnCrear);
        accionesPanel.add(btnListar);
        accionesPanel.add(btnBuscar);
        accionesPanel.add(btnEliminar);

        // Lista central con lecciones
        listModel = new DefaultListModel<>();
        listaLecciones = new JList<>(listModel);
        JScrollPane scrollLista = new JScrollPane(listaLecciones);
        scrollLista.setBorder(new TitledBorder("Lecciones disponibles"));

        // Panel inferior con área de resultados
        textAreaResultados = new JTextArea(5, 30);
        textAreaResultados.setEditable(false);
        JScrollPane scrollTextArea = new JScrollPane(textAreaResultados);
        scrollTextArea.setBorder(new TitledBorder("Resultado"));

        // Añadir componentes al panel principal
        add(inputPanel, BorderLayout.NORTH);
        add(accionesPanel, BorderLayout.WEST);
        add(scrollLista, BorderLayout.CENTER);
        add(scrollTextArea, BorderLayout.SOUTH);

        // Acciones de botones
        btnCrear.addActionListener(e -> crearLeccion());
        btnListar.addActionListener(e -> actualizarLista());
        btnBuscar.addActionListener(e -> buscarLeccionPorTitulo());
        btnEliminar.addActionListener(e -> eliminarLeccionPorId());

        // Acción al hacer doble clic en la lista - abre ventana con PalabraView
        listaLecciones.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    mostrarPalabrasDeLeccion();
                }
            }
        });

        // Cargar lista inicial
        actualizarLista();
    }

    /**
     * Crea una nueva lección con el título introducido en inputCampo.
     */
    private void crearLeccion() {
        String titulo = inputCampo.getText().trim();
        if (titulo.isEmpty()) {
            mostrarMensaje("El título no puede estar vacío.");
            return;
        }
        Leccion nueva = new Leccion();
        nueva.setTitulo(titulo);
        try {
            leccionService.crearLeccion(nueva);
            mostrarMensaje("Lección creada: " + nueva.getTitulo());
            actualizarLista();
            limpiarInput();
        } catch (Exception ex) {
            mostrarMensaje("Error al crear la lección: " + ex.getMessage());
        }
    }

    /**
     * Busca una lección por título y muestra su información.
     */
    private void buscarLeccionPorTitulo() {
        String titulo = inputCampo.getText().trim();
        if (titulo.isEmpty()) {
            mostrarMensaje("Introduce un título para buscar.");
            return;
        }
        try {
            Leccion leccion = leccionService.buscarPorTitulo(titulo);
            if (leccion != null) {
                textAreaResultados.setText("Encontrado:\nID: " + leccion.getId() + "\nTítulo: " + leccion.getTitulo());
            } else {
                mostrarMensaje("No se encontró ninguna lección con ese título.");
            }
        } catch (Exception ex) {
            mostrarMensaje("Error en la búsqueda: " + ex.getMessage());
        }
    }

    /**
     * Elimina una lección según el ID introducido.
     */
    private void eliminarLeccionPorId() {
        String id = inputCampo.getText().trim();
        if (id.isEmpty()) {
            mostrarMensaje("Introduce un ID para eliminar.");
            return;
        }
        try {
            leccionService.eliminarLeccion(id);
            mostrarMensaje("Lección eliminada si existía con ID: " + id);
            actualizarLista();
            limpiarInput();
        } catch (Exception ex) {
            mostrarMensaje("Error al eliminar la lección: " + ex.getMessage());
        }
    }

    /**
     * Muestra una ventana con las palabras de la lección seleccionada en la lista.
     */
    private void mostrarPalabrasDeLeccion() {
        int indexSeleccionado = listaLecciones.getSelectedIndex();
        if (indexSeleccionado == -1) {
            mostrarMensaje("Selecciona una lección para ver sus palabras.");
            return;
        }

        Leccion leccionSeleccionada = lecciones.get(indexSeleccionado);

        // Crear nueva ventana para mostrar palabras
        JFrame framePalabras = new JFrame("Palabras de: " + leccionSeleccionada.getTitulo());
        PalabraView palabraView = new PalabraView(palabraService);

        // Pasar el id de la lección y la lista de palabras a PalabraView
        palabraView.setLeccionId(leccionSeleccionada.getId(), palabraService.buscarPalabrasPorLeccion(leccionSeleccionada.getId()));

        framePalabras.setContentPane(palabraView);
        framePalabras.setSize(600, 400);
        framePalabras.setLocationRelativeTo(null);
        framePalabras.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        framePalabras.setVisible(true);
    }

    /**
     * Muestra un mensaje en el área de resultados.
     * @param mensaje texto a mostrar
     */
    private void mostrarMensaje(String mensaje) {
        textAreaResultados.setText(mensaje);
    }

    /**
     * Actualiza la lista de lecciones en la interfaz.
     */
    private void actualizarLista() {
        listModel.clear();
        lecciones = leccionService.listarTodasLasLecciones();
        if (lecciones == null || lecciones.isEmpty()) {
            mostrarMensaje("No hay lecciones disponibles.");
            return;
        }
        for (Leccion l : lecciones) {
            listModel.addElement(l.getTitulo() + " (ID: " + l.getId() + ")");
        }
        mostrarMensaje("Lista de lecciones actualizada.");
    }

    /**
     * Limpia el campo de entrada.
     */
    private void limpiarInput() {
        inputCampo.setText("");
    }

    /**
     * Obtiene la lista gráfica de lecciones.
     * @return JList con los títulos de las lecciones
     */
    public JList<String> getListaLecciones() {
        return listaLecciones;
    }

    /**
     * Obtiene la lista interna de objetos Leccion.
     * @return lista de Leccion
     */
    public List<Leccion> getLecciones() {
        return lecciones;
    }
}
