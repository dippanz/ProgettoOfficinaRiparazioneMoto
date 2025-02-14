
package it.officina.OfficinaRiparazioneMoto.controller.api;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.officina.OfficinaRiparazioneMoto.dto.ClienteDto;
import it.officina.OfficinaRiparazioneMoto.service.ClienteService;

/**
 * ClienteRestController is a REST controller for managing client resources.
 *
 * <p>
 * Base URL: "/api/clienti"
 * </p>
 *
 * @version 1.0
 */
@RestController
@RequestMapping("/api/clienti")
public class ClienteRestController {

    @Autowired
    private ClienteService service;

    /**
     * Retrieves all clients.
     *
     * <p>
     * This method handles GET requests to the "/api/clienti" endpoint and
     * returns a list of all clients available in the system.
     * </p>
     *
     * @return a ResponseEntity containing a List of ClienteDto objects representing
     *         all clients.
     */
    @GetMapping
    public ResponseEntity<List<ClienteDto>> getAllClienti() {
        return ResponseEntity.ok(service.getAllClienti());
    }

    /**
     * Retrieves a client by its unique identifier.
     *
     * <p>
     * This method handles GET requests to the "/api/clienti/{id}" endpoint. It
     * looks
     * up and returns the client corresponding to the provided UUID.
     * </p>
     *
     * @param id the unique UUID representing the client.
     * @return a ResponseEntity containing a ClienteDto object corresponding to the
     *         specified client.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getClienteDtoById(id));
    }
}