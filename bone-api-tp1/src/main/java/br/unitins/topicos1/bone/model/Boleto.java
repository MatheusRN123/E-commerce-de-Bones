package br.unitins.topicos1.bone.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Boleto extends Pagamento{
    
    private String codigoBarras;
    private LocalDate dataVencimento;
    
    public String getCodigoBarras() {
        return codigoBarras;
    }
    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }
    public LocalDate getDataVencimento() {
        return dataVencimento;
    }
    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}
