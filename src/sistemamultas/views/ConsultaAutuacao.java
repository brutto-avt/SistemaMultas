package sistemamultas.views;

import java.util.ArrayList;
import javax.swing.JTabbedPane;
import org.pushingpixels.substance.api.renderers.SubstanceDefaultTableCellRenderer.DoubleRenderer;
import sistemamultas.controllers.CondutorDAO;
import sistemamultas.controllers.UsuarioDAO;
import sistemamultas.models.Multa;

public class ConsultaAutuacao extends javax.swing.JPanel {
    private final JTabbedPane pai;
    private CondutorDAO condutor;
    
    
    public ConsultaAutuacao(JTabbedPane pai) {
        this.pai = pai;
        this.condutor = new CondutorDAO(UsuarioDAO.getUsuarioLogado().getCondutorId());
        initComponents();
        this.listaPendencias.addAll(this.condutor.listaPontuacao());
        atualizaTotal();
    }
    
    private void atualizaTotal() {
        Integer total = 0;
        
        for (Multa multa: this.listaPendencias) {
            total += multa.getTotalPontos();
        }
        if ((20 - total) <= 0) {
            lblPontuacao.setText("CNH suspensa");
        } else {
            lblPontuacao.setText("Pontos restantes na CNH: " + (20 - total));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        listaPendencias = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(new ArrayList());
        btnFechar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePendencias = new javax.swing.JTable();
        lblPontuacao = new javax.swing.JLabel();

        setName("Minhas multas"); // NOI18N

        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemamultas/res/Error-icon.png"))); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pagamentos pendentes"));

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listaPendencias, tablePendencias);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${veiculoId.placa}"));
        columnBinding.setColumnName("Veículo");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dataAutuacao}"));
        columnBinding.setColumnName("Data Autuação");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${localAutuacao}"));
        columnBinding.setColumnName("Local");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${total}"));
        columnBinding.setColumnName("Valor");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${totalPontos}"));
        columnBinding.setColumnName("Pontuação");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(tablePendencias);
        if (tablePendencias.getColumnModel().getColumnCount() > 0) {
            tablePendencias.getColumnModel().getColumn(3).setCellRenderer(new DoubleRenderer());
            tablePendencias.getColumnModel().getColumn(4).setCellRenderer(null);
        }

        lblPontuacao.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblPontuacao.setText("Pontos restantes na CNH: 20");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnFechar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPontuacao)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPontuacao))
                .addContainerGap())
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.pai.remove(this);
    }//GEN-LAST:event_btnFecharActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPontuacao;
    private java.util.List<Multa> listaPendencias;
    private javax.swing.JTable tablePendencias;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    public CondutorDAO getCondutor() {
        return condutor;
    }

    public void setCondutor(CondutorDAO condutor) {
        this.condutor = condutor;
    }
}
