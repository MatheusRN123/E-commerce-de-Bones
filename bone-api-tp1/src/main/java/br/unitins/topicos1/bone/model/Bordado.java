package br.unitins.topicos1.bone.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Bordado {

    COM_BORDADO(1, "Com Bordado"),
    SEM_BORDADO(2, "Sem Bordado"),
    PERSONALIZADO(3, "Personalizado");

    private int id;
    private String label;

    Bordado(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
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
