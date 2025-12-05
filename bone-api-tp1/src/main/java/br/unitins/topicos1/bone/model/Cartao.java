package br.unitins.topicos1.bone.model;

<<<<<<< HEAD
import jakarta.persistence.Column;
=======
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
import jakarta.persistence.Entity;

@Entity
public class Cartao extends Pagamento{
    
<<<<<<< HEAD
    @Column(name = "nome_titular")
=======
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
    private String nomeTitular;
    private String numero;
    private String validade;
    private String cvv;
    
    
    public String getNomeTitular() {
        return nomeTitular;
    }
    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getValidade() {
        return validade;
    }
    public void setValidade(String validade) {
        this.validade = validade;
    }
    public String getCvv() {
        return cvv;
    }
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
