// Inizializzazione globale dell'applicazione
document.addEventListener("DOMContentLoaded", function () {
    // Estrai il nome della pagina corrente dal dataset del body
    const page = document.body.dataset.page;

    // Mappa dei moduli con funzioni da eseguire dopo l'import
    const modules = {
        registration: () => import("./forms/admin/registrazione_utente.js").then(module => module.handleRegistrationForm()),
        riparazione: () => import("./forms/publics/cerca_riparazione.js").then(module => module.handleCercaRiparazioneForm()),
        moduloAccettazione: () => import("./forms/accettazione/moduloAccettazione.js").then(module => module.handleModuloAccettazioneForm()),
        indexAccettazione: () => import("./forms/accettazione/index.js").then(module => module.handleIndexPage()),
        prendiInCarico: () => import("./forms/meccanico/prendiInCarico.js").then(module => module.handlePrendiInCaricoPage()),
        meccanicoDettaglio: () => import("./forms/meccanico/dettaglio.js").then(module => module.handleDettaglioPage()),
        meccanicoAggiungiLavorazione: () => import("./forms/meccanico/aggiungiLavorazione.js").then(module => module.handleAggiungiLavorazioneForm()),
    };

    if (modules[page]) {
        // Carica il modulo corrispondente alla pagina corrente
        modules[page]()
            .then(() => console.log(`Modulo "${page}" caricato ed eseguito`))
            .catch(err => console.error(`Errore nel caricamento del modulo "${page}"`, err));
    }
});
