package sistemamultas.controllers;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import sistemamultas.models.Taxa;
import util.EMF;

public class TaxaDAO {
    private Taxa taxa;
    
    public TaxaDAO(Taxa taxa) {
        this.taxa = taxa;
        if (taxa == null) {
            this.taxa = new Taxa();
        }
    }
    
    public static List<Taxa> listaTaxas(String busca) {
        List<Taxa> ret = null;
        EntityManager em = EMF.get().createEntityManager();
        StringBuilder sql = new StringBuilder("SELECT t from Taxa t");
        Query query;
        if (busca != null) {
            sql.append(" WHERE t.descricao LIKE :busca");
        }
        sql.append(" ORDER BY t.descricao");
        query = em.createQuery(sql.toString());
        query.setHint(QueryHints.REFRESH, HintValues.TRUE);
        if (busca != null) {
            query.setParameter("busca", "%"+busca+"%");
        }
        if (!query.getResultList().isEmpty()) {
            ret = (List<Taxa>) query.getResultList();
            em.close();
            return ret;
        }
        em.close();
        return ret;
    }
    
    public boolean exclui() {
        if (taxa.getId() != null) {
            EntityManager em = EMF.get().createEntityManager();
            em.getTransaction().begin();
            Taxa i = em.merge(taxa);
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
    
    public void grava(Character tipoValor, Character periodo) {
        EntityManager em = EMF.get().createEntityManager();
        em.getTransaction().begin();
        taxa.setTipoValor(tipoValor);
        taxa.setPeriodo(periodo);
        taxa = em.merge(taxa);
        em.getTransaction().commit();
        em.close();
    }
    
    public String valida(Character tipoValor, Character periodo) {
        if (taxa.getDescricao() == null || taxa.getDescricao().trim().length() == 0) {
            return "Informe a descrição da taxa";
        }
        if (taxa.getValor() == null) {
            return "Informe o valor da taxa";
        }
        if (tipoValor == null) {
            return "Informe o tipo de valor";
        }
        if (periodo == null) {
            return "Informe a periodicidade";
        }
        return null;
    }

    public Taxa getTaxa() {
        return taxa;
    }

    public void setTaxa(Taxa taxa) {
        this.taxa = taxa;
    }
}
