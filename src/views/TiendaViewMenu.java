package views;


import model.entities.Producto;
import model.services.ProductoService;
import model.services.ProductoServiceImpl;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TiendaViewMenu extends JFrame {
    private JTable listadoTable;
    private JScrollPane scrollPane;

    private JPanel formPanel;

    JTextField _codigo_producto;
    JTextField _descripcion;
    JTextField _precio;
    JTextField _stock;


    private ProductoService service;

    public TiendaViewMenu() {
        service = new ProductoServiceImpl();
        setTitle("Listado de Productos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Crear menú
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Archivo");
        JMenuItem exitMenuItem = new JMenuItem("Salir");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        JMenuItem showCarsMenuItem = new JMenuItem("Coches");
        showCarsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCars();
            }
        });


        fileMenu.add(showCarsMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        JPanel panelAbajo = new JPanel();

        JPanel panelBotones = new JPanel();
        JButton nuevo = new JButton("Nuevo");
        JButton acturalizar = new JButton("Actualizar");
        JButton eliminar = new JButton("Eliminar");

        panelBotones.add(nuevo);
        panelBotones.add(acturalizar);
        panelBotones.add(eliminar);
        add(panelAbajo, BorderLayout.PAGE_END);

        panelAbajo.add(panelBotones, BorderLayout.PAGE_END);


        setJMenuBar(menuBar);


        // Crear tabla
        listadoTable = new JTable();
        scrollPane = new JScrollPane(listadoTable);


        listadoTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = listadoTable.getSelectedRow();
                if (selectedRow >= 0) {
                    showCarForm(selectedRow);
                }
            }
        });


        // Crear formulario
        formPanel = new JPanel(new GridLayout(3, 2));

        formPanel.setBorder(BorderFactory.createTitledBorder("Detalles del Coche"));
        formPanel.add(new JLabel("codigo_producto:"));
        _codigo_producto = new JTextField();
        formPanel.add(_codigo_producto);

        formPanel.add(new JLabel("Descripcion:"));
        _descripcion = new JTextField();
        formPanel.add(_descripcion);

        formPanel.add(new JLabel("Precio:"));
        _precio = new JTextField();
        formPanel.add(_precio);

        formPanel.add(new JLabel("Stock:"));
        _stock = new JTextField();
        formPanel.add(_stock);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        panelAbajo.add(formPanel, BorderLayout.PAGE_START);
        formPanel.setVisible(false);
    }

    private void showCars() {

        // Configurar modelo de datos
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("codigo_producto");
        model.addColumn("Descripcion");
        model.addColumn("Precio");
        model.addColumn("Stock");

        service = new ProductoServiceImpl();

        List<Producto> productos = service.getList();

        for (Producto producto : productos) {
            model.addRow(new Object[]{
                    producto.getCodigo_producto(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    producto.getStock(),

            });
        }

        listadoTable.setModel(model);

        formPanel.setVisible(true);

    }



    private void showCarForm(int rowIndex) {
        DefaultTableModel model = (DefaultTableModel) listadoTable.getModel();
        String matricula = (String) model.getValueAt(rowIndex, 0);

        Producto producto = service.getById(matricula);

        _codigo_producto.setText(producto.getCodigo_producto());
        _descripcion.setText(producto.getDescripcion());
        _precio.setText(String.valueOf(producto.getPrecio()));
        _stock.setText(String.valueOf(producto.getStock()));

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TiendaViewMenu example = new TiendaViewMenu();
            example.setVisible(true);
        });
    }
}
