package sistemamultas.models;

import java.io.Serializable;
import java.text.NumberFormat;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "multa_taxa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MultaTaxa.findAll", query = "SELECT m FROM MultaTaxa m")})
public class MultaTaxa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @JoinColumn(name = "taxa_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Taxa taxaId;
    @JoinColumn(name = "multa_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Multa multaId;

    public MultaTaxa() {
    }

    public MultaTaxa(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Taxa getTaxaId() {
        return taxaId;
    }

    public void setTaxaId(Taxa taxaId) {
        this.taxaId = taxaId;
    }

    public Multa getMultaId() {
        return multaId;
    }

    public void setMultaId(Multa multaId) {
        this.multaId = multaId;
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
        if (!(object instanceof MultaTaxa)) {
            return false;
        }
        MultaTaxa other = (MultaTaxa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        nf.setMinimumIntegerDigits(1);
        String retorno = this.getTaxaId().getDescricao() + " (";
        if (this.getTaxaId().getTipoValor().equals('V')) {
            retorno += "R$ " + nf.format(this.getTaxaId().getValor());
        } else {
            retorno += nf.format(this.getTaxaId().getValor()) + "%";
        }
        switch (this.getTaxaId().getPeriodo()) {
            case 'U':
                retorno += ')';
                break;
            case 'D':
                retorno += " / dia)";
                break;
            case 'S':
                retorno += " / semana)";
                break;
            case 'M':
                retorno += " / mês)";
                break;
            case 'A':
                retorno += " / ano)";
                break;
        }
        return  retorno;
    }
    
}
