package UserInterface;

import java.util.ArrayList;
import java.sql.SQLException;
import DAO.EnfermedadDAO;
import DAO.AlergiaDAO;
import DAO.VacunaDAO;
import DAO.CirugiaDAO;
import DAO.MedicamentoDAO;
import TransferObject.Enfermedad;
import TransferObject.Alergia;
import TransferObject.Vacuna;
import TransferObject.Cirugia;
import TransferObject.Medicamento;
import javax.swing.JOptionPane;
import UserInterface.Plantillas.PlantillaAntecedente;
import com.toedter.calendar.JTextFieldDateEditor;

/**
 *
 * @author epale
 */
public class RegistroAntecedente extends javax.swing.JFrame {
    private final ArrayList<Enfermedad> listaEnfermedades = new ArrayList<>();
    private final ArrayList<Alergia> listaAlergias = new ArrayList<>();
    private ArrayList<Vacuna> listaVacunas;
    private ArrayList<Cirugia> listaCirugias;
    private final ArrayList<Medicamento> listaMedicamentos = new ArrayList<>();
    
    private ArrayList<Enfermedad> antecedenteEnfermedades;
    private ArrayList<Alergia> antecedenteAlergias;
    private ArrayList<Medicamento> antecedenteMedicamentos;
    
    private final BussinesLogic.SessionDetails PACIENTE = BussinesLogic.SessionDetails.getInstance();
    
    public RegistroAntecedente() {
        initComponents();
        
        recuperarAntecedenteEnfermedades();
        recuperarAntecedenteAlergias();
        recuperarAntecedenteVacunas();
        recuperarAntecedenteCirugias();
        recuperarAntecedenteMedicamentos();
        
        llenarComboBoxVacunas();
        llenarComboBoxCirugias();
        
        agregarRestriccionesDateChooser();
    }
    
    private void llenarComboBoxEnfermedades() {
        this.listaEnfermedades.clear();
        this.ComboBoxEnfermedades.removeAllItems();
        this.ComboBoxEnfermedades.addItem("<Seleccione una opción>");
        EnfermedadDAO enfermedadDAO = new EnfermedadDAO();
        ArrayList<Enfermedad> todasEnfermedades = new ArrayList<>();
        
        try {
            todasEnfermedades = enfermedadDAO.obtenerEnfermedades();
        } catch(SQLException error) {
            //TODO: manejar la excepción
            JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        
        if (!todasEnfermedades.isEmpty()) {
            for (Enfermedad e : todasEnfermedades) {
                if (!this.antecedenteEnfermedades.contains(e)) {
                    this.listaEnfermedades.add(e);
                    this.ComboBoxEnfermedades.addItem(e.getNombre());
                }
            }
        }
    }

    private void llenarComboBoxAlergias() {
        this.listaAlergias.clear();
        this.ComboBoxAlergias.removeAllItems();
        this.ComboBoxAlergias.addItem("<Seleccione una opción>");
        AlergiaDAO alergiDAO = new AlergiaDAO();
        ArrayList<Alergia> todasAlergias = new ArrayList<>();
        
        try {
            todasAlergias = alergiDAO.obtenerAlergia();
        } catch(SQLException error) {
            //TODO: manejar la excepción
            JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        
        if (!todasAlergias.isEmpty()) {
            for (Alergia a : todasAlergias) {
                if (!this.antecedenteAlergias.contains(a)) {
                    this.listaAlergias.add(a);
                    this.ComboBoxAlergias.addItem(a.getNombre());
                }
            }
        }
    }
    
    private void llenarComboBoxVacunas() {
        this.ComboBoxVacunas.removeAllItems();
        this.ComboBoxVacunas.addItem("<Seleccione una opción>");
        VacunaDAO vacunaDAO = new VacunaDAO();
        
        try {
            this.listaVacunas = vacunaDAO.obtenerVacunas();
        } catch(SQLException error) {
            //TODO: manejar la excepción
            JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        
        if (!this.listaVacunas.isEmpty()) {
            for (Vacuna v : this.listaVacunas) {
                this.ComboBoxVacunas.addItem(v.getNombre());
            }
        }
    }
    
    private void llenarComboBoxCirugias() {
        this.ComboBoxCirugias.removeAllItems();
        this.ComboBoxCirugias.addItem("<Seleccione una opción>");
        CirugiaDAO cirugiaDAO = new CirugiaDAO();
        
        try {
            this.listaCirugias = cirugiaDAO.obtenerCirugias();
        } catch (SQLException error) {
            //TODO: manejar la excepción
            JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        
        if (!this.listaCirugias.isEmpty()) {
            for (Cirugia c : this.listaCirugias) {
                this.ComboBoxCirugias.addItem(c.getNombre());
            }
        }
    }
    
    private void llenarComboBoxMedicamentos() {
        this.listaMedicamentos.clear();
        this.ComboBoxMedicamentos.removeAllItems();
        this.ComboBoxMedicamentos.addItem("<Seleccione una opción>");
        MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
        ArrayList<Medicamento> todosMedicamentos = new ArrayList<>();
        
        try {
            todosMedicamentos = medicamentoDAO.obtenerMedicamento();
        } catch (SQLException error) {
            //TODO: manejar la excepción
            JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        
        if (!todosMedicamentos.isEmpty()) {
            for (Medicamento m : todosMedicamentos) {
                if (!this.antecedenteMedicamentos.contains(m)) {
                    this.listaMedicamentos.add(m);
                    this.ComboBoxMedicamentos.addItem(m.getNombre());
                }
            }
        }
    }
    
    public final void recuperarAntecedenteEnfermedades() {
        this.PanelConsultaEnfermedades.removeAll();
        this.PanelConsultaEnfermedades.revalidate();
        this.PanelConsultaEnfermedades.repaint();
        EnfermedadDAO enfermedadDAO = new EnfermedadDAO();
        
        try {
           this.antecedenteEnfermedades = enfermedadDAO.obtenerEnfermedadesDelPaciente(this.PACIENTE.getId());
        } catch (SQLException error) {
            //TODO: registrar excepción
            JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        
        for (Enfermedad enfermedad : this.antecedenteEnfermedades) {
            this.PanelConsultaEnfermedades.add(new PlantillaAntecedente(this, PlantillaAntecedente.TipoAntecedente.Enfermedad, enfermedad.getId(), enfermedad.getNombre(), null));
        }
        
        this.llenarComboBoxEnfermedades();
    }
    
    public final void recuperarAntecedenteAlergias() {
        this.PanelConsultaAlergias.removeAll();
        this.PanelConsultaAlergias.revalidate();
        this.PanelConsultaAlergias.repaint();
        AlergiaDAO alergiaDAO = new AlergiaDAO();
        
        try {
            this.antecedenteAlergias = alergiaDAO.obtenerAlergiasDelPaciente(this.PACIENTE.getId());
        } catch (SQLException error) {
            //TODO: registrar excepción
            JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        
        for (Alergia alergia : this.antecedenteAlergias) {
            this.PanelConsultaAlergias.add(new PlantillaAntecedente(this, PlantillaAntecedente.TipoAntecedente.Alergia, alergia.getId(), alergia.getNombre(), null));
        }
        
        this.llenarComboBoxAlergias();
    }
    
    public final void recuperarAntecedenteVacunas() {
        this.PanelConsultaVacunas.removeAll();
        this.PanelConsultaVacunas.revalidate();
        this.PanelConsultaVacunas.repaint();
        VacunaDAO vacunaDAO = new VacunaDAO();
        ArrayList<Vacuna> antecedente = new ArrayList<>();
        
        try {
            antecedente = vacunaDAO.obtenerVacunasDelPaciente(this.PACIENTE.getId());
        } catch (SQLException error) {
            //TODO: registrar excepción
            JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        
        for (Vacuna vacuna : antecedente) {
            this.PanelConsultaVacunas.add(new PlantillaAntecedente(this, PlantillaAntecedente.TipoAntecedente.Vacuna, vacuna.getId(), vacuna.getNombre(), vacuna.getFechaAplicacion()));
        }
    }
    
    public final void recuperarAntecedenteCirugias() {
        this.PanelConsultaCirugias.removeAll();
        this.PanelConsultaCirugias.revalidate();
        this.PanelConsultaCirugias.repaint();
        CirugiaDAO cirugiaDAO = new CirugiaDAO();
        ArrayList<Cirugia> antecedente = new ArrayList<>();
        
        try {
            antecedente = cirugiaDAO.obtenerCirugiasDelPaciente(this.PACIENTE.getId());
        } catch (SQLException error) {
            //TODO: manejar excepción
            JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        
        for (Cirugia cirugia : antecedente) {
            this.PanelConsultaCirugias.add(new PlantillaAntecedente(this, PlantillaAntecedente.TipoAntecedente.Cirugia, cirugia.getId(), cirugia.getNombre(), cirugia.getFechaAplicacion()));
        }
    }
    
    public final void recuperarAntecedenteMedicamentos() {
        this.PanelConsultaMedicamentos.removeAll();
        this.PanelConsultaMedicamentos.revalidate();
        this.PanelConsultaMedicamentos.repaint();
        MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
        
        try {
            this.antecedenteMedicamentos = medicamentoDAO.obtenerMedicamentosDelPaciente(this.PACIENTE.getId());
        } catch (SQLException error) {
            //TODO: registrar excepción
            JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        
        for (Medicamento medicamento : this.antecedenteMedicamentos) {
            this.PanelConsultaMedicamentos.add(new PlantillaAntecedente(this, PlantillaAntecedente.TipoAntecedente.Medicamento, medicamento.getId(), medicamento.getNombre(), null));
        }
        
        this.llenarComboBoxMedicamentos();
    }
    
    private void agregarRestriccionesDateChooser() {
        JTextFieldDateEditor editorVacuna = (JTextFieldDateEditor) DateChooserFechaVacunacion.getDateEditor();
        editorVacuna.setEditable(false);
        
        JTextFieldDateEditor editorCirugia = (JTextFieldDateEditor) DateChooserFechaCirugia.getDateEditor();
        editorCirugia.setEditable(false);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        PanelEnfermedades = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        ComboBoxEnfermedades = new javax.swing.JComboBox<>();
        BotonAgregarEnfermedad = new javax.swing.JButton();
        Panel1 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PanelConsultaEnfermedades = new javax.swing.JPanel();
        PanelAlergias = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        ComboBoxAlergias = new javax.swing.JComboBox<>();
        BotonAgregarAlergia = new javax.swing.JButton();
        Panel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        PanelConsultaAlergias = new javax.swing.JPanel();
        PanelVacunas = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        ComboBoxVacunas = new javax.swing.JComboBox<>();
        BotonAgregarVacuna = new javax.swing.JButton();
        DateChooserFechaVacunacion = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        Panel3 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        PanelConsultaVacunas = new javax.swing.JPanel();
        PanelCirugias = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        ComboBoxCirugias = new javax.swing.JComboBox<>();
        BotonAgregarCirugia = new javax.swing.JButton();
        DateChooserFechaCirugia = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        Panel4 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        PanelConsultaCirugias = new javax.swing.JPanel();
        PanelMedicamentos = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        ComboBoxMedicamentos = new javax.swing.JComboBox<>();
        BotonAgregarMedicamento = new javax.swing.JButton();
        Panel5 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        PanelConsultaMedicamentos = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Antecedente médico");
        setBackground(new java.awt.Color(102, 204, 255));
        setResizable(false);

        jPanel8.setBackground(new java.awt.Color(70, 141, 212));
        jPanel8.setForeground(new java.awt.Color(0, 0, 0));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 35)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ANTECECEDENTES MÉDICOS");
        jLabel3.setAlignmentX(0.5F);
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel8.add(jLabel3, java.awt.BorderLayout.CENTER);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("<---");
        jButton1.setToolTipText("");
        jPanel8.add(jButton1, java.awt.BorderLayout.LINE_START);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setForeground(new java.awt.Color(0, 0, 0));
        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jTabbedPane1.setOpaque(true);

        PanelEnfermedades.setBackground(new java.awt.Color(255, 255, 255));
        PanelEnfermedades.setLayout(new java.awt.BorderLayout());

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setPreferredSize(new java.awt.Dimension(100, 150));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(51, 153, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(50, 35));

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Agregar enfermedad al antecedente");
        jLabel10.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(865, 115));

        ComboBoxEnfermedades.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxEnfermedades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Seleccione una opción>" }));
        ComboBoxEnfermedades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxEnfermedadesActionPerformed(evt);
            }
        });

        BotonAgregarEnfermedad.setBackground(new java.awt.Color(0, 102, 255));
        BotonAgregarEnfermedad.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BotonAgregarEnfermedad.setForeground(new java.awt.Color(255, 255, 255));
        BotonAgregarEnfermedad.setText("Agregar al antecedente");
        BotonAgregarEnfermedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarEnfermedadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BotonAgregarEnfermedad)
                    .addComponent(ComboBoxEnfermedades, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(376, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(ComboBoxEnfermedades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BotonAgregarEnfermedad)
                .addGap(18, 18, 18))
        );

        jPanel10.add(jPanel2, java.awt.BorderLayout.SOUTH);

        PanelEnfermedades.add(jPanel10, java.awt.BorderLayout.NORTH);

        Panel1.setBackground(new java.awt.Color(255, 255, 255));
        Panel1.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1000, 35));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(269, 35));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Antecedente de enfermedades");
        jLabel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(jLabel8, java.awt.BorderLayout.CENTER);

        Panel1.add(jPanel1, java.awt.BorderLayout.NORTH);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(6, 35));

        PanelConsultaEnfermedades.setBackground(new java.awt.Color(255, 255, 255));
        PanelConsultaEnfermedades.setLayout(new javax.swing.BoxLayout(PanelConsultaEnfermedades, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(PanelConsultaEnfermedades);

        Panel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        PanelEnfermedades.add(Panel1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Enfermedades", PanelEnfermedades);

        PanelAlergias.setBackground(new java.awt.Color(255, 255, 255));
        PanelAlergias.setLayout(new java.awt.BorderLayout());

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setPreferredSize(new java.awt.Dimension(100, 150));
        jPanel15.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(51, 153, 255));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(50, 35));

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Agregar alergia al antecedente");
        jLabel11.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jPanel15.add(jPanel4, java.awt.BorderLayout.NORTH);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(865, 115));

        ComboBoxAlergias.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxAlergias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Seleccione una opción>" }));
        ComboBoxAlergias.setMinimumSize(new java.awt.Dimension(170, 30));
        ComboBoxAlergias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxAlergiasActionPerformed(evt);
            }
        });

        BotonAgregarAlergia.setBackground(new java.awt.Color(0, 102, 255));
        BotonAgregarAlergia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BotonAgregarAlergia.setForeground(new java.awt.Color(255, 255, 255));
        BotonAgregarAlergia.setText("Agregar al antecedente");
        BotonAgregarAlergia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarAlergiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BotonAgregarAlergia)
                    .addComponent(ComboBoxAlergias, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(376, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(ComboBoxAlergias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BotonAgregarAlergia)
                .addGap(18, 18, 18))
        );

        jPanel15.add(jPanel5, java.awt.BorderLayout.SOUTH);

        PanelAlergias.add(jPanel15, java.awt.BorderLayout.NORTH);

        Panel2.setBackground(new java.awt.Color(255, 255, 255));
        Panel2.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(51, 153, 255));
        jPanel6.setForeground(new java.awt.Color(255, 255, 255));
        jPanel6.setMaximumSize(new java.awt.Dimension(1000, 35));
        jPanel6.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel6.setPreferredSize(new java.awt.Dimension(269, 35));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Antecedente de alergias");
        jLabel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel6.add(jLabel9, java.awt.BorderLayout.CENTER);

        Panel2.add(jPanel6, java.awt.BorderLayout.NORTH);

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(6, 35));

        PanelConsultaAlergias.setBackground(new java.awt.Color(255, 255, 255));
        PanelConsultaAlergias.setLayout(new javax.swing.BoxLayout(PanelConsultaAlergias, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane2.setViewportView(PanelConsultaAlergias);

        Panel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        PanelAlergias.add(Panel2, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Alergias", PanelAlergias);

        PanelVacunas.setBackground(new java.awt.Color(255, 255, 255));
        PanelVacunas.setLayout(new java.awt.BorderLayout());

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setPreferredSize(new java.awt.Dimension(100, 150));
        jPanel16.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(51, 153, 255));
        jPanel7.setForeground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(50, 35));

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Agregar vacuna al antecedente");
        jLabel12.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jPanel16.add(jPanel7, java.awt.BorderLayout.NORTH);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setPreferredSize(new java.awt.Dimension(865, 150));

        ComboBoxVacunas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxVacunas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Seleccione una opción>" }));
        ComboBoxVacunas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxVacunasActionPerformed(evt);
            }
        });

        BotonAgregarVacuna.setBackground(new java.awt.Color(0, 102, 255));
        BotonAgregarVacuna.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BotonAgregarVacuna.setForeground(new java.awt.Color(255, 255, 255));
        BotonAgregarVacuna.setText("Agregar al antecedente");
        BotonAgregarVacuna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarVacunaActionPerformed(evt);
            }
        });

        DateChooserFechaVacunacion.setDateFormatString("dd / MM / yyyy");
        DateChooserFechaVacunacion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        DateChooserFechaVacunacion.setMaxSelectableDate(new java.util.Date());
        DateChooserFechaVacunacion.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Fecha de aplicación de la vacuna:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DateChooserFechaVacunacion, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ComboBoxVacunas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(168, 168, 168)
                .addComponent(BotonAgregarVacuna)
                .addGap(14, 14, 14))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addComponent(ComboBoxVacunas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DateChooserFechaVacunacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(BotonAgregarVacuna))
                .addGap(18, 18, 18))
        );

        jPanel16.add(jPanel9, java.awt.BorderLayout.SOUTH);

        PanelVacunas.add(jPanel16, java.awt.BorderLayout.NORTH);

        Panel3.setBackground(new java.awt.Color(255, 255, 255));
        Panel3.setMaximumSize(new java.awt.Dimension(2147483647, 100));
        Panel3.setPreferredSize(new java.awt.Dimension(269, 100));
        Panel3.setLayout(new java.awt.BorderLayout());

        jPanel11.setBackground(new java.awt.Color(51, 153, 255));
        jPanel11.setForeground(new java.awt.Color(255, 255, 255));
        jPanel11.setMaximumSize(new java.awt.Dimension(1000, 35));
        jPanel11.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel11.setPreferredSize(new java.awt.Dimension(269, 35));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jLabel13.setBackground(new java.awt.Color(0, 0, 0));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Antecedente de vacunas");
        jLabel13.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel11.add(jLabel13, java.awt.BorderLayout.CENTER);

        Panel3.add(jPanel11, java.awt.BorderLayout.NORTH);

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(6, 35));

        PanelConsultaVacunas.setBackground(new java.awt.Color(255, 255, 255));
        PanelConsultaVacunas.setLayout(new javax.swing.BoxLayout(PanelConsultaVacunas, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane3.setViewportView(PanelConsultaVacunas);

        Panel3.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        PanelVacunas.add(Panel3, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Vacunas", PanelVacunas);

        PanelCirugias.setBackground(new java.awt.Color(255, 255, 255));
        PanelCirugias.setLayout(new java.awt.BorderLayout());

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setPreferredSize(new java.awt.Dimension(100, 150));
        jPanel17.setLayout(new java.awt.BorderLayout());

        jPanel12.setBackground(new java.awt.Color(51, 153, 255));
        jPanel12.setForeground(new java.awt.Color(255, 255, 255));
        jPanel12.setPreferredSize(new java.awt.Dimension(50, 35));

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Agregar cirugía al antecedente");
        jLabel14.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jPanel17.add(jPanel12, java.awt.BorderLayout.NORTH);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setPreferredSize(new java.awt.Dimension(865, 150));

        ComboBoxCirugias.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxCirugias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Seleccione una opción>" }));
        ComboBoxCirugias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxCirugiasActionPerformed(evt);
            }
        });

        BotonAgregarCirugia.setBackground(new java.awt.Color(0, 102, 255));
        BotonAgregarCirugia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BotonAgregarCirugia.setForeground(new java.awt.Color(255, 255, 255));
        BotonAgregarCirugia.setText("Agregar al antecedente");
        BotonAgregarCirugia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarCirugiaActionPerformed(evt);
            }
        });

        DateChooserFechaCirugia.setDateFormatString("dd / MM / yyyy");
        DateChooserFechaCirugia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        DateChooserFechaCirugia.setMaxSelectableDate(new java.util.Date());
        DateChooserFechaCirugia.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Fecha de aplicación de la cirugía:");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DateChooserFechaCirugia, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ComboBoxCirugias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(168, 168, 168)
                .addComponent(BotonAgregarCirugia)
                .addGap(14, 14, 14))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addComponent(ComboBoxCirugias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DateChooserFechaCirugia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(BotonAgregarCirugia))
                .addGap(18, 18, 18))
        );

        jPanel17.add(jPanel18, java.awt.BorderLayout.SOUTH);

        PanelCirugias.add(jPanel17, java.awt.BorderLayout.NORTH);

        Panel4.setBackground(new java.awt.Color(255, 255, 255));
        Panel4.setMaximumSize(new java.awt.Dimension(2147483647, 100));
        Panel4.setPreferredSize(new java.awt.Dimension(269, 100));
        Panel4.setLayout(new java.awt.BorderLayout());

        jPanel19.setBackground(new java.awt.Color(51, 153, 255));
        jPanel19.setForeground(new java.awt.Color(255, 255, 255));
        jPanel19.setMaximumSize(new java.awt.Dimension(1000, 35));
        jPanel19.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel19.setPreferredSize(new java.awt.Dimension(269, 35));
        jPanel19.setLayout(new java.awt.BorderLayout());

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Antecedente de cirugías");
        jLabel15.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel19.add(jLabel15, java.awt.BorderLayout.CENTER);

        Panel4.add(jPanel19, java.awt.BorderLayout.NORTH);

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(6, 35));

        PanelConsultaCirugias.setBackground(new java.awt.Color(255, 255, 255));
        PanelConsultaCirugias.setLayout(new javax.swing.BoxLayout(PanelConsultaCirugias, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane4.setViewportView(PanelConsultaCirugias);

        Panel4.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        PanelCirugias.add(Panel4, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Cirugías", PanelCirugias);

        PanelMedicamentos.setBackground(new java.awt.Color(255, 255, 255));
        PanelMedicamentos.setLayout(new java.awt.BorderLayout());

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setPreferredSize(new java.awt.Dimension(100, 150));
        jPanel20.setLayout(new java.awt.BorderLayout());

        jPanel13.setBackground(new java.awt.Color(51, 153, 255));
        jPanel13.setForeground(new java.awt.Color(255, 255, 255));
        jPanel13.setPreferredSize(new java.awt.Dimension(50, 35));

        jLabel16.setBackground(new java.awt.Color(0, 0, 0));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Agregar medicamento al antecedente");
        jLabel16.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jPanel20.add(jPanel13, java.awt.BorderLayout.NORTH);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setPreferredSize(new java.awt.Dimension(865, 115));

        ComboBoxMedicamentos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxMedicamentos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Seleccione una opción>" }));
        ComboBoxMedicamentos.setMinimumSize(new java.awt.Dimension(170, 30));
        ComboBoxMedicamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxMedicamentosActionPerformed(evt);
            }
        });

        BotonAgregarMedicamento.setBackground(new java.awt.Color(0, 102, 255));
        BotonAgregarMedicamento.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BotonAgregarMedicamento.setForeground(new java.awt.Color(255, 255, 255));
        BotonAgregarMedicamento.setText("Agregar al antecedente");
        BotonAgregarMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarMedicamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BotonAgregarMedicamento)
                    .addComponent(ComboBoxMedicamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(376, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(ComboBoxMedicamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BotonAgregarMedicamento)
                .addGap(18, 18, 18))
        );

        jPanel20.add(jPanel21, java.awt.BorderLayout.SOUTH);

        PanelMedicamentos.add(jPanel20, java.awt.BorderLayout.NORTH);

        Panel5.setBackground(new java.awt.Color(255, 255, 255));
        Panel5.setLayout(new java.awt.BorderLayout());

        jPanel22.setBackground(new java.awt.Color(51, 153, 255));
        jPanel22.setForeground(new java.awt.Color(255, 255, 255));
        jPanel22.setMaximumSize(new java.awt.Dimension(1000, 35));
        jPanel22.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel22.setPreferredSize(new java.awt.Dimension(269, 35));
        jPanel22.setLayout(new java.awt.BorderLayout());

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Antecedente de medicamentos");
        jLabel17.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel22.add(jLabel17, java.awt.BorderLayout.CENTER);

        Panel5.add(jPanel22, java.awt.BorderLayout.NORTH);

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane5.setPreferredSize(new java.awt.Dimension(6, 35));

        PanelConsultaMedicamentos.setBackground(new java.awt.Color(255, 255, 255));
        PanelConsultaMedicamentos.setLayout(new javax.swing.BoxLayout(PanelConsultaMedicamentos, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane5.setViewportView(PanelConsultaMedicamentos);

        Panel5.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        PanelMedicamentos.add(Panel5, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Medicamentos", PanelMedicamentos);

        jTabbedPane1.setSelectedComponent(PanelEnfermedades);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Enfermedades");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboBoxEnfermedadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxEnfermedadesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxEnfermedadesActionPerformed

    private void BotonAgregarEnfermedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarEnfermedadActionPerformed
        int indiceSeleccionado = this.ComboBoxEnfermedades.getSelectedIndex();
        
        if (indiceSeleccionado != 0) {
            EnfermedadDAO enfermedadDAO = new EnfermedadDAO();
            int resultado;
                
            try {
                resultado = enfermedadDAO.agregarEnfermedadAlAntecedente(this.PACIENTE.getId(), this.listaEnfermedades.get(indiceSeleccionado - 1).getId());
            } catch (SQLException error) {
                //TODO: manejar excepción
                JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
                resultado = -1;
            }
            
            if (resultado == 1) {
                this.recuperarAntecedenteEnfermedades();
                JOptionPane.showMessageDialog(null, "La enfermedad fue registrada en el antecedente exitosamente", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else if (resultado == 0) {
                JOptionPane.showMessageDialog(null, "Ocurrió un problema al tratar de agregar la enfermedad al antecedente", "Operación fallida", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor seleccione una opción de la lista", "Sin selección", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_BotonAgregarEnfermedadActionPerformed

    private void ComboBoxAlergiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxAlergiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxAlergiasActionPerformed

    private void BotonAgregarAlergiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarAlergiaActionPerformed
        int indiceSeleccionado = this.ComboBoxAlergias.getSelectedIndex();
        
        if (indiceSeleccionado != 0) {
            AlergiaDAO alergiaDAO = new AlergiaDAO();
            int resultado;
                
            try {
                resultado = alergiaDAO.agregarAlergiaAlAntecedente(this.PACIENTE.getId(), this.listaAlergias.get(indiceSeleccionado - 1).getId());
            } catch (SQLException error) {
                //TODO: manejar excepción
                JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
                resultado = -1;
            }
            
            if (resultado == 1) {
                this.recuperarAntecedenteAlergias();
                JOptionPane.showMessageDialog(null, "La alergia fue registrada en el antecedente exitosamente", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else if (resultado == 0) {
                JOptionPane.showMessageDialog(null, "Ocurrió un problema al tratar de agregar la alergia al antecedente", "Operación fallida", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor seleccione una opción de la lista", "Sin selección", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_BotonAgregarAlergiaActionPerformed

    private void ComboBoxVacunasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxVacunasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxVacunasActionPerformed

    private void BotonAgregarVacunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarVacunaActionPerformed
        int indiceSeleccionado = this.ComboBoxVacunas.getSelectedIndex();
        java.util.Date fechaVacunacion = this.DateChooserFechaVacunacion.getDate();
        
        if (indiceSeleccionado == 0) {
            JOptionPane.showMessageDialog(null, "Por favor seleccione una opción de la lista", "Vacuna no seleccionada", JOptionPane.WARNING_MESSAGE);
        } else if (fechaVacunacion == null) {
            JOptionPane.showMessageDialog(null, "Por favor seleccione una fecha de vacunación", "Fecha de vacunación no seleccionada", JOptionPane.WARNING_MESSAGE);
        } else {
            VacunaDAO vacunaDAO = new VacunaDAO();
            int resultado;
            
            try {
                resultado = vacunaDAO.agregarVacunaAlAntecedente(this.PACIENTE.getId(), this.listaVacunas.get(indiceSeleccionado - 1).getId(), new java.sql.Date(fechaVacunacion.getTime()));
            } catch (SQLException error) {
                //TODO: manejar excepción
                JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
                resultado = -1;
            }
            
            if (resultado == 1) {
                this.recuperarAntecedenteVacunas();
                JOptionPane.showMessageDialog(null, "La vacuna fue registrada en el antecedente exitosamente", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else if (resultado == 0) {
                JOptionPane.showMessageDialog(null, "Ocurrió un problema al tratar de agregar la vacuna al antecedente", "Operación fallida", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BotonAgregarVacunaActionPerformed

    private void ComboBoxCirugiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxCirugiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxCirugiasActionPerformed

    private void BotonAgregarCirugiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarCirugiaActionPerformed
        int indiceSeleccionado = this.ComboBoxCirugias.getSelectedIndex();
        java.util.Date fechaCirugia = this.DateChooserFechaCirugia.getDate();
        
        if (indiceSeleccionado == 0) {
            JOptionPane.showMessageDialog(null, "Por favor seleccione una opción de la lista", "Cirugia no seleccionada", JOptionPane.WARNING_MESSAGE);
        } else if (fechaCirugia == null) {
            JOptionPane.showMessageDialog(null, "Por favor seleccione una fecha de cirugia", "Fecha de cirugia no seleccionada", JOptionPane.WARNING_MESSAGE);
        } else {
            CirugiaDAO cirugiaDAO = new CirugiaDAO();
            int resultado;
            
            try {
                resultado = cirugiaDAO.agregarCirugiaAlAntecedente(this.PACIENTE.getId(), this.listaCirugias.get(indiceSeleccionado - 1).getId(), new java.sql.Date(fechaCirugia.getTime()));
            } catch (SQLException error) {
                //TODO: manejar excepción
                JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
                resultado = -1;
            }
            
            if (resultado == 1) {
                this.recuperarAntecedenteCirugias();
                JOptionPane.showMessageDialog(null, "La cirugia fue registrada en el antecedente exitosamente", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else if (resultado == 0) {
                JOptionPane.showMessageDialog(null, "Ocurrió un problema al tratar de agregar la cirugia al antecedente", "Operación fallida", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BotonAgregarCirugiaActionPerformed

    private void ComboBoxMedicamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxMedicamentosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxMedicamentosActionPerformed

    private void BotonAgregarMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarMedicamentoActionPerformed
        int indiceSeleccionado = this.ComboBoxMedicamentos.getSelectedIndex();
        
        if (indiceSeleccionado != 0) {
            MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
            int resultado;
                
            try {
                resultado = medicamentoDAO.agregarMedicamentoAlAntecedente(this.PACIENTE.getId(), this.listaMedicamentos.get(indiceSeleccionado - 1).getId());
            } catch (SQLException error) {
                //TODO: manejar excepción
                JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
                resultado = -1;
            }
            
            if (resultado == 1) {
                this.recuperarAntecedenteMedicamentos();
                JOptionPane.showMessageDialog(null, "El medicamento fue registrada en el antecedente exitosamente", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else if (resultado == 0) {
                JOptionPane.showMessageDialog(null, "Ocurrió un problema al tratar de agregar el medicamento al antecedente", "Operación fallida", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor seleccione una opción de la lista", "Sin selección", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_BotonAgregarMedicamentoActionPerformed

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
            java.util.logging.Logger.getLogger(RegistroAntecedente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroAntecedente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroAntecedente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroAntecedente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroAntecedente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAgregarAlergia;
    private javax.swing.JButton BotonAgregarCirugia;
    private javax.swing.JButton BotonAgregarEnfermedad;
    private javax.swing.JButton BotonAgregarMedicamento;
    private javax.swing.JButton BotonAgregarVacuna;
    private javax.swing.JComboBox<String> ComboBoxAlergias;
    private javax.swing.JComboBox<String> ComboBoxCirugias;
    private javax.swing.JComboBox<String> ComboBoxEnfermedades;
    private javax.swing.JComboBox<String> ComboBoxMedicamentos;
    private javax.swing.JComboBox<String> ComboBoxVacunas;
    private com.toedter.calendar.JDateChooser DateChooserFechaCirugia;
    private com.toedter.calendar.JDateChooser DateChooserFechaVacunacion;
    private javax.swing.JPanel Panel1;
    private javax.swing.JPanel Panel2;
    private javax.swing.JPanel Panel3;
    private javax.swing.JPanel Panel4;
    private javax.swing.JPanel Panel5;
    private javax.swing.JPanel PanelAlergias;
    private javax.swing.JPanel PanelCirugias;
    private javax.swing.JPanel PanelConsultaAlergias;
    private javax.swing.JPanel PanelConsultaCirugias;
    private javax.swing.JPanel PanelConsultaEnfermedades;
    private javax.swing.JPanel PanelConsultaMedicamentos;
    private javax.swing.JPanel PanelConsultaVacunas;
    private javax.swing.JPanel PanelEnfermedades;
    private javax.swing.JPanel PanelMedicamentos;
    private javax.swing.JPanel PanelVacunas;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
