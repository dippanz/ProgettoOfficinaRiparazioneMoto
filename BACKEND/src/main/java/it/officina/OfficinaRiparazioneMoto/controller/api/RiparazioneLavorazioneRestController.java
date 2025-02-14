package it.officina.OfficinaRiparazioneMoto.controller.api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;
import it.officina.OfficinaRiparazioneMoto.service.RiparazioneLavorazioneService;

/**
 * REST controller for managing repair work operations.
 * <p>
 * This controller provides endpoints to perform operations related to the repair works.
 * </p>
 */
@RestController
@RequestMapping("/api/lavorazione")
public class RiparazioneLavorazioneRestController {

    @Autowired
    private RiparazioneLavorazioneService service;

    /**
     * Deletes a repair work entry identified by its unique ID.
     *
     * @param id the unique identifier of the repair work to be deleted
     * @return a {@link ResponseEntity} containing a boolean value indicating whether
     *         the deletion was successful (true if deleted successfully)
     * @throws BadRequestException if the request is invalid or deletion fails
     */
    @DeleteMapping("/elimina/{id}")
    public ResponseEntity<Boolean> elimina(@PathVariable UUID id) throws BadRequestException{
        service.eliminaLavorazione(id);
        // se non arriva un eccezzione allora è stato eliminato correttamente
        return ResponseEntity.ok(true);
    }
}
