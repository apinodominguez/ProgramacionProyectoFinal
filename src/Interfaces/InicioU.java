/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Metodos.MetodosLibros;
import Metodos.MetodosUsuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author apinodominguez
 */
public class InicioU extends javax.swing.JFrame {
    
    private String nombreU;
    private String password;
    private int idUsuario;
    private String accion;
    
    MetodosLibros objMU = new MetodosLibros();
    ModificarUsuario objMod = new ModificarUsuario();
    Mensaje objMen = new Mensaje();
    MetodosUsuario objU = new MetodosUsuario();
    
    /**
     * Creates new form InicioU
     */
    public InicioU() {
        initComponents();
        limpiarTabla();
        selectDisponibles("S");
        setMensaje();
        textoI.setEnabled(false);
        textoT.setEnabled(false);
        textoA.setEnabled(false);
        comboParametros.setEnabled(false);
        
    }

    public String getNombreU() {
        return nombreU;
    }

    public void setNombreU(String nombreU) {
        this.nombreU = nombreU;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public void limpiarTexto(){
      textoI.setText("");
      textoT.setText("");
      textoA.setText("");
    }
    
    public void setVariables(String msg, String pass){
        setIdUsuario(objU.sacarUsuarioId(msg));
        setMensaje();
        setNombreU(msg);
        setPassword(pass);
    }

    public void setMensaje(){
        System.out.println(getIdUsuario());
        etiquetaUsuario.setText("Bienvenido: " + objU.sacarUsuarioNombre(getIdUsuario()));
    }
    
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:ProyectoFinal.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public void limpiarTabla(){
        DefaultTableModel dtm = (DefaultTableModel) tablaLibros.getModel();
        dtm.setRowCount(0);
    } 
    
    public void selectDisponibles(String disponible){
        String sql = "SELECT isbn, titulo, nombre FROM libros INNER JOIN autores ON autores.id = libros.autorid WHERE disponible = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
             pstmt.setString(1,disponible);
             ResultSet rs    = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                Object[] row = { rs.getInt("isbn"), rs.getString("titulo"), rs.getString("nombre")};
                 DefaultTableModel model = (DefaultTableModel) tablaLibros.getModel();

                model.addRow(row);
            }
       
    }   catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void selectIsbn(int isbn){
        String sql = "SELECT isbn, titulo, nombre FROM libros INNER JOIN autores ON autores.id = libros.autorid WHERE isbn = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
             pstmt.setInt(1,isbn);
             ResultSet rs    = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                Object[] row = { rs.getInt("isbn"), rs.getString("titulo"), rs.getString("nombre")};
                 DefaultTableModel model = (DefaultTableModel) tablaLibros.getModel();

                model.addRow(row);
            }
       
    }   catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void selectTitulo(String titulo){
        String sql = "SELECT isbn, titulo, nombre FROM libros INNER JOIN autores ON autores.id = libros.autorid WHERE titulo = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
             pstmt.setString(1,titulo);
             ResultSet rs    = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                Object[] row = { rs.getInt("isbn"), rs.getString("titulo"), rs.getString("nombre")};
                 DefaultTableModel model = (DefaultTableModel) tablaLibros.getModel();

                model.addRow(row);
            }
       
    }   catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void selectAutor(String autor){
        String sql = "SELECT isbn, titulo, nombre FROM libros INNER JOIN autores ON autores.id = libros.autorid WHERE autorid = (SELECT id FROM autores WHERE nombre = (?))";
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
             pstmt.setString(1,autor);
             ResultSet rs    = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                Object[] row = { rs.getInt("isbn"), rs.getString("titulo"), rs.getString("nombre")};
                 DefaultTableModel model = (DefaultTableModel) tablaLibros.getModel();

                model.addRow(row);
            }
       
    }   catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void selectMisLibros(String user){
        String sql = "SELECT isbn, titulo, nombre FROM libros INNER JOIN autores ON autores.id = libros.autorid WHERE usuario = (SELECT id FROM users WHERE nombre = (?))";
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
             pstmt.setString(1,user);
             ResultSet rs    = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                Object[] row = { rs.getInt("isbn"), rs.getString("titulo"), rs.getString("nombre")};
                 DefaultTableModel model = (DefaultTableModel) tablaLibros.getModel();

                model.addRow(row);
            }
       
    }   catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
     
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        etiquetaUsuario = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaLibros = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        etiquetaTitulo = new javax.swing.JLabel();
        etiquetaTablas = new javax.swing.JLabel();
        comboTablas = new javax.swing.JComboBox<>();
        etiquetaAcciones = new javax.swing.JLabel();
        comboAcciones = new javax.swing.JComboBox<>();
        etiquetaParametros = new javax.swing.JLabel();
        comboParametros = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textoI = new javax.swing.JTextField();
        textoT = new javax.swing.JTextField();
        textoA = new javax.swing.JTextField();
        botonAceptar = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        etiquetaUsuario.setText("USUARIOS");

        tablaLibros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISBN", "Titulo", "Autor"
            }
        ));
        jScrollPane1.setViewportView(tablaLibros);

        jButton2.setText("MODIFICAR USUARIO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        etiquetaTitulo.setText("LIBROS DISPONIBLES");

        etiquetaTablas.setText("TABLA: ");

        comboTablas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disponibles", "Mis libros" }));
        comboTablas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTablasActionPerformed(evt);
            }
        });

        etiquetaAcciones.setText("ACCIONES: ");

        comboAcciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguna", "Buscar", "Prestamo", "Devolver" }));
        comboAcciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboAccionesActionPerformed(evt);
            }
        });

        etiquetaParametros.setText("PARAMETROS: ");

        comboParametros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "ISBN", "Titulo", "Autor" }));
        comboParametros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboParametrosActionPerformed(evt);
            }
        });

        jLabel1.setText("ISBN: ");

        jLabel2.setText("TITULO: ");

        jLabel3.setText("AUTOR: ");

        botonAceptar.setText("ACEPTAR");
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        botonSalir.setText("SALIR");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiquetaUsuario)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(botonAceptar)
                                .addGap(18, 18, 18)
                                .addComponent(botonSalir))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(326, 326, 326)
                        .addComponent(etiquetaTitulo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 91, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textoA, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textoT, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiquetaTablas)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textoI, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comboTablas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(etiquetaAcciones)
                                .addGap(18, 18, 18)
                                .addComponent(comboAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(etiquetaParametros)
                                .addGap(18, 18, 18)
                                .addComponent(comboParametros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(132, 132, 132))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiquetaUsuario)
                .addGap(30, 30, 30)
                .addComponent(etiquetaTitulo)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaTablas)
                    .addComponent(comboTablas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaAcciones)
                    .addComponent(comboAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaParametros)
                    .addComponent(comboParametros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textoI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textoT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(botonAceptar)
                    .addComponent(botonSalir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        objMod.setVisible(true);
        objMod.rellenarCampos(nombreU, password);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void comboTablasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTablasActionPerformed
        JComboBox comboTablas = (JComboBox) evt.getSource(); //obtener accion realizada
        Object selected1 = comboTablas.getSelectedItem(); //obtener opcion escogida
        
        switch(selected1.toString()){
            case "Disponibles":
                limpiarTabla();
                limpiarTexto();
                textoI.setEnabled(false);
                textoT.setEnabled(false);
                textoA.setEnabled(false);
                selectDisponibles("S");
                comboParametros.setEnabled(false);
                etiquetaTitulo.setText("LIBROS DISPONIBLES");
                break;
            case "Mis libros":
                limpiarTabla();
                limpiarTexto();
                textoI.setEnabled(false);
                textoT.setEnabled(false);
                textoA.setEnabled(false);
                selectMisLibros(getNombreU());
                comboParametros.setEnabled(false);
                etiquetaTitulo.setText("MIS LIBROS");
                break;
            default:
                break;
        }
    }//GEN-LAST:event_comboTablasActionPerformed

    private void comboAccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboAccionesActionPerformed
       JComboBox comboAcciones = (JComboBox) evt.getSource(); //obtener accion realizada
        Object selected1 = comboAcciones.getSelectedItem(); //obtener opcion escogida
        
        switch(selected1.toString()){
            case "Ninguna":
                 limpiarTexto();
                textoI.setEnabled(false);
                textoT.setEnabled(false);
                textoA.setEnabled(false);
                comboParametros.setEnabled(false);
                break;
            case "Buscar":
                 limpiarTexto();
                textoI.setEnabled(false);
                textoT.setEnabled(false);
                textoA.setEnabled(false);
                comboParametros.setEnabled(true);
                setAccion("Buscar");
                break;
            case "Prestamo" :
                 limpiarTexto();
                textoI.setEnabled(true);
                textoT.setEnabled(false);
                textoA.setEnabled(false);
                comboParametros.setEnabled(false);
                setAccion("Prestamo");
                break;
            case "Devolver" :
                 limpiarTexto();
                textoI.setEnabled(true);
                textoT.setEnabled(false);
                textoA.setEnabled(false);
                comboParametros.setEnabled(false);
                setAccion("Devolver");
            default:
                break;
        }
    }//GEN-LAST:event_comboAccionesActionPerformed

    private void comboParametrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboParametrosActionPerformed
       JComboBox comboParametros = (JComboBox) evt.getSource(); //obtener accion realizada
        Object selected1 = comboParametros.getSelectedItem(); //obtener opcion escogida
        
        switch(selected1.toString()){
            case "Ninguno":
                 limpiarTexto();
                textoI.setEnabled(false);
                textoT.setEnabled(false);
                textoA.setEnabled(false);
                break;
            case "ISBN":
                 limpiarTexto();
                textoI.setEnabled(true);
                textoT.setEnabled(false);
                textoA.setEnabled(false);
                break;
            case "Titulo" :
                 limpiarTexto();
                textoI.setEnabled(false);
                textoT.setEnabled(true);
                textoA.setEnabled(false);
                break;
            case "Autor" :
                 limpiarTexto();
                textoI.setEnabled(false);
                textoT.setEnabled(false);
                textoA.setEnabled(true);
            default:
                break;
        }
    }//GEN-LAST:event_comboParametrosActionPerformed

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        String op = getAccion();
        int valor = 0;
        String aux = "0";
        switch(op){
            case "Buscar":
                if(!textoI.getText().isEmpty()){
                    valor = objMU.existeIsbn(Integer.parseInt(textoI.getText()));
                    if(valor != 0){
                        limpiarTabla();
                        selectIsbn(Integer.parseInt(textoI.getText()));
                    }
                    else{
                        objMen.setMensaje("No existe el libro");
                        objMen.setVisible(true);
                    }
                }
                else if(!textoT.getText().isEmpty()){
                    aux = objMU.existeTitulo(textoT.getText());
                    if(!"0".equals(aux)){
                        limpiarTabla();
                        selectIsbn(Integer.parseInt(textoI.getText()));
                    }
                    else{
                        objMen.setMensaje("No existe el libro");
                        objMen.setVisible(true);
                    }
                }
                else if (!textoA.getText().isEmpty()){
                   valor = objMU.existeAutor(textoA.getText());
                    if(valor != 0){
                        limpiarTabla();
                        selectIsbn(Integer.parseInt(textoI.getText()));
                    }
                    else{
                        
                        objMen.setMensaje("No existe el libro");
                        objMen.setVisible(true);
                    }
                }
                else {
                   objMen.setVisible(true);
                   objMen.setMensaje("Debes rellenar el campo escogido para realizar una busqueda");
                }
                break;
            case "Prestamo":
                if(!textoI.getText().isEmpty()){
                    valor = objMU.existeIsbn(Integer.parseInt(textoI.getText()));
                    if(valor != 0){
                    objMU.prestamo("N", getNombreU() ,Integer.parseInt(textoI.getText()));
                    objMen.setVisible(true);
                    objMen.setMensaje("El prestamo se realizo correctamente");
                    }
                    else{
                        objMen.setVisible(true);
                        objMen.setMensaje("No existe el libro seleccionado");
                    }
                }
                else{
                   objMen.setVisible(true);
                   objMen.setMensaje("Debes rellenar el ISBN para conseguir un prestamo"); 
                }
                break;
            case "Devolver":
                if(!textoI.getText().isEmpty()){
                    valor = objMU.existeIsbn(Integer.parseInt(textoI.getText()));
                    if(valor != 0){
                    objMU.devolver("S", Integer.parseInt(textoI.getText()));
                    objMen.setVisible(true);
                    objMen.setMensaje("El prestamo se realizo correctamente");
                    }
                    else{
                        objMen.setVisible(true);
                        objMen.setMensaje("No existe el libro seleccionado");
                    }
                }
                else{
                   objMen.setVisible(true);
                   objMen.setMensaje("Debes rellenar el ISBN para realizar una devolucion"); 
                }
                break;
        }
    }//GEN-LAST:event_botonAceptarActionPerformed

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_botonSalirActionPerformed

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InicioU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InicioU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InicioU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InicioU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InicioU().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonSalir;
    private javax.swing.JComboBox<String> comboAcciones;
    private javax.swing.JComboBox<String> comboParametros;
    private javax.swing.JComboBox<String> comboTablas;
    private javax.swing.JLabel etiquetaAcciones;
    private javax.swing.JLabel etiquetaParametros;
    private javax.swing.JLabel etiquetaTablas;
    private javax.swing.JLabel etiquetaTitulo;
    private javax.swing.JLabel etiquetaUsuario;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaLibros;
    private javax.swing.JTextField textoA;
    private javax.swing.JTextField textoI;
    private javax.swing.JTextField textoT;
    // End of variables declaration//GEN-END:variables
}
