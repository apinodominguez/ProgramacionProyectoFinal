/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Metodos.MetodosLibros;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author apinodominguez
 */
public class InicioA extends javax.swing.JFrame {

    MetodosLibros objL = new MetodosLibros();
    Mensaje objM = new Mensaje();
    
    private String accion;
    private String tabla;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }
    
    public InicioA() {
        initComponents();
        limpiarTabla();
        selectTodo();
        textoI.setEnabled(false);
        textoT.setEnabled(false);
        textoA.setEnabled(false);
        textoD.setEnabled(false);
        textoU.setEnabled(false);
        comboParametros.setEnabled(false);
        comboAcciones.setEnabled(false);
        limpiarTablaAutores();
        etiquetaTablaAutores.setVisible(false);
    }

    
    
    public void limpiarTexto(){
      textoI.setText("");
      textoT.setText("");
      textoA.setText("");
      textoD.setText("");
      textoU.setText("");
    }
    
    public void limpiarTabla(){
        DefaultTableModel dtm = (DefaultTableModel) tablaLibros.getModel();
        dtm.setRowCount(0);
    } 
    
    public void limpiarTablaAutores(){
        DefaultTableModel dtm = (DefaultTableModel) tablaAutores.getModel();
        dtm.setRowCount(0);
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
    
    public void selectTodo(){
        String sql = "SELECT isbn, titulo, autores.nombre AS autor, disponible, users.nombre AS usuario  FROM libros INNER JOIN autores ON autores.id = libros.autorid INNER JOIN users ON users.id = libros.usuario";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                Object[] row = { rs.getInt("isbn"), rs.getString("titulo"), rs.getString("autor"), rs.getString("disponible"), rs.getString("usuario")};
                 DefaultTableModel model = (DefaultTableModel) tablaLibros.getModel();

                model.addRow(row);
            }
       
    }   catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     public void selectIsbn(int isbn){
        String sql = "SELECT isbn, titulo, autores.nombre AS autor, disponible, users.nombre AS usuario  FROM libros INNER JOIN autores ON autores.id = libros.autorid INNER JOIN users ON users.id = libros.usuario where isbn = (?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
             pstmt.setInt(1,isbn);
             ResultSet rs    = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                Object[] row = { rs.getInt("isbn"), rs.getString("titulo"), rs.getString("autor"), rs.getString("disponible"), rs.getString("usuario")};
                 DefaultTableModel model = (DefaultTableModel) tablaLibros.getModel();

                model.addRow(row);
            }
       
    }   catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void selectTitulo(String titulo){
        String sql = "SELECT isbn, titulo, autores.nombre AS autor, disponible, users.nombre AS usuario  FROM libros INNER JOIN autores ON autores.id = libros.autorid INNER JOIN users ON users.id = libros.usuario where titulo = (?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
             pstmt.setString(1,titulo);
             ResultSet rs    = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                Object[] row = { rs.getInt("isbn"), rs.getString("titulo"), rs.getString("autor"), rs.getString("disponible"), rs.getString("usuario")};
                 DefaultTableModel model = (DefaultTableModel) tablaLibros.getModel();

                model.addRow(row);
            }
       
    }   catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void selectAutor(String autor){
        String sql = "SELECT isbn, titulo, autores.nombre AS autor, disponible, users.nombre AS usuario  FROM libros INNER JOIN autores ON autores.id = libros.autorid INNER JOIN users ON users.id = libros.usuario where autorid= (Select id from autores where nombre = (?))";
        try (Connection conn = this.connect(); 
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
             pstmt.setString(1,autor);
             ResultSet rs    = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                Object[] row = { rs.getInt("isbn"), rs.getString("titulo"), rs.getString("autor"), rs.getString("disponible"), rs.getString("usuario")};
                 DefaultTableModel model = (DefaultTableModel) tablaLibros.getModel();

                model.addRow(row);
            }
       
    }   catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void selectDisponible(String disponible){
        String sql = "SELECT isbn, titulo, autores.nombre AS autor, disponible, users.nombre AS usuario  FROM libros INNER JOIN autores ON autores.id = libros.autorid INNER JOIN users ON users.id = libros.usuario where disponible = (?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
             pstmt.setString(1,disponible);
             ResultSet rs    = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                Object[] row = { rs.getInt("isbn"), rs.getString("titulo"), rs.getString("autor"), rs.getString("disponible"), rs.getString("usuario")};
                 DefaultTableModel model = (DefaultTableModel) tablaLibros.getModel();

                model.addRow(row);
            }
       
    }   catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void selectUsuario(String usuario){
        String sql = "SELECT isbn, titulo, autores.nombre AS autor, disponible, users.nombre AS usuario  FROM libros INNER JOIN autores ON autores.id = libros.autorid INNER JOIN users ON users.id = libros.usuario where usuario = (SELECT id from users where nombre = (?))";
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
             pstmt.setString(1,usuario);
             ResultSet rs    = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                Object[] row = { rs.getInt("isbn"), rs.getString("titulo"), rs.getString("autor"), rs.getString("disponible"), rs.getString("usuario")};
                 DefaultTableModel model = (DefaultTableModel) tablaLibros.getModel();

                model.addRow(row);
            }
       
    }   catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void selectTodosAutores(){
        String sql = "Select id, nombre from Autores";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                Object[] row = { rs.getInt("id"), rs.getString("nombre")};
                 DefaultTableModel model = (DefaultTableModel) tablaAutores.getModel();

                model.addRow(row);
            }
       
    }   catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void selectTodosUsuarios(){
    String sql = "Select id, nombre from users";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                Object[] row = { rs.getInt("id"), rs.getString("nombre")};
                 DefaultTableModel model = (DefaultTableModel) tablaAutores.getModel();

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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaLibros = new javax.swing.JTable();
        comboAcciones = new javax.swing.JComboBox<>();
        etiquetaAcciones = new javax.swing.JLabel();
        etiquetaParametros = new javax.swing.JLabel();
        comboParametros = new javax.swing.JComboBox<>();
        etiquetaI = new javax.swing.JLabel();
        etiquetaT = new javax.swing.JLabel();
        etiquetaA = new javax.swing.JLabel();
        etiquetaD = new javax.swing.JLabel();
        etiquetaU = new javax.swing.JLabel();
        textoD = new javax.swing.JTextField();
        textoT = new javax.swing.JTextField();
        textoI = new javax.swing.JTextField();
        textoU = new javax.swing.JTextField();
        textoA = new javax.swing.JTextField();
        botonAceptar = new javax.swing.JButton();
        comboTablas = new javax.swing.JComboBox<>();
        etiquetaTablas = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaAutores = new javax.swing.JTable();
        etiquetaTablaAutores = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("TABLA LIBROS");

        tablaLibros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISBN", "Titulo", "Autor", "Disponible", "Usuario"
            }
        ));
        jScrollPane1.setViewportView(tablaLibros);

        comboAcciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "Mostrar todo", "Buscar", "Añadir", "Borrar", "Modificar" }));
        comboAcciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboAccionesActionPerformed(evt);
            }
        });

        etiquetaAcciones.setText("ACCIONES");

        etiquetaParametros.setText("PARAMETROS: ");

        comboParametros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "ISBN", "Titulo", "Autor", "Disponible", "Usuario" }));
        comboParametros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboParametrosActionPerformed(evt);
            }
        });

        etiquetaI.setText("ISBN:");

        etiquetaT.setText("TITULO: ");

        etiquetaA.setText("AUTOR: ");

        etiquetaD.setText("DISPONIBLE:");

        etiquetaU.setText("USUARIO: ");

        botonAceptar.setText("ACEPTAR");
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        comboTablas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "Libros", "Autor", "Usuario" }));
        comboTablas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTablasActionPerformed(evt);
            }
        });

        etiquetaTablas.setText("TABLAS:");

        tablaAutores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre"
            }
        ));
        jScrollPane2.setViewportView(tablaAutores);

        etiquetaTablaAutores.setText("TABLA AUTORES");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addGap(104, 104, 104))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(etiquetaTablaAutores)
                .addGap(221, 221, 221))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonAceptar)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(etiquetaD)
                                            .addComponent(etiquetaU))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textoU)
                                            .addComponent(textoD)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(etiquetaA)
                                            .addComponent(etiquetaT))
                                        .addGap(39, 39, 39)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textoT)
                                            .addComponent(textoA)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(etiquetaI)
                                            .addComponent(etiquetaTablas))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(comboTablas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(etiquetaAcciones)
                                                .addGap(18, 18, 18)
                                                .addComponent(comboAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(22, 22, 22))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(41, 41, 41)
                                                .addComponent(textoI, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(19, 19, 19)
                                .addComponent(etiquetaParametros)
                                .addGap(18, 18, 18)
                                .addComponent(comboParametros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(442, 442, 442)
                        .addComponent(jLabel1)))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiquetaAcciones)
                            .addComponent(etiquetaParametros)
                            .addComponent(comboParametros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboTablas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiquetaTablas))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etiquetaI)
                            .addComponent(textoI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etiquetaT)
                            .addComponent(textoT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etiquetaA)
                            .addComponent(textoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etiquetaD)
                            .addComponent(textoD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etiquetaU)
                            .addComponent(textoU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonAceptar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addComponent(etiquetaTablaAutores)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboAccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboAccionesActionPerformed
         JComboBox comboAcciones = (JComboBox) evt.getSource(); //obtener accion realizada
        Object selected1 = comboAcciones.getSelectedItem(); //obtener opcion escogida
        
        switch(selected1.toString()){
            case "Ninguno":
                 limpiarTexto();
                textoI.setEnabled(false);
                textoT.setEnabled(false);
                textoA.setEnabled(false);
                textoD.setEnabled(false);
                textoU.setEnabled(false);
                comboParametros.setEnabled(false);
                break;
            case "Buscar":
                 limpiarTexto();
                textoI.setEnabled(false);
                textoT.setEnabled(false);
                textoA.setEnabled(false);
                textoD.setEnabled(false);
                textoU.setEnabled(false);
                comboParametros.setEnabled(true);
                setAccion("Buscar");
                break;
            case "Añadir" :
                limpiarTexto();
                textoI.setEnabled(false);
                textoT.setEnabled(true);
                textoA.setEnabled(true);
                textoD.setEnabled(false);
                textoU.setEnabled(false);
                comboParametros.setEnabled(false);
                setAccion("Añadir");
                break;
            case "Borrar" :
                 limpiarTexto();
                textoI.setEnabled(true);
                textoT.setEnabled(false);
                textoA.setEnabled(false);
                textoD.setEnabled(false);
                textoU.setEnabled(false);
                comboParametros.setEnabled(false);
                setAccion("Borrar");
                break;
            case "Modificar" :
                limpiarTexto();
                textoI.setEnabled(true);
                textoT.setEnabled(false);
                textoA.setEnabled(false);
                textoD.setEnabled(false);
                textoU.setEnabled(false);
                comboParametros.setEnabled(true);
                setAccion("Modificar");
                break;
            case "Mostrar todo" :
                limpiarTexto();
                limpiarTabla();
                textoI.setEnabled(false);
                textoT.setEnabled(false);
                textoA.setEnabled(false);
                textoD.setEnabled(false);
                textoU.setEnabled(false);
                comboParametros.setEnabled(false);
                selectTodo();
                break;
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
                textoD.setEnabled(false);
                textoU.setEnabled(false);
                break;
            case "ISBN":
                 limpiarTexto();
                textoI.setEnabled(true);
                textoT.setEnabled(false);
                textoA.setEnabled(false);
                textoD.setEnabled(false);
                textoU.setEnabled(false);
                break;
            case "Titulo" :
                limpiarTexto();
                if ("Modificar".equals(getAccion())){
                    textoI.setEnabled(true);
                    textoT.setEnabled(true);
                    textoA.setEnabled(false);
                    textoD.setEnabled(false);
                    textoU.setEnabled(false);
                }
                else{
                    textoI.setEnabled(false);
                    textoT.setEnabled(true);
                    textoA.setEnabled(false);
                    textoD.setEnabled(false);
                    textoU.setEnabled(false);
                }
                break;
            case "Autor" :
                limpiarTexto();
                if ("Modificar".equals(getAccion())){
                    textoI.setEnabled(true);
                    textoT.setEnabled(false);
                    textoA.setEnabled(true);
                    textoD.setEnabled(false);
                    textoU.setEnabled(false);
                }
                else{
                    textoI.setEnabled(false);
                    textoT.setEnabled(false);
                    textoA.setEnabled(true);
                    textoD.setEnabled(false);
                    textoU.setEnabled(false);
                }
                break;
            case "Disponible" :
                limpiarTexto();
                if ("Modificar".equals(getAccion())){
                    textoI.setEnabled(true);
                    textoT.setEnabled(false);
                    textoA.setEnabled(false);
                    textoD.setEnabled(true);
                    textoU.setEnabled(false);
                }
                else{
                    textoI.setEnabled(false);
                    textoT.setEnabled(false);
                    textoA.setEnabled(false);
                    textoD.setEnabled(true);
                    textoU.setEnabled(false);
                }
                break;
            case "Usuario":
                limpiarTexto();
                if ("Modificar".equals(getAccion())){
                    textoI.setEnabled(true);
                    textoT.setEnabled(false);
                    textoA.setEnabled(false);
                    textoD.setEnabled(false);
                    textoU.setEnabled(true);
                }
                else{
                    textoI.setEnabled(false);
                    textoT.setEnabled(false);
                    textoA.setEnabled(false);
                    textoD.setEnabled(false);
                    textoU.setEnabled(true);
                }
                break;
            default:
                break;
        }
    }//GEN-LAST:event_comboParametrosActionPerformed

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        int valor = 0;
        String aux = "0";
        String op = getAccion();
        
        switch(op){
            case "Buscar":
                if(!textoI.getText().isEmpty()){
                    valor = objL.existeIsbn(Integer.parseInt(textoI.getText()));
                    if(valor != 0){
                    limpiarTabla();
                    selectIsbn(Integer.parseInt(textoI.getText()));
                    }
                    else{
                        objM.setMensaje("No existe el libro");
                        objM.setVisible(true);
                    }
                    
                }
                else if(!textoT.getText().isEmpty()){
                    aux = objL.existeTitulo(textoT.getText());
                    if(!"0".equals(aux)){
                    limpiarTabla();
                    selectTitulo(textoT.getText());
                    }
                    else{
                        objM.setMensaje("No existe el libro");
                        objM.setVisible(true);
                    }
                }
                else if(!textoA.getText().isEmpty()){
                    valor = objL.existeAutor(textoA.getText());
                    if(valor != 0){
                     limpiarTabla();
                     selectAutor(textoA.getText());
                    }
                    objM.setMensaje("No existe el libro");
                    objM.setVisible(true);
                }
                else if(!textoD.getText().isEmpty()){
                    if("S".equals(textoD.getText()) || textoD.getText() == "N"){
                    aux = objL.existeDisponible(textoD.getText());
                    if(!"0".equals(aux)){
                     limpiarTabla();
                     selectDisponible(textoD.getText());
                    }
                    else{
                    objM.setMensaje("No existe el libro");
                    objM.setVisible(true);
                    }
                    }
                    else{
                     objM.setMensaje("Para disponible solamente existen los valores S y N");
                    objM.setVisible(true);   
                    }
                }
                else if(!textoU.getText().isEmpty()){
                     valor = objL.existeUsuario(textoU.getText());
                    if(valor != 0){
                    limpiarTabla();
                    selectUsuario(textoU.getText());
                    }
                    else{
                    objM.setMensaje("No existe el libro");
                    objM.setVisible(true); 
                    }
                }
                else{
                    objM.setVisible(true);
                    objM.setMensaje("Rellena el campo correspondiente para realizar una busqueda");
                }
                break;
            case "Añadir": 
                if (!textoT.getText().isEmpty() && textoA.getText().isEmpty()){
                    objL.añadirLibro(textoT.getText(), "ninguno", "S", "Libre");
                }
                else if (!textoT.getText().isEmpty() && !textoA.getText().isEmpty()){
                    objL.añadirLibro(textoT.getText(), textoA.getText(), "S", "Libre");
                }
                else{
                    objM.setVisible(true);
                    objM.setMensaje("Escribe al menos el titulo del libro");
                }
                break;
            case "Borrar" :
                if (!textoI.getText().isEmpty()){
                    valor = objL.existeIsbn(Integer.parseInt(textoI.getText()));
                    if(valor != 0){
                    objL.borrarLibros(Integer.parseInt(textoI.getText()));
                    }
                    else{
                    objM.setMensaje("No existe el libro");
                    objM.setVisible(true);  
                    }
                }
                else {
                   objM.setVisible(true);
                   objM.setMensaje("Introduce el campo borrar"); 
                }
                break;
            case "Modificar" :
                if (!textoI.getText().isEmpty()){
                    valor = objL.existeIsbn(Integer.parseInt(textoI.getText()));
                    if(valor != 0){
                    if (!textoT.getText().isEmpty()){
                        objL.modificarTitulo(textoT.getText(), Integer.parseInt(textoI.getText()));
                    }
                    else if (!textoA.getText().isEmpty()){
                        objL.modificarAutor(textoA.getText(), Integer.parseInt(textoI.getText()));
                    }
                    else if (!textoD.getText().isEmpty()){
                        objL.modificarDisponible(textoD.getText(), Integer.parseInt(textoI.getText()));
                    }
                    else if (!textoU.getText().isEmpty()){
                        objL.modificarUsuario(textoU.getText(), Integer.parseInt(textoI.getText()));
                    }
                    }
                    else{
                    objM.setMensaje("No existe el libro");
                    objM.setVisible(true);  
                    }
                }
                else{
                   objM.setVisible(true);
                   objM.setMensaje("Introduce el ISBN y un campo a mayores"); 
                }
        }
    }//GEN-LAST:event_botonAceptarActionPerformed

    private void comboTablasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTablasActionPerformed
       JComboBox comboTablas = (JComboBox) evt.getSource(); //obtener accion realizada
       Object selected1 = comboTablas.getSelectedItem();
        
       switch(selected1.toString()){
            case "Ninguno":
                limpiarTexto();
                textoI.setEnabled(false);
                textoT.setEnabled(false);
                textoA.setEnabled(false);
                textoD.setEnabled(false);
                textoU.setEnabled(false);
                etiquetaTablaAutores.setVisible(false);
                comboParametros.setEnabled(false);
                comboAcciones.setEnabled(false);
                limpiarTablaAutores();
                break;
            case "Libros":
                limpiarTexto();
                textoI.setEnabled(false);
                textoT.setEnabled(false);
                textoA.setEnabled(false);
                textoD.setEnabled(false);
                textoU.setEnabled(false);
                etiquetaTablaAutores.setVisible(false);
                comboParametros.setEnabled(false);
                comboAcciones.setEnabled(true);
                limpiarTabla();
                selectTodo();
                limpiarTablaAutores();
                setTabla("Libros");
                break;
            case "Autor":
                limpiarTexto();
                textoI.setEnabled(false);
                textoT.setEnabled(false);
                textoA.setEnabled(false);
                textoD.setEnabled(false);
                textoU.setEnabled(false);
                comboParametros.setEnabled(false);
                comboAcciones.setEnabled(true);
                limpiarTablaAutores();
                selectTodosAutores();
                etiquetaTablaAutores.setVisible(true);
                etiquetaTablaAutores.setText("Autores");
                setTabla("Autor");
                break;    
            case "Usuario":
                limpiarTexto();
                textoI.setEnabled(false);
                textoT.setEnabled(false);
                textoA.setEnabled(false);
                textoD.setEnabled(false);
                textoU.setEnabled(false);
                comboParametros.setEnabled(false);
                comboAcciones.setEnabled(true);
                limpiarTablaAutores();
                selectTodosUsuarios();
                etiquetaTablaAutores.setVisible(true);
                etiquetaTablaAutores.setText("Usuarios");
                setTabla("Usuario");
                break;
            default:
                break;
       }
    }//GEN-LAST:event_comboTablasActionPerformed

    
    
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
            java.util.logging.Logger.getLogger(InicioA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InicioA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InicioA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InicioA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InicioA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JComboBox<String> comboAcciones;
    private javax.swing.JComboBox<String> comboParametros;
    private javax.swing.JComboBox<String> comboTablas;
    private javax.swing.JLabel etiquetaA;
    private javax.swing.JLabel etiquetaAcciones;
    private javax.swing.JLabel etiquetaD;
    private javax.swing.JLabel etiquetaI;
    private javax.swing.JLabel etiquetaParametros;
    private javax.swing.JLabel etiquetaT;
    private javax.swing.JLabel etiquetaTablaAutores;
    private javax.swing.JLabel etiquetaTablas;
    private javax.swing.JLabel etiquetaU;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaAutores;
    private javax.swing.JTable tablaLibros;
    private javax.swing.JTextField textoA;
    private javax.swing.JTextField textoD;
    private javax.swing.JTextField textoI;
    private javax.swing.JTextField textoT;
    private javax.swing.JTextField textoU;
    // End of variables declaration//GEN-END:variables
}
