package tienda.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import tienda.dominio.fabricante.FabricanteEntidad;

public class FabricanteDAO extends DAO {

    public void guardarFabricante(FabricanteEntidad fabricante) throws Exception {

        try {

            if (fabricante == null) {
                throw new Exception("Se debe indicar un fabricante!");
            }

            String sql = "INSERT INTO Fabricante (Nombre, codigo)"
                    + "VALUES ( '" + fabricante.getNombre() + "' , '" + fabricante.getCodigo() + "');";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }

    }

    public void modificarNombreFabricante(FabricanteEntidad fabricante) throws Exception {

        try {
            if (fabricante == null) {
                throw new Exception("Se debe indicar un fabricante!");
            }

            String sql = "UPDATE Fabricante SET "
                    + "Nombre = '" + fabricante.getNombre() + "'WHERE Codigo = '" + fabricante.getNombre() + "';";
            insertarModificarEliminar(sql);
        } catch (Exception ex) {
            throw ex;
        } finally {

            desconectarBase();
        }
    }

    public void eliminarFabricante(String codigo) throws Exception {

        try {

            String sql = "DELETE FROM Fabricante Where clave=" + codigo + "';";
            insertarModificarEliminar(sql);

        } catch (Exception ex) {
            throw ex;
        } finally {
            desconectarBase();
        }

    }

    public FabricanteEntidad buscarFabricantePorNombre(String nombre) throws Exception {
        try {
            String sql = "SELECT * FROM Fabricante "
                    + "WHERE Nombre = '" + nombre + "';";

            consultarBase(sql);

            FabricanteEntidad fabricante = null;

            while (resultado.next()) {
                fabricante = new FabricanteEntidad();
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
            }
            desconectarBase();
            return fabricante;

        } catch (Exception ex) {
            desconectarBase();
            throw ex;

        }

    }

    public FabricanteEntidad buscarFabricantePorCodigo(String codigo) throws Exception {

        try {
            String sql = "SELECT * FROM Fabricante WHERE codigo="
                    + codigo + ";";
            consultarBase(sql);

            FabricanteEntidad fabricante = null;

            while (resultado.next()) {
                fabricante = new FabricanteEntidad();
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
            }
            desconectarBase();
            return fabricante;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }

    }

    public Collection<FabricanteEntidad> listarFabricantes() throws Exception {
        try {
            String sql = "SELECT codigo, nombre FROM Fabricante;";

            consultarBase(sql);

            FabricanteEntidad fabricante = null;

            Collection<FabricanteEntidad> fabricantes = new ArrayList();

            while (resultado.next()) {
                fabricante = new FabricanteEntidad();
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
                fabricantes.add(fabricante);

            }
            desconectarBase();
            return fabricantes;

        } catch (Exception ex) {
            ex.printStackTrace();
            desconectarBase();
            throw new Exception("Error del sistema");

        }

    }

    public void modificarClaveFabricante(FabricanteEntidad fabricante) throws Exception {

        try {
            if (fabricante == null) {
                throw new Exception("Debe indicar el fabricante a modificar");

            }

            String sql = "UPDATE Fabricante SET "
                    + "codigo = '" + fabricante.getCodigo() + "' WHERE nombre = '"
                    + fabricante.getNombre() + "';";
            insertarModificarEliminar(sql);

        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }

    }

}
