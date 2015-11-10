package sistemamultas.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
    @JoinColumn(name = "veiculo_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Veiculo veiculoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "multaId")
    private List<MultaTaxa> multaTaxaList;
    @OneToMany(mappedBy = "multaId")
    private List<MultaInfracao> multaInfracaoList;

    public Multa() {
    }

    public Multa(Integer id) {
        this.id = id;
    }

    public Multa(Integer id, Date dataAutuacao, String localAutuacao, Date dataVencimento, Date dataPagamento) {
        this.id = id;
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

    public Veiculo getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Veiculo veiculoId) {
        this.veiculoId = veiculoId;
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
    
    public Double getTotalInfracoes () {
        Double total = 0.0;        
        for (MultaInfracao inf: this.multaInfracaoList) {
            total += inf.getInfracaoId().getValor();
        }
        return total;
    }
    
    public Double getTotalTaxas () {
        Double total = 0.0;
        Date vencimento;
        Date hoje = new Date();
        
        for (MultaTaxa tax: this.multaTaxaList) {
            vencimento = this.getDataVencimento();
            long diferenca = hoje.getTime() - vencimento.getTime();
            
            switch (tax.getTaxaId().getPeriodo()) {
                    case 'U':
                        diferenca = 1;
                        break;
                    case 'D':
                        diferenca = TimeUnit.DAYS.convert(diferenca, TimeUnit.MILLISECONDS);
                        break;
                    case 'S':
                        diferenca = TimeUnit.DAYS.convert(diferenca, TimeUnit.MILLISECONDS) / 7;
                        break;
                    case 'M':
                        diferenca = TimeUnit.DAYS.convert(diferenca, TimeUnit.MILLISECONDS) / 30;
                        break;
                    case 'A':
                        diferenca = TimeUnit.DAYS.convert(diferenca, TimeUnit.MILLISECONDS) / 365;
                        break;
                }
                if (tax.getTaxaId().getTipoValor().equals('V')) {
                    total += (tax.getTaxaId().getValor() * diferenca);
                } else {
                    total += ((tax.getTaxaId().getValor() / 100) * this.getTotalInfracoes()) * diferenca;
                }
        }
        return total;
    }
    
    public Double getTotal () {
        return this.getTotalInfracoes() + this.getTotalTaxas();
    }
}
