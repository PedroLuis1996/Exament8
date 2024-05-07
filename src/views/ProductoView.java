package views;


import model.entities.Producto;
import model.services.ProductoService;

import model.services.ProductoServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProductoView extends JPanel {

    static final String USERNAME = "root";
    static final String PASSWORD = "root";
    static final String CONN_STRING = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false";

    JTable jt;
    public ProductoView() {

        ProductoService service = new ProductoServiceImpl();

        List<Producto> list = service.getList();

        Object[] header = new Object[]{"codigo_producto"};
        DefaultTableModel tableModel = new DefaultTableModel(header, 0);

        for (Producto producto: list) {
            tableModel.addRow(new Object[]{producto.getCodigo_producto()});
        }


            jt = new JTable(tableModel);

            jt.setPreferredScrollableViewportSize(new Dimension(450, 63));
            jt.setFillsViewportHeight(true);

            JScrollPane jps = new JScrollPane(jt);
            add(jps);

    }

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        ProductoView t = new ProductoView();
        jf.setTitle("Test");
        jf.setSize(500,500);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(t);
    }
}