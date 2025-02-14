package it.officina.OfficinaRiparazioneMoto.converter;

import jakarta.persistence.AttributeConverter;

/**
 * A JPA attribute converter that converts string attributes to lowercase before
 * persisting them to the database.
 * <p>
 * This converter trims the input string and converts it to lowercase during the
 * conversion to the database column. When reading from the database, the string is
 * returned without modifications.
 * </p>
 */
public class LowercaseConverter implements AttributeConverter<String, String> {

    /**
     * Converts the given attribute to a lowercase string for storing in the database.
     * <p>
     * If the input attribute is not null, it trims any leading or trailing whitespace
     * and converts the string to lowercase. Otherwise, it returns the attribute as is.
     * </p>
     *
     * @param attribute the entity attribute value to be converted
     * @return the trimmed and lowercased string for the database column, or null if the attribute is null
     */
    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute != null) {
            return attribute.trim().toLowerCase();
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
