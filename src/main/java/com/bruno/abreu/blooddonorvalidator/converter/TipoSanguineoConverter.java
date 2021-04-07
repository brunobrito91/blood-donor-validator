package com.bruno.abreu.blooddonorvalidator.converter;

import com.bruno.abreu.blooddonorvalidator.model.TipoSanguineo;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TipoSanguineoConverter implements AttributeConverter<TipoSanguineo, String> {
    @Override
    public String convertToDatabaseColumn(TipoSanguineo attribute) {
        return attribute.getTipoSanguineo();
    }

    @Override
    public TipoSanguineo convertToEntityAttribute(String dbData) {
        return TipoSanguineo.valueOf(dbData);
    }
}
