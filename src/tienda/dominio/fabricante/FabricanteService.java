/*
// Curso Egg FullStack
 */
package tienda.dominio.fabricante;

import java.util.Collection;
import tienda.persistencia.FabricanteDAO;

/* * @author Gabiezzi
 */
public class FabricanteService {

    private FabricanteDAO dao;

    public FabricanteService() {
        this.dao = new FabricanteDAO();
    }

    public void crearFabricante(String codigo, String nombre) throws Exception {

        try {

            if (codigo == null || codigo.trim().isEmpty()) {
                throw new Exception("El codigo no puede estar vacio!");
            }

            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Nombre no puede estar vacio!");
            }

            if (nombre.length() < 1) {
                throw new Exception("El nombre no puede tener menos de 4 caracteres!");
            }

            //Creamos el fabricante
            FabricanteEntidad fabricante = new FabricanteEntidad();
            fabricante.setCodigo(Integer.parseInt(codigo));
            fabricante.setNombre(nombre);
            dao.guardarFabricante(fabricante);

        } catch (Exception e) {
            throw e;
        }

    }

    public void modificarCodigoFabricante(String codigoActual, String codigoNuevo, String nombre) {

        try {
            //VALIDACION

            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe ingresar el nombre del fabricante");
            }

            if (codigoActual == null || codigoActual.trim().isEmpty()) {

                throw new Exception("Debe ingresar el codigo actual fabricante");
            }

            if (codigoNuevo == null || codigoNuevo.trim().isEmpty()) {

                throw new Exception("Debe ingresar el codigo Nuevo fabricante");
            }
            
            //Buscamos
            
            FabricanteEntidad fabricante = buscarFabricantePorCodigo(nombre);
            
            //Validamos
            if (!(fabricante.getCodigo()==Integer.parseInt(codigoNuevo))) {
                throw new Exception("La clave actual no coincide con la registrada en sistema ");
                
            }
            
            //Modificamos
            
            fabricante.setCodigo(Integer.parseInt(codigoNuevo));
            
            dao.modificarClaveFabricante(fabricante);

        } catch (Exception e) {
        }

    }

    public void eliminarFabricante(String codigo) throws Exception {

        try {
            if (codigo == null || codigo.trim().isEmpty()) {
                throw new Exception("Debe indicar un codigo");
            }

            dao.eliminarFabricante(codigo);

        } catch (Exception e) {
            throw e;
        }

    }

    public FabricanteEntidad buscarFabricantePorCodigo(String codigo) throws Exception {
        try {

            //Validacion
            if (codigo == null || codigo.trim().isEmpty()) {
                throw new Exception("Debe indicar un codigo de fabricante!");

            }
            FabricanteEntidad fabricante = dao.buscarFabricantePorCodigo(codigo);

            return fabricante;
        } catch (Exception e) {
            throw e;
        }

    }

    public FabricanteEntidad buscarFabricantePorNombre(String nombre) throws Exception {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar un nombre de fabricante!");
            }

            FabricanteEntidad fabricante = dao.buscarFabricantePorNombre(nombre);

            return fabricante;

        } catch (Exception e) {
            throw e;

        }

    }
    
    public Collection<FabricanteEntidad> listarFabricante() throws Exception {
        
        try {
            
            Collection<FabricanteEntidad> fabricantes = dao.listarFabricantes();
            
            return fabricantes;
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void imprimirFabricantes() throws Exception {
        try {
            
            Collection<FabricanteEntidad> fabricantes = listarFabricante();
           
            if (fabricantes.isEmpty()) {
                throw new Exception("No existen fabricantes para imprimir");
            } else {
                for (FabricanteEntidad fabricanteUnitario : fabricantes) {
                    System.out.println(fabricanteUnitario);
                    
                }
            }
            
        } catch (Exception e) {
            throw e;
        }
        
    }

}
