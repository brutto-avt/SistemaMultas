package sistemamultas.controllers;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import sistemamultas.models.Veiculo;
import util.EMF;

public class VeiculoDAO {
    private Veiculo veiculo;
    
    public VeiculoDAO(Veiculo veiculo) {
        this.veiculo = veiculo;
        if (veiculo == null) {
            this.veiculo = new Veiculo();
        }
    }
    
    public static List<Veiculo> listaVeiculos(String busca) {
        List<Veiculo> ret = null;
        EntityManager em = EMF.get().createEntityManager();
        StringBuilder sql = new StringBuilder("SELECT v from Veiculo v");
        Query query;
        if (busca != null) {
            sql.append("WHERE v.modelo LIKE :busca");
            sql.append("OR v.placa LIKE = :busca");
        }
        sql.append(" ORDER BY v.placa");
        query = em.createQuery(sql.toString());
        query.setHint(QueryHints.REFRESH, HintValues.TRUE);
        if (busca != null) {
            query.setParameter("busca", "%"+busca+"%");
        }
        if (!query.getResultList().isEmpty()) {
            ret = (List<Veiculo>) query.getResultList();
            em.close();
            return ret;
        }
        em.close();
        return ret;
    }
    
    public boolean exclui() {
        if (veiculo.getId() != null) {
            EntityManager em = EMF.get().createEntityManager();
            em.getTransaction().begin();
            Veiculo p = em.merge(veiculo);
            em.remove(p);
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
        veiculo = em.merge(veiculo);
        em.getTransaction().commit();
        em.close();
    }
    
    public String valida() {
        if (veiculo.getAno() == 0) {
            return "Informe o ano do veículo";
        }
        if (veiculo.getPlaca() == null || veiculo.getPlaca().trim().length() == 0) {
            return "Informe a placa do veículo";
        }
        if (veiculo.getMarca() == null || veiculo.getMarca().trim().length() == 0) {
            return "Informe a marca do veículo";
        }
        if (veiculo.getModelo() == null || veiculo.getModelo().trim().length() == 0) {
            return "Informe o modelo do veículo";
        }
        if (veiculo.getCor() == null || veiculo.getCor().trim().length() == 0) {
            return "Informe a cor do veículo";
        }
        if (veiculo.getProprietarioId() == null) {
            return "O veículo deve possuir um proprietário";
        }
        return null;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

}
