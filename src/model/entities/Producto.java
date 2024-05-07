package model.entities;

import java.util.Objects;

public class Producto {
    private String codigo_producto;

    private String descripcion;

    private double precio;

    private  int stock;

    public Producto() {
    }

    public Producto(String codigo_producto, String descripcion, double precio, int stock) {
        this.codigo_producto = codigo_producto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo_producto='" + codigo_producto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(codigo_producto, producto.codigo_producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo_producto);
    }
}
