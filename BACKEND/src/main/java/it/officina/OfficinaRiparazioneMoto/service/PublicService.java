package it.officina.OfficinaRiparazioneMoto.service;

import java.util.List;

import it.officina.OfficinaRiparazioneMoto.dto.RiparazioneMotoDto;
import it.officina.OfficinaRiparazioneMoto.dto.publics.RiparazioneDettaglioGeneraleDto;

/**
 * Service interface for public operations.
 * <p>
 * This interface defines methods for retrieving general repair details based on
 * the service code and
 * vehicle license plate. It is intended for use in public-facing
 * functionalities.
 * </p>
 */
public interface PublicService {

    /**
     * Retrieves general repair details based on the provided service code and
     * vehicle license plate.
     * <p>
     * The method fetches a list of {@link RiparazioneMotoDto} objects from the
     * {@link RiparazioneService},
     * then transforms each repair data into a
     * {@link RiparazioneDettaglioGeneraleDto} containing key details.
     * </p>
     *
     * @param codiceServizio the service code associated with the repair (optional)
     * @param targa          the vehicle's license plate
     * @return a list of {@link RiparazioneDettaglioGeneraleDto} objects with
     *         general repair details
     */
    List<RiparazioneDettaglioGeneraleDto> getRiparazioneDettaglioGenerale(String codiceServizio, String targa);
}
