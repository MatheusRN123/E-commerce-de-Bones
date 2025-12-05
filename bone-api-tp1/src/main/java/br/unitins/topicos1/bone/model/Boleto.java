package br.unitins.topicos1.bone.model;

import java.time.LocalDate;

<<<<<<< HEAD
import jakarta.persistence.Column;
=======
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
import jakarta.persistence.Entity;

@Entity
public class Boleto extends Pagamento{
    
<<<<<<< HEAD
    @Column(name = "codigo_barras")
    private String codigoBarras;

    @Column(name = "data_vencimento")
=======
    private String codigoBarras;
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
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
