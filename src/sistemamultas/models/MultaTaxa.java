package sistemamultas.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
        return "sistemamultas.models.MultaTaxa[ id=" + id + " ]";
    }
    
}
