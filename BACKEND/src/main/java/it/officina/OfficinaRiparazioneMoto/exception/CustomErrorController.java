package it.officina.OfficinaRiparazioneMoto.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

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
