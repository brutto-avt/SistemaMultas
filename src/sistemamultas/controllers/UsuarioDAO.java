package sistemamultas.controllers;

import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import sistemamultas.models.Funcao;
import sistemamultas.models.Usuario;
import util.Criptografia;
import util.EMF;

public class UsuarioDAO {

    private Usuario usuario;
    private static Usuario usuarioLogado;

    public UsuarioDAO(Usuario usuario) {
        this.usuario = usuario;
        if (usuario == null) {
            this.usuario = new Usuario();
        }
    }

    public static List<Usuario> listaUsuarios(String busca) {
        List<Usuario> ret = null;
        EntityManager em = EMF.get().createEntityManager();
        StringBuilder sql = new StringBuilder("SELECT u from Usuario u");
        Query query;
        if (busca != null) {
            sql.append(" WHERE u.condutorId.cpf LIKE :busca");
            sql.append(" OR u.condutorId.nome LIKE :busca");
            sql.append(" OR u.condutorId.cnhNumero LIKE :busca");
        }
        sql.append(" ORDER BY u.condutorId.nome");
        query = em.createQuery(sql.toString());
        query.setHint(QueryHints.REFRESH, HintValues.TRUE);
        if (busca != null) {
            query.setParameter("busca", "%" + busca + "%");
        }
        if (!query.getResultList().isEmpty()) {
            ret = (List<Usuario>) query.getResultList();
            em.close();
            return ret;
        }
        em.close();
        return ret;
    }

    public boolean temAcesso(String menu) {
        usuario.getFuncaoList().size();
        
        if (usuario.getTipo().equals('A')) {
            return true;
        }
        
        for (Funcao funcao : usuario.getFuncaoList()) {
            if (funcao.getMenu().equals(menu)) {
                return true;
            }
        }
        return false;
    }

    public boolean darAcesso(String menu) {
        Funcao acesso = null;
        for (Funcao funcao : usuario.getFuncaoList()) {
            if (funcao.getMenu().equals(menu)) {
                acesso = funcao;
                break;
            }
        }
        usuario.getFuncaoList().add(acesso);
        return true;
    }

    public void removerAcesso(String menu) {
        Iterator<Funcao> iterator = usuario.getFuncaoList().iterator();
        while (iterator.hasNext()) {
            Funcao funcao = iterator.next();
            if (funcao.getMenu().equals(menu)) {
                iterator.remove();
            }
        }
    }

    public void grava(String senha) {
        EntityManager em = EMF.get().createEntityManager();
        em.getTransaction().begin();
        //
        if (usuario.getId() == null || !usuario.getSenha().equals(senha)) {
            usuario.setSenha(Criptografia.encripta(senha));
        }
        //
        usuario = em.merge(usuario);
        em.getTransaction().commit();
        em.close();
    }

    public boolean exclui() {
        if (usuario.getId() != null) {
            EntityManager em = EMF.get().createEntityManager();
            em.getTransaction().begin();
            Usuario u = em.merge(usuario);
            em.remove(u);
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

    public String valida(String senha) {
        if (usuario.getCondutorId() == null) {
            return "Selecione um condutor";
        }
        if (senha.length() == 0) {
            return "Informe uma senha";
        }
        if (usuario.getFuncaoList().isEmpty()) {
            return "Adicione ao menos uma permissão";
        }
        return null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(Usuario aUsuarioLogado) {
        usuarioLogado = aUsuarioLogado;
    }
}
