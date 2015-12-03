package util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;
import org.jboleto.control.Generator;
import org.jboleto.control.PDFGenerator;
import sistemamultas.models.Configuracao;
import sistemamultas.models.Multa;

public class GeraBoleto {

    static SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
    static NumberFormat formatoNumero = NumberFormat.getInstance();

    public static boolean geraBoletoBB(Multa multa) throws Exception {
        boolean resultado = true;
        JBoletoBean jBoletoBean = new JBoletoBean();
        formatoNumero.setMinimumIntegerDigits(1);
        formatoNumero.setMinimumFractionDigits(2);

        //Dados gerais
        jBoletoBean.setDataDocumento(formatoData.format(new Date(System.currentTimeMillis())));
        jBoletoBean.setDataProcessamento(formatoData.format(new Date(System.currentTimeMillis())));

        //Cedente
        jBoletoBean.setCedente("Departamento de Transito Fake");

        //Sacado
        if (multa.getCondutorId().getNome().length() > 42) {
            jBoletoBean.setNomeSacado(multa.getCondutorId().getNome().substring(0, 42));
        } else {
            jBoletoBean.setNomeSacado(multa.getCondutorId().getNome());
        }
        jBoletoBean.setEnderecoSacado(multa.getCondutorId().getLogradouro() + "," + multa.getCondutorId().getNumero());
        jBoletoBean.setBairroSacado(multa.getCondutorId().getBairro());
        jBoletoBean.setCidadeSacado(multa.getCondutorId().getCidade());
        jBoletoBean.setUfSacado(multa.getCondutorId().getUf());
        jBoletoBean.setCepSacado(multa.getCondutorId().getCep());
        jBoletoBean.setCpfSacado(multa.getCondutorId().getCpf());

        //Pagamento
        jBoletoBean.setLocalPagamento("PAGÁVEL EM QUALQUER BANCO ATÉ O VENCIMENTO");
        jBoletoBean.setLocalPagamento2("APÓS O VENCIMENTO, SOMENTE NO BANCO DO BRASIL");

        //Vencimento
        jBoletoBean.setDataVencimento(formatoData.format(multa.getDataVencimento()));
        jBoletoBean.setInstrucao1("NÃO RECEBER APÓS VENCIMENTO");

        //Conta
        jBoletoBean.setCarteira("17");
        jBoletoBean.setAgencia("3415");
        jBoletoBean.setContaCorrente("00543004");
        jBoletoBean.setNumConvenio("1101354");
        
        //Documento
        String nossoNumero = proximoNossoNumero();
        jBoletoBean.setNossoNumero(nossoNumero, 10);
        jBoletoBean.setNoDocumento(String.valueOf(multa.getId()));
        jBoletoBean.setValorBoleto(String.valueOf(multa.getTotal()));
        jBoletoBean.setEspecieDocumento("DM");

        //Geracao do boleto
        Generator generator = new PDFGenerator(jBoletoBean, JBoleto.BANCO_DO_BRASIL);
        JBoleto jBoleto = new JBoleto(generator, jBoletoBean, JBoleto.BANCO_DO_BRASIL);
        jBoleto.addBoleto();
        jBoleto.closeBoleto("C:\\SistemaMultas\\" + nossoNumero + ".pdf");

        // Exibição do boleto
        String arquivo = "C:\\SistemaMultas\\" + nossoNumero + ".pdf";
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            Runtime.getRuntime().exec("cmd /c start AcroRd32.exe /p \"" + arquivo + "\"").waitFor();
        }

        return resultado;
    }

    private static String preenchecomZeros(String numero, int tamanho) {
        while (numero.length() < tamanho) {
            numero = "0" + numero;
        }
        return numero;
    }
    
    private static String proximoNossoNumero() {
        Configuracao nossoNumero;
        String ultimo;
        EntityManager em = EMF.get().createEntityManager();
        Query query = em.createQuery("SELECT c from Configuracao c where c.chave = 'ultimoNossoNumero'");
        query.setHint(QueryHints.REFRESH, HintValues.TRUE);

        nossoNumero = (Configuracao) query.getSingleResult();
        ultimo = nossoNumero.getValor();

        em.getTransaction().begin();
        nossoNumero.setValor(String.valueOf(Integer.valueOf(ultimo) + 1));
        nossoNumero.setValor(preenchecomZeros(nossoNumero.getValor(), 10));
        em.merge(nossoNumero);
        em.getTransaction().commit();
        em.close();

        return ultimo;
    }

    public static boolean geraBoleto(Multa multa) {
        formatoNumero.setGroupingUsed(false);
        try {
            return geraBoletoBB(multa);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
