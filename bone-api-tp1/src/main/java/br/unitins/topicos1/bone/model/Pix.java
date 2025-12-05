package br.unitins.topicos1.bone.model;

<<<<<<< HEAD
import jakarta.persistence.Column;
=======
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
import jakarta.persistence.Entity;

@Entity
public class Pix extends Pagamento {
    private String chave;
<<<<<<< HEAD

    @Column(name = "tipo_chave")
=======
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
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
