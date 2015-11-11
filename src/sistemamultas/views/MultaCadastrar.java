package sistemamultas.views;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import sistemamultas.controllers.CondutorDAO;
import sistemamultas.controllers.MultaDAO;
import sistemamultas.controllers.VeiculoDAO;
import sistemamultas.models.Condutor;
import sistemamultas.models.Multa;
import sistemamultas.models.MultaInfracao;
import sistemamultas.models.MultaTaxa;
import sistemamultas.models.Veiculo;

public class MultaCadastrar extends javax.swing.JDialog {
    private MultaDAO multa;
    
    public MultaCadastrar(java.awt.Frame parent, boolean modal, Multa multa) {
        super(parent, modal);
        this.multa = new MultaDAO(multa);
        initComponents();
        if (this.multa.getMulta().getId() != null) {
            infracoes.clear();
            infracoes.addAll(this.multa.getMulta().getMultaInfracaoList());
            taxas.clear();
            taxas.addAll(this.multa.getMulta().getMultaTaxaList());
            edDataAutuacao.setDate(this.multa.getMulta().getDataAutuacao());
            edVencimento.setDate(this.multa.getMulta().getDataVencimento());
            edPagamento.setDate(this.multa.getMulta().getDataPagamento());
            setTitle("Alterando autuação");
        } else {
            setTitle("Nova autuação");
            btnExcluir.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        listCondutor = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(new ArrayList());
        listVeiculo = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(new ArrayList());
        infracoes = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(new ArrayList());
        taxas = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(new ArrayList());
        jLabel1 = new javax.swing.JLabel();
        edId = new javax.swing.JTextField();
        btnGravar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        edDataAutuacao = new org.jdesktop.swingx.JXDatePicker();
        jLabel3 = new javax.swing.JLabel();
        edLocal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        edVencimento = new org.jdesktop.swingx.JXDatePicker();
        jLabel5 = new javax.swing.JLabel();
        edPagamento = new org.jdesktop.swingx.JXDatePicker();
        jLabel6 = new javax.swing.JLabel();
        cbVeiculo = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        cbCondutor = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        btnRemoverInfracao = new javax.swing.JButton();
        btnAdicionarInfracao = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableInfracoes = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnRemoverTaxa = new javax.swing.JButton();
        btnAdicionarTaxa = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTaxas = new javax.swing.JTable();

        listCondutor.addAll(CondutorDAO.listaCondutores(null));

        listVeiculo.addAll(VeiculoDAO.listaVeiculos(null));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Protocolo:");

        edId.setEnabled(false);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${multa.multa.id}"), edId, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        btnGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemamultas/res/Good-or-Tick-icon.png"))); // NOI18N
        btnGravar.setText("Gravar");
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemamultas/res/trash-icon.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemamultas/res/Error-icon.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel2.setText("Data autuação:");

        jLabel3.setText("Local:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${multa.multa.localAutuacao}"), edLocal, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel4.setText("Vencimento:");

        jLabel5.setText("Pagamento:");

        jLabel6.setText("Veículo:");

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listVeiculo, cbVeiculo);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${multa.multa.veiculoId}"), cbVeiculo, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        cbVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbVeiculoActionPerformed(evt);
            }
        });

        jLabel7.setText("Condutor:");

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listCondutor, cbCondutor);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${multa.multa.condutorId}"), cbCondutor, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Infrações"));

        btnRemoverInfracao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemamultas/res/Button-Delete-icon.png"))); // NOI18N
        btnRemoverInfracao.setText("Remover");
        btnRemoverInfracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverInfracaoActionPerformed(evt);
            }
        });

        btnAdicionarInfracao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemamultas/res/Button-Add-icon.png"))); // NOI18N
        btnAdicionarInfracao.setText("Adicionar");
        btnAdicionarInfracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarInfracaoActionPerformed(evt);
            }
        });

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, infracoes, tableInfracoes);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${infracaoId}"));
        columnBinding.setColumnName("Descrição");
        columnBinding.setColumnClass(sistemamultas.models.Infracao.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(tableInfracoes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRemoverInfracao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdicionarInfracao)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionarInfracao)
                    .addComponent(btnRemoverInfracao))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Taxas"));

        btnRemoverTaxa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemamultas/res/Button-Delete-icon.png"))); // NOI18N
        btnRemoverTaxa.setText("Remover");
        btnRemoverTaxa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverTaxaActionPerformed(evt);
            }
        });

        btnAdicionarTaxa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemamultas/res/Button-Add-icon.png"))); // NOI18N
        btnAdicionarTaxa.setText("Adicionar");
        btnAdicionarTaxa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarTaxaActionPerformed(evt);
            }
        });

        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, taxas, tableTaxas);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${taxaId}"));
        columnBinding.setColumnName("Descrição");
        columnBinding.setColumnClass(sistemamultas.models.Taxa.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane2.setViewportView(tableTaxas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRemoverTaxa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdicionarTaxa)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionarTaxa)
                    .addComponent(btnRemoverTaxa))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edId, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edDataAutuacao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edLocal))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGravar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbVeiculo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCondutor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(edId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(edDataAutuacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(edLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbCondutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(edPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGravar)
                            .addComponent(btnCancelar)
                            .addComponent(btnExcluir)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        String msg = multa.valida(edDataAutuacao.getDate(), edVencimento.getDate(), edPagamento.getDate());
        if (msg == null) {
            multa.grava();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, msg);
        }
    }//GEN-LAST:event_btnGravarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        Object[] options = {"Sim", "Não"};
        int n = JOptionPane.showOptionDialog(null, "Deseja realmente excluir esta autuação?", "Exclusão",
            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        if (n == 0) {
            if (getMulta().exclui()) {
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível excluir esta autuação.\nVerifique se ela não está sendo utilizada.");
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cbVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbVeiculoActionPerformed
        if (this.cbCondutor.getSelectedIndex() == -1) {
            Veiculo veiculo = (Veiculo) this.cbVeiculo.getSelectedItem();
            if (veiculo != null) {
                this.cbCondutor.setSelectedItem(veiculo.getProprietarioId());
            }
        }
    }//GEN-LAST:event_cbVeiculoActionPerformed

    private void btnAdicionarInfracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarInfracaoActionPerformed
        MultaCadastrarInfracao tela = new MultaCadastrarInfracao(null, true, multa);
        tela.setLocationRelativeTo(this);
        tela.setVisible(true);
        try {
            infracoes.clear();
            infracoes.addAll(multa.getMulta().getMultaInfracaoList());
        } catch (Exception e) {}
    }//GEN-LAST:event_btnAdicionarInfracaoActionPerformed

    private void btnRemoverInfracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverInfracaoActionPerformed
        Integer linha = tableInfracoes.convertRowIndexToModel(tableInfracoes.getSelectedRow());
        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Selecione uma infração");
        } else {
            MultaInfracao infracao = infracoes.get(linha);
            multa.removerInfracao(infracao);
            infracoes.clear();
            infracoes.addAll(multa.getMulta().getMultaInfracaoList());
        }
    }//GEN-LAST:event_btnRemoverInfracaoActionPerformed

    private void btnAdicionarTaxaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarTaxaActionPerformed
        MultaCadastrarTaxa tela = new MultaCadastrarTaxa(null, true, multa);
        tela.setLocationRelativeTo(this);
        tela.setVisible(true);
        try {
            taxas.clear();
            taxas.addAll(multa.getMulta().getMultaTaxaList());
        } catch (Exception e) {}
    }//GEN-LAST:event_btnAdicionarTaxaActionPerformed

    private void btnRemoverTaxaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverTaxaActionPerformed
        Integer linha = tableTaxas.convertRowIndexToModel(tableTaxas.getSelectedRow());
        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Selecione uma taxa");
        } else {
            MultaTaxa taxa = taxas.get(linha);
            multa.removerTaxa(taxa);
            taxas.clear();
            taxas.addAll(multa.getMulta().getMultaTaxaList());
        }
    }//GEN-LAST:event_btnRemoverTaxaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarInfracao;
    private javax.swing.JButton btnAdicionarTaxa;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnRemoverInfracao;
    private javax.swing.JButton btnRemoverTaxa;
    private javax.swing.JComboBox cbCondutor;
    private javax.swing.JComboBox cbVeiculo;
    private org.jdesktop.swingx.JXDatePicker edDataAutuacao;
    private javax.swing.JTextField edId;
    private javax.swing.JTextField edLocal;
    private org.jdesktop.swingx.JXDatePicker edPagamento;
    private org.jdesktop.swingx.JXDatePicker edVencimento;
    private java.util.List<MultaInfracao> infracoes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private java.util.List<Condutor> listCondutor;
    private java.util.List<Veiculo> listVeiculo;
    private javax.swing.JTable tableInfracoes;
    private javax.swing.JTable tableTaxas;
    private java.util.List<MultaTaxa> taxas;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    public MultaDAO getMulta() {
        return multa;
    }

    public void setMulta(MultaDAO condutor) {
        this.multa = condutor;
    }
}
