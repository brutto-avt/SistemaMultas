package sistemamultas.controllers;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import sistemamultas.models.Infracao;
import util.EMF;

public class InfracaoDAO {
    private Infracao infracao;
    
    public InfracaoDAO(Infracao infracao) {
        this.infracao = infracao;
        if (infracao == null) {
            this.infracao = new Infracao();
        }
    }
    
    public static List<Infracao> listaInfracaoes(String busca) {
        List<Infracao> ret = null;
        EntityManager em = EMF.get().createEntityManager();
        StringBuilder sql = new StringBuilder("SELECT i from Infracao i");
        Query query;
        if (busca != null) {
            sql.append(" WHERE i.artigo LIKE :busca");
            sql.append(" OR i.descricao LIKE :busca");
        }
        sql.append(" ORDER BY i.gravidade");
        query = em.createQuery(sql.toString());
        query.setHint(QueryHints.REFRESH, HintValues.TRUE);
        if (busca != null) {
            query.setParameter("busca", "%"+busca+"%");
        }
        if (!query.getResultList().isEmpty()) {
            ret = (List<Infracao>) query.getResultList();
            em.close();
            return ret;
        }
        em.close();
        return ret;
    }
    
    public boolean exclui() {
        if (infracao.getId() != null) {
            EntityManager em = EMF.get().createEntityManager();
            em.getTransaction().begin();
            Infracao i = em.merge(infracao);
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
    
    public void grava(Character gravidade) {
        EntityManager em = EMF.get().createEntityManager();
        em.getTransaction().begin();
        infracao.setGravidade(gravidade);
        infracao = em.merge(infracao);
        em.getTransaction().commit();
        em.close();
    }
    
    public String valida(Character gravidade) {        
        if (infracao.getArtigo() == null || infracao.getArtigo().trim().length() == 0) {
            return "Informe o artido da infração";
        }
        if (infracao.getDescricao() == null || infracao.getDescricao().trim().length() == 0) {
            return "Informe a descrição da infração";
        }
        if (gravidade == null) {
            return "Informe a gravidade da infração";
        }
        return null;
    }

    public Infracao getInfracao() {
        return infracao;
    }

    public void setInfracao(Infracao infracao) {
        this.infracao = infracao;
    }
}
