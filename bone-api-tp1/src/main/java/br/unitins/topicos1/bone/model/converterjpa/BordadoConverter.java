package br.unitins.topicos1.bone.model.converterjpa;

import br.unitins.topicos1.bone.model.Bordado;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BordadoConverter implements AttributeConverter<Bordado, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Bordado bordado) {
        return bordado == null ? null : bordado.getId();
    }

    @Override
    public Bordado convertToEntityAttribute(Integer idBordado) {
        return Bordado.valueOf(idBordado);
    }
    
}