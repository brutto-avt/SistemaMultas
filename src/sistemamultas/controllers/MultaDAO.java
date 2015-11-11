package sistemamultas.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import sistemamultas.models.Infracao;
import sistemamultas.models.Multa;
import sistemamultas.models.MultaInfracao;
import sistemamultas.models.MultaTaxa;
import sistemamultas.models.Taxa;
import util.EMF;

public class MultaDAO {

    private Multa multa;
    private final List<MultaInfracao> removerInfracoes;
    private final List<MultaTaxa> removerTaxas;

    public MultaDAO(Multa multa) {
        this.removerInfracoes = new ArrayList<>();
        this.removerTaxas = new ArrayList<>();
        this.multa = multa;
        if (multa == null) {
            this.multa = new Multa();
        }
    }

    public static List<Multa> listaMultas(String busca) {
        List<Multa> ret = null;
        EntityManager em = EMF.get().createEntityManager();
        StringBuilder sql = new StringBuilder("SELECT m from Multa m");
        Query query;
        if (busca != null) {
            sql.append(" WHERE m.veiculoId.placa LIKE :busca");
            sql.append(" OR m.condutorId.nome LIKE :busca");
            sql.append(" OR m.veiculoId.proprietarioId.nome LIKE :busca");
            sql.append(" OR m.localAutuacao LIKE :busca");
            sql.append(" OR m.id LIKE :busca");
        }
        sql.append(" ORDER BY m.dataAutuacao desc");
        query = em.createQuery(sql.toString());
        query.setHint(QueryHints.REFRESH, HintValues.TRUE);
        if (busca != null) {
            query.setParameter("busca", "%" + busca + "%");
        }
        if (!query.getResultList().isEmpty()) {
            ret = (List<Multa>) query.getResultList();
            em.close();
            return ret;
        }
        em.close();
        return ret;
    }

    public boolean exclui() {
        if (multa.getId() != null) {
            EntityManager em = EMF.get().createEntityManager();
            em.getTransaction().begin();
            Multa i = em.merge(multa);
            em.remove(i);
            try {
                em.getTransaction().commit();
                em.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public void grava() {
        EntityManager em = EMF.get().createEntityManager();
        em.getTransaction().begin();
        //
        if (removerInfracoes.size() > 0) {
            for (MultaInfracao i : removerInfracoes) {
                if (i.getId() != null) {
                    i = em.merge(i);
                    em.remove(i);
                }
            }
        }
        if (removerTaxas.size() > 0) {
            for (MultaTaxa t : removerTaxas) {
                if (t.getId() != null) {
                    t = em.merge(t);
                    em.remove(t);
                }
            }
        }
        //
        multa = em.merge(multa);
        em.getTransaction().commit();
        em.close();
    }

    public String valida(Date autuacao, Date vencimento, Date pagamento) {
        if (multa.getCondutorId() == null) {
            multa.setCondutorId(multa.getVeiculoId().getProprietarioId());
        }
        if (multa.getLocalAutuacao() == null || multa.getLocalAutuacao().trim().length() == 0) {
            return "Informe o local da autuação";
        }
        if (autuacao == null) {
            multa.setDataAutuacao(new Date());
        } else {
            multa.setDataAutuacao(autuacao);
        }
        if (vencimento == null) {
            Calendar venc = Calendar.getInstance();
            venc.setTime(multa.getDataAutuacao());
            venc.add(Calendar.DATE, 30);
            multa.setDataVencimento(venc.getTime());
        } else {
            multa.setDataVencimento(vencimento);
        }
        if (multa.getMultaInfracaoList().isEmpty()) {
            return "Informe as infrações cometidas";
        }
        if (multa.getVeiculoId() == null) {
            return "Informe o veículo multado";
        }
        multa.setDataPagamento(pagamento);
        return null;
    }

    public boolean adicionarInfracao(Infracao infracao) {
        for (MultaInfracao m : multa.getMultaInfracaoList()) {
            if (m.getInfracaoId().getId().equals(infracao.getId())) {
                return false;
            }
        }
        MultaInfracao inf = new MultaInfracao();
        inf.setInfracaoId(infracao);
        inf.setMultaId(multa);
        multa.getMultaInfracaoList().add(inf);
        return true;
    }

    public void removerInfracao(MultaInfracao infracao) {
        if (infracao != null) {
            if (infracao.getId() != null) {
                removerInfracoes.add(infracao);
            }
            Iterator<MultaInfracao> iterator = multa.getMultaInfracaoList().iterator();
            while (iterator.hasNext()) {
                MultaInfracao i = iterator.next();
                if (i.getInfracaoId().getId().equals(infracao.getInfracaoId().getId())) {
                    iterator.remove();
                }
            }
        }
    }

     public boolean adicionarTaxa (Taxa taxa) {
        for (MultaTaxa m: multa.getMultaTaxaList()) {
            if (m.getTaxaId().getId().equals(taxa.getId())) {
                return false;
            }
        }
        MultaTaxa tax = new MultaTaxa();
        tax.setTaxaId(taxa);
        tax.setMultaId(multa);
        multa.getMultaTaxaList().add(tax);
        return true;
    }
    
    public void removerTaxa(MultaTaxa taxa) {
        if (taxa != null) {
            if (taxa.getId() != null) {
                removerTaxas.add(taxa);
            }
            Iterator<MultaTaxa> iterator = multa.getMultaTaxaList().iterator();
            while (iterator.hasNext()) {
                MultaTaxa t = iterator.next();
                if (t.getTaxaId().getId().equals(taxa.getTaxaId().getId())) {
                    iterator.remove();
                }
            }
        }
    }

    public Multa getMulta() {
        return multa;
    }

    public void setMulta(Multa multa) {
        this.multa = multa;
    }
}
