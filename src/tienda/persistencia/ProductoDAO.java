/*
// Curso Egg FullStack
 */
package tienda.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import tienda.dominio.fabricante.FabricanteEntidad;
import tienda.dominio.fabricante.FabricanteService;
import tienda.dominio.producto.ProductoEntidad;


/* * @author Gabiezzi
 */
public class ProductoDAO extends DAO {

    private final FabricanteService fabricanteService;

    public ProductoDAO() {
        this.fabricanteService = new FabricanteService();
    }

    public void guardarProducto(ProductoEntidad producto) throws Exception {

        try {
            if (producto == null) {
                throw new Exception("Debe indicar un valor para producto!");
            }

            String sql = "INSERT INTO Producto (codigo, nombre, precio, codigo_fabricante) "
                    + "VALUES ( " + producto.getCodigo() + ", '" + producto.getNombre() + "'," + producto.getPrecio()
                    + "," + producto.getFabricante().getCodigo() + ");";

            System.out.println(sql);
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void modificarProducto(ProductoEntidad producto) throws Exception {
        try {

            if (producto == null) {
                throw new Exception("Producto inválido!");
            }
            String sql = "UPDATE Producto SET "
                    + "nombre = '" + producto.getNombre() + "' , precio = " + producto.getPrecio() + " , codigo_fabricante = " + producto.getFabricante().getCodigo()
                    + " WHERE codigo = " + producto.getCodigo() + " ;";
            System.out.println(sql);
            insertarModificarEliminar(sql);

        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }

    }

    public void eliminarProducto(int codigo) throws Exception {
        try {
            String sql = "DELETE FROM Producto WHERE codigo=" + codigo + ";";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public ProductoEntidad buscarProductoPorCodigo(int codigo) throws Exception {
        try {
            String sql = "SELECT * FROM Producto WHERE codigo = " + codigo + ";";
            
            consultarBase(sql);
           
            ProductoEntidad producto = null;
           
            while (resultado.next()) {
                producto = new ProductoEntidad();
                
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                Integer codigoFabricante = resultado.getInt(4);
                FabricanteEntidad fabricante = fabricanteService.buscarFabricantePorCodigo(String.valueOf(codigoFabricante));
                producto.setFabricante(fabricante);
            }
            
            return producto;

        } catch (Exception e) {
            desconectarBase();
            throw e;
        } finally{
            desconectarBase();
        }
    }

    public Collection<ProductoEntidad> listarNombreProductos() throws Exception {

        try {
            String sql = "SELECT nombre FROM Producto;";
            consultarBase(sql);
            ProductoEntidad producto = null;
            Collection<ProductoEntidad> productos = new ArrayList();
            while (resultado.next()) {
                producto = new ProductoEntidad();

                producto.setNombre(resultado.getString(1));

                productos.add(producto);

            }
            desconectarBase();
            return productos;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    public Collection<ProductoEntidad> listarProductoPrecio() throws Exception {

        try {

            String sql = "SELECT * FROM Producto;";
            consultarBase(sql);
            ProductoEntidad producto = null;
            Collection<ProductoEntidad> productos = new ArrayList();
            while (resultado.next()) {
                producto = new ProductoEntidad();

                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                int fabricanteCodigo = resultado.getInt(4);
                FabricanteEntidad fabricante = fabricanteService.buscarFabricantePorCodigo(String.valueOf(fabricanteCodigo));
                producto.setFabricante(fabricante);
                productos.add(producto);
            }
            desconectarBase();
            return productos;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    public Collection<ProductoEntidad> ListarProductosEntrePrecio1y2(int precio1, int precio2) throws Exception {

        try {
            String sql = "SELECT * FROM Producto WHERE precio >= " + precio1 + " AND precio <" + precio2 + ";";
            consultarBase(sql);
            ProductoEntidad producto = null;
            Collection<ProductoEntidad> productos = new ArrayList();
            while (resultado.next()) {
                producto = new ProductoEntidad();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                Integer codigoFabricante = resultado.getInt(4);
                FabricanteEntidad fabricante = fabricanteService.buscarFabricantePorCodigo(String.valueOf(codigoFabricante));
                producto.setFabricante(fabricante);
                productos.add(producto);
            }
            desconectarBase();
            return productos;

        } catch (Exception e) {
            throw e;
        }
    }

    public Collection<ProductoEntidad> listarPortatiles() throws Exception {
        try {

            String sql = "SELECT * FROM Producto WHERE nombre like ('%portatil%');";
            consultarBase(sql);
            ProductoEntidad producto = null;
            Collection<ProductoEntidad> portatiles = new ArrayList();
            while (resultado.next()) {
                producto = new ProductoEntidad();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                Integer codigoFabricante = resultado.getInt(4);
                FabricanteEntidad fabricante = fabricanteService.buscarFabricantePorCodigo(String.valueOf(codigoFabricante));
                producto.setFabricante(fabricante);
                portatiles.add(producto);
            }
            desconectarBase();
            return portatiles;

        } catch (Exception e) {
            throw e;
        }
    }

    public ProductoEntidad buscarProductoMasBarato() throws Exception {

        try {

            String sql = "SELECT * FROM Producto ORDER BY precio limit 1 ;";
            consultarBase(sql);
            ProductoEntidad producto = null;
            while (resultado.next()) {
                producto = new ProductoEntidad();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                Integer codigoFabricante = resultado.getInt(4);
                FabricanteEntidad fabricante = fabricanteService.buscarFabricantePorCodigo(String.valueOf(codigoFabricante));
                producto.setFabricante(fabricante);

            }
            desconectarBase();
            return producto;

        } catch (Exception e) {
            throw e;
        }
    }

}
