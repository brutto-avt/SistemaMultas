package sistemamultas.controllers;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import sistemamultas.models.Condutor;
import util.CpfCnpj;
import util.EMF;

public class CondutorDAO {
    private Condutor condutor;
    
    public CondutorDAO(Condutor condutor) {
        this.condutor = condutor;
        if (condutor == null) {
            this.condutor = new Condutor();
        }
    }
    
    public static List<Condutor> listaCondutores(String busca) {
        List<Condutor> ret = null;
        EntityManager em = EMF.get().createEntityManager();
        StringBuilder sql = new StringBuilder("SELECT c from Condutor c");
        Query query;
        if (busca != null) {
            sql.append(" WHERE c.nome LIKE :busca");
            sql.append(" OR c.cnhNumero LIKE :busca");
            sql.append(" OR c.cpf LIKE :busca");
        }
        sql.append(" ORDER BY c.nome");
        query = em.createQuery(sql.toString());
        query.setHint(QueryHints.REFRESH, HintValues.TRUE);
        if (busca != null) {
            query.setParameter("busca", "%"+busca+"%");
        }
        if (!query.getResultList().isEmpty()) {
            ret = (List<Condutor>) query.getResultList();
            em.close();
            return ret;
        }
        em.close();
        return ret;
    }
    
    public boolean exclui() {
        if (condutor.getId() != null) {
            EntityManager em = EMF.get().createEntityManager();
            em.getTransaction().begin();
            Condutor f = em.merge(condutor);
            em.remove(f);
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
    
    public void grava(Date nascimento) {
        EntityManager em = EMF.get().createEntityManager();
        em.getTransaction().begin();
        condutor.setNascimento(nascimento);
        condutor = em.merge(condutor);
        em.getTransaction().commit();
        em.close();
    }
    
    public String valida(Date nascimento) {
        if (!CpfCnpj.isValid(condutor.getCpf())) {
            return "CPF inválido";
        }
        if (condutor.getCnhNumero() == null) {
            return "Informe a CNH";
        }
        if (nascimento == null) {
            return "Informe a data de nascimento";
        }
        return null;
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }
}
