package br.unitins.topicos1.bone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Bone extends DefaultEntity {
    

    private String nome;
    private String cor;
    private String categoriaAba;
    private Float tamanhoAba;
    private Float profundidade;
    private String circunferência;

    @Enumerated(EnumType.STRING)
    private Bordado bordado;

    @ManyToOne
    @JoinColumn(name = "id_material")
    private Material material;



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

    public String getCircunferência() {
        return circunferência;
    }

    public void setCircunferência(String circunferência) {
        this.circunferência = circunferência;
    }

    public Bordado getBordado() {
        return bordado;
    }

    public void setBordado(Bordado bordado) {
        this.bordado = bordado;
    }
}
