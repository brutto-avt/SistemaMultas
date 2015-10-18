package sistemamultas.views;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
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
        for (int i=0; i<jmbPrincipal.getComponentCount(); i++) {
            JMenu menu = jmbPrincipal.getMenu(i);
            menu.setVisible(false);
            
            for (int j=0; i<menu.getComponentCount(); j++) {
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
        for (Component c: jmbPrincipal.getComponents()) {
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
        jmRelatorios = new javax.swing.JMenu();
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
        jmCadastros.add(jmiVeiculos);

        jmiCondutores.setText("Condutores");
        jmCadastros.add(jmiCondutores);

        jmiInfracoes.setText("Infrações");
        jmCadastros.add(jmiInfracoes);

        jmiTaxas.setText("Taxas");
        jmCadastros.add(jmiTaxas);

        jmbPrincipal.add(jmCadastros);

        jmConsultas.setText("Consultas");
        jmbPrincipal.add(jmConsultas);

        jmRelatorios.setText("Relatórios");
        jmbPrincipal.add(jmRelatorios);

        jmSistema.setText("Sistema");

        jmiUsuarios.setText("Usuários");
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu jmCadastros;
    private javax.swing.JMenu jmConsultas;
    private javax.swing.JMenu jmRelatorios;
    private javax.swing.JMenu jmSistema;
    private javax.swing.JMenuBar jmbPrincipal;
    private javax.swing.JMenuItem jmiCondutores;
    private javax.swing.JMenuItem jmiInfracoes;
    private javax.swing.JMenuItem jmiTaxas;
    private javax.swing.JMenuItem jmiUsuarios;
    private javax.swing.JMenuItem jmiVeiculos;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTabbedPane tpAbas;
    // End of variables declaration//GEN-END:variables
}
