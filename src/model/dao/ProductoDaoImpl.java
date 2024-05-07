package model.dao;

import datasource.DataSource;
import model.entities.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDaoImpl extends BaseDao implements ProductoDao {

    private Connection connection;

    public ProductoDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Producto> findAll() {

        List<Producto> productos = new ArrayList<Producto>();

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from producto order by codigo_producto";
            ps = connection.prepareStatement(sql);

            rs = ps.executeQuery();


            while(rs.next()){
                Producto producto = new Producto();
                producto.setCodigo_producto(rs.getString("codigo_producto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSource.cerrarStatement(ps);
            DataSource.cerrarResulset(rs);
        }
        return productos;
    }


    @Override
    public Producto findById(String codigo_producto) {

        Producto producto = null;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from producto where codigo_producto = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, codigo_producto);

            rs = ps.executeQuery();


            if(rs.next()){

                producto = new Producto();
                producto.setCodigo_producto(rs.getString("codigo_producto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSource.cerrarStatement(ps);
            DataSource.cerrarResulset(rs);
        }
        return producto;
    }

    @Override
    public Producto save(Producto producto) {

        PreparedStatement ps = null;

        try {
            String sql = "insert into producto (codigo_producto, descripcion, precio, stock) values (?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, producto.getCodigo_producto());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());


            int fila = ps.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSource.cerrarStatement(ps);
        }
        return producto;
    }

    @Override
    public void update(Producto producto) {

        PreparedStatement ps = null;

        try {
            String sql = "update producto set descripcion = ?, precio = ?, stock = ? where codigo_producto = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, producto.getDescripcion());
            ps.setDouble(2, producto.getPrecio());
            ps.setInt(3, producto.getStock());
            ps.setString(4, producto.getCodigo_producto());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSource.cerrarStatement(ps);
        }
    }

    @Override
    public void delete(Producto producto) {
        PreparedStatement ps = null;

        try {
            String sql = "delete from producto where codigo_producto = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, producto.getCodigo_producto());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSource.cerrarStatement(ps);
        }
    }
}
