package it.officina.OfficinaRiparazioneMoto.converter;

import jakarta.persistence.AttributeConverter;

public class LowercaseConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute != null) {
            return attribute.trim().toLowerCase();
        }
        return attribute;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }

}
