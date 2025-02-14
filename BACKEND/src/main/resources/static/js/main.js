import { showErrorModal, showSuccessModal } from "./utils/domManager.js";

// Inizializzazione globale dell'applicazione
document.addEventListener("DOMContentLoaded", function () {
  // Estrai il nome della pagina corrente dal dataset del body
  const page = document.body.dataset.page;

  // Mappa dei moduli con funzioni da eseguire dopo l'import
  const modules = {
    registration: () =>
      import("./pages/admin/registrazione_utente.js").then((module) =>
        module.handleRegistrationForm()
      ),
    riparazione: () =>
      import("./pages/publics/cerca_riparazione.js").then((module) =>
        module.handleCercaRiparazioneForm()
      ),
    moduloAccettazione: () =>
      import("./pages/accettazione/moduloAccettazione.js").then((module) =>
        module.handleModuloAccettazioneForm()
      ),
    indexAccettazione: () =>
      import("./pages/accettazione/index.js").then((module) =>
        module.handleIndexPage()
      ),
    prendiInCarico: () =>
      import("./pages/meccanico/prendiInCarico.js").then((module) =>
        module.handlePrendiInCaricoPage()
      ),
    meccanicoDettaglio: () =>
      import("./pages/meccanico/dettaglio.js").then((module) =>
        module.handleDettaglioPage()
      ),
    meccanicoAggiungiLavorazione: () =>
      import("./pages/meccanico/aggiungiLavorazione.js").then((module) =>
        module.handleAggiungiLavorazioneForm()
      ),
    meccanicoIndex: () =>
      import("./pages/meccanico/index.js").then((module) =>
        module.handleIndexPage()
      ),
    accettazioneStorico: () =>
      import("./pages/accettazione/storico.js").then((module) =>
        module.handleStoricoPage()
      ),
    adminModificaUtente: () =>
      import("./pages/admin/modificaUtente.js").then((module) =>
        module.handleModificaUtenteForm()
      ),
    adminModificaCliente: () =>
      import("./pages/admin/modificaCliente.js").then((module) =>
        module.handleModificaClienteForm()
      ),
    adminModificaMoto: () =>
      import("./pages/admin/modificaMoto.js").then((module) =>
        module.handleModificaMotoForm()
      ),
  };

  if (modules[page]) {
    // Carica il modulo corrispondente alla pagina corrente
    modules[page]()
      .then(() => console.log(`Modulo "${page}" caricato ed eseguito`))
      .catch((err) =>
        console.error(`Errore nel caricamento del modulo "${page}"`, err)
      );
  }

  // gestione dell'header
  document
    .getElementById("navbarHeader")
    .querySelectorAll("a")
    .forEach((link) => {
      if (
        link.getAttribute("href") == window.location.pathname ||
        (window.location.pathname == "/public/home" &&
          link.getAttribute("href") == "/")
      ) {
        link.setAttribute("href", "javascript:void(0)");
      }
    });

  // gestione di modali di successo o errore
  const successMessage = sessionStorage.getItem("successMessage");
  if (successMessage) {
    setTimeout(() => {
      showSuccessModal(successMessage);
      sessionStorage.removeItem("successMessage");
    }, 100);
  }

  const errorMessage = sessionStorage.getItem("errorMessage");
  const flashMessageError = document.getElementById("flashMessage");
  if (errorMessage || flashMessageError) {
    const error = flashMessageError
      ? flashMessageError.dataset.errorMessage
      : errorMessage;
    setTimeout(() => {
      showErrorModal(error);
      sessionStorage.removeItem("errorMessage");
    }, 100);
  }
});
