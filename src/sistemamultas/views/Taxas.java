package sistemamultas.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.Timer;
import org.pushingpixels.substance.api.renderers.SubstanceDefaultTableCellRenderer.DoubleRenderer;
import sistemamultas.controllers.TaxaDAO;
import sistemamultas.models.Taxa;

public class Taxas extends javax.swing.JPanel {
    private final JTabbedPane pai;
    
    public Taxas(JTabbedPane pai) {
        this.pai = pai;
        initComponents();
        Timer timer = new Timer(1*5000, new ActionListenerBusca());
        executaBusca();
        timer.start();
    }
    
    // Atualizar tela
    class ActionListenerBusca implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            executaBusca();
        }
    }
    
    private void executaBusca() {
        String busca = edBusca.getText();
        if (busca.trim().length() == 0) {
            busca = null;
        }
        try {
            listaTaxa.clear();
            listaTaxa.addAll(TaxaDAO.listaTaxas(busca));
        } catch (Exception e) {}
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        listaTaxa = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(new ArrayList());
        btnEditar = new javax.swing.JButton();
        btnInserir = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableClientes = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        edBusca = new javax.swing.JTextField();
        btnBusca = new javax.swing.JButton();

        setName("Taxas"); // NOI18N
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemamultas/res/Misc-Edit-icon.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemamultas/res/Plus-icon.png"))); // NOI18N
        btnInserir.setText("Inserir");
        btnInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirActionPerformed(evt);
            }
        });

        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemamultas/res/Error-icon.png"))); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        tableClientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listaTaxa, tableClientes);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${descricao}"));
        columnBinding.setColumnName("Descrição");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${periodoStr}"));
        columnBinding.setColumnName("Periodicidade");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tipoValorStr}"));
        columnBinding.setColumnName("Tipo Valor");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${valor}"));
        columnBinding.setColumnName("Valor");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        tableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableClientes);
        if (tableClientes.getColumnModel().getColumnCount() > 0) {
            tableClientes.getColumnModel().getColumn(3).setCellRenderer(new DoubleRenderer());
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Busca"));

        edBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edBuscaActionPerformed(evt);
            }
        });
        edBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                edBuscaKeyReleased(evt);
            }
        });

        btnBusca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemamultas/res/Search-icon.png"))); // NOI18N
        btnBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(edBusca, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(edBusca)
            .addComponent(btnBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnFechar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 372, Short.MAX_VALUE)
                        .addComponent(btnInserir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFechar)
                    .addComponent(btnInserir)
                    .addComponent(btnEditar))
                .addContainerGap())
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void edBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edBuscaActionPerformed
        executaBusca();
    }//GEN-LAST:event_edBuscaActionPerformed

    private void edBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edBuscaKeyReleased
        executaBusca();
    }//GEN-LAST:event_edBuscaKeyReleased

    private void btnBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaActionPerformed
        executaBusca();
    }//GEN-LAST:event_btnBuscaActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Integer linha = tableClientes.convertRowIndexToModel(tableClientes.getSelectedRow());
        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Selecione uma taxa");
        } else {
            Taxa t = listaTaxa.get(linha);
            if (t != null) {
                TaxaCadastrar tela = new TaxaCadastrar(null, true, t);
                tela.setLocationRelativeTo(this);
                tela.setVisible(true);
            }
            executaBusca();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirActionPerformed
        TaxaCadastrar tela = new TaxaCadastrar(null, true, null);
        tela.setLocationRelativeTo(this);
        tela.setVisible(true);
        executaBusca();
    }//GEN-LAST:event_btnInserirActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.pai.remove(this);
    }//GEN-LAST:event_btnFecharActionPerformed

    private void tableClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClientesMouseClicked
        if (evt.getClickCount() == 2) {
            btnEditarActionPerformed(null);
        }
    }//GEN-LAST:event_tableClientesMouseClicked

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        executaBusca();
    }//GEN-LAST:event_formFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusca;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnInserir;
    private javax.swing.JTextField edBusca;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.util.List<Taxa> listaTaxa;
    private javax.swing.JTable tableClientes;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
