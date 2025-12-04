package br.unitins.topicos1.bone.model;

import jakarta.persistence.Entity;

@Entity
public class Pix extends Pagamento {
    private String chave;
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
