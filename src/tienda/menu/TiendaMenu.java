package tienda.menu;

import tienda.dominio.fabricante.FabricanteService;
import tienda.dominio.producto.ProductoService;
import java.util.Scanner;
import tienda.dominio.fabricante.FabricanteEntidad;
import tienda.dominio.producto.ProductoEntidad;

/* * @author Gabiezzi
 */
public class TiendaMenu {

    private Scanner read;
    private FabricanteService fabricanteService;
    private ProductoService productoService;
    private int respuesta;

    public TiendaMenu() {
        // ISO-8859-1 PERMITE USAR CARACTERES ESPECIALES
        read = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n");
        fabricanteService = new FabricanteService();
        productoService = new ProductoService();

    }

    public void menuPrincipal() throws Exception {
        do {
            try {

                System.out.println("ELIJA UNA OPCIÓN");
                System.out.println("1. Lista el nombre de todos los productos que hay en la tabla producto.");
                System.out.println("2. Lista los nombres y los precios de todos los productos de la tabla producto.");
                System.out.println("3. Listar aquellos productos que su precio esté entre 120 y 202.");
                System.out.println("4. Buscar y listar todos los Portátiles de la tabla producto");
                System.out.println("5. Listar el nombre y el precio del producto más barato.");
                System.out.println("6. Ingresar un producto a la base de datos.");
                System.out.println("7. Ingresar un fabricante a la base de datos.");
                System.out.println("8. Editar un producto con datos a elección.");
                System.out.println("9. Lista los nombres y codigos de todos los fabricante de la tabla fabricante.");
                System.out.println("10. SALIR");

                respuesta = read.nextInt();

                switch (respuesta) {
                    case 1:
                        listarProductos();

                        break;
                    case 2:
                        productoService.imprimirListaProducto(productoService.listarProductoConPrecio());

                        break;
                    case 3:
                        productoService.imprimirListaProducto(productoService.ProductosEntrePrecio1y2(120, 202));
                        break;
                    case 4:
                        productoService.imprimirListaProducto(productoService.ListarPortatiles());
                        break;
                    case 5:
                        productoService.buscarProductoMasBarato();
                        break;
                    case 6:
                        ingresarProductoABD();
                        break;
                    case 7:
                        ingresarFabricanteABD();

                        break;
                    case 8:
                        editarProducto();
                        break;
                    case 9:
                        mostrarFabricantes();
                    case 10:
                        System.out.println("Operacion Finalizada!");

                }

            } catch (Exception e) {
                System.out.println(e.getMessage());

                throw e;
            }

        } while (respuesta != 10);
    }

    public void listarProductos() throws Exception {

        System.out.println("Listado de productos");

        for (ProductoEntidad producto : productoService.listarNombreProducto()) {
            if (producto == null) {
                throw new Exception("Producto no existe");
            } else {

                System.out.println(producto.getNombre());
            }

        }

    }
    
    private void mostrarFabricantes() throws Exception {
        
        System.out.println("Listado fabricantes:");
        for (FabricanteEntidad fabricanteUnitario : fabricanteService.listarFabricante()) {
            if (fabricanteUnitario==null) {
                throw new Exception ("Fabricante inexistente");
            } else {
                System.out.println("|"+fabricanteUnitario.getCodigo()+"|"+fabricanteUnitario.getNombre()+"|");
            }
            
        }
        
    }

    public void ingresarProductoABD() throws Exception {
        System.out.println("Ingresar codigo");
        int codigo = read.nextInt();
        System.out.println("Ingresar nombre");
        String nombre = read.next();
        System.out.println("Ingresar precio");
        double precio = read.nextDouble();
        System.out.println("Ingresar codigo fabricante");
        int codigoFabricante = read.nextInt();
        FabricanteEntidad fabricante = fabricanteService.buscarFabricantePorCodigo(String.valueOf(codigoFabricante));

        productoService.crearProducto(codigo, nombre, precio, fabricante);

    }

    public void ingresarFabricanteABD() throws Exception {
        try {
            System.out.println("Ingrese NOMBRE del fabricante");
            String nombreFabricante = read.next();
            System.out.println("Ingrese el CODIGO del fabricante");
            int codigoFabri = read.nextInt();

            if (nombreFabricante == null || nombreFabricante.trim().isEmpty()) {

                throw new Exception("Debe ingresar un nombre para el fabricante nuevo");
            }

            if (codigoFabri < 0) {
                throw new Exception("Debe ingresar un codigo para el fabricante nuevo");
            }

            fabricanteService.crearFabricante(String.valueOf(codigoFabri), nombreFabricante);

        } catch (Exception e) {
            throw e;
        }

    }

    public void editarProducto() throws Exception {
        try {

            System.out.println("Ingresar codigo");
            int codigo = read.nextInt();
            System.out.println("Ingresar nombre");
            String nombre = read.next();
            System.out.println("Ingresar precio");
            double precio = read.nextDouble();
            System.out.println("Ingresar codigo fabricante");
            int codigoFabricante = read.nextInt();
            FabricanteEntidad fabricante = fabricanteService.buscarFabricantePorCodigo(String.valueOf(codigoFabricante));

            if (codigo < 0) {
                throw new Exception("Debe ingresar un codigo de producto a modificar");
            }
            if (nombre == null) {
                throw new Exception("Debe ingresar un nombre de producto a modificar");

            }
            if (precio < 0) {

                throw new Exception("Debe ingresar un precio de producto a modificar");
            }
            if (codigoFabricante < 0) {

                throw new Exception("Debe ingresar un codigoFabricante de producto a modificar");
            }
            
            productoService.modificarCodigoProducto(codigo, nombre, precio, codigoFabricante);
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarPortatiles() throws Exception {

        System.out.println("Listado de productos");

        if (productoService.ListarPortatiles() == null) {
            throw new Exception("Producto no existe");
        } else {
            productoService.imprimirListaProducto(productoService.ListarPortatiles());
        }

    }

    

}
