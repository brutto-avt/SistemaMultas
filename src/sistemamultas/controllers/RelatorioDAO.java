package sistemamultas.controllers;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import sistemamultas.models.Condutor;

public class RelatorioDAO {
    private enum Relatorios {
        PONTUACAO("pontuacao.jasper"),
        MEUS_VEICULOS("meusVeiculos.jasper");
        
        private final String arquivo;
        
        Relatorios (String arquivo) {
            this.arquivo = arquivo;
        }
        
        public String getArquivo () {
            return this.arquivo;
        }
    };
    
    private Connection criaConexao () {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/sistema_multas", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível conectar-se ao banco de dados");
            return null;
        }
    }
    
    private String criaSQLPeriodo (String campo, Date inicio, Date fim) {
        StringBuilder sql = new StringBuilder();
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        if (inicio == null && fim == null) {
            return "";
        } else if (inicio != null && fim == null) {
            sql.append(campo).append(" >= STR_TO_DATE('").append(df.format(inicio)).append(" 00:00:00', '%d/%m/%y %T')");
        } else if (inicio == null && fim != null) {
            sql.append(campo).append(" <= STR_TO_DATE('").append(df.format(fim)).append(" 23:59:59', '%d/%m/%y %T')");
        } else if (inicio != null && fim != null) {
            sql.append(campo).append(" BETWEEN STR_TO_DATE('").append(df.format(inicio)).append(" 00:00:00', '%d/%m/%y %T') AND STR_TO_DATE('").append(df.format(fim)).append(" 23:59:59', '%d/%m/%y %T')");
        }

        return sql.toString();
    }
    
    public JasperPrint geraRelatorioPontuacao (Condutor condutor) throws Exception {
        String caminho = "sistemamultas/relatorios/" + Relatorios.PONTUACAO.getArquivo();
        StringBuilder sql = new StringBuilder("");
        
        sql.append("SELECT * FROM relatorio_pontuacao p");
        sql.append(" WHERE p.condutor_id = ").append(String.valueOf(condutor.getId()));
        sql.append(" ORDER BY p.data_autuacao");
        
        return geraRelatorio(caminho, sql.toString());
    }
    
    public JasperPrint geraRelatorioMeusVeiculos (Condutor condutor) throws Exception {
        String caminho = "sistemamultas/relatorios/" + Relatorios.MEUS_VEICULOS.getArquivo();
        StringBuilder sql = new StringBuilder("");
        
        sql.append("SELECT * FROM relatorio_meus_veiculos v");
        sql.append(" WHERE v.condutor_id = ").append(String.valueOf(condutor.getId()));
        sql.append(" ORDER BY v.placa");
        
        return geraRelatorio(caminho, sql.toString());
    }
    
    private JasperPrint geraRelatorio (String caminho, String sql) throws Exception {
        Connection connection = criaConexao();
        Statement stmt = connection.createStatement();
        JRResultSetDataSource jrrs = new JRResultSetDataSource(stmt.executeQuery(sql));
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(caminho);
        JasperPrint jasperPrint = JasperFillManager.fillReport(is, new HashMap(), jrrs);
        
        return jasperPrint;
    }
}
