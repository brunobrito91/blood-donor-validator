package com.bruno.abreu.blooddonorvalidator.converter;

import com.bruno.abreu.blooddonorvalidator.model.TipoSanguineo;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.bruno.abreu.blooddonorvalidator.model.TipoSanguineo.*;

@Converter(autoApply = true)
public class TipoSanguineoConverter implements AttributeConverter<TipoSanguineo, String> {
    @Override
    public String convertToDatabaseColumn(TipoSanguineo attribute) {
        return attribute.getTipoSanguineo();
    }

    @Override
    public TipoSanguineo convertToEntityAttribute(String dbData) {
        switch (dbData){
            case "A+": return AMAIS;
            case "A-": return AMENOS;
            case "B+": return BMAIS;
            case "B-": return BMENOS;
            case "AB+": return ABMAIS;
            case "AB-": return ABMENOS;
            case "O+": return OMAIS;
            case "O-": return OMENOS;
            default: return null;
        }
    }
}
