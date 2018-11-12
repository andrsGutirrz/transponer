/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package una.cr.transponer.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import una.cr.transponer.model.ColsFijas;
import una.cr.transponer.model.Respuesta;

/**
 *
 * @author Andres
 */
public class Dao {

    RelDatabase db;

    public Dao() {
        db = new RelDatabase();
    }

    //BUILDERS
    //Respuesta Builder
    public Respuesta respuestaBuilder(ResultSet rs) {
        try {

            Respuesta pr = new Respuesta();
            pr.setCodigo(rs.getString("SVBTESD_QCOD_CODE"));
            String tipoRespuesta = rs.getString("SVBTESD_ACOD_CODE"); //tipo de respuesta
            if (tipoRespuesta.equals("ESCAL-AB")) {
                //Entonces es de respuesta libre!
                String temp = rs.getString("SVBTESD_OPEN_ANSWER");
                if (temp == null) {
                    temp = "-1";
                }
                pr.setRespuesta(temp);
            } else {
                String temp = rs.getString("SVBTESD_PVAC_QPOINTS");
                if (temp == null) {
                    temp = "-1";
                }
                pr.setRespuesta(temp);

            }
            return pr;
        } catch (SQLException e) {
            return null;
        }
    }

    public ColsFijas ColsFijasBuilder(ResultSet rs) {
        try {

            ColsFijas cf = new ColsFijas();
            cf.setCiclo(rs.getString("SVBTESD_TERM_CODE"));
            cf.setCrn(rs.getString("SVBTESD_CRN"));
            cf.setEncuesta(rs.getString("SVBTESD_ESAS_TEMP_PIDM"));
            cf.setPidm(rs.getString("SVBTESD_FACULTY_PIDM"));
            cf.setTssc(rs.getString("SVBTESD_TSSC_CODE"));
            return cf;
        } catch (SQLException e) {
            return null;
        }
    }

    /*
    // Provincia Builder
    public Provincia provinciaBuilder(ResultSet rs) {
        try {
            Provincia pr = new Provincia();
            pr.setNumero(rs.getInt("numero"));
            pr.setNombre(rs.getString("nombre"));
            return pr;
        } catch (SQLException e) {
            return null;
        }
    }

    // Viaje Builder 
    public Viaje viajeBuilder(ResultSet rs) {
        try {
            Viaje vj = new Viaje();
            vj.setPrecio(rs.getFloat("precio"));
            vj.setFecha_salida(rs.getDate("fecha_salida").toString());
            vj.setLugar(rs.getString("lugar"));
            vj.setLugar_salida(rs.getString("lugar_salida"));
            return vj;
        } catch (SQLException e) {
            return null;
        }
    }

    //Lugar Builder
    public Lugar lugarBuilder(ResultSet rs) {
        try {
            Lugar lg = new Lugar();
            lg.setDescripcion(rs.getString("descripcion"));
            lg.setDireccion(rs.getString("direccion"));
            lg.setNombre(rs.getString("nombre"));
            lg.setProvincia(rs.getInt("provincia"));
            return lg;
        } catch (SQLException e) {
            return null;
        }
    }

    //Others
    //############################################## CLIENTES
    //Get all clients, with no filters
    public List<Cliente> getAllClientes() {
        List<Cliente> clientes = new ArrayList<Cliente>();
        try {
            String sql = "select * from Cliente;";
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                clientes.add(clienteBuilder(rs));
            }
        } catch (SQLException e) {

        }
        return clientes;
    }

    //Registrar un cliente
    public void registrarCliente(Cliente cl) throws Exception {
        String sql = "insert into Cliente (cedula,nombre,telefono,correo,residencia)"
                + "values('%s','%s','%s','%s','%s')";
        sql = String.format(sql, cl.getCedula(), cl.getNombre(), cl.getTelefono(), cl.getCorreo(), cl.getResidencia());
        int count = db.executeUpdate(sql);
        if (count == 0) {
            throw new Exception("Cliente ya existe");
        }
    }

    //######################################################## Lugares
    //Todas las provincias
    public List<Provincia> getAllProvincia() {
        List<Provincia> provincias = new ArrayList<Provincia>();
        try {
            String sql = "select * from Provincia;";
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                provincias.add(provinciaBuilder(rs));
            }
        } catch (SQLException e) {

        }
        return provincias;
    }

    //############################################# LUGAR
    public void registrarLugar(Lugar lg) throws Exception {
        String sql = "insert into Lugar (provincia,nombre,direccion,descripcion)"
                + "values('%d','%s','%s','%s')";
        sql = String.format(sql, lg.getProvincia(), lg.getNombre(), lg.getDireccion(), lg.getDescripcion());
        int count = db.executeUpdate(sql);
        if (count == 0) {
            throw new Exception("Lugar ya existe");
        }
    }
    
        //Todas los lugares
    public List<Lugar> getAllLugar() {
        List<Lugar> lugares = new ArrayList<Lugar>();
        try {
            String sql = "select * from Lugar;";
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                lugares.add(lugarBuilder(rs));
            }
        } catch (SQLException e) {

        }
        return lugares;
    }

    // ############################################ VIAJE
    //Todas los viajes
    public List<Viaje> getAllViaje() {
        List<Viaje> viajes = new ArrayList<Viaje>();
        try {
            String sql = "select * from Viaje;";
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                viajes.add(viajeBuilder(rs));
            }
        } catch (SQLException e) {

        }
        return viajes;
    }
    
    
    public void registrarViaje(Viaje vj) throws Exception {
        String sql = "insert into Viaje (lugar,precio,fecha_salida,lugar_salida)"
           + "values('%s','%f','%s','%s')";
        sql = String.format(sql,vj.getLugar(),vj.getPrecio(),vj.getFecha_salida(),vj.getLugar_salida());
        int count = db.executeUpdate(sql);
        if (count == 0) {
            throw new Exception("Viaje error");
        }
    }

    //######################################### CLIENTE_VIAJE
    public void registrarCliente_Viaje(Cliente_Viaje cl) throws Exception {
        String sql = "insert into Cliente_Viaje (cliente,fecha,precio,lugar,fecha_salida,lugar_salida,campos)"
           + "values('%s','%s','%f','%s','%s','%s','%d')";
        sql = String.format(sql,cl.getCliente(),cl.getFecha(), cl.getPrecio(),cl.getLugar(),cl.getFecha_salida(),cl.getLugar_salida(),cl.getCampos());
        int count = db.executeUpdate(sql);
        if (count == 0) {
            throw new Exception("Cliente_Viaje error");
        }
    }
    
    //######################################### BUSETA

    public void registrarBuseta(Buseta bs) throws Exception {
        String sql = "insert into Buseta (proveedor,asientos_totales,asientos_disponibles,"
                + "telefono,lugar,fecha_salida,lugar_salida)"
           + "values('%s','%d','%d','%s','%s','%s','%s')";
        sql = String.format(sql,bs.getProveedor(),bs.getAsientos_totales(),bs.getAsientos_disponibles(),
                bs.getTelefono(),bs.getLugar(),bs.getFecha_salida(),bs.getLugar_salida());
        int count = db.executeUpdate(sql);
        if (count == 0) {
            throw new Exception("Buseta error");
        }
        
    }
    
     */
}
