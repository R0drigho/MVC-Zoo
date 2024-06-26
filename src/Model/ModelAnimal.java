package Model;

//@author Rodrigo

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ModelAnimal extends Conexion{
    public ResultSet listar(){
      PreparedStatement ps = null;
       ResultSet rs=null;
         try{   
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();                        
            String sql = "select codigo,nombre,animal,genero,tipo,pais from animal";//este es el comando establecido en SQL
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();// para ejecutar sentenciia SQL
           //return rs; 
   }catch (SQLException e) {
            System.err.println(e);    
        } 
    return rs; 
    }
    public boolean registrar(EntidadAnimal animal) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO animal (codigo, nombre, animal, genero, tipo, pais) VALUES(?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, animal.getCodigo());
            ps.setString(2, animal.getNombre());
            ps.setString(3, animal.getAnimal());
            ps.setString(4, animal.getGenero());
            ps.setString(5, animal.getTipoA());
            ps.setString(6, animal.getPais());
            ps.execute();
            return true;
            
        } catch (SQLException e) {
            System.err.println(e);
            return false;
            
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    //Metodo para ACTULALIZAR
    public boolean modificar(EntidadAnimal animal) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE animal SET codigo=?, nombre=?, animal=?, genero=?, tipo=?, pais=? WHERE id=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, animal.getCodigo());
            ps.setString(2, animal.getNombre());
            ps.setString(3, animal.getAnimal());
            ps.setString(4, animal.getGenero());
            ps.setString(5, animal.getTipoA());
            ps.setString(6, animal.getPais());
            ps.setInt(7,animal.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    //METODO  BUSCAR
    public boolean buscar(EntidadAnimal animal) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM animal WHERE codigo=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, animal.getCodigo());
            rs = ps.executeQuery();
            
            if(rs.next()) 
            {
               animal.setId( Integer.parseInt(rs.getString("id"))); //declarado así en BDatos
               animal.setCodigo(Integer.parseInt(rs.getString("codigo")));
               animal.setNombre(rs.getString("nombre"));
               animal.setAnimal(rs.getString("animal"));
               animal.setGenero(rs.getString("genero"));
               animal.setTipoA(rs.getString("tipo"));
               animal.setPais(rs.getString("pais"));
               return true;
            }
            return false;
            
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    //Metodo Eliminar
    public boolean eliminar(EntidadAnimal animal) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM animal WHERE id=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, animal.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
