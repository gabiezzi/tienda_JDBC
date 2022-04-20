/*
// Curso Egg FullStack
 */

package tienda.dominio.producto;

import tienda.dominio.fabricante.FabricanteEntidad;

/* * @author Gabiezzi
 */


public class ProductoEntidad {
    
    private int codigo ;
    private String nombre;
    private double precio ;
    private FabricanteEntidad fabricante;

    public ProductoEntidad(int codigo, String nombre, double precio, FabricanteEntidad fabricante) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.fabricante = fabricante;
    }

    public ProductoEntidad() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public FabricanteEntidad getFabricante() {
        return fabricante;
    }

    public void setFabricante(FabricanteEntidad fabricante) {
        this.fabricante = fabricante;
    }

    @Override
    public String toString() {
        return "ProductoEntidad{" + "codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", fabricante=" + fabricante + '}';
    }
    
    

}
