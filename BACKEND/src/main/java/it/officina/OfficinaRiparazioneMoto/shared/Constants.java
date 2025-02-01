package it.officina.OfficinaRiparazioneMoto.shared;

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

        private static final Map<String, String> errorCodes = Map.of(
            USERNAME_ALREADY_EXISTS, "Username già presente nel sistema",
            EMAIL_ALREADY_EXISTS, "Email già presente nel sistema",
            RIPARAZIONE_NON_TROVATA_COD_SERVIZIO, "Riparazione non trovata con il codice servizio specificato",
            RIPARAZIONE_NON_TROVATA_TARGA, "Riparazioni non trovate con la targa specificata"
        );

        public static String getErrorMessage(String errorCode){
            return errorCodes.get(errorCode);
        }
    }

    






}
