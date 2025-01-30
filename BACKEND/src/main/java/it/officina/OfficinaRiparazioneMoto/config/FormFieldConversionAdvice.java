package it.officina.OfficinaRiparazioneMoto.config;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class FormFieldConversionAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    // Classe interna per il convertitore personalizzata
    // static class StringTrimmerEditor extends java.beans.PropertyEditorSupport {
    //     @Override
    //     public void setAsText(String text) {
    //         setValue((text == null || text.trim().isEmpty()) ? null : text.trim());
    //     }
    // }
}
