package br.unitins.topicos1.bone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
<<<<<<< HEAD
import jakarta.persistence.JoinColumn;
=======
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Endereco {

<<<<<<< HEAD
    @Id
=======
    @Id 
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;
    private String logradouro;
    private String numero;

    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "id_cidade")
=======
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
    private Cidade cidade;

    @OneToOne(mappedBy = "endereco")
    private Pedido pedido;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
