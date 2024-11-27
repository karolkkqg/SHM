package UserInterface.Plantillas;

import UserInterface.RegistroAntecedente;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author epale
 */
public class PlantillaAntecedente extends javax.swing.JPanel {
    public enum TipoAntecedente {
        Enfermedad,
        Alergia,
        Vacuna,
        Cirugia,
        Medicamento
    }
    
    private TipoAntecedente tipoAntecedente;
    private final int idAntecedente;
    private java.util.Date fechaAplicacion;
    private RegistroAntecedente panelPadre;
    
    private final BussinesLogic.SessionDetails PACIENTE = BussinesLogic.SessionDetails.getInstance();
    
    public PlantillaAntecedente(RegistroAntecedente panelPadre, TipoAntecedente tipoAntecedente, int idAntecedente, String nombreAntecedente, java.util.Date fecha) {
        initComponents();
        this.panelPadre = panelPadre;
        this.tipoAntecedente = tipoAntecedente;
        this.idAntecedente = idAntecedente;
        this.LabelNombre.setText(nombreAntecedente);
        if (fecha != null) {
            this.LabelFechaRegistro.setText("Fecha de aplicación:");
            SimpleDateFormat sdf = new SimpleDateFormat("dd / MM / yyyy");
            this.LabelFecha.setText(sdf.format(fecha));
            this.fechaAplicacion = fecha;
        }
    }

    private void eliminarAntecedenteEnfermedad() {
        DAO.EnfermedadDAO enfermedadDAO = new DAO.EnfermedadDAO();
        int resultado;
        
        try {
            resultado = enfermedadDAO.eliminarEnfermedadDeAntecedente(this.PACIENTE.getId(), this.idAntecedente);
        } catch (java.sql.SQLException error) {
            //TODO: loggear excepcion
            JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
            resultado = -1;
        }
        
        if (resultado == 1) {
            JOptionPane.showMessageDialog(null, "La enfermedad fue borrada de su antecedente exitosamente", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (resultado == 0) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar eliminar la enfermedad", "Operación fallida", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void eliminarAntecedenteAlergia() {
        DAO.AlergiaDAO alergiaDAO = new DAO.AlergiaDAO();
        int resultado;
        
        try {
            resultado = alergiaDAO.eliminarAlergiaDeAntecedente(this.PACIENTE.getId(), this.idAntecedente);
        } catch (java.sql.SQLException error) {
            //TODO: loggear excepcion
            JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
            resultado = -1;
        }
        
        if (resultado == 1) {
            JOptionPane.showMessageDialog(null, "La alergia fue borrada de su antecedente exitosamente", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (resultado == 0) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar eliminar la alergia", "Operación fallida", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void eliminarAntecedenteVacuna() {
        DAO.VacunaDAO vacunaDAO = new DAO.VacunaDAO();
        int resultado;
        
        try {
            resultado = vacunaDAO.eliminarVacunaDeAntecedente(this.PACIENTE.getId(), this.idAntecedente, new java.sql.Date(this.fechaAplicacion.getTime()));
        } catch (java.sql.SQLException error) {
            //TODO: loggear excepcion
            JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
            resultado = -1;
        }
        
        if (resultado >= 1) {
            JOptionPane.showMessageDialog(null, "La vacuna fue borrada de su antecedente exitosamente", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (resultado == 0) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar eliminar la vacuna", "Operación fallida", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void eliminarAntecedenteCirugia() {
        DAO.CirugiaDAO cirugiaDAO = new DAO.CirugiaDAO();
        int resultado;
        
        try {
            resultado = cirugiaDAO.eliminarCirugiaDeAntecedente(this.PACIENTE.getId(), this.idAntecedente, new java.sql.Date(this.fechaAplicacion.getTime()));
        } catch (java.sql.SQLException error) {
            //TODO: loggear excepcion
            JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
            resultado = -1;
        }
        
        if (resultado >= 1) {
            JOptionPane.showMessageDialog(null, "La cirugia fue borrada de su antecedente exitosamente", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (resultado == 0) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar eliminar la cirugia", "Operación fallida", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void eliminarAntecedenteMedicamento() {
        DAO.MedicamentoDAO medicamentoDAO = new DAO.MedicamentoDAO();
        int resultado;
        
        try {
            resultado = medicamentoDAO.eliminarMedicamentoDeAntecedente(this.PACIENTE.getId(), this.idAntecedente);
        } catch (java.sql.SQLException error) {
            //TODO: loggear excepcion
            JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
            resultado = -1;
        }
        
        if (resultado >= 1) {
            JOptionPane.showMessageDialog(null, "El medicamento fue borrado de su antecedente exitosamente", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (resultado == 0) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar eliminar el medicamento", "Operación fallida", JOptionPane.ERROR_MESSAGE);
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
        jPanel1 = new javax.swing.JPanel();
        LabelFecha = new javax.swing.JLabel();
        LabelFechaRegistro = new javax.swing.JLabel();
        LabelNombre = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 7, 7, 7));
        setMaximumSize(new java.awt.Dimension(1280, 85));
        setMinimumSize(new java.awt.Dimension(300, 50));
        setPreferredSize(new java.awt.Dimension(650, 85));
        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(229, 242, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 4, true));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(229, 242, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        LabelFecha.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LabelFecha.setText("No disponible");
        LabelFecha.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 5, 0));
        jPanel1.add(LabelFecha, java.awt.BorderLayout.PAGE_END);

        LabelFechaRegistro.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LabelFechaRegistro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LabelFechaRegistro.setText("Fecha de ocurrencia: ");
        LabelFechaRegistro.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 0, 0));
        jPanel1.add(LabelFechaRegistro, java.awt.BorderLayout.PAGE_START);

        jPanel2.add(jPanel1, java.awt.BorderLayout.CENTER);

        LabelNombre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LabelNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelNombre.setText("nombre");
        LabelNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 5, 0));
        LabelNombre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(LabelNombre, java.awt.BorderLayout.PAGE_START);

        jButton1.setBackground(new java.awt.Color(181, 220, 255));
        jButton1.setText("Quitar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, java.awt.BorderLayout.LINE_END);

        add(jPanel2, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        switch (this.tipoAntecedente) {
            case Enfermedad -> {
                eliminarAntecedenteEnfermedad();
                this.panelPadre.recuperarAntecedenteEnfermedades();
            }
            case Alergia -> {
                eliminarAntecedenteAlergia();
                this.panelPadre.recuperarAntecedenteAlergias();
            }
            case Vacuna -> {
                eliminarAntecedenteVacuna();
                this.panelPadre.recuperarAntecedenteVacunas();
            }
            case Cirugia -> {
                eliminarAntecedenteCirugia();
                this.panelPadre.recuperarAntecedenteCirugias();
            }
            case Medicamento -> {
                eliminarAntecedenteMedicamento();
                this.panelPadre.recuperarAntecedenteMedicamentos();
            }
            default -> {break;}
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelFecha;
    private javax.swing.JLabel LabelFechaRegistro;
    private javax.swing.JLabel LabelNombre;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
