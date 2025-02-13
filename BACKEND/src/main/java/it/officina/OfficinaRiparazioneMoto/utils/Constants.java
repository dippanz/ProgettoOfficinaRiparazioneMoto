package it.officina.OfficinaRiparazioneMoto.utils;

public class Constants {
    public enum ErrorManager {

        // Cliente section
        CLIENTE_NON_TROVATO("00011", "Cliente con l'id specificato non trovato"),
        CLIENTE_EMAIL_ESISTENTE("00005", "Cliente con questa email già presente nel sistema"),
        CLIENTE_EMAIL_NON_PRESENTE("00012", "L'email per il cliente è obbligatoria"),

        // Moto section
        MOTO_NON_TROVATA("00009", "Moto non trovata"),
        MOTO_TARGA_NON_PRESENTE("00013", "La targa per i veicoli è obbligatoria"),
        MOTO_TARGA_ESISTENTE("00006", "Moto con questa targa già presente nel sistema"),

        // Riparazione section
        RIPARAZIONE_NON_TROVATA("00014", "Riparazione con l'id specificato non trovata"),
        STATO_RIPARAZIONE_NON_TROVATO("00010", "Stato riparazione specificato non trovato"),
        RIPARAZIONE_GIA_COMPLETA("00016", "Non è possibile aggiornare una riparazione già completa"),
        RIPARAZIONE_NON_ELIMINABILE("00018", "La riparazione selezionata non è eliminabile"),
        RIPARAZIONE_NON_RIFIUTABILE("00019", "Non è possibile rifiutare questa riparazione"),

        // Lavorazione section
        LAVORAZIONE_NON_TROVATA("00017", "Lavorazione con l'id specificato non trovata"),

        // Admin section
        USERNAME_ALREADY_EXISTS("00001", "Username già presente nel sistema"),
        EMAIL_ALREADY_EXISTS("00002", "Email già presente nel sistema"),

        // Public section
        RIPARAZIONE_NON_TROVATA_COD_SERVIZIO("00003", "Riparazione non trovata con il codice servizio specificato"),
        RIPARAZIONE_NON_TROVATA_TARGA("00004", "Riparazioni non trovate con la targa specificata"),

        // Accettazione section
        CLIENTE_NON_ASSEGNATO_MOTO("00007", "Non è stato possibile assegnare la moto al cliente scelto"),

        // Meccanico section
        NESSUN_ID_PRESA_IN_CARICO("00015", "Specificare almeno un ID per la presa in carico"),

        // Auth section
        UTENTE_NON_LOGGATO("00008", "Utente non loggato"),
        IDENTIFY_NULLO("00020", "Inserire email o username");

        private final String code;
        private final String message;

        ErrorManager(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public static String getErrorMessage(ErrorManager error) {
            return error != null ? error.getMessage() : "Errore sconosciuto";
        }
    }

    public enum EnumStatoRiparazione {
        REGISTRATO(1),
        RIFIUTATO(2),
        ACCETTATO(3),
        IN_LAVORAZIONE(4),
        COMPLETATA(5);

        private final int valore;

        EnumStatoRiparazione(int valore) {
            this.valore = valore;
        }

        public int getValue() {
            return valore;
        }

        // Metodo per ottenere lo stato successivo
        public static EnumStatoRiparazione getNextStato(int stato) {
            EnumStatoRiparazione statoAttuale = fromValue(stato);
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
        public static EnumStatoRiparazione fromValue(int valore) {
            for (EnumStatoRiparazione stato : EnumStatoRiparazione.values()) {
                if (stato.getValue() == valore) {
                    return stato;
                }
            }
            throw new IllegalArgumentException("Stato non valido: " + valore);
        }
    }

}
