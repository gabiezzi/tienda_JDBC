/*
// Curso Egg FullStack
 */
package tienda.dominio.producto;

import java.util.ArrayList;
import java.util.Collection;
import tienda.dominio.fabricante.FabricanteEntidad;
import tienda.persistencia.ProductoDAO;
import tienda.dominio.fabricante.FabricanteService;

public class ProductoService {

    private ProductoDAO dao;
    private FabricanteService fabricanteService;

    public ProductoService() {
        this.dao = new ProductoDAO();
        fabricanteService = new FabricanteService();
    }

    public void crearProducto(int codigo, String nombre, double precio, FabricanteEntidad fabricante) throws Exception {

        try {

            //Validamos
            if (codigo<0) {
                throw new Exception("Debe indicar el codigo del producto");
            }
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre del producto");
            }
            if (fabricante == null) {
                throw new Exception("Debe indicar el fabricante!");
            }
            if (precio<0) {
                throw new Exception("Debe indicar el precio del producto");
            }

            //creamos el producto
            ProductoEntidad producto = new ProductoEntidad();
            producto.setCodigo(codigo);
            producto.setPrecio(precio);
            producto.setNombre(nombre);
            producto.setFabricante(fabricante);

            dao.guardarProducto(producto);
        } catch (Exception e) {
        }

    }

    public void modificarCodigoProducto(int codigo, String nombre, double precio, int codigoFabricante) throws Exception {

        try {

            //Validacion
            if (codigo <= 0) {
                throw new Exception("Debe indicar id");

            }
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar nombre!");
            }
            if (precio <= 0) {

                throw new Exception("Debe indicar precio del producto!");

            }

            if (codigoFabricante <= 0) {
                throw new Exception("Debe indicar codigo del Fabricante!");

            }

            //Busqueda
            ProductoEntidad producto = dao.buscarProductoPorCodigo(codigo);
            
            if (producto==null) {
                throw new Exception ("El codigo ingresado no esta asociado a ningun usuario");
            }
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            FabricanteEntidad fabricante = fabricanteService.buscarFabricantePorCodigo(String.valueOf(codigoFabricante));
            producto.setFabricante(fabricante);

            dao.modificarProducto(producto);

        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarProducto(int codigo) throws Exception {

        try {

            //Validacion
            if (codigo <= 0) {

                throw new Exception("Debe indicar codigo de producto a eliminar");

            }

            ProductoEntidad producto = dao.buscarProductoPorCodigo(codigo);
            //Verificacion

            if (producto == null) {
                throw new Exception("No se encontró el codigo del producto a eliminar!");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public ProductoEntidad buscarProductoPorCodigo(int codigo) throws Exception {

        try {

            if (codigo >= 0) {
                throw new Exception("Debe indicar un codigo de producto para su busqueda!");
            }

            ProductoEntidad producto = dao.buscarProductoPorCodigo(codigo);

            //Verificacion
            if (producto == null) {
                throw new Exception("No se encontro el producto con el codigo indicado");
            }

            return producto;

        } catch (Exception e) {
            throw e;
        }
    }

    public Collection<ProductoEntidad> listarNombreProducto() throws Exception {

        try {

            Collection<ProductoEntidad> productos = dao.listarNombreProductos();

            return productos;

        } catch (Exception e) {
            throw e;
        }
    }

    public Collection<ProductoEntidad> listarProductoConPrecio() throws Exception {

        try {
            Collection<ProductoEntidad> productos = dao.listarProductoPrecio();
            return productos;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Collection <ProductoEntidad> ProductosEntrePrecio1y2(int precio1, int precio2) throws Exception {
        
        try {
            Collection<ProductoEntidad> productos = dao.ListarProductosEntrePrecio1y2(precio1,precio2);
            return productos ;
        } catch (Exception e) {
            throw e;
        }
        
    }
    
    public Collection<ProductoEntidad> ListarPortatiles() throws Exception {
        
        try {
            
            Collection<ProductoEntidad> portatiles = dao.listarPortatiles();
            return portatiles;
        } catch (Exception e) {
            throw e;
        }
    }
    
     public void imprimirListaProductoNombre(Collection<ProductoEntidad> productos) throws Exception {

        try {

            if (productos.isEmpty()) {
                throw new Exception("No existen productos para imprimir!");
            } else {
                for (ProductoEntidad productoUnitario : productos) {
                    System.out.println(productoUnitario.getNombre());
                }
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public void imprimirListaProducto(Collection<ProductoEntidad> productos) throws Exception {

        try {

            if (productos.isEmpty()) {
                throw new Exception("No existen productos para imprimir!");
            } else {
                for (ProductoEntidad productoUnitario : productos) {
                    System.out.println(productoUnitario.getCodigo() +"|"+ productoUnitario.getNombre() +"|"+ productoUnitario.getPrecio()+"|"+ productoUnitario.getFabricante().getCodigo()+"|"+ productoUnitario.getFabricante().getNombre());
                }
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public ProductoEntidad buscarProductoMasBarato() throws Exception {
        try {
            ProductoEntidad producto = dao.buscarProductoMasBarato();
            
            if (producto==null) {
                throw new Exception("No existe producto mas barato para mostrar");
            } else {
                System.out.println("El producto mas barato: \n"+producto.getCodigo()+"|"+producto.getNombre()+"|"+producto.getPrecio()+"|"+producto.getFabricante().getCodigo());
            }
            
            return producto;
            
        } catch (Exception e) {
            throw e;
        }
    }

}
