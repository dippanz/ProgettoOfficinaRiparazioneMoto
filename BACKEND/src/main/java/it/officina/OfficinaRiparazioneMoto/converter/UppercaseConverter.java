package it.officina.OfficinaRiparazioneMoto.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * A JPA attribute converter that converts string attributes to uppercase before
 * storing them in the database.
 * <p>
 * This converter trims the input string and converts it to uppercase during the
 * conversion to the database column. It does not auto-apply to entity attributes,
 * so it must be explicitly specified on the desired fields.
 * </p>
 */
@Converter(autoApply = false)
public class UppercaseConverter implements AttributeConverter<String, String> {

    /**
     * Converts the given attribute to an uppercase string for storing in the database.
     * <p>
     * If the input attribute is not null, it trims any leading or trailing whitespace
     * and converts the string to uppercase. Otherwise, it returns the attribute as is.
     * </p>
     *
     * @param attribute the entity attribute value to be converted
     * @return the trimmed and uppercased string for the database column, or null if the attribute is null
     */
    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute != null) {
            return attribute.trim().toUpperCase();
        }
        return attribute;
    }

    /**
     * Converts the data from the database column to the entity attribute.
     * <p>
     * This implementation returns the database value without any modification.
     * </p>
     *
     * @param dbData the data from the database column
     * @return the same string as the entity attribute
     */
    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }
}
