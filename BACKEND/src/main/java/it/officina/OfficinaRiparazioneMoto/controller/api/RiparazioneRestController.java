package it.officina.OfficinaRiparazioneMoto.controller.api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.officina.OfficinaRiparazioneMoto.exception.BadRequestException;
import it.officina.OfficinaRiparazioneMoto.service.RiparazioneService;

/**
 * REST controller for managing repair operations.
 * <p>
 * This controller provides endpoints to perform operations related to repairs.
 * </p>
 */
@RestController
@RequestMapping("/api/riparazione")
public class RiparazioneRestController {

    @Autowired
    private RiparazioneService service;

    /**
     * Advances the state of a repair operation.
     *
     * @param id the unique identifier of the repair to update
     * @return a {@link ResponseEntity} containing a boolean value indicating
     *         whether
     *         the state update was successful (true if updated successfully)
     * @throws BadRequestException if the request is invalid or the update fails
     */
    @PatchMapping("/avanza_stato_riparazione")
    public ResponseEntity<Boolean> aggiornaStatoRiparazione(@RequestParam UUID id) throws BadRequestException {
        service.aggiornaStatoRiparazione(id, null);
        return ResponseEntity.ok(true);
    }

    /**
     * Rejects a repair operation.
     *
     * @param id the unique identifier of the repair to reject
     * @return a {@link ResponseEntity} containing a boolean value indicating
     *         whether
     *         the rejection was successful (true if rejected successfully)
     * @throws BadRequestException if the request is invalid or the rejection fails
     */
    @PatchMapping("/rifiuta_riparazione")
    public ResponseEntity<Boolean> rifiutaRiparazione(@RequestParam UUID id) throws BadRequestException {
        service.rifiutaRiparazione(id);
        return ResponseEntity.ok(true);
    }

    /**
     * Deletes a repair operation.
     *
     * @param id the unique identifier of the repair to delete
     * @return a {@link ResponseEntity} containing a boolean value indicating
     *         whether
     *         the deletion was successful (true if deleted successfully)
     * @throws BadRequestException if the request is invalid or the deletion fails
     */
    @DeleteMapping("/elimina")
    public ResponseEntity<Boolean> elimina(@RequestParam UUID id) throws BadRequestException {
        service.eliminaRiparazione(id);
        // se non arriva un eccezzione allora è stato eliminato correttamente
        return ResponseEntity.ok(true);
    }
}
