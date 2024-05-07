package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalView extends JFrame {
    public PrincipalView() throws HeadlessException {
        setTitle("Tienda");
        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel pnlFondo = new JPanel();
        add(pnlFondo);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem itemProducto = new JMenuItem("Producto");
        itemProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlFondo.removeAll();
                pnlFondo.repaint();
                ProductoView cocheView = new ProductoView();
                pnlFondo.add(cocheView);
                pnlFondo.repaint();
                PrincipalView.super.repaint();
            }
        });

        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(itemProducto);
        menu.addSeparator();
        menu.add(itemSalir);

        menuBar.add(menu);
        setJMenuBar(menuBar);



        setVisible(true);
    }
}
