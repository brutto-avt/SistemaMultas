package sistemamultas.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "multa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Multa.findAll", query = "SELECT m FROM Multa m")})
public class Multa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "protocolo", nullable = false, length = 12)
    private String protocolo;
    @Basic(optional = false)
    @Column(name = "data_autuacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAutuacao;
    @Basic(optional = false)
    @Column(name = "local_autuacao", nullable = false, length = 200)
    private String localAutuacao;
    @Basic(optional = false)
    @Column(name = "data_vencimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    @Basic(optional = false)
    @Column(name = "data_pagamento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;
    @JoinColumn(name = "condutor_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Condutor condutorId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "multaId")
    private List<MultaTaxa> multaTaxaList;
    @OneToMany(mappedBy = "multaId")
    private List<MultaInfracao> multaInfracaoList;

    public Multa() {
    }

    public Multa(Integer id) {
        this.id = id;
    }

    public Multa(Integer id, String protocolo, Date dataAutuacao, String localAutuacao, Date dataVencimento, Date dataPagamento) {
        this.id = id;
        this.protocolo = protocolo;
        this.dataAutuacao = dataAutuacao;
        this.localAutuacao = localAutuacao;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public Date getDataAutuacao() {
        return dataAutuacao;
    }

    public void setDataAutuacao(Date dataAutuacao) {
        this.dataAutuacao = dataAutuacao;
    }

    public String getLocalAutuacao() {
        return localAutuacao;
    }

    public void setLocalAutuacao(String localAutuacao) {
        this.localAutuacao = localAutuacao;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Condutor getCondutorId() {
        return condutorId;
    }

    public void setCondutorId(Condutor condutorId) {
        this.condutorId = condutorId;
    }

    @XmlTransient
    public List<MultaTaxa> getMultaTaxaList() {
        return multaTaxaList;
    }

    public void setMultaTaxaList(List<MultaTaxa> multaTaxaList) {
        this.multaTaxaList = multaTaxaList;
    }

    @XmlTransient
    public List<MultaInfracao> getMultaInfracaoList() {
        return multaInfracaoList;
    }

    public void setMultaInfracaoList(List<MultaInfracao> multaInfracaoList) {
        this.multaInfracaoList = multaInfracaoList;
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
        if (!(object instanceof Multa)) {
            return false;
        }
        Multa other = (Multa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sistemamultas.models.Multa[ id=" + id + " ]";
    }
    
}
