package br.unitins.topicos1.bone.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Estoque extends DefaultEntity {

    @Column(nullable = false)
    private Integer quantidade = 0;

    @Column(name = "data_atualizacao")
    private LocalDate dataAtualizacao;


    public Boolean verificarDisponibilidade() {
        return quantidade != null && quantidade > 0;
    }

    public void atualizarQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        this.dataAtualizacao = LocalDate.now();
    }

    public void adicionarQuantidade(Integer quantidade){
        if(this.quantidade == null){
            this.quantidade = 0;
        }

        this.quantidade += quantidade;
        this.dataAtualizacao = LocalDate.now();
    }


    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDate dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
