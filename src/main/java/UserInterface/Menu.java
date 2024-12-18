package UserInterface;

/**
 *
 * @author epale
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSuperior = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        panelBanner = new javax.swing.JPanel();
        Banner = new javax.swing.JLabel();
        PanelMenuPrincipal = new javax.swing.JPanel();
        panelOpciones = new javax.swing.JPanel();
        ButtonPerfil = new javax.swing.JButton();
        ButtonTratamientoMedico = new javax.swing.JButton();
        ButtonHistorialMedico = new javax.swing.JButton();
        ButtonAntecedenteMedico = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(777, 507));
        setResizable(false);

        panelSuperior.setBackground(new java.awt.Color(70, 141, 212));
        panelSuperior.setPreferredSize(new java.awt.Dimension(789, 71));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setText("Sistema de Historial Médico");

        javax.swing.GroupLayout panelSuperiorLayout = new javax.swing.GroupLayout(panelSuperior);
        panelSuperior.setLayout(panelSuperiorLayout);
        panelSuperiorLayout.setHorizontalGroup(
            panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuperiorLayout.createSequentialGroup()
                .addContainerGap(131, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addContainerGap(131, Short.MAX_VALUE))
        );
        panelSuperiorLayout.setVerticalGroup(
            panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuperiorLayout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        getContentPane().add(panelSuperior, java.awt.BorderLayout.NORTH);

        Banner.setIcon(new javax.swing.ImageIcon("src/main/java/UserInterface/Recursos/banner.jpg"));
        Banner.setMaximumSize(new java.awt.Dimension(841, 800));
        Banner.setPreferredSize(new java.awt.Dimension(841, 800));

        javax.swing.GroupLayout panelBannerLayout = new javax.swing.GroupLayout(panelBanner);
        panelBanner.setLayout(panelBannerLayout);
        panelBannerLayout.setHorizontalGroup(
            panelBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Banner, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
        );
        panelBannerLayout.setVerticalGroup(
            panelBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Banner, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
        );

        getContentPane().add(panelBanner, java.awt.BorderLayout.CENTER);

        PanelMenuPrincipal.setPreferredSize(new java.awt.Dimension(491, 391));
        PanelMenuPrincipal.setLayout(new java.awt.BorderLayout());

        panelOpciones.setBackground(java.awt.Color.white);
        panelOpciones.setPreferredSize(new java.awt.Dimension(491, 391));

        ButtonPerfil.setBackground(new java.awt.Color(181, 220, 255));
        ButtonPerfil.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        ButtonPerfil.setForeground(new java.awt.Color(0, 0, 0));
        ButtonPerfil.setText("Perfil");
        ButtonPerfil.setPreferredSize(new java.awt.Dimension(500, 100));
        ButtonPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPerfilActionPerformed(evt);
            }
        });

        ButtonTratamientoMedico.setBackground(new java.awt.Color(181, 220, 255));
        ButtonTratamientoMedico.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        ButtonTratamientoMedico.setForeground(new java.awt.Color(0, 0, 0));
        ButtonTratamientoMedico.setText("Tratamiento médico");
        ButtonTratamientoMedico.setPreferredSize(new java.awt.Dimension(500, 100));
        ButtonTratamientoMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonTratamientoMedicoActionPerformed(evt);
            }
        });

        ButtonHistorialMedico.setBackground(new java.awt.Color(181, 220, 255));
        ButtonHistorialMedico.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        ButtonHistorialMedico.setForeground(new java.awt.Color(0, 0, 0));
        ButtonHistorialMedico.setText("Historial médico");
        ButtonHistorialMedico.setPreferredSize(new java.awt.Dimension(500, 100));
        ButtonHistorialMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonHistorialMedicoActionPerformed(evt);
            }
        });

        ButtonAntecedenteMedico.setBackground(new java.awt.Color(181, 220, 255));
        ButtonAntecedenteMedico.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        ButtonAntecedenteMedico.setForeground(new java.awt.Color(0, 0, 0));
        ButtonAntecedenteMedico.setText("Antecedente médico");
        ButtonAntecedenteMedico.setPreferredSize(new java.awt.Dimension(500, 100));
        ButtonAntecedenteMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAntecedenteMedicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOpcionesLayout = new javax.swing.GroupLayout(panelOpciones);
        panelOpciones.setLayout(panelOpcionesLayout);
        panelOpcionesLayout.setHorizontalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpcionesLayout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ButtonHistorialMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonTratamientoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonAntecedenteMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
        );
        panelOpcionesLayout.setVerticalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcionesLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(ButtonPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(ButtonAntecedenteMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(ButtonTratamientoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(ButtonHistorialMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        PanelMenuPrincipal.add(panelOpciones, java.awt.BorderLayout.LINE_END);

        getContentPane().add(PanelMenuPrincipal, java.awt.BorderLayout.LINE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonPerfilActionPerformed

    private void ButtonHistorialMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonHistorialMedicoActionPerformed
        ConsultaTratamientoMedico ventanaHistorialMedico = new ConsultaTratamientoMedico(this);
        this.setVisible(false);
        ventanaHistorialMedico.setVisible(true);
    }//GEN-LAST:event_ButtonHistorialMedicoActionPerformed

    private void ButtonAntecedenteMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAntecedenteMedicoActionPerformed
        RegistroAntecedente ventanaAntecedente = new RegistroAntecedente(this);
        this.setVisible(false);
        ventanaAntecedente.setVisible(true);
    }//GEN-LAST:event_ButtonAntecedenteMedicoActionPerformed

    private void ButtonTratamientoMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonTratamientoMedicoActionPerformed
        RegistroTratamientoMedico ventanaTratamientoMedico = new RegistroTratamientoMedico(this);
        this.setVisible(false);
        ventanaTratamientoMedico.setVisible(true);
    }//GEN-LAST:event_ButtonTratamientoMedicoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Banner;
    private javax.swing.JButton ButtonAntecedenteMedico;
    private javax.swing.JButton ButtonHistorialMedico;
    private javax.swing.JButton ButtonPerfil;
    private javax.swing.JButton ButtonTratamientoMedico;
    private javax.swing.JPanel PanelMenuPrincipal;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelBanner;
    private javax.swing.JPanel panelOpciones;
    private javax.swing.JPanel panelSuperior;
    // End of variables declaration//GEN-END:variables
}
