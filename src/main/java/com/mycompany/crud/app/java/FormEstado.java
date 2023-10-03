package com.mycompany.crud.app.java;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class FormEstado extends javax.swing.JPanel {

    /**
     * Creates new form PEstado
     */
    public FormEstado() {
        initComponents();
        
        CEstado objetoEstado = new CEstado();
        objetoEstado.MostrarEstados(tbEstados);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        JPhotoEstado = new javax.swing.JButton();
        Jphoto = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbEstados = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtEstado = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setForeground(new java.awt.Color(0, 102, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("IdEstado:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 62, -1, -1));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Imagen:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 87, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Descripción:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 115, -1, -1));

        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });
        add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 112, 130, -1));

        JPhotoEstado.setText("Cargar");
        JPhotoEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JPhotoEstadoActionPerformed(evt);
            }
        });
        add(JPhotoEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 84, 130, 20));
        add(Jphoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 102, 109));

        tbEstados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbEstados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbEstadosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbEstados);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 254, 379, 162));

        jButton3.setText("Modificar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, -1, -1));

        jButton4.setText("Eliminar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 80, -1));

        txtEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstadoActionPerformed(evt);
            }
        });
        add(txtEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 56, 130, -1));

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 420));
    }// </editor-fold>//GEN-END:initComponents

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        byte[] imagenBytes = convertirImagenABytes(Jphoto);
        CEstado objetoEstado = new CEstado();
        objetoEstado.ModificarEstado(txtEstado, txtDescripcion, imagenBytes);
        objetoEstado.MostrarEstados(tbEstados);
    }//GEN-LAST:event_jButton3ActionPerformed
    
    private byte[] convertirImagenABytes(JLabel label) {
    Icon icon = label.getIcon();
        if (icon instanceof ImageIcon) {
            BufferedImage imagen = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics g = imagen.createGraphics();
            // Dibuja el icono en la imagen
            icon.paintIcon(label, g, 0, 0);
            g.dispose();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                ImageIO.write(imagen, "png", bos); // Puedes cambiar el formato según tus necesidades
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bos.toByteArray();
        }
        return null;
    }
    
    private void JPhotoEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JPhotoEstadoActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String path = f.getAbsolutePath();
        try {
            BufferedImage bi = ImageIO.read(new File(path));
            Image img = bi.getScaledInstance(140, 140, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img);
            Jphoto.setIcon(icon);
            
        } catch (IOException ex){
        }
    }//GEN-LAST:event_JPhotoEstadoActionPerformed

    private void tbEstadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEstadosMouseClicked
        CEstado objetoEstado = new CEstado();
        objetoEstado.SeleccionarEstado(tbEstados, txtEstado, txtDescripcion, Jphoto);
    }//GEN-LAST:event_tbEstadosMouseClicked

    private void txtEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        CEstado objetoEstado = new CEstado();
        objetoEstado.EliminarEstado(txtEstado);
        objetoEstado.MostrarEstados(tbEstados);
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JPhotoEstado;
    private javax.swing.JLabel Jphoto;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tbEstados;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtEstado;
    // End of variables declaration//GEN-END:variables
}