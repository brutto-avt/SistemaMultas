package sistemamultas.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "taxa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Taxa.findAll", query = "SELECT t FROM Taxa t")})
public class Taxa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 200)
    private String descricao;
    @Basic(optional = false)
    @Column(name = "valor", nullable = false)
    private double valor;
    @Basic(optional = false)
    @Column(name = "tipo_valor", nullable = false)
    private Character tipoValor;
    @Basic(optional = false)
    @Column(name = "periodo", nullable = false)
    private Character periodo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taxaId")
    private List<MultaTaxa> multaTaxaList;

    public Taxa() {
        this.valor = 0.0;
    }

    public Taxa(Integer id) {
        this.id = id;
        this.valor = 0.0;
    }

    public Taxa(Integer id, String descricao, Double valor, Character tipoValor, Character periodo) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.tipoValor = tipoValor;
        this.periodo = periodo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Character getTipoValor() {
        return tipoValor;
    }

    public void setTipoValor(Character tipoValor) {
        this.tipoValor = tipoValor;
    }

    public Character getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Character periodo) {
        this.periodo = periodo;
    }

    @XmlTransient
    public List<MultaTaxa> getMultaTaxaList() {
        return multaTaxaList;
    }

    public void setMultaTaxaList(List<MultaTaxa> multaTaxaList) {
        this.multaTaxaList = multaTaxaList;
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
        if (!(object instanceof Taxa)) {
            return false;
        }
        Taxa other = (Taxa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sistemamultas.models.Taxa[ id=" + id + " ]";
    }
    
    public String getPeriodoStr() {
        switch (this.periodo) {
            case 'U':
                return "Única";
            case 'D':
                return "Diária";
            case 'S':
                return "Semanal";
            case 'M':
                return "Mensal";
            case 'A':
                return "Anual";
        }
        return null;
    }
    
    public String getTipoValorStr() {
        switch (this.tipoValor) {
            case 'V':
                return "Valor";
            case 'P':
                return "Percentual";
        }
        return null;
    }
}
