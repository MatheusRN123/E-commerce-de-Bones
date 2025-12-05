package br.unitins.topicos1.bone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Pix extends Pagamento {
    private String chave;

    @Column(name = "tipo_chave")
    private String tipoChave;
    
    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getTipoChave() {
        return tipoChave;
    }

    public void setTipoChave(String tipoChave) {
        this.tipoChave = tipoChave;
    }
}
