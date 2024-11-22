/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UserInterface;

import DAO.PacienteDAO;
import TransferObject.Paciente;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author luis_
 */
public class RegistrarPaciente extends javax.swing.JFrame {

    /**
     * Creates new form RegistrarPaciente
     */
    public RegistrarPaciente() {
        initComponents();
        llenarComboBoxSexo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmbSexo = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        pwdContrasena = new javax.swing.JPasswordField();
        btnRegistrarPaciente = new javax.swing.JButton();
        btnTieneCuenta = new javax.swing.JButton();
        dcFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 900));
        setPreferredSize(new java.awt.Dimension(1200, 899));

        jPanel1.setBackground(new java.awt.Color(70, 141, 212));
        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 56));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 34)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Registro de cuenta");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jPanel2.setBackground(java.awt.Color.white);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(33, 0, 99));
        jLabel2.setText("¿Aún no tienes cuenta?");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setForeground(java.awt.Color.black);
        jLabel3.setText("Nombre:");

        txtNombre.setBackground(java.awt.Color.white);
        txtNombre.setForeground(java.awt.Color.black);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(java.awt.Color.black);
        jLabel4.setText("Apellidos:");

        txtApellidos.setBackground(java.awt.Color.white);
        txtApellidos.setForeground(java.awt.Color.black);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(java.awt.Color.black);
        jLabel5.setText("Sexo:");

        cmbSexo.setBackground(java.awt.Color.white);
        cmbSexo.setEditable(true);
        cmbSexo.setForeground(java.awt.Color.black);
        cmbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(java.awt.Color.black);
        jLabel6.setText("Fecha de nacimiento:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(java.awt.Color.black);
        jLabel7.setText("Correo:");

        txtCorreo.setBackground(java.awt.Color.white);
        txtCorreo.setForeground(java.awt.Color.black);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(java.awt.Color.black);
        jLabel8.setText("Contraseña:");

        pwdContrasena.setBackground(java.awt.Color.white);
        pwdContrasena.setForeground(java.awt.Color.black);

        btnRegistrarPaciente.setBackground(java.awt.Color.white);
        btnRegistrarPaciente.setForeground(java.awt.Color.black);
        btnRegistrarPaciente.setText("Registrarse");
        btnRegistrarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarPacienteActionPerformed(evt);
            }
        });

        btnTieneCuenta.setBackground(java.awt.Color.white);
        btnTieneCuenta.setForeground(java.awt.Color.black);
        btnTieneCuenta.setText("¿Ya tienes una cuenta?");
        btnTieneCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTieneCuentaActionPerformed(evt);
            }
        });

        dcFechaNacimiento.setBackground(java.awt.Color.white);
        dcFechaNacimiento.setForeground(java.awt.Color.black);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(33, 0, 99));
        jLabel9.setText("Registrate, es gratis.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(txtNombre)
                            .addComponent(jLabel4)
                            .addComponent(txtApellidos)
                            .addComponent(cmbSexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dcFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                            .addComponent(txtCorreo)
                            .addComponent(pwdContrasena))
                        .addComponent(jLabel2)
                        .addComponent(jLabel9))
                    .addComponent(btnRegistrarPaciente)
                    .addComponent(btnTieneCuenta))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(40, 40, 40)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pwdContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnRegistrarPaciente)
                .addGap(40, 40, 40)
                .addComponent(btnTieneCuenta)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean hayCamposVacios(){
        return txtNombre.getText().isEmpty()
                || txtApellidos.getText().isEmpty()
                || cmbSexo.getSelectedItem().toString().isEmpty()
                || cmbSexo.getSelectedItem() == null
                || dcFechaNacimiento.getDate() == null
                || txtCorreo.getText().isEmpty()
                || pwdContrasena.getPassword().length == 0;
    }
    
    private void btnTieneCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTieneCuentaActionPerformed
        // TODO: llevar a pantalla de inicio de sesion
    }//GEN-LAST:event_btnTieneCuentaActionPerformed

    private void btnRegistrarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarPacienteActionPerformed
        if (hayCamposVacios()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor llene todos los campos", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
        } else {
            registrarPaciente();
        }
    }//GEN-LAST:event_btnRegistrarPacienteActionPerformed

    private void llenarComboBoxSexo() {
        cmbSexo.removeAllItems();
        cmbSexo.addItem("masculino");
        cmbSexo.addItem("femenino");
        cmbSexo.addItem("otro");
    }

    private void registrarPaciente() {
        try {
            int resultado = getResultado();

            if (resultado == 1) {
                javax.swing.JOptionPane.showMessageDialog(this, "Paciente registrado correctamente", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "No se pudo registrar al paciente", "Error al registrar", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "No se pudo registrar al paciente", "Error al registrar", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int getResultado() throws SQLException {
        PacienteDAO pacienteDAO = new PacienteDAO();
        Paciente paciente = new Paciente(){
            {
                setNombre(txtNombre.getText());
                setApellido(txtApellidos.getText());
                setGenero(Objects.requireNonNull(cmbSexo.getSelectedItem()).toString());
                setFechaNacimiento(dcFechaNacimiento.getDate());
                setCorreo(txtCorreo.getText());
                setContrasena(new String(pwdContrasena.getPassword()));
            }
        };
        return pacienteDAO.agregarPaciente(paciente);
    }

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
            java.util.logging.Logger.getLogger(RegistrarPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarPaciente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrarPaciente;
    private javax.swing.JButton btnTieneCuenta;
    private javax.swing.JComboBox<String> cmbSexo;
    private com.toedter.calendar.JDateChooser dcFechaNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField pwdContrasena;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
