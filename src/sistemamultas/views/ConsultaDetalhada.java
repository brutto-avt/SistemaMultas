package sistemamultas.views;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import org.pushingpixels.substance.api.renderers.SubstanceDefaultTableCellRenderer.DoubleRenderer;
import sistemamultas.controllers.CondutorDAO;
import sistemamultas.controllers.UsuarioDAO;
import sistemamultas.models.Multa;
import sistemamultas.models.Veiculo;

public class ConsultaDetalhada extends javax.swing.JPanel {

    private final JTabbedPane pai;
    private CondutorDAO condutor;

    public ConsultaDetalhada(JTabbedPane pai) {
        this.pai = pai;
        this.condutor = new CondutorDAO(UsuarioDAO.getUsuarioLogado().getCondutorId());
        initComponents();
        this.listaMulta.addAll(this.condutor.listaPendencias());
        this.listVeiculo.addAll(this.condutor.getCondutor().getVeiculoList());
        Veiculo todos = new Veiculo();
        todos.setModelo("Todos");
        listVeiculo.add(todos);
        this.cbVeiculo.setSelectedIndex(cbVeiculo.getItemCount() - 1);
    }

    private void executaBusca() {
        Veiculo veiculo = null;
        Character tipo = null;
        try {
            listaMulta.clear();
            if (cbVeiculo.getSelectedIndex() != cbVeiculo.getItemCount() - 1) {
                veiculo = (Veiculo) cbVeiculo.getSelectedItem();
            }
            if (cbTipo.getSelectedIndex() != cbTipo.getItemCount() - 1) {
                switch (cbTipo.getSelectedIndex()) {
                    case 0:
                        tipo = 'P';
                        break;
                    case 1:
                        tipo = 'V';
                        break;
                    case 2:
                        tipo = 'T';
                        break;
                }
            }
            listaMulta.addAll(condutor.listaDetalhada(edPeriodoInicio.getDate(), edPeriodoFim.getDate(), tipo, veiculo));
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        listaMulta = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(new ArrayList());
        listVeiculo = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(new ArrayList());
        btnFechar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMultas = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnBusca = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        edPeriodoInicio = new org.jdesktop.swingx.JXDatePicker();
        jLabel3 = new javax.swing.JLabel();
        edPeriodoFim = new org.jdesktop.swingx.JXDatePicker();
        jLabel1 = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cbVeiculo = new javax.swing.JComboBox();

        setName("Consulta detalhada"); // NOI18N

        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemamultas/res/Error-icon.png"))); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultado"));

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listaMulta, tableMultas);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${id}"));
        columnBinding.setColumnName("Protocolo");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${veiculoId}"));
        columnBinding.setColumnName("Veículo");
        columnBinding.setColumnClass(sistemamultas.models.Veiculo.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dataAutuacao}"));
        columnBinding.setColumnName("Data Autuação");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dataVencimento}"));
        columnBinding.setColumnName("Vencimento");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${totalInfracoes}"));
        columnBinding.setColumnName("Total Multas");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${totalTaxas}"));
        columnBinding.setColumnName("Total Taxas");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${total}"));
        columnBinding.setColumnName("Total");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${situacao}"));
        columnBinding.setColumnName("Situação");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        tableMultas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMultasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableMultas);
        if (tableMultas.getColumnModel().getColumnCount() > 0) {
            tableMultas.getColumnModel().getColumn(4).setCellRenderer(new DoubleRenderer());
            tableMultas.getColumnModel().getColumn(5).setCellRenderer(new DoubleRenderer());
            tableMultas.getColumnModel().getColumn(6).setCellRenderer(new DoubleRenderer());
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Busca"));

        btnBusca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemamultas/res/Search-icon.png"))); // NOI18N
        btnBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaActionPerformed(evt);
            }
        });

        jLabel2.setText("De:");

        edPeriodoInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edPeriodoInicioActionPerformed(evt);
            }
        });

        jLabel3.setText("até:");

        edPeriodoFim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edPeriodoFimActionPerformed(evt);
            }
        });

        jLabel1.setText("Situação:");

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pagas", "Vencidas", "Transferidas", "Todas" }));
        cbTipo.setSelectedIndex(3);
        cbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoActionPerformed(evt);
            }
        });

        jLabel4.setText("Veículo:");

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listVeiculo, cbVeiculo);
        bindingGroup.addBinding(jComboBoxBinding);

        cbVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbVeiculoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edPeriodoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edPeriodoFim, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbVeiculo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(edPeriodoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(edPeriodoFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnFechar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.pai.remove(this);
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaActionPerformed
        executaBusca();
    }//GEN-LAST:event_btnBuscaActionPerformed

    private void edPeriodoInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edPeriodoInicioActionPerformed
        executaBusca();
    }//GEN-LAST:event_edPeriodoInicioActionPerformed

    private void edPeriodoFimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edPeriodoFimActionPerformed
        executaBusca();
    }//GEN-LAST:event_edPeriodoFimActionPerformed

    private void cbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoActionPerformed
        executaBusca();
    }//GEN-LAST:event_cbTipoActionPerformed

    private void cbVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbVeiculoActionPerformed
        executaBusca();
    }//GEN-LAST:event_cbVeiculoActionPerformed

    private void tableMultasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMultasMouseClicked
        if (evt.getClickCount() == 2) {
            Integer linha = tableMultas.convertRowIndexToModel(tableMultas.getSelectedRow());
            if (linha == -1) {
                JOptionPane.showMessageDialog(null, "Selecione uma multa");
            } else {
                Multa m = listaMulta.get(linha);
                if (m != null) {
                    ConsultaDetalhadaItem tela = new ConsultaDetalhadaItem(null, true, m);
                    tela.setLocationRelativeTo(this);
                    tela.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_tableMultasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusca;
    private javax.swing.JButton btnFechar;
    private javax.swing.JComboBox cbTipo;
    private javax.swing.JComboBox cbVeiculo;
    private org.jdesktop.swingx.JXDatePicker edPeriodoFim;
    private org.jdesktop.swingx.JXDatePicker edPeriodoInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.util.List<Veiculo> listVeiculo;
    private java.util.List<Multa> listaMulta;
    private javax.swing.JTable tableMultas;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    public CondutorDAO getCondutor() {
        return condutor;
    }

    public void setCondutor(CondutorDAO condutor) {
        this.condutor = condutor;
    }
}
