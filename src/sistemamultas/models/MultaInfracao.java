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
@Table(name = "multa_infracao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MultaInfracao.findAll", query = "SELECT m FROM MultaInfracao m")})
public class MultaInfracao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @JoinColumn(name = "infracao_id", referencedColumnName = "id")
    @ManyToOne
    private Infracao infracaoId;
    @JoinColumn(name = "multa_id", referencedColumnName = "id")
    @ManyToOne
    private Multa multaId;

    public MultaInfracao() {
    }

    public MultaInfracao(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Infracao getInfracaoId() {
        return infracaoId;
    }

    public void setInfracaoId(Infracao infracaoId) {
        this.infracaoId = infracaoId;
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
        if (!(object instanceof MultaInfracao)) {
            return false;
        }
        MultaInfracao other = (MultaInfracao) object;
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
        
        return this.getInfracaoId().getArtigo() + " - " + this.getInfracaoId().getDescricao() + "( R$ " + nf.format(this.getInfracaoId().getValor()) + ")";
    }
    
}
