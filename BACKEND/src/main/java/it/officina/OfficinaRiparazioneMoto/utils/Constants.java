package it.officina.OfficinaRiparazioneMoto.utils;

/**
 * Contains application-wide constants including error management and repair
 * state enumerations.
 */
public class Constants {

    /**
     * Enum for managing error codes and messages.
     * <p>
     * Each constant represents a specific error scenario with a unique code and
     * descriptive message.
     * </p>
     */
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
        UTENTE_NON_TROVATO("00021", "Utente con l'id specificato non trovato"),
        IDENTIFY_NULLO("00020", "Inserire email o username");

        private final String code;
        private final String message;

        /**
         * Constructs an error manager constant with the specified code and message.
         *
         * @param code    the unique error code
         * @param message the descriptive error message
         */
        ErrorManager(String code, String message) {
            this.code = code;
            this.message = message;
        }

        /**
         * Retrieves the error code.
         *
         * @return the error code as a {@code String}
         */
        public String getCode() {
            return code;
        }

        /**
         * Retrieves the error message.
         *
         * @return the error message as a {@code String}
         */
        public String getMessage() {
            return message;
        }

        /**
         * Returns the error message for the given error manager constant.
         *
         * @param error the {@code ErrorManager} constant
         * @return the associated error message, or "Errore sconosciuto" if null
         */
        public static String getErrorMessage(ErrorManager error) {
            return error != null ? error.getMessage() : "Errore sconosciuto";
        }
    }

    /**
     * Enum representing the states of a repair.
     * <p>
     * Each constant corresponds to a repair state with an associated integer value.
     * Utility methods are provided to get the next state, check if a state exists,
     * and convert a numeric value to its corresponding state.
     * </p>
     */
    public enum EnumStatoRiparazione {
        REGISTRATO(1),
        RIFIUTATO(2),
        ACCETTATO(3),
        IN_LAVORAZIONE(4),
        COMPLETATA(5);

        private final int valore;

        /**
         * Constructs a repair state with the specified numeric value.
         *
         * @param valore the integer value associated with the state
         */
        EnumStatoRiparazione(int valore) {
            this.valore = valore;
        }

        /**
         * Retrieves the numeric value of the repair state.
         *
         * @return the state value as an {@code int}
         */
        public int getValue() {
            return valore;
        }

        /**
         * Obtains the next repair state in the workflow.
         * <p>
         * The state transitions are defined as follows:
         * REGISTRATO → ACCETTATO, ACCETTATO → IN_LAVORAZIONE, IN_LAVORAZIONE →
         * COMPLETATA.
         * For any other state, {@code null} is returned.
         * </p>
         *
         * @param stato the current state value
         * @return the next {@code EnumStatoRiparazione} or {@code null} if not
         *         applicable
         */
        public static EnumStatoRiparazione getNextStato(int stato) {
            EnumStatoRiparazione statoAttuale = fromValue(stato);
            return switch (statoAttuale) {
                case REGISTRATO -> ACCETTATO;
                case ACCETTATO -> IN_LAVORAZIONE;
                case IN_LAVORAZIONE -> COMPLETATA;
                default -> null;
            };
        }

        /**
         * Checks whether the provided state value corresponds to a valid repair state.
         *
         * @param stato the state value to check
         * @return {@code true} if the state exists, {@code false} otherwise
         */
        public static boolean haveThisState(int stato) {
            for (EnumStatoRiparazione s : EnumStatoRiparazione.values()) {
                if (s.getValue() == stato) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Converts a numeric value to its corresponding {@code EnumStatoRiparazione}.
         *
         * @param valore the state value to convert
         * @return the corresponding {@code EnumStatoRiparazione}
         * @throws IllegalArgumentException if the value does not correspond to any
         *                                  valid state
         */
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
