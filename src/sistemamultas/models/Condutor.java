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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "condutor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Condutor.findAll", query = "SELECT c FROM Condutor c")})
public class Condutor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome", nullable = false, length = 200)
    private String nome;
    @Basic(optional = false)
    @Column(name = "nascimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date nascimento;
    @Basic(optional = false)
    @Column(name = "cnh_numero", nullable = false)
    private Integer cnhNumero;
    @Basic(optional = false)
    @Column(name = "cnh_categoria", nullable = false, length = 2)
    private String cnhCategoria;
    @Basic(optional = false)
    @Column(name = "cpf", nullable = false, length = 20)
    private String cpf;
    @Basic(optional = false)
    @Column(name = "cep", nullable = false, length = 10)
    private String cep;
    @Basic(optional = false)
    @Column(name = "logradouro", nullable = false, length = 200)
    private String logradouro;
    @Basic(optional = false)
    @Column(name = "numero", nullable = false, length = 20)
    private String numero;
    @Basic(optional = false)
    @Column(name = "complemento", nullable = false, length = 80)
    private String complemento;
    @Basic(optional = false)
    @Column(name = "uf", nullable = false, length = 2)
    private String uf;
    @Basic(optional = false)
    @Column(name = "cidade", nullable = false, length = 200)
    private String cidade;
    @Basic(optional = false)
    @Column(name = "bairro", nullable = false, length = 80)
    private String bairro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "condutorId")
    private List<Multa> multaList;
    @OneToMany(mappedBy = "condutorId")
    private List<Usuario> usuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proprietarioId")
    private List<Veiculo> veiculoList;

    public Condutor() {
    }

    public Condutor(Integer id) {
        this.id = id;
    }

    public Condutor(Integer id, String nome, Date nascimento, Integer cnhNumero, String cnhCategoria, String cpf, String cep, String logradouro, String numero, String complemento, String uf, String cidade, String bairro) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
        this.cnhNumero = cnhNumero;
        this.cnhCategoria = cnhCategoria;
        this.cpf = cpf;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.uf = uf;
        this.cidade = cidade;
        this.bairro = bairro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Integer getCnhNumero() {
        return cnhNumero;
    }

    public void setCnhNumero(Integer cnhNumero) {
        this.cnhNumero = cnhNumero;
    }

    public String getCnhCategoria() {
        return cnhCategoria;
    }

    public void setCnhCategoria(String cnhCategoria) {
        this.cnhCategoria = cnhCategoria;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @XmlTransient
    public List<Multa> getMultaList() {
        return multaList;
    }

    public void setMultaList(List<Multa> multaList) {
        this.multaList = multaList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<Veiculo> getVeiculoList() {
        return veiculoList;
    }

    public void setVeiculoList(List<Veiculo> veiculoList) {
        this.veiculoList = veiculoList;
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
        if (!(object instanceof Condutor)) {
            return false;
        }
        Condutor other = (Condutor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nome;
    }
    
}
