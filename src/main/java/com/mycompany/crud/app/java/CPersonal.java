package com.mycompany.crud.app.java;

import java.awt.HeadlessException;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CPersonal {
    
    int codigo;
    String nombre;
    String apePat;
    String apeMat;
    String curp;
    String adscripcion;
    String contrato;
    String puesto;
    Date fecNac;
    String sexo;
    String estado;
    JComboBox<String> estadoComboBox;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombreALumnos) {
        this.nombre = nombreALumnos;
    }

    public String getApePat() {
        return apePat;
    }

    public void setApePat(String apePat) {
        this.apePat = apePat;
    }

    public String getApeMat() {
        return apeMat;
    }

    public void setApeMat(String apeMat) {
        this.apeMat = apeMat;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getAdscripcion() {
        return adscripcion;
    }

    public void setAdscripcion(String adscripcion) {
        this.adscripcion = adscripcion;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Date getFecNac() {
        return fecNac;
    }

    public void setFecNac(Date fecNac) {
        this.fecNac = fecNac;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public JComboBox<String> getEstadoComboBox() {
        return estadoComboBox;
    }

    public void setEstadoComboBox(JComboBox<String> estadoComboBox) {
        this.estadoComboBox = estadoComboBox;
    }
    
    
    
    public void InsertarPersonal(JTextField paramNombre, JTextField paramApePat, 
            JTextField paramApeMat, JTextField paramCurp, JTextField paramAdscrip, JTextField paramContract, JTextField paramPuesto,
            JTextField paramFecNac, JTextField paramSexo, JComboBox<String> paramComboEstado, byte[] imagenBytes){
    
        setNombre(paramNombre.getText());
        setApePat(paramApePat.getText());
        setApeMat(paramApeMat.getText());
        setCurp(paramCurp.getText());
        setAdscripcion(paramAdscrip.getText());
        setPuesto(paramPuesto.getText());
        setContrato(paramContract.getText());
        setSexo(paramSexo.getText());
        setEstado(paramComboEstado.getSelectedItem().toString());
        
        String fechaNacimientoStr = paramFecNac.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date fechaNacimiento = dateFormat.parse(fechaNacimientoStr);
            setFecNac(fechaNacimiento);
        } catch (ParseException e) {
        }
        
        CConexion objetoConexion = new CConexion();

        String consultaExistencia = "SELECT COUNT(*) FROM personal WHERE nombre = ? AND apePat = ? AND apeMat = ?";

        try {
            CallableStatement csExistencia = objetoConexion.estableceConexion().prepareCall(consultaExistencia);
            csExistencia.setString(1, getNombre());
            csExistencia.setString(2, getApePat());
            csExistencia.setString(3, getApeMat());
            
            ResultSet resultadoExistencia = csExistencia.executeQuery();
            
            resultadoExistencia.next();
            int count = resultadoExistencia.getInt(1);
            
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Datos ingresados ya existentes");
            } else {
                String consultaInsercion = "INSERT INTO personal (nombre, apePat, apeMat, curp, adscripcion, tipoContrato, "
                    + "puesto, fecNac, sexo, estado, fotoPersonal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

                CallableStatement csInsercion = objetoConexion.estableceConexion().prepareCall(consultaInsercion);
                csInsercion.setString(1, getNombre());
                csInsercion.setString(2, getApePat());
                csInsercion.setString(3, getApeMat());
                csInsercion.setString(4, getCurp());
                csInsercion.setString(5, getAdscripcion());
                csInsercion.setString(6, getContrato());
                csInsercion.setString(7, getPuesto());

                Date fechaNacimiento = getFecNac();
                String fechaNacimientoStrFormatted = dateFormat.format(fechaNacimiento);
                csInsercion.setString(8, fechaNacimientoStrFormatted);    
                csInsercion.setString(9, getSexo());
                csInsercion.setString(10, getEstado());

                ByteArrayInputStream inputStream = new ByteArrayInputStream(imagenBytes);
                csInsercion.setBinaryStream(11, inputStream, imagenBytes.length);

                csInsercion.execute();

                JOptionPane.showMessageDialog(null, "Se insertó correctamente el alumno");
            }

            resultadoExistencia.close();
            csExistencia.close();
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "No se pudo realizar la inserción del alumno, error: "+e.getMessage());
             e.printStackTrace();
        }
    }



    public void MostrarPersonal(JTable paramTablaTotalAlumnos) {
        CConexion objetoConexion = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<>(modelo);
        paramTablaTotalAlumnos.setRowSorter(OrdenarTabla);

        String sql;
        sql = "";

        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APEPAT");
        modelo.addColumn("APEMAT");
        modelo.addColumn("CURP");
        modelo.addColumn("ADCRIPCION");
        modelo.addColumn("CONTRATO");
        modelo.addColumn("PUESTO");
        modelo.addColumn("FECNAC");
        modelo.addColumn("SEXO");
        modelo.addColumn("ESTADO");
        modelo.addColumn("FOTOPERFIL");

        paramTablaTotalAlumnos.setModel(modelo);

        sql = "select * from personal;";

        String[] datos = new String[13];
        Statement st;

        try {
            st = objetoConexion.estableceConexion().createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                datos[10] = rs.getString(11);
                datos[11] = rs.getString(12);                

                byte[] imagenBytes = rs.getBytes(12);
                if (imagenBytes != null) {
                    ImageIcon icono = new ImageIcon(imagenBytes);
                    datos[11] = icono.toString();
                } else {
                    datos[11] = "";
                }

                modelo.addRow(datos);
                }

            paramTablaTotalAlumnos.setModel(modelo);

            } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
        }
                
    }

    
    public void SeleccionarPersonal(JTable paramTablaAlumnos, JTextField paramId, JTextField paramNombres, JTextField paramApePat, 
            JTextField paramApeMat, JTextField paramCurp, JTextField paramAdscrip, JTextField paramContract, JTextField paramPuesto,
            JTextField paramFecNac, JTextField paramSexo, JComboBox<String> paramComboEstado, JLabel Jphoto){
        
        try {
            int fila = paramTablaAlumnos.getSelectedRow();
            
            if (fila >= 0) {
                JTextField[] camposTexto = {paramId, paramNombres, paramApePat, paramApeMat, paramCurp, paramAdscrip, paramContract, 
                    paramPuesto, paramFecNac, paramSexo};
                
                for (int columna = 0; columna < camposTexto.length; columna++) {
                    Object valor = paramTablaAlumnos.getValueAt(fila, columna);
                    camposTexto[columna].setText((valor != null) ? valor.toString() : "");
                }
                    if (paramTablaAlumnos.getColumnCount() > 10 && paramTablaAlumnos.getValueAt(fila, 10) != null) {
                        String estadoSeleccionado = paramTablaAlumnos.getValueAt(fila, 10).toString();
                        paramComboEstado.setSelectedItem(estadoSeleccionado);
                    }

                String idAlumno = paramTablaAlumnos.getValueAt(fila, 0).toString();

                CConexion objetoConexion = new CConexion();
                String sql = "SELECT fotoPersonal FROM personal WHERE idPersonal = ?";
                try (PreparedStatement preparedStatement = objetoConexion.prepareStatement(sql)) {
                    preparedStatement.setString(1, idAlumno);
                    
                    ResultSet resultado = preparedStatement.executeQuery();
                    
                    if (resultado.next()) {
                        Blob imagenBlob = resultado.getBlob("fotoPersonal");
                        
                        if (imagenBlob != null) {
                            byte[] bytesImagen = imagenBlob.getBytes(1, (int) imagenBlob.length());
                            ImageIcon icono = new ImageIcon(bytesImagen);
                            Jphoto.setIcon(icono);
                        } else {
                            Jphoto.setIcon(null);
                        }
                    } else {
                        Jphoto.setIcon(null);
                    }
                    
                    resultado.close();
                }


            }else
            {
                JOptionPane.showMessageDialog(null,"Fila no seleccionada");
            }
        } catch (HeadlessException | SQLException e) {
            
                JOptionPane.showMessageDialog(null,"Error de seleccion, error: "+ e.toString());
        }
        
    }
    
    
    public void ModificarPersonal(JTextField paramCodigo, JTextField paramNombre, JTextField paramApePat,
        JTextField paramApeMat, JTextField paramCurp, JTextField paramAdscrip, JTextField paramContract,
        JTextField paramPuesto, JTextField paramFecNac, JTextField paramSexo, JComboBox<String> paramComboEstado, byte[] imagenBytes) {

        setCodigo(Integer.parseInt(paramCodigo.getText()));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (!paramNombre.getText().isEmpty()) {
            setNombre(paramNombre.getText());
        }
        if (!paramApePat.getText().isEmpty()) {
            setApePat(paramApePat.getText());
        }
        if (!paramApeMat.getText().isEmpty()) {
            setApeMat(paramApeMat.getText());
        }
        if (!paramCurp.getText().isEmpty()) {
            setCurp(paramCurp.getText());
        }
        if (!paramAdscrip.getText().isEmpty()) {
            setAdscripcion(paramAdscrip.getText());
        }
        if (!paramContract.getText().isEmpty()) {
            setContrato(paramContract.getText());
        }
        if (!paramPuesto.getText().isEmpty()) {
            setPuesto(paramPuesto.getText());
        }
        if (!paramFecNac.getText().isEmpty()) {
            String fechaNacimientoStr = paramFecNac.getText();

            try {
                Date fechaNacimiento = dateFormat.parse(fechaNacimientoStr);
                setFecNac(fechaNacimiento);
            } catch (ParseException e) {
                
            }
        }
        if (!paramSexo.getText().isEmpty()) {
            setSexo(paramSexo.getText());
        }
        if (paramComboEstado.getSelectedItem().toString().equals("Seleccionar")) {
        } else if (paramComboEstado.getSelectedIndex() != -1) {
            setEstado(paramComboEstado.getSelectedItem().toString());
        }

        CConexion objetoConexion = new CConexion();

        String consulta = "UPDATE personal SET personal.nombre = ?, personal.apePat = ?, personal.apeMat = ?, personal.curp = ?,"
                + "personal.adscripcion = ?, personal.tipoContrato = ?, personal.puesto = ?, personal.fecNac = ?, personal.sexo = ?,"
                + "personal.estado = ?, personal.fotoPersonal = ? WHERE personal.idPersonal = ?;";

        try {

            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

            cs.setString(1, getNombre());
            cs.setString(2, getApePat());
            cs.setString(3, getApeMat());
            cs.setString(4, getCurp());
            cs.setString(5, getAdscripcion());
            cs.setString(6, getContrato());
            cs.setString(7, getPuesto());

            Date fechaNacimiento = getFecNac();
            String getfechaNacimientoStr = dateFormat.format(fechaNacimiento);
            cs.setString(8, getfechaNacimientoStr);
            cs.setString(9, getSexo());
            cs.setString(10, getEstado());
            cs.setInt(12, getCodigo());
            
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imagenBytes);
            cs.setBinaryStream(11, inputStream, imagenBytes.length);

            cs.execute();

            JOptionPane.showMessageDialog(null, "Modificación Exitosa");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se modificó, error:" + e.toString());
        }
    }

    
    public void EliminarPersonal(JTextField paramCodigo){
    
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "DELETE FROM personal WHERE personal.idPersonal = ?;";
        
        try {
             CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
             cs.setInt(1, getCodigo());
             cs.execute();
             
             JOptionPane.showMessageDialog(null,"Se eliminó correctamente el Personal");
            
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null,"No se pudo eliminar, error: "+ e.toString());
        }
        
    }
    
    public ArrayList<CEstado> getIdEstado(){
        CConexion objetoConexion = new CConexion();
        Statement stmt;
        ResultSet rs;
        ArrayList<CEstado> listaEstado = new ArrayList<>();
        
        try{
            stmt = objetoConexion.createStatement();
            rs = stmt.executeQuery("SELECT idEstado FROM estados");
            
            while(rs.next()){
                CEstado estado = new CEstado();
                estado.setIdEstado(rs.getString("idEstado"));
                listaEstado.add(estado);
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"error: "+ e.toString());
        }
        return listaEstado;
    }
}
