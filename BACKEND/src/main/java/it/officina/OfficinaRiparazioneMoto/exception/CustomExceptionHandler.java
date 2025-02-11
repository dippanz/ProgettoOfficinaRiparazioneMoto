package it.officina.OfficinaRiparazioneMoto.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, String>> handleCustomBadRequest(BadRequestException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("errorCode", ex.getErrorCode());
        errorDetails.put("message", ex.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // qua è possibile gestire gli errori generici cosi per non farli uscire e fare
    // scoppiare il sito
    // cosi facendo mando un errore personalizzato e non faccio vedere il vero
    // errore

    @ExceptionHandler(NoResourceFoundException.class)
    public String handleNotFoundException(HttpServletRequest request, RedirectAttributes redirectAttributes,
            NoResourceFoundException ex) {

        redirectAttributes.addFlashAttribute("errorCode", "404");
        return "redirect:/error";
    }

    @ExceptionHandler(Exception.class)
    public Object handleGlobalException(HttpServletRequest request, RedirectAttributes redirectAttributes,
            Exception ex) {

        String httpMethod = request.getMethod();
        // se NON è una get allora torna un json classico, altrimenti fa un redirect verso la stessa pagina
        if (!"GET".equalsIgnoreCase(httpMethod)) {
            Map<String, String> errorDetails = new HashMap<>();
            errorDetails.put("errorCode", "UNKNOWN_ERROR");
            errorDetails.put("message", "Si è verificato un errore imprevisto.");
            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        } else {
            String referer = request.getHeader("Referer");
            redirectAttributes.addFlashAttribute("errorMessage", "Si è verificato un errore imprevisto.");
            return referer != null ? "redirect:" + referer : "redirect:/";
        }
    }
}
