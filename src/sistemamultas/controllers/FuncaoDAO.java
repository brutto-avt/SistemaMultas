package sistemamultas.controllers;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import sistemamultas.models.Funcao;
import util.EMF;

public class FuncaoDAO {
    
    public static List<Funcao> listaFuncoes(String nome) {
        List<Funcao> ret = null;
        EntityManager em = EMF.get().createEntityManager();
        Query query;
        if (nome != null) {
            query = em.createNamedQuery("Funcao.findByNome");
            query.setParameter("nome", nome);
        } else {
            query = em.createNamedQuery("Funcao.findAll");
        }
        if (!query.getResultList().isEmpty()) {
            ret = (List<Funcao>) query.getResultList();
            em.close();
            return ret;
        }
        em.close();
        return ret;
    }
}
