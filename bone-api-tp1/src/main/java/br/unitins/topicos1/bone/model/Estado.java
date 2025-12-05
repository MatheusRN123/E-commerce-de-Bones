package br.unitins.topicos1.bone.model;

import java.util.List;

<<<<<<< HEAD
import jakarta.persistence.Entity;
=======
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

<<<<<<< HEAD
@Entity
=======
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sigla;

    @OneToMany(mappedBy = "estado")
    private List<Cidade> cidades;
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public List<Cidade> getCidades() {
        return cidades;
    }
    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }
    public String getSigla() {
        return sigla;
    }
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
