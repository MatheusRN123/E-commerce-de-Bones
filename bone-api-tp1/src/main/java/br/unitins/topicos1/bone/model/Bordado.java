package br.unitins.topicos1.bone.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Bordado {

    COM_BORDADO(1, "Com Bordado"),
    SEM_BORDADO(2, "Sem Bordado"),
    PERSONALIZADO(3, "Personalizado");

    @JsonProperty("id")
    public final int ID;

    @JsonProperty("nome")
    public final String NOME;

    Bordado(int id, String nome) {
        this.ID = id;
        this.NOME = nome;
    }

    public int getId() {
        return ID;
    }

    public String getNome() {
        return NOME;
    }

    public static Bordado valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;

        for (Bordado b : Bordado.values()) {
            if (id.equals(b.getId()))
                return b;
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
