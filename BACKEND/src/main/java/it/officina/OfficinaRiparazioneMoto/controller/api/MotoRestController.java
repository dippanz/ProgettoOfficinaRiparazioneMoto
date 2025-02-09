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

@RestController
@RequestMapping("/api/moto")
public class MotoRestController {
    
    @Autowired
    private MotoService motoService;

    @GetMapping
    public ResponseEntity<List<MotoDto>> getAllMoto() {
        return ResponseEntity.ok(motoService.getAllMoto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoDto> getMotoById(@PathVariable UUID id) {
        return ResponseEntity.ok(motoService.getMotoDtoById(id));
    }
}
