package sistemamultas.views;

import java.util.Date;
import javax.swing.JOptionPane;
import sistemamultas.controllers.CondutorDAO;
import sistemamultas.controllers.UsuarioDAO;

public class TransferenciaCondutor extends javax.swing.JDialog {
    private final CondutorDAO condutor;
    
    public TransferenciaCondutor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.condutor = new CondutorDAO(UsuarioDAO.getUsuarioLogado().getCondutorId());
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        edProtocolo = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        edPlaca = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        edDataAtuacao = new org.jdesktop.swingx.JXDatePicker();
        btnTransferir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Transferir Condutor");
        setModalityType(java.awt.Dialog.ModalityType.DOCUMENT_MODAL);

        jLabel1.setText("Protocolo:");

        edProtocolo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jLabel2.setText("Placa do veículo:");

        jLabel3.setText("Data autuação:");

        btnTransferir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemamultas/res/Good-or-Tick-icon.png"))); // NOI18N
        btnTransferir.setText("Transferir multa");
        btnTransferir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferirActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemamultas/res/Error-icon.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edDataAtuacao, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edProtocolo, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTransferir)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(edProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(edPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(edDataAtuacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTransferir)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(459, 163));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTransferirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferirActionPerformed
        String erro = "";
        if (this.edProtocolo.getValue() == null) {
            erro += "Informe o número do protocolo";
        }
        if (this.edDataAtuacao.getDate() == null) {
            if (erro.length() > 0) erro += "\n";
            erro += "Informe a data de autuação";            
        }
        if (this.edPlaca.getText() == null || this.edPlaca.getText().trim().length() == 0) {
            if (erro.length() > 0) erro += "\n";
            erro += "Informe a placa do veículo multado";
        }
        if (erro.length() > 0) {
            JOptionPane.showMessageDialog(null, erro);
            return;
        }
        //
        int id = ((Long) this.edProtocolo.getValue()).intValue();
        String placa = this.edPlaca.getText().trim().toUpperCase();
        Date autuacao = this.edDataAtuacao.getDate();
        
        if (this.condutor.transferirMulta(id, placa, autuacao)) {
            JOptionPane.showMessageDialog(null, "Multa transferida com sucesso");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Multa não encontrada. Confira os dados e tente novamente");
        }
    }//GEN-LAST:event_btnTransferirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnTransferir;
    private org.jdesktop.swingx.JXDatePicker edDataAtuacao;
    private javax.swing.JTextField edPlaca;
    private javax.swing.JFormattedTextField edProtocolo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
