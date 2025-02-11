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

@RestController
@RequestMapping("/api/riparazione")
public class RiparazioneRestController {

    @Autowired
    private RiparazioneService service;

    @PatchMapping("/avanza_stato_riparazione")
    public ResponseEntity<Boolean> aggiornaStatoRiparazione(@RequestParam UUID id) throws BadRequestException{
        service.aggiornaStatoRiparazione(id, null);
        return ResponseEntity.ok(true);
    }

    @PatchMapping("/rifiuta_riparazione")
    public ResponseEntity<Boolean> rifiutaRiparazione(@RequestParam UUID id) throws BadRequestException{
        service.rifiutaRiparazione(id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/elimina")
    public ResponseEntity<Boolean> elimina(@RequestParam UUID id) throws BadRequestException{
        service.eliminaRiparazione(id);
        // se non arriva un eccezzione allora è stato eliminato correttamente
        return ResponseEntity.ok(true);
    }
}
