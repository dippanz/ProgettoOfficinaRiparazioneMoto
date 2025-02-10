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

@RestController
@RequestMapping("/api/lavorazione")
public class RiparazioneLavorazioneRestController {

    @Autowired
    private RiparazioneLavorazioneService service;

    @DeleteMapping("/elimina/{id}")
    public ResponseEntity<Boolean> elimina(@PathVariable UUID id) throws BadRequestException{
        service.eliminaLavorazione(id);
        // se non arriva un eccezzione allora è stato eliminato correttamente
        return ResponseEntity.ok(true);
    }
}
