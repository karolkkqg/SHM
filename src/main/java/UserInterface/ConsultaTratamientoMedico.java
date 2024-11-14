/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UserInterface;

import DAO.MedicamentoDAO;
import DAO.TratamientoDAO;
import TransferObject.Dosis;
import TransferObject.Medicamento;
import TransferObject.Tratamiento;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kahun
 */
public class ConsultaTratamientoMedico extends javax.swing.JFrame {

    /**
     * Creates new form ConsultaTratamientoMedico
     */
    public ConsultaTratamientoMedico() {
        initComponents();
        cargarTratamientosEnTabla(1);
    }
    
    private void cargarTratamientosEnTabla(int idPaciente) {
        try {
            // Configurar el modelo de tabla con las columnas necesarias
            DefaultTableModel modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("Fecha de Inicio");
            modeloTabla.addColumn("Nombre del Medicamento");
            modeloTabla.addColumn("Duración");
            modeloTabla.addColumn("Cantidad");
            modeloTabla.addColumn("Método");
            modeloTabla.addColumn("Nota");

            // Obtener los tratamientos y dosis del paciente
            TratamientoDAO tratamientoDAO = new TratamientoDAO();
            ArrayList<Tratamiento> listaTratamientos = tratamientoDAO.obtenerTratamientosConDosis(idPaciente);

            // Llenar el modelo de tabla con los datos obtenidos
            for (Tratamiento tratamiento : listaTratamientos) {
                for (Dosis dosis : tratamiento.getDosisList()) {
                    String fechaInicio = tratamiento.getFechaInicio().toString();
                    String nombreMedicamento = dosis.getNombreMedicamento();
                    String duracion = String.valueOf(dosis.getDiasDuracion());
                    String cantidad = dosis.getCantidad();
                    String metodo = dosis.getMetodoAdministracion();
                    String nota = dosis.getNota();
                    
                    // Agregar cada fila con la fecha de inicio y el nombre del medicamento
                    modeloTabla.addRow(new Object[]{fechaInicio, nombreMedicamento, duracion, cantidad, metodo, nota});
                }
            }

            // Asignar el modelo de tabla al JTable
            tblTratamientosMedicos.setModel(modeloTabla);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar tratamientos en la tabla", "Error", JOptionPane.ERROR_MESSAGE);
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

        jPanel2 = new javax.swing.JPanel();
        lblRegistrarTratamiento = new javax.swing.JLabel();
        lblRegistrarTratamiento1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTratamientosMedicos = new javax.swing.JTable();
        btnVisualizarTratamiento = new javax.swing.JButton();
        btnModificarTratamiento = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(70, 141, 212));

        lblRegistrarTratamiento.setFont(new java.awt.Font("Segoe UI", 1, 35)); // NOI18N
        lblRegistrarTratamiento.setForeground(java.awt.Color.white);
        lblRegistrarTratamiento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRegistrarTratamiento.setText("Visualizar tratamiento");

        lblRegistrarTratamiento1.setFont(new java.awt.Font("Segoe UI", 1, 35)); // NOI18N
        lblRegistrarTratamiento1.setForeground(java.awt.Color.white);
        lblRegistrarTratamiento1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRegistrarTratamiento1.setText("Visualizar tratamiento");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 343, Short.MAX_VALUE)
                .addComponent(lblRegistrarTratamiento1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(395, 395, 395))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblRegistrarTratamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(418, 418, 418))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblRegistrarTratamiento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRegistrarTratamiento1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        jPanel3.setBackground(java.awt.Color.white);

        tblTratamientosMedicos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblTratamientosMedicos);

        btnVisualizarTratamiento.setText("Visualizar tratamiento");
        btnVisualizarTratamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarTratamientoActionPerformed(evt);
            }
        });

        btnModificarTratamiento.setText("Modificar tratamiento");
        btnModificarTratamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarTratamientoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnVisualizarTratamiento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnModificarTratamiento))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVisualizarTratamiento)
                    .addComponent(btnModificarTratamiento))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVisualizarTratamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarTratamientoActionPerformed
        int filaSeleccionada = tblTratamientosMedicos.getSelectedRow();
    
    if (filaSeleccionada != -1) { // Verifica que haya una fila seleccionada
        // Obtén los valores de la fila seleccionada
        String fechaInicio = (String) tblTratamientosMedicos.getValueAt(filaSeleccionada, 0);
        String medicamento = (String) tblTratamientosMedicos.getValueAt(filaSeleccionada, 1);
        String duracion = (String) tblTratamientosMedicos.getValueAt(filaSeleccionada, 2);
        String cantidad = (String) tblTratamientosMedicos.getValueAt(filaSeleccionada, 3);
        String metodoAdministracion = (String) tblTratamientosMedicos.getValueAt(filaSeleccionada, 4);
        String nota = (String) tblTratamientosMedicos.getValueAt(filaSeleccionada, 5);

        // Abre la ventana de visualización y pasa los datos
        VisualizacionTratamientoMedico visualizacion = new VisualizacionTratamientoMedico(
            fechaInicio, medicamento, duracion, cantidad, metodoAdministracion, nota
        );
        visualizacion.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona un tratamiento para visualizar.");
    }
    }//GEN-LAST:event_btnVisualizarTratamientoActionPerformed

    private void btnModificarTratamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarTratamientoActionPerformed
        int filaSeleccionada = tblTratamientosMedicos.getSelectedRow();
    
    if (filaSeleccionada != -1) { // Verifica que haya una fila seleccionada
        // Obtén los valores de la fila seleccionada
        String fechaInicio = (String) tblTratamientosMedicos.getValueAt(filaSeleccionada, 0);
        String medicamento = (String) tblTratamientosMedicos.getValueAt(filaSeleccionada, 1);
        int duracion = Integer.parseInt(tblTratamientosMedicos.getValueAt(filaSeleccionada, 2).toString());
        String cantidad = (String) tblTratamientosMedicos.getValueAt(filaSeleccionada, 3);
        String metodoAdministracion = (String) tblTratamientosMedicos.getValueAt(filaSeleccionada, 4);
        String nota = (String) tblTratamientosMedicos.getValueAt(filaSeleccionada, 5);

        // Abre la ventana de visualización y pasa los datos
        ModificacionTratamientoMedico visualizacion = null;
        try {
            try {
                visualizacion = new ModificacionTratamientoMedico(
                        fechaInicio, medicamento, duracion, cantidad, metodoAdministracion, nota
                );
            } catch (ParseException ex) {
                Logger.getLogger(ConsultaTratamientoMedico.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaTratamientoMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
        visualizacion.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona un tratamiento para visualizar.");
    }
    }//GEN-LAST:event_btnModificarTratamientoActionPerformed

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
            java.util.logging.Logger.getLogger(ConsultaTratamientoMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaTratamientoMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaTratamientoMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaTratamientoMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultaTratamientoMedico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnModificarTratamiento;
    private javax.swing.JButton btnVisualizarTratamiento;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblRegistrarTratamiento;
    private javax.swing.JLabel lblRegistrarTratamiento1;
    private javax.swing.JTable tblTratamientosMedicos;
    // End of variables declaration//GEN-END:variables
}
