package it.officina.OfficinaRiparazioneMoto.utils;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static class ErrorManager {

        // Admin section
        public static final String USERNAME_ALREADY_EXISTS = "00001";
        public static final String EMAIL_ALREADY_EXISTS = "00002";

        // Public section
        public static final String RIPARAZIONE_NON_TROVATA_COD_SERVIZIO = "00003";
        public static final String RIPARAZIONE_NON_TROVATA_TARGA = "00004";

        // Accettazione section
        public static final String CLIENTE_EMAIL_ESISTENTE = "00005";
        public static final String MOTO_TARGA_ESISTENTE = "00006";
        public static final String CLIENTE_NON_ASSEGNATO_MOTO = "00007";
        public static final String MOTO_NON_TROVATA = "00009";
        public static final String STATO_RIPARAZIONE_NON_TROVATO = "00010";

        // Auth section
        public static final String UTENTE_NON_LOGGATO = "00008";

        private static final Map<String, String> errorCodes = Map.of(
            USERNAME_ALREADY_EXISTS, "Username già presente nel sistema",
            EMAIL_ALREADY_EXISTS, "Email già presente nel sistema",
            RIPARAZIONE_NON_TROVATA_COD_SERVIZIO, "Riparazione non trovata con il codice servizio specificato",
            CLIENTE_EMAIL_ESISTENTE, "Cliente con questa email già presente nel sistema",
            MOTO_TARGA_ESISTENTE, "Moto con questa targa già presente nel sistema",
            CLIENTE_NON_ASSEGNATO_MOTO, "Non è stato possibile assegnare la moto al cliente scelto",
            UTENTE_NON_LOGGATO, "Utente non loggato",
            MOTO_NON_TROVATA, "Moto non trovata",
            STATO_RIPARAZIONE_NON_TROVATO, "Stato riparazione specificato non trovato",
            RIPARAZIONE_NON_TROVATA_TARGA, "Riparazioni non trovate con la targa specificata"
        );

        public static String getErrorMessage(String errorCode){
            return errorCodes.get(errorCode);
        }
    }

    public static class StatoRiparazioni{
        public static final int ACCETTATO = 1;
        public static final int IN_LAVORAZIONE = 2;
        public static final int COMPLETATA = 3;
    
    }
}
