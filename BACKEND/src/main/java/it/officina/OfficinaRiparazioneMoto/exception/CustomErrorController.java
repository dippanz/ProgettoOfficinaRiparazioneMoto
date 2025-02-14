package it.officina.OfficinaRiparazioneMoto.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Custom error controller to handle application errors.
 * <p>
 * This controller intercepts errors that occur during application runtime and
 * directs users to a custom error page with an appropriate error message based
 * on the error code.
 * </p>
 */
@Controller
public class CustomErrorController implements ErrorController {

    /**
     * Handles error requests.
     * <p>
     * This method retrieves the error code from the model attributes, determines
     * an appropriate error message based on the error code, and then adds both the
     * error code and error message to the model. Finally, it returns the view name
     * for the error page. Mainly used to handle not found errors.
     * </p>
     *
     * @param model the model used to pass data to the view
     * @return the view name for the custom error page
     */
    @GetMapping("/error")
    public String handleError(Model model) {
        String errorMessage = "Si è verificato un errore imprevisto.";
        Object errorAttr = model.getAttribute("errorCode");
        String errorCode = errorAttr != null ? errorAttr.toString() : "UNKNOWN_ERROR";

        if (errorCode.equals("404")) {
            errorMessage = "La pagina che stai cercando non esiste.";
        } else if (errorCode.equals("500")) {
            errorMessage = "Errore interno del server.";
        }

        model.addAttribute("errorCode", errorCode);
        model.addAttribute("errorMessage", errorMessage);
        return "errorPage";
    }
}
