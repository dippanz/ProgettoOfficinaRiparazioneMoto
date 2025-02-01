// Inizializzazione globale dell'applicazione
document.addEventListener("DOMContentLoaded", function () {
    // Estrai il nome della pagina corrente dal dataset del body
    const page = document.body.dataset.page;

    // Mappa dei moduli con funzioni da eseguire dopo l'import
    const modules = {
        registration: () => import("./forms/admin/registrazione_utente.js").then(module => module.handleRegistrationForm()),
        riparazione: () => import("./forms/publics/cerca_riparazione.js").then(module => module.handleCercaRiparazioneForm()),
    };

    if (modules[page]) {
        // Carica il modulo corrispondente alla pagina corrente
        modules[page]()
            .then(() => console.log(`Modulo "${page}" caricato ed eseguito`))
            .catch(err => console.error(`Errore nel caricamento del modulo "${page}"`, err));
    }
});
