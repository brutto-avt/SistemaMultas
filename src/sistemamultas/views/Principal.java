package sistemamultas.views;

import java.awt.Component;
import java.awt.Dialog;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import sistemamultas.controllers.RelatorioDAO;
import sistemamultas.controllers.UsuarioDAO;

public class Principal extends javax.swing.JFrame {

    UsuarioDAO usuario;

    public Principal(ImageIcon icone) {
        initComponents();
        setIconImage(icone.getImage());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        ocultaMenus();
        this.requestFocus();
        Login login = new Login(null, true);
        login.setLocationRelativeTo(this);
        login.setVisible(true);
        UsuarioDAO.setUsuarioLogado(login.getUsuario());
        usuario = new UsuarioDAO(UsuarioDAO.getUsuarioLogado());
        lblUsuario.setText(UsuarioDAO.getUsuarioLogado().getCondutorId().getNome());
        //
        liberaMenus();
    }

    private void liberaMenus() {
        for (int i = 0; i < jmbPrincipal.getMenuCount() - 1; i++) {
            JMenu menu = (JMenu) jmbPrincipal.getMenu(i);
            menu.setVisible(false);

            for (int j = 0; j < menu.getItemCount(); j++) {
                JMenuItem item = menu.getItem(j);
                item.setVisible(false);

                if (usuario.temAcesso(item.getName())) {
                    item.setVisible(true);
                    menu.setVisible(true);
                }
            }
        }
    }

    private void ocultaMenus() {
        for (Component c : jmbPrincipal.getComponents()) {
            c.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        tpAbas = new javax.swing.JTabbedPane();
        jmbPrincipal = new javax.swing.JMenuBar();
        jmCadastros = new javax.swing.JMenu();
        jmiVeiculos = new javax.swing.JMenuItem();
        jmiCondutores = new javax.swing.JMenuItem();
        jmiInfracoes = new javax.swing.JMenuItem();
        jmiTaxas = new javax.swing.JMenuItem();
        jmConsultas = new javax.swing.JMenu();
        jmiConsultaCondutor = new javax.swing.JMenuItem();
        jmiConsultaVeiculos = new javax.swing.JMenuItem();
        jmiConsultaAutuacoes = new javax.swing.JMenuItem();
        jmiConsultaDetalhada = new javax.swing.JMenuItem();
        jmOperacional = new javax.swing.JMenu();
        jmiGestaoAutuacoes = new javax.swing.JMenuItem();
        jmiTransferirCondutor = new javax.swing.JMenuItem();
        jmRelatorios = new javax.swing.JMenu();
        jmiHistoricoPontuacao = new javax.swing.JMenuItem();
        jmSistema = new javax.swing.JMenu();
        jmiUsuarios = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema Multas");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblUsuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUsuario.setText("Não logado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 568, Short.MAX_VALUE)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblUsuario))
        );

        jmCadastros.setText("Cadastros");

        jmiVeiculos.setText("Veículos");
        jmiVeiculos.setName("jmiVeiculos"); // NOI18N
        jmiVeiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiVeiculosActionPerformed(evt);
            }
        });
        jmCadastros.add(jmiVeiculos);

        jmiCondutores.setText("Condutores");
        jmiCondutores.setName("jmiCondutores"); // NOI18N
        jmiCondutores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCondutoresActionPerformed(evt);
            }
        });
        jmCadastros.add(jmiCondutores);

        jmiInfracoes.setText("Infrações");
        jmiInfracoes.setName("jmiInfracoes"); // NOI18N
        jmiInfracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiInfracoesActionPerformed(evt);
            }
        });
        jmCadastros.add(jmiInfracoes);

        jmiTaxas.setText("Taxas");
        jmiTaxas.setName("jmiTaxas"); // NOI18N
        jmiTaxas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTaxasActionPerformed(evt);
            }
        });
        jmCadastros.add(jmiTaxas);

        jmbPrincipal.add(jmCadastros);

        jmConsultas.setText("Consultas");

        jmiConsultaCondutor.setText("Meus dados");
        jmiConsultaCondutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiConsultaCondutorActionPerformed(evt);
            }
        });
        jmConsultas.add(jmiConsultaCondutor);

        jmiConsultaVeiculos.setText("Meus veículos");
        jmiConsultaVeiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiConsultaVeiculosActionPerformed(evt);
            }
        });
        jmConsultas.add(jmiConsultaVeiculos);

        jmiConsultaAutuacoes.setText("Minhas multas (Pontuação)");
        jmiConsultaAutuacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiConsultaAutuacoesActionPerformed(evt);
            }
        });
        jmConsultas.add(jmiConsultaAutuacoes);

        jmiConsultaDetalhada.setText("Consulta detalhada");
        jmiConsultaDetalhada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiConsultaDetalhadaActionPerformed(evt);
            }
        });
        jmConsultas.add(jmiConsultaDetalhada);

        jmbPrincipal.add(jmConsultas);

        jmOperacional.setText("Operacional");

        jmiGestaoAutuacoes.setText("Gestão de Autuações");
        jmiGestaoAutuacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiGestaoAutuacoesActionPerformed(evt);
            }
        });
        jmOperacional.add(jmiGestaoAutuacoes);

        jmiTransferirCondutor.setText("Transferir condutor");
        jmiTransferirCondutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTransferirCondutorActionPerformed(evt);
            }
        });
        jmOperacional.add(jmiTransferirCondutor);

        jmbPrincipal.add(jmOperacional);

        jmRelatorios.setText("Relatórios");

        jmiHistoricoPontuacao.setText("Histórico de pontuação");
        jmiHistoricoPontuacao.setName("jmiUsuarios"); // NOI18N
        jmiHistoricoPontuacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiHistoricoPontuacaoActionPerformed(evt);
            }
        });
        jmRelatorios.add(jmiHistoricoPontuacao);

        jmbPrincipal.add(jmRelatorios);

        jmSistema.setText("Sistema");

        jmiUsuarios.setText("Usuários");
        jmiUsuarios.setName("jmiUsuarios"); // NOI18N
        jmiUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiUsuariosActionPerformed(evt);
            }
        });
        jmSistema.add(jmiUsuarios);

        jmbPrincipal.add(jmSistema);

        setJMenuBar(jmbPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tpAbas)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(tpAbas, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiUsuariosActionPerformed
        Usuarios tela = new Usuarios(this.tpAbas);
        if (this.tpAbas.indexOfTab(tela.getName()) == -1) {
            this.tpAbas.add(tela.getName(), tela);
            this.tpAbas.setSelectedIndex(this.tpAbas.indexOfTab(tela.getName()));
        } else {
            this.tpAbas.setSelectedIndex(this.tpAbas.indexOfTab(tela.getName()));
        }
    }//GEN-LAST:event_jmiUsuariosActionPerformed

    private void jmiVeiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiVeiculosActionPerformed
        Veiculos tela = new Veiculos(this.tpAbas);
        if (this.tpAbas.indexOfTab(tela.getName()) == -1) {
            this.tpAbas.add(tela.getName(), tela);
            this.tpAbas.setSelectedIndex(this.tpAbas.indexOfTab(tela.getName()));
        } else {
            this.tpAbas.setSelectedIndex(this.tpAbas.indexOfTab(tela.getName()));
        }
    }//GEN-LAST:event_jmiVeiculosActionPerformed

    private void jmiCondutoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCondutoresActionPerformed
        Condutores tela = new Condutores(this.tpAbas);
        if (this.tpAbas.indexOfTab(tela.getName()) == -1) {
            this.tpAbas.add(tela.getName(), tela);
            this.tpAbas.setSelectedIndex(this.tpAbas.indexOfTab(tela.getName()));
        } else {
            this.tpAbas.setSelectedIndex(this.tpAbas.indexOfTab(tela.getName()));
        }
    }//GEN-LAST:event_jmiCondutoresActionPerformed

    private void jmiInfracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiInfracoesActionPerformed
        Infracoes tela = new Infracoes(this.tpAbas);
        if (this.tpAbas.indexOfTab(tela.getName()) == -1) {
            this.tpAbas.add(tela.getName(), tela);
            this.tpAbas.setSelectedIndex(this.tpAbas.indexOfTab(tela.getName()));
        } else {
            this.tpAbas.setSelectedIndex(this.tpAbas.indexOfTab(tela.getName()));
        }
    }//GEN-LAST:event_jmiInfracoesActionPerformed

    private void jmiTaxasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTaxasActionPerformed
        Taxas tela = new Taxas(this.tpAbas);
        if (this.tpAbas.indexOfTab(tela.getName()) == -1) {
            this.tpAbas.add(tela.getName(), tela);
            this.tpAbas.setSelectedIndex(this.tpAbas.indexOfTab(tela.getName()));
        } else {
            this.tpAbas.setSelectedIndex(this.tpAbas.indexOfTab(tela.getName()));
        }
    }//GEN-LAST:event_jmiTaxasActionPerformed

    private void jmiConsultaCondutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiConsultaCondutorActionPerformed
        ConsultaCondutor tela = new ConsultaCondutor(this.tpAbas);
        if (this.tpAbas.indexOfTab(tela.getName()) == -1) {
            this.tpAbas.add(tela.getName(), tela);
            this.tpAbas.setSelectedIndex(this.tpAbas.indexOfTab(tela.getName()));
        } else {
            this.tpAbas.setSelectedIndex(this.tpAbas.indexOfTab(tela.getName()));
        }
    }//GEN-LAST:event_jmiConsultaCondutorActionPerformed

    private void jmiConsultaVeiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiConsultaVeiculosActionPerformed
        ConsultaVeiculo tela = new ConsultaVeiculo(this.tpAbas);
        if (this.tpAbas.indexOfTab(tela.getName()) == -1) {
            this.tpAbas.add(tela.getName(), tela);
            this.tpAbas.setSelectedIndex(this.tpAbas.indexOfTab(tela.getName()));
        } else {
            this.tpAbas.setSelectedIndex(this.tpAbas.indexOfTab(tela.getName()));
        }
    }//GEN-LAST:event_jmiConsultaVeiculosActionPerformed

    private void jmiTransferirCondutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTransferirCondutorActionPerformed
        TransferenciaCondutor tela = new TransferenciaCondutor(this, true);
        tela.setVisible(true);
    }//GEN-LAST:event_jmiTransferirCondutorActionPerformed

    private void jmiConsultaAutuacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiConsultaAutuacoesActionPerformed
        ConsultaAutuacao tela = new ConsultaAutuacao(this.tpAbas);
        if (this.tpAbas.indexOfTab(tela.getName()) == -1) {
            this.tpAbas.add(tela.getName(), tela);
            this.tpAbas.setSelectedIndex(this.tpAbas.indexOfTab(tela.getName()));
        } else {
            this.tpAbas.setSelectedIndex(this.tpAbas.indexOfTab(tela.getName()));
        }
    }//GEN-LAST:event_jmiConsultaAutuacoesActionPerformed

    private void jmiConsultaDetalhadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiConsultaDetalhadaActionPerformed
        ConsultaDetalhada tela = new ConsultaDetalhada(this.tpAbas);
        if (this.tpAbas.indexOfTab(tela.getName()) == -1) {
            this.tpAbas.add(tela.getName(), tela);
            this.tpAbas.setSelectedIndex(this.tpAbas.indexOfTab(tela.getName()));
        } else {
            this.tpAbas.setSelectedIndex(this.tpAbas.indexOfTab(tela.getName()));
        }
    }//GEN-LAST:event_jmiConsultaDetalhadaActionPerformed

    private void jmiGestaoAutuacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiGestaoAutuacoesActionPerformed
        Multas tela = new Multas(this.tpAbas);
        if (this.tpAbas.indexOfTab(tela.getName()) == -1) {
            this.tpAbas.add(tela.getName(), tela);
            this.tpAbas.setSelectedIndex(this.tpAbas.indexOfTab(tela.getName()));
        } else {
            this.tpAbas.setSelectedIndex(this.tpAbas.indexOfTab(tela.getName()));
        }
    }//GEN-LAST:event_jmiGestaoAutuacoesActionPerformed

    private void jmiHistoricoPontuacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiHistoricoPontuacaoActionPerformed
        RelatorioDAO relatorios = new RelatorioDAO();
        try {
            JasperPrint print = relatorios.geraRelatorioPontuacao(UsuarioDAO.getUsuarioLogado().getCondutorId());
            if (print.getPages().size() > 0) {
                JasperViewer jv = new JasperViewer(print, false);
                jv.setTitle("Histórico de pontuação");
                jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
                jv.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
                jv.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma infração encontrada no período.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Falha ao gerar o relatório");
        }
    }//GEN-LAST:event_jmiHistoricoPontuacaoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu jmCadastros;
    private javax.swing.JMenu jmConsultas;
    private javax.swing.JMenu jmOperacional;
    private javax.swing.JMenu jmRelatorios;
    private javax.swing.JMenu jmSistema;
    private javax.swing.JMenuBar jmbPrincipal;
    private javax.swing.JMenuItem jmiCondutores;
    private javax.swing.JMenuItem jmiConsultaAutuacoes;
    private javax.swing.JMenuItem jmiConsultaCondutor;
    private javax.swing.JMenuItem jmiConsultaDetalhada;
    private javax.swing.JMenuItem jmiConsultaVeiculos;
    private javax.swing.JMenuItem jmiGestaoAutuacoes;
    private javax.swing.JMenuItem jmiHistoricoPontuacao;
    private javax.swing.JMenuItem jmiInfracoes;
    private javax.swing.JMenuItem jmiTaxas;
    private javax.swing.JMenuItem jmiTransferirCondutor;
    private javax.swing.JMenuItem jmiUsuarios;
    private javax.swing.JMenuItem jmiVeiculos;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTabbedPane tpAbas;
    // End of variables declaration//GEN-END:variables
}
