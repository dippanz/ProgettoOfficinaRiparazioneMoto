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

/**
 * Global exception handler for the application.
 * <p>
 * This class handles exceptions thrown by controllers and provides custom
 * responses
 * for specific exception types, such as {@link BadRequestException},
 * {@link NoResourceFoundException},
 * and all other generic exceptions.
 * </p>
 */
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * Handles {@link BadRequestException} and returns a JSON response with error
     * details.
     *
     * @param ex the {@link BadRequestException} thrown by the application
     * @return a {@link ResponseEntity} containing a map with the error code and
     *         message,
     *         and an HTTP status of BAD_REQUEST
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, String>> handleCustomBadRequest(BadRequestException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("errorCode", ex.getErrorCode());
        errorDetails.put("message", ex.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles {@link NoResourceFoundException} by redirecting to a custom error
     * page.
     * <p>
     * Adds a flash attribute with an error code ("404") and redirects the user to
     * the error page managed by js.
     * </p>
     *
     * @param request            the current HTTP request
     * @param redirectAttributes attributes for flash scope
     * @param ex                 the {@link NoResourceFoundException} thrown by the
     *                           application
     * @return a redirect string to the error page
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public String handleNotFoundException(HttpServletRequest request, RedirectAttributes redirectAttributes,
            NoResourceFoundException ex) {
        redirectAttributes.addFlashAttribute("errorCode", "404");
        return "redirect:/error";
    }

    /**
     * Handles all other exceptions globally.
     * <p>
     * If the HTTP method is not GET, it returns a JSON response with error details.
     * If the HTTP method is GET, it redirects the user back to the referer page (or
     * to the home page if no referer is available)
     * with a flash attribute containing an error message.
     * </p>
     *
     * @param request            the current HTTP request
     * @param redirectAttributes attributes for flash scope
     * @param ex                 the {@link Exception} thrown by the application
     * @return either a {@link ResponseEntity} with error details for non-GET
     *         requests,
     *         or a redirect string for GET requests
     */
    @ExceptionHandler(Exception.class)
    public Object handleGlobalException(HttpServletRequest request, RedirectAttributes redirectAttributes,
            Exception ex) {

        System.err.println(ex);

        String httpMethod = request.getMethod();
        // se NON è una get allora torna un json classico, altrimenti fa un redirect
        // verso la stessa pagina
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
