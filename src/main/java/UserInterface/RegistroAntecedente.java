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

/**
 *
 * @author epale
 */
public class RegistroAntecedente extends javax.swing.JFrame {
    private ArrayList<Enfermedad> listaEnfermedades = new ArrayList<>();
    private ArrayList<Alergia> listaAlergias;
    private ArrayList<Vacuna> listaVacunas;
    private ArrayList<Cirugia> listaCirugias;
    private ArrayList<Medicamento> listaMedicamentos;
    private ArrayList<Enfermedad> antecedenteEnfermedades;
    private int idPaciente = 20;
    
    public RegistroAntecedente() {
        initComponents();
        recuperarAntecedenteEnfermedades();
        
        llenarComboBoxAlergias();
        llenarComboBoxVacunas();
        llenarComboBoxCirugias();
        llenarComboBoxMedicamentos();
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
        AlergiaDAO alergiDAO = new AlergiaDAO();
        
        try {
            this.listaAlergias = alergiDAO.obtenerAlergia();
        } catch(SQLException error) {
            //TODO: manejar la excepción
            JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        
        if (!this.listaAlergias.isEmpty()) {
            for (Alergia a : this.listaAlergias) {
                this.ComboBoxAlergias.addItem(a.getNombre());
            }
        }
    }
    
    private void llenarComboBoxVacunas() {
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
        MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
        
        try {
            this.listaMedicamentos = medicamentoDAO.obtenerMedicamento();
        } catch (SQLException error) {
            //TODO: manejar la excepción
            JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        
        if (!this.listaMedicamentos.isEmpty()) {
            for (Medicamento m : this.listaMedicamentos) {
                this.ComboBoxMedicamentos.addItem(m.getNombre());
            }
        }
    }
    
    public final void recuperarAntecedenteEnfermedades() {
        this.PanelConsultaEnfermedades.removeAll();
        this.PanelConsultaEnfermedades.revalidate();
        this.PanelConsultaEnfermedades.repaint();
        EnfermedadDAO enfermedadDAO = new EnfermedadDAO();
        
        try {
           this.antecedenteEnfermedades = enfermedadDAO.obtenerEnfermedadesDelPaciente(this.idPaciente);
        } catch (SQLException error) {
            //TODO: registrar excepción
            JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        
        for (Enfermedad enfermedad : this.antecedenteEnfermedades) {
            this.PanelConsultaEnfermedades.add(new PlantillaAntecedente(this, PlantillaAntecedente.TipoAntecedente.Enfermedad, enfermedad.getId(), enfermedad.getNombre(), null));
        }
        
        
        this.llenarComboBoxEnfermedades();
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
        jPanel11 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        ComboBoxAlergias = new javax.swing.JComboBox<>();
        BotonAgregarAlergia = new javax.swing.JButton();
        PanelAntecedenteAlergias = new javax.swing.JPanel();
        PanelVacunas = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        ComboBoxVacunas = new javax.swing.JComboBox<>();
        BotonAgregarVacuna = new javax.swing.JButton();
        PanelAntecedenteVacunas = new javax.swing.JPanel();
        PanelCirugias = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        ComboBoxCirugias = new javax.swing.JComboBox<>();
        BotonAgregarCirugia = new javax.swing.JButton();
        PanelAntecedenteCirugias = new javax.swing.JPanel();
        PanelMedicamentos = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        ComboBoxMedicamentos = new javax.swing.JComboBox<>();
        BotonAgregarMedicamento = new javax.swing.JButton();
        PanelAntecedenteMedicamentos = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Antecedente médico");
        setBackground(new java.awt.Color(102, 204, 255));
        setResizable(false);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setForeground(new java.awt.Color(0, 0, 0));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 35)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ANTECECEDENTES MÉDICOS");
        jLabel3.setAlignmentX(0.5F);
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel8.add(jLabel3, java.awt.BorderLayout.CENTER);

        jButton1.setText("Regresar");
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
                    .addComponent(ComboBoxEnfermedades, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(216, Short.MAX_VALUE))
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

        PanelAlergias.setLayout(new java.awt.BorderLayout());

        jPanel11.setPreferredSize(new java.awt.Dimension(100, 100));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Selecciona una alergia");

        ComboBoxAlergias.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxAlergias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Selecciona una opción>" }));
        ComboBoxAlergias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxAlergiasActionPerformed(evt);
            }
        });

        BotonAgregarAlergia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotonAgregarAlergia.setText("Agregar al antecedente");
        BotonAgregarAlergia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarAlergiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(ComboBoxAlergias, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(BotonAgregarAlergia, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxAlergias, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonAgregarAlergia, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        PanelAlergias.add(jPanel11, java.awt.BorderLayout.NORTH);

        javax.swing.GroupLayout PanelAntecedenteAlergiasLayout = new javax.swing.GroupLayout(PanelAntecedenteAlergias);
        PanelAntecedenteAlergias.setLayout(PanelAntecedenteAlergiasLayout);
        PanelAntecedenteAlergiasLayout.setHorizontalGroup(
            PanelAntecedenteAlergiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 865, Short.MAX_VALUE)
        );
        PanelAntecedenteAlergiasLayout.setVerticalGroup(
            PanelAntecedenteAlergiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 538, Short.MAX_VALUE)
        );

        PanelAlergias.add(PanelAntecedenteAlergias, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Alergias", PanelAlergias);

        PanelVacunas.setLayout(new java.awt.BorderLayout());

        jPanel12.setPreferredSize(new java.awt.Dimension(100, 100));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Selecciona una vacuna");

        ComboBoxVacunas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxVacunas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Selecciona una opción>" }));
        ComboBoxVacunas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxVacunasActionPerformed(evt);
            }
        });

        BotonAgregarVacuna.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotonAgregarVacuna.setText("Agregar al antecedente");
        BotonAgregarVacuna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarVacunaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(ComboBoxVacunas, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(BotonAgregarVacuna, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxVacunas, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonAgregarVacuna, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        PanelVacunas.add(jPanel12, java.awt.BorderLayout.NORTH);

        javax.swing.GroupLayout PanelAntecedenteVacunasLayout = new javax.swing.GroupLayout(PanelAntecedenteVacunas);
        PanelAntecedenteVacunas.setLayout(PanelAntecedenteVacunasLayout);
        PanelAntecedenteVacunasLayout.setHorizontalGroup(
            PanelAntecedenteVacunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 865, Short.MAX_VALUE)
        );
        PanelAntecedenteVacunasLayout.setVerticalGroup(
            PanelAntecedenteVacunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 538, Short.MAX_VALUE)
        );

        PanelVacunas.add(PanelAntecedenteVacunas, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Vacunas", PanelVacunas);

        PanelCirugias.setLayout(new java.awt.BorderLayout());

        jPanel13.setPreferredSize(new java.awt.Dimension(100, 100));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Selecciona una cirugía");

        ComboBoxCirugias.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxCirugias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Selecciona una opción>" }));
        ComboBoxCirugias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxCirugiasActionPerformed(evt);
            }
        });

        BotonAgregarCirugia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotonAgregarCirugia.setText("Agregar al antecedente");
        BotonAgregarCirugia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarCirugiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(ComboBoxCirugias, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(BotonAgregarCirugia, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxCirugias, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonAgregarCirugia, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        PanelCirugias.add(jPanel13, java.awt.BorderLayout.NORTH);

        javax.swing.GroupLayout PanelAntecedenteCirugiasLayout = new javax.swing.GroupLayout(PanelAntecedenteCirugias);
        PanelAntecedenteCirugias.setLayout(PanelAntecedenteCirugiasLayout);
        PanelAntecedenteCirugiasLayout.setHorizontalGroup(
            PanelAntecedenteCirugiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 865, Short.MAX_VALUE)
        );
        PanelAntecedenteCirugiasLayout.setVerticalGroup(
            PanelAntecedenteCirugiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 538, Short.MAX_VALUE)
        );

        PanelCirugias.add(PanelAntecedenteCirugias, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Cirugías", PanelCirugias);

        PanelMedicamentos.setLayout(new java.awt.BorderLayout());

        jPanel14.setPreferredSize(new java.awt.Dimension(100, 100));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Selecciona un medicamento");

        ComboBoxMedicamentos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxMedicamentos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Selecciona una opción>" }));
        ComboBoxMedicamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxMedicamentosActionPerformed(evt);
            }
        });

        BotonAgregarMedicamento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotonAgregarMedicamento.setText("Agregar al antecedente");
        BotonAgregarMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarMedicamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(ComboBoxMedicamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 236, Short.MAX_VALUE)
                        .addComponent(BotonAgregarMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxMedicamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonAgregarMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        PanelMedicamentos.add(jPanel14, java.awt.BorderLayout.NORTH);

        javax.swing.GroupLayout PanelAntecedenteMedicamentosLayout = new javax.swing.GroupLayout(PanelAntecedenteMedicamentos);
        PanelAntecedenteMedicamentos.setLayout(PanelAntecedenteMedicamentosLayout);
        PanelAntecedenteMedicamentosLayout.setHorizontalGroup(
            PanelAntecedenteMedicamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 865, Short.MAX_VALUE)
        );
        PanelAntecedenteMedicamentosLayout.setVerticalGroup(
            PanelAntecedenteMedicamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 538, Short.MAX_VALUE)
        );

        PanelMedicamentos.add(PanelAntecedenteMedicamentos, java.awt.BorderLayout.CENTER);

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
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                resultado = enfermedadDAO.agregarEnfermedadAlAntecedente(this.idPaciente, this.listaEnfermedades.get(indiceSeleccionado - 1).getId());
            } catch (SQLException error) {
                //TODO: manejar excepción
                JOptionPane.showMessageDialog(null, "Ocurrió un error, vuelva a intentarlo más tarde", "Error de conexión", JOptionPane.ERROR_MESSAGE);
                resultado = -1;
            }
            
            if (resultado == 1) {
                this.recuperarAntecedenteEnfermedades();
                JOptionPane.showMessageDialog(null, "La enfermedad fue registrada en el antecedente exitosamente", "Operación exitosa", JOptionPane.OK_OPTION);
            } else if (resultado == 0) {
                JOptionPane.showMessageDialog(null, "Ocurrió un problema al tratar de agregar la enfermedad al antecednete", "Operación fallida", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor seleccione una opción de la lista", "Sin selección", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_BotonAgregarEnfermedadActionPerformed

    private void ComboBoxAlergiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxAlergiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxAlergiasActionPerformed

    private void BotonAgregarAlergiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarAlergiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonAgregarAlergiaActionPerformed

    private void ComboBoxVacunasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxVacunasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxVacunasActionPerformed

    private void BotonAgregarVacunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarVacunaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonAgregarVacunaActionPerformed

    private void ComboBoxCirugiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxCirugiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxCirugiasActionPerformed

    private void BotonAgregarCirugiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarCirugiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonAgregarCirugiaActionPerformed

    private void ComboBoxMedicamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxMedicamentosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxMedicamentosActionPerformed

    private void BotonAgregarMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarMedicamentoActionPerformed
        // TODO add your handling code here:
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
    private javax.swing.JPanel Panel1;
    private javax.swing.JPanel PanelAlergias;
    private javax.swing.JPanel PanelAntecedenteAlergias;
    private javax.swing.JPanel PanelAntecedenteCirugias;
    private javax.swing.JPanel PanelAntecedenteMedicamentos;
    private javax.swing.JPanel PanelAntecedenteVacunas;
    private javax.swing.JPanel PanelCirugias;
    private javax.swing.JPanel PanelConsultaEnfermedades;
    private javax.swing.JPanel PanelEnfermedades;
    private javax.swing.JPanel PanelMedicamentos;
    private javax.swing.JPanel PanelVacunas;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
