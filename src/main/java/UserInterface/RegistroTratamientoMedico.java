package UserInterface;

import DAO.DosisDAO;
import DAO.MedicamentoDAO;
import DAO.TratamientoDAO;
import TransferObject.Dosis;
import TransferObject.Medicamento;
import TransferObject.Tratamiento;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author kahun
 */
public class RegistroTratamientoMedico extends javax.swing.JFrame {
    private final javax.swing.JFrame VENTANA_ANTERIOR;
    
    public RegistroTratamientoMedico(javax.swing.JFrame panelAnterior) {
        this.VENTANA_ANTERIOR = panelAnterior;
        initComponents();
        cargarMedicamentoComboBox();
        cargarComboBoxMetodosAdministracion();
        //cargarMedicamentoComboBox();
        setLocationRelativeTo(null);

    }

    private void cargarComboBoxMetodosAdministracion() {
        cmbMetodoAdministracion.removeAllItems();
        String[] elementos = {"Oral", "Intravenosa", "Intramuscular", "Ocular", "Nasal", "Cutánea"};

        for (String elemento : elementos) {
            cmbMetodoAdministracion.addItem(elemento);
        }

    }

    private void cargarMedicamentoComboBox() {
        cmbMedicamentos.removeAllItems();
        try {
            MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
            ArrayList<Medicamento> listaMedicamentos = medicamentoDAO.obtenerMedicamento();

            

            for (Medicamento medicamento : listaMedicamentos) {
                System.out.println("Añadiendo: " + medicamento.getNombre());
                cmbMedicamentos.addItem(medicamento.getNombre());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar medicamentos en el ComboBox", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void guardarTratamientoConDosis() {
        try {
            // Obtener la fecha seleccionada
            java.sql.Date fechaInicio = obtenerFechaSeleccionada();
            if (fechaInicio == null) {
                return;
            }

            // ID del paciente, ajusta este valor según tu lógica de negocio
            int idPaciente = BussinesLogic.SessionDetails.getInstance().getId();

            // Guardar el tratamiento y obtener el ID
            int idTratamiento = guardarTratamiento(fechaInicio, idPaciente);
            if (idTratamiento == -1) {
                mostrarMensajeError("No se pudo registrar el tratamiento.");
                return;
            }

            // Obtener el ID del medicamento seleccionado
            int idMedicamento = obtenerIdMedicamentoSeleccionado();
            if (idMedicamento == -1) {
                mostrarMensajeError("Error al obtener el ID del medicamento.");
                return;
            }

            // Crear y guardar la dosis
            Dosis dosis = crearDosis(idTratamiento, idMedicamento);
            int resultadoDosis = guardarDosis(dosis);
            if (resultadoDosis > 0) {
                mostrarMensajeExito("Tratamiento y dosis registrados exitosamente.");
            } else {
                mostrarMensajeError("No se pudo registrar la dosis.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            mostrarMensajeError("Ocurrió un error al registrar el tratamiento y la dosis.");
        }
    }

// Métodos auxiliares para modularizar el proceso
    private java.sql.Date obtenerFechaSeleccionada() {
        if (jdcFechaInicio.getDate() == null) {
            mostrarMensajeError("Debe seleccionar una fecha de inicio.");
            return null;
        }
        return new java.sql.Date(jdcFechaInicio.getDate().getTime());
    }

    private int guardarTratamiento(java.sql.Date fechaInicio, int idPaciente) throws SQLException {
        TratamientoDAO tratamientoDAO = new TratamientoDAO();
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setIdPaciente(idPaciente);
        tratamiento.setFechaInicio(fechaInicio);
        return tratamientoDAO.agregarTratamiento(tratamiento);
    }

    private int obtenerIdMedicamentoSeleccionado() throws SQLException {
        String nombreMedicamento = (String) cmbMedicamentos.getSelectedItem();
        MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
        return medicamentoDAO.obtenerIdMedicamentoPorNombre(nombreMedicamento);
    }

    private Dosis crearDosis(int idTratamiento, int idMedicamento) {
        Dosis dosis = new Dosis();
        dosis.setIdTratamiento(idTratamiento);
        dosis.setIdMedicamento(idMedicamento);
        dosis.setDiasDuracion((int)this.SpinnerDuracion.getValue());
        dosis.setCantidad(txtCantidad.getText());
        dosis.setMetodoAdministracion((String) cmbMetodoAdministracion.getSelectedItem());
        dosis.setNota(txtaNota.getText());
        return dosis;
    }

    private int guardarDosis(Dosis dosis) throws SQLException {
        DosisDAO dosisDAO = new DosisDAO();
        return dosisDAO.agregarDosisAlTratamiento(dosis);
    }

// Métodos para mostrar mensajes
    private void mostrarMensajeExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
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
        btnRegresar = new javax.swing.JButton();
        lblFechaInicio = new javax.swing.JLabel();
        lblMedicamento = new javax.swing.JLabel();
        lblDuracion = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmbMedicamentos = new javax.swing.JComboBox<>();
        txtCantidad = new javax.swing.JTextField();
        cmbMetodoAdministracion = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaNota = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jdcFechaInicio = new com.toedter.calendar.JDateChooser();
        SpinnerDuracion = new javax.swing.JSpinner();
        lblDuracion1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblRegistrarTratamiento = new javax.swing.JLabel();
        ButtonRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(java.awt.Color.white);

        btnRegresar.setBackground(new java.awt.Color(0, 153, 255));
        btnRegresar.setBorderPainted(false);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        lblFechaInicio.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblFechaInicio.setText("Fecha de inicio");

        lblMedicamento.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblMedicamento.setText("Medicamento");

        lblDuracion.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDuracion.setText("Duración");

        lblCantidad.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblCantidad.setText("Cantidad");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Método de adminsitración");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Nota");

        cmbMedicamentos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbMetodoAdministracion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbMetodoAdministracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMetodoAdministracionActionPerformed(evt);
            }
        });

        txtaNota.setColumns(20);
        txtaNota.setRows(5);
        jScrollPane1.setViewportView(txtaNota);

        jButton1.setBackground(new java.awt.Color(181, 220, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton1.setText("Guardar cambios");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblDuracion1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDuracion1.setText("días");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmbMetodoAdministracion, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1)
                                    .addComponent(lblFechaInicio)
                                    .addComponent(jdcFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblDuracion)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(SpinnerDuracion, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblDuracion1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMedicamento)
                                    .addComponent(lblCantidad)
                                    .addComponent(cmbMedicamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(121, 121, 121))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblMedicamento)
                        .addGap(18, 18, 18)
                        .addComponent(cmbMedicamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblFechaInicio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jdcFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCantidad)
                    .addComponent(lblDuracion))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SpinnerDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDuracion1))
                .addGap(40, 40, 40)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(cmbMetodoAdministracion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        jPanel2.setBackground(new java.awt.Color(70, 141, 212));

        lblRegistrarTratamiento.setFont(new java.awt.Font("Segoe UI", 1, 35)); // NOI18N
        lblRegistrarTratamiento.setForeground(java.awt.Color.white);
        lblRegistrarTratamiento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRegistrarTratamiento.setText("Registrar tratamiento");

        ButtonRegresar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ButtonRegresar.setIcon(new javax.swing.ImageIcon("src/main/java/UserInterface/Recursos/icono_regresar.png"));
        ButtonRegresar.setToolTipText("");
        ButtonRegresar.setBorderPainted(false);
        ButtonRegresar.setContentAreaFilled(false);
        ButtonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(ButtonRegresar)
                .addGap(227, 227, 227)
                .addComponent(lblRegistrarTratamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ButtonRegresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblRegistrarTratamiento, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                .addGap(0, 1, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void cmbMetodoAdministracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMetodoAdministracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMetodoAdministracionActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        guardarTratamientoConDosis();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ButtonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonRegresarActionPerformed
        this.VENTANA_ANTERIOR.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ButtonRegresarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonRegresar;
    private javax.swing.JSpinner SpinnerDuracion;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbMedicamentos;
    public javax.swing.JComboBox<String> cmbMetodoAdministracion;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcFechaInicio;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblDuracion1;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblMedicamento;
    private javax.swing.JLabel lblRegistrarTratamiento;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextArea txtaNota;
    // End of variables declaration//GEN-END:variables
}
