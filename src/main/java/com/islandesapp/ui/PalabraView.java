package com.islandesapp.ui;

import com.islandesapp.model.Palabra;
import com.islandesapp.service.PalabraService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Vista para gestionar las palabras de una lección,
 * con formulario para añadir o actualizar palabras y lista de palabras disponibles.
 */
public class PalabraView extends JPanel {

    /**
     * Servicio para gestionar las operaciones sobre Palabras.
     */
    private final PalabraService palabraService;

    /**
     * ID de la lección actualmente seleccionada.
     */
    private String leccionId = "";

    /**
     * Campo de texto para introducir la palabra en islandés.
     */
    private JTextField tfIslandes;

    /**
     * Campo de texto para introducir la traducción de la palabra.
     */
    private JTextField tfTraduccion;

    /**
     * Campo de texto para introducir la categoría gramatical o temática.
     */
    private JTextField tfCategoria;

    /**
     * Campo de texto para introducir un ejemplo con la palabra.
     */
    private JTextField tfEjemplo;

    /**
     * Area de texto para mostrar las palabras en formato texto.
     */
    private JTextArea taPalabras;

    /**
     * Lista gráfica para mostrar las palabras disponibles.
     */
    private JList<Palabra> listaPalabras;

    /**
     * Modelo de lista para gestionar elementos en listaPalabras.
     */
    private DefaultListModel<Palabra> listaModel;

    /**
     * Constructor que inicializa la vista y componentes con el servicio inyectado.
     *
     * @param palabraService Servicio para gestionar las palabras.
     */
    public PalabraView(PalabraService palabraService) {
        this.palabraService = palabraService;

        setLayout(new BorderLayout(10, 10));

        // Panel izquierdo con JList
        listaModel = new DefaultListModel<>();
        listaPalabras = new JList<>(listaModel);
        listaPalabras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaPalabras.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Palabra) {
                    value = ((Palabra) value).getIslandes() + " - " + ((Palabra) value).getTraduccion();
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        listaPalabras.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Palabra seleccionada = listaPalabras.getSelectedValue();
                if (seleccionada != null) {
                    cargarPalabraEnFormulario(seleccionada);
                }
            }
        });

        JScrollPane scrollLista = new JScrollPane(listaPalabras);
        scrollLista.setPreferredSize(new Dimension(250, 0));
        add(scrollLista, BorderLayout.WEST);

        // Panel de formulario
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        tfIslandes = new JTextField();
        tfTraduccion = new JTextField();
        tfCategoria = new JTextField();
        tfEjemplo = new JTextField();

        formPanel.add(new JLabel("Islandés:"));
        formPanel.add(tfIslandes);
        formPanel.add(new JLabel("Traducción:"));
        formPanel.add(tfTraduccion);
        formPanel.add(new JLabel("Categoría:"));
        formPanel.add(tfCategoria);
        formPanel.add(new JLabel("Ejemplo:"));
        formPanel.add(tfEjemplo);

        JButton btnAgregar = new JButton("Agregar/Actualizar Palabra");
        JButton btnListar = new JButton("Listar Palabras");

        formPanel.add(btnAgregar);
        formPanel.add(btnListar);

        JPanel centro = new JPanel(new BorderLayout());
        centro.add(formPanel, BorderLayout.NORTH);

        taPalabras = new JTextArea();
        taPalabras.setEditable(false);
        JScrollPane scrollTexto = new JScrollPane(taPalabras);
        centro.add(scrollTexto, BorderLayout.CENTER);

        add(centro, BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> {
            agregarOActualizarPalabra();
            listarPalabras();
        });

        btnListar.addActionListener(e -> listarPalabras());
    }

    /**
     * Establece el ID de la lección actual y actualiza la lista de palabras mostradas.
     *
     * @param leccionId ID de la lección seleccionada.
     * @param palabras  Lista de palabras asociadas a la lección.
     */
    public void setLeccionId(String leccionId, List<Palabra> palabras) {
        this.leccionId = leccionId;
        actualizarPalabras(palabras);
    }

    /**
     * Agrega una nueva palabra o actualiza una existente con los datos del formulario.
     * Muestra mensajes en caso de error o éxito.
     */
    private void agregarOActualizarPalabra() {
        String islandes = tfIslandes.getText().trim();
        String traduccion = tfTraduccion.getText().trim();
        String categoria = tfCategoria.getText().trim();
        String ejemplo = tfEjemplo.getText().trim();

        if (islandes.isEmpty() || traduccion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Islandés y Traducción son obligatorios.");
            return;
        }

        if (leccionId == null || leccionId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar una lección primero.");
            return;
        }

        Palabra palabra = new Palabra(islandes, traduccion, categoria, ejemplo, leccionId);
        palabraService.crearOActualizarPalabra(palabra);
        JOptionPane.showMessageDialog(this, "Palabra agregada o actualizada correctamente.");
        limpiarCampos();
    }

    /**
     * Limpia los campos del formulario y deselecciona la palabra en la lista.
     */
    private void limpiarCampos() {
        tfIslandes.setText("");
        tfTraduccion.setText("");
        tfCategoria.setText("");
        tfEjemplo.setText("");
        listaPalabras.clearSelection();
    }

    /**
     * Carga los datos de una palabra seleccionada en el formulario para edición.
     *
     * @param palabra Palabra a cargar en el formulario.
     */
    private void cargarPalabraEnFormulario(Palabra palabra) {
        tfIslandes.setText(palabra.getIslandes());
        tfTraduccion.setText(palabra.getTraduccion());
        tfCategoria.setText(palabra.getCategoria());
        tfEjemplo.setText(palabra.getEjemplo());
    }

    /**
     * Lista las palabras en el área de texto y actualiza la lista gráfica.
     * Obtiene las palabras desde el servicio según la lección seleccionada o todas si no hay.
     */
    public void listarPalabras() {
        List<Palabra> palabras;
        try {
            if (leccionId != null && !leccionId.isEmpty()) {
                palabras = palabraService.buscarPalabrasPorLeccion(leccionId);
            } else {
                palabras = palabraService.listarTodasLasPalabras();
            }
        } catch (Exception e) {
            taPalabras.setText("Error al obtener las palabras: " + e.getMessage());
            return;
        }

        if (palabras == null || palabras.isEmpty()) {
            taPalabras.setText("No hay palabras para mostrar.");
            listaModel.clear();
            return;
        }

        mostrarPalabras(palabras);
    }

    /**
     * Actualiza la lista y área de texto con las palabras proporcionadas.
     *
     * @param palabras Lista de palabras a mostrar.
     */
    public void actualizarPalabras(List<Palabra> palabras) {
        if (palabras == null || palabras.isEmpty()) {
            taPalabras.setText("No hay palabras para esta lección.");
            listaModel.clear();
            return;
        }
        mostrarPalabras(palabras);
    }

    /**
     * Muestra las palabras en el área de texto y en la lista gráfica.
     *
     * @param palabras Lista de palabras a mostrar.
     */
    private void mostrarPalabras(List<Palabra> palabras) {
        StringBuilder sb = new StringBuilder();
        listaModel.clear();
        for (Palabra p : palabras) {
            sb.append(p.getIslandes()).append(" - ").append(p.getTraduccion());
            if (p.getCategoria() != null && !p.getCategoria().isEmpty()) {
                sb.append(" (").append(p.getCategoria()).append(")");
            }
            if (p.getEjemplo() != null && !p.getEjemplo().isEmpty()) {
                sb.append(" Ej: ").append(p.getEjemplo());
            }
            sb.append("\n");
            listaModel.addElement(p);
        }
        taPalabras.setText(sb.toString());
    }
}