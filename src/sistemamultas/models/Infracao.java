package sistemamultas.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "infracao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Infracao.findAll", query = "SELECT i FROM Infracao i")})
public class Infracao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "artigo", nullable = false, length = 20)
    private String artigo;
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 200)
    private String descricao;
    @Basic(optional = false)
    @Column(name = "gravidade", nullable = false, length = 20)
    private String gravidade;
    @Basic(optional = false)
    @Column(name = "pontuacao", nullable = false)
    private int pontuacao;
    @Basic(optional = false)
    @Column(name = "valor", nullable = false)
    private double valor;

    public Infracao() {
    }

    public Infracao(Integer id) {
        this.id = id;
    }

    public Infracao(Integer id, String artigo, String descricao, String gravidade, int pontuacao, double valor) {
        this.id = id;
        this.artigo = artigo;
        this.descricao = descricao;
        this.gravidade = gravidade;
        this.pontuacao = pontuacao;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArtigo() {
        return artigo;
    }

    public void setArtigo(String artigo) {
        this.artigo = artigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getGravidade() {
        return gravidade;
    }

    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Infracao)) {
            return false;
        }
        Infracao other = (Infracao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sistemamultas.models.Infracao[ id=" + id + " ]";
    }
    
}
