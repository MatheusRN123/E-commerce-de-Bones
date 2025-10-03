package br.unitins.topicos1.bone.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Bone extends DefaultEntity {
    

    private String nome;
    private String cor;

    @Column(name = "categoria_aba")
    private String categoriaAba;

    @Column(name = "tamanho_aba")
    private Float tamanhoAba;
    private Float profundidade;

    private String circunferencia;

    @Enumerated(EnumType.ORDINAL)
    private Bordado bordado;

    @ManyToOne
    @JoinColumn(name = "id_material")
    private Material material;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_estoque")
    private Estoque estoque;



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getCategoriaAba() {
        return categoriaAba;
    }

    public void setCategoriaAba(String categoriaAba) {
        this.categoriaAba = categoriaAba;
    }

    public Float getTamanhoAba() {
        return tamanhoAba;
    }

    public void setTamanhoAba(Float tamanhoAba) {
        this.tamanhoAba = tamanhoAba;
    }

    public Float getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(Float profundidade) {
        this.profundidade = profundidade;
    }

    public String getCircunferencia() {
        return circunferencia;
    }

    public void setCircunferencia(String circunferencia) {
        this.circunferencia = circunferencia;
    }

    public Bordado getBordado() {
        return bordado;
    }

    public void setBordado(Bordado bordado) {
        this.bordado = bordado;
    }

    public Marca getMarca(){
        return marca;
    }

    public void setMarca(Marca marca){
        this.marca = marca;
    }

    public Estoque getEstoque(){
        return estoque;
    }

    public void setEstoque(Estoque estoque){
        this.estoque = estoque;
    }
}
