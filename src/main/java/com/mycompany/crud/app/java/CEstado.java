package com.mycompany.crud.app.java;

import java.awt.HeadlessException;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CEstado {
    private String idEstado;
    private String descripcion;
    private byte[] fotoEstado;

    // Getters y setters para las propiedades
    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getFotoEstado() {
        return fotoEstado;
    }

    public void setFotoEstado(byte[] fotoEstado) {
        this.fotoEstado = fotoEstado;
    }
        
    
    public void MostrarEstados(JTable paramtbEstados) {
        CConexion objetoConexion = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<>(modelo);
        paramtbEstados.setRowSorter(OrdenarTabla);

        String sql;
        sql = "";

        modelo.addColumn("IDESTADO");
        modelo.addColumn("DESCRIPCION");
        modelo.addColumn("IMAGEN");

        paramtbEstados.setModel(modelo);

        sql = "select * from estados;";

        String[] datos = new String[3]; // Cambiado a 3 para almacenar los datos
        Statement st;

        try {
            st = objetoConexion.estableceConexion().createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);

                // Verifica si los datos de imagen no son nulos
                byte[] imagenBytes = rs.getBytes(3);
                if (imagenBytes != null) {
                    ImageIcon icono = new ImageIcon(imagenBytes);
                    datos[2] = icono.toString(); // Mostrará como "javax.swing.ImageIcon@xxxx"
                } else {
                    datos[2] = ""; // Si es nulo, coloca un valor vacío
                }

                modelo.addRow(datos);
            }

            paramtbEstados.setModel(modelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
        }
    }

    
    public void SeleccionarEstado(JTable paramtbEstados, JTextField paramEstado, JTextField paramDescripcion, JLabel Jphoto){
        
        try {
            int fila = paramtbEstados.getSelectedRow();
            
            if (fila >= 0) {
                Object valorDescripcion = paramtbEstados.getValueAt(fila, 1);

                paramDescripcion.setText((valorDescripcion != null) ? valorDescripcion.toString() : "");

                if (paramtbEstados.getColumnCount() > 0 && paramtbEstados.getValueAt(fila, 0) != null) {
                    String estadoSeleccionado = paramtbEstados.getValueAt(fila, 0).toString();
                    paramEstado.setText(estadoSeleccionado);
                } else {
                }
                String idCEstado = paramtbEstados.getValueAt(fila, 0).toString();

                CConexion objetoConexion = new CConexion();
                String sql = "SELECT fotoEstado FROM estados WHERE idEstado = ?";
                try (PreparedStatement preparedStatement = objetoConexion.prepareStatement(sql)) {
                    preparedStatement.setString(1, idCEstado);
                    
                    ResultSet resultado = preparedStatement.executeQuery();
                    
                    if (resultado.next()) {
                        Blob imagenBlob = resultado.getBlob("fotoEstado");
                        
                        if (imagenBlob != null) {
                            byte[] bytesImagen = imagenBlob.getBytes(1, (int) imagenBlob.length());
                            ImageIcon imagenIcono = new ImageIcon(bytesImagen);
                            Image imagenEscalada = imagenIcono.getImage().getScaledInstance(Jphoto.getWidth(), Jphoto.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
                            Jphoto.setIcon(iconoEscalado);
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
    
    public void ModificarEstado(JTextField paramEstado, JTextField paramDescripcion, byte[] imagenBytes) {

        setIdEstado(paramEstado.getText());
        setDescripcion(paramDescripcion.getText());
        
        CConexion objetoConexion = new CConexion();

        String consulta = "UPDATE estados SET estados.idEstado = ?, estados.descripcion = ?, estados.fotoEstado = ?"
                + "WHERE estados.idEstado = ?;";

        try {

            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

            
            cs.setString(1, getIdEstado());
            cs.setString(2, getDescripcion());
            
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imagenBytes);
            cs.setBinaryStream(3, inputStream, imagenBytes.length);
            
            cs.setString(4, getIdEstado());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Modificación Exitosa");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se modificó, error:" + e.toString());
        }
    }
    
    public void EliminarEstado(JTextField paramEstado){
    
        setIdEstado(paramEstado.getText());
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "DELETE FROM estados WHERE estados.idEstado = ?;";
        
        try {
             CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
             cs.setString(1, getIdEstado());
             cs.execute();
             
             JOptionPane.showMessageDialog(null,"Se eliminó correctamente el Estado");
            
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null,"No se pudo eliminar, error: "+ e.toString());
        }
        
    }

}
