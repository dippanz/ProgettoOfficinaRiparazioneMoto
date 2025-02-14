package it.officina.OfficinaRiparazioneMoto.controller.api;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.officina.OfficinaRiparazioneMoto.dto.MotoDto;
import it.officina.OfficinaRiparazioneMoto.service.MotoService;

/**
 * REST controller providing endpoints to manage motorcycle resources.
 * <p>
 * Base URL: /api/moto
 * </p>
 * 
 * @version 1.0
 */
@RestController
@RequestMapping("/api/moto")
public class MotoRestController {

    @Autowired
    private MotoService motoService;

    /**
     * Retrieves a list of all motorcycles.
     *
     * @return a ResponseEntity containing a list of MotoDto objects
     */
    @GetMapping
    public ResponseEntity<List<MotoDto>> getAllMoto() {
        return ResponseEntity.ok(motoService.getAllMoto());
    }

    /**
     * Retrieves the details of a specific motorcycle identified by its UUID.
     *
     * @param id the UUID of the motorcycle
     * @return a ResponseEntity containing the MotoDto of the specified motorcycle
     */
    @GetMapping("/{id}")
    public ResponseEntity<MotoDto> getMotoById(@PathVariable UUID id) {
        return ResponseEntity.ok(motoService.getMotoDtoById(id));
    }
}