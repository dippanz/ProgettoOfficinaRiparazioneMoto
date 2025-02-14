package it.officina.OfficinaRiparazioneMoto.config;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * Global controller advice that configures form field data binding.
 * <p>
 * This class registers a custom editor for all String fields in web requests
 * to automatically trim whitespace and interpret empty strings as null values.
 * It applies to all controllers by using the
 * {@link org.springframework.web.bind.annotation.ControllerAdvice} annotation.
 * </p>
 */
@ControllerAdvice
public class FormFieldConversionAdvice {

    /**
     * Initializes the data binder for web request parameters.
     * <p>
     * Registers a
     * {@link org.springframework.beans.propertyeditors.StringTrimmerEditor}
     * for all String types, which trims leading and trailing whitespace and
     * converts empty strings to null.
     * This ensures that form inputs are normalized before binding to model
     * attributes.
     * </p>
     *
     * @param binder the {@link org.springframework.web.bind.WebDataBinder} used for
     *               data binding in web requests
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
