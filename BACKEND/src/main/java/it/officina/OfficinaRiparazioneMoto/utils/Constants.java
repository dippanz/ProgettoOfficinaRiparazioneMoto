package it.officina.OfficinaRiparazioneMoto.utils;

import java.util.Map;

public class Constants {

    public static class ErrorManager {

        // Cliente secion
        public static final String CLIENTE_NON_TROVATO = "00011";
        public static final String CLIENTE_EMAIL_ESISTENTE = "00005";
        public static final String CLIENTE_EMAIL_NON_PRESENTE = "00012";

        // Moto section
        public static final String MOTO_NON_TROVATA = "00009";
        public static final String MOTO_TARGA_NON_PRESENTE = "00013";

        // Riparazione section
        public static final String RIPARAZIONE_NON_TROVATA = "00014";
        public static final String STATO_RIPARAZIONE_NON_TROVATO = "00010";
        public static final String RIPARAZIONE_GIA_COMPLETA = "00016";

        // Lavorazione section
        public static final String LAVORAZIONE_NON_TROVATA = "00017";

        // Admin section
        public static final String USERNAME_ALREADY_EXISTS = "00001";
        public static final String EMAIL_ALREADY_EXISTS = "00002";

        // Public section
        public static final String RIPARAZIONE_NON_TROVATA_COD_SERVIZIO = "00003";
        public static final String RIPARAZIONE_NON_TROVATA_TARGA = "00004";

        // Accettazione section
        public static final String MOTO_TARGA_ESISTENTE = "00006";
        public static final String CLIENTE_NON_ASSEGNATO_MOTO = "00007";

        // Meccanico section
        public static final String NESSUN_ID_PRESA_IN_CARICO = "00015";
        
        // Auth section
        public static final String UTENTE_NON_LOGGATO = "00008";

        private static final Map<String, String> errorCodes = Map.ofEntries(
                Map.entry(USERNAME_ALREADY_EXISTS, "Username già presente nel sistema"),
                Map.entry(EMAIL_ALREADY_EXISTS, "Email già presente nel sistema"),
                Map.entry(RIPARAZIONE_NON_TROVATA_COD_SERVIZIO, "Riparazione non trovata con il codice servizio specificato"),
                Map.entry(CLIENTE_EMAIL_ESISTENTE, "Cliente con questa email già presente nel sistema"),
                Map.entry(MOTO_TARGA_ESISTENTE, "Moto con questa targa già presente nel sistema"),
                Map.entry(CLIENTE_NON_ASSEGNATO_MOTO, "Non è stato possibile assegnare la moto al cliente scelto"),
                Map.entry(UTENTE_NON_LOGGATO, "Utente non loggato"),
                Map.entry(MOTO_NON_TROVATA, "Moto non trovata"),
                Map.entry(STATO_RIPARAZIONE_NON_TROVATO, "Stato riparazione specificato non trovato"),
                Map.entry(CLIENTE_NON_TROVATO, "Cliente con l'id specificato non trovato"),
                Map.entry(CLIENTE_EMAIL_NON_PRESENTE, "L'email per il cliente è obbligatoria"),
                Map.entry(MOTO_TARGA_NON_PRESENTE, "La targa per i veicoli è obbligatoria"),
                Map.entry(RIPARAZIONE_NON_TROVATA, "Riparazione con l'id specificato non trovata"),
                Map.entry(NESSUN_ID_PRESA_IN_CARICO, "Specificare alemno un id per la presa in carico"),
                Map.entry(RIPARAZIONE_GIA_COMPLETA, "Non è possibile aggiornare una riparazione gia completa"),
                Map.entry(LAVORAZIONE_NON_TROVATA, "Lavorazione con l'id specificato non trovato"),
                Map.entry(RIPARAZIONE_NON_TROVATA_TARGA, "Riparazioni non trovate con la targa specificata")
            );

        public static String getErrorMessage(String errorCode) {
            return errorCodes.get(errorCode);
        }
    }

    // public static class StatoRiparazioni {
    //     public static final int REGISTRATO = 1;
    //     public static final int ACCETTATO = 2;
    //     public static final int IN_LAVORAZIONE = 3;
    //     public static final int COMPLETATA = 4;

    //     public static int getNextStato(int statoAttuale) {
    //         return switch (statoAttuale) {
    //             case StatoRiparazioni.REGISTRATO -> StatoRiparazioni.ACCETTATO;
    //             case StatoRiparazioni.ACCETTATO -> StatoRiparazioni.IN_LAVORAZIONE;
    //             case StatoRiparazioni.IN_LAVORAZIONE -> StatoRiparazioni.COMPLETATA;
    //             default -> -1;
    //         };
    //     }

    //     public static boolean haveThisState(int stato){
            
    //     }
    // }

    public enum EnumStatoRiparazione {
        REGISTRATO(1),
        ACCETTATO(2),
        IN_LAVORAZIONE(3),
        COMPLETATA(4);
    
        private final int valore;
    
        EnumStatoRiparazione(int valore) {
            this.valore = valore;
        }
    
        public int getValue() {
            return valore;
        }
    
        // Metodo per ottenere lo stato successivo
        public static EnumStatoRiparazione getNextStato(int stato) {
            EnumStatoRiparazione statoAttuale = fromValore(stato);
            return switch (statoAttuale) {
                case REGISTRATO -> ACCETTATO;
                case ACCETTATO -> IN_LAVORAZIONE;
                case IN_LAVORAZIONE -> COMPLETATA;
                default -> null;
            };
        }
    
        // Controlla se un valore esiste tra gli stati validi
        public static boolean haveThisState(int stato) {
            for (EnumStatoRiparazione s : EnumStatoRiparazione.values()) {
                if (s.getValue() == stato) {
                    return true;
                }
            }
            return false;
        }
    
        // Converte un valore numerico nello stato corrispondente
        public static EnumStatoRiparazione fromValore(int valore) {
            for (EnumStatoRiparazione stato : EnumStatoRiparazione.values()) {
                if (stato.getValue() == valore) {
                    return stato;
                }
            }
            throw new IllegalArgumentException("Stato non valido: " + valore);
        }
    }
    
}
