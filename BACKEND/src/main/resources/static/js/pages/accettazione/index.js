import { fetchHandled } from "../../utils/fetchManager.js";
import { updateInnerHTML } from "../../utils/domManager.js";

export function handleIndexPage() {
  $("#formContainer").on("submit", "#moduloAccettazioneForm", function (event) {
    event.preventDefault(); // Previeni l'invio standard del form

    // Preparazione dei dati del form
    const form = event.target;
    const formData = new FormData(form);

    if ($("#motoEsistenteCheckbox").is(":checked")) {
      formData.delete("targa");
      formData.delete("modello");
      formData.delete("nome");
      formData.delete("cognome");
      formData.delete("email");
      formData.delete("telefono");
      formData.delete("idCliente");
    } else if ($("#clienteEsistenteCheckbox").is(":checked")) {
      formData.delete("nome");
      formData.delete("cognome");
      formData.delete("email");
      formData.delete("telefono");
      formData.delete("idMoto");
    } else {
      formData.delete("idCliente");
      formData.delete("idMoto");
    }

    // Disabilita il pulsante di invio per evitare doppie richieste
    const submitButton = form.querySelector("button[type='submit']");
    submitButton.disabled = true;

    // Chiamata AJAX
    fetchHandled(form.action, {
      method: "POST",
      body: formData,
    })
      .then((html) => {
        updateInnerHTML("formContainer", html);
      })
      .finally(() => {
        submitButton.disabled = false;
      });
  });

  // bottoni accetta
  document.querySelectorAll(".btn-accetta-riparazione").forEach((button) => {
    button.addEventListener("click", function () {
      let idRiparazione = $(this).attr("data-id");

      fetchHandled(
        `/api/riparazione/avanza_stato_riparazione?id=${idRiparazione}`,
        {
          method: "PATCH",
        }
      ).then((isUpdated) => {
        if (isUpdated) {
          location.reload();
        }
      });
    });
  });

  // bottoni rifiuta
  document.querySelectorAll(".btn-rifiuta-riparazione").forEach((button) => {
    button.addEventListener("click", function () {
      let idRiparazione = $(this).attr("data-id");

      if (!confirm("Sicuro di voler rifiutare la riparazione selezionata?")) {
        return;
      }

      fetchHandled(`/api/riparazione/rifiuta_riparazione?id=${idRiparazione}`, {
        method: "PATCH",
      }).then((isUpdated) => {
        if (isUpdated) {
          location.reload();
        }
      });
    });
  });

  // bottoni elimina
  document.querySelectorAll(".btn-elimina-riparazione").forEach((button) => {
    button.addEventListener("click", function () {
      let idRiparazione = $(this).attr("data-id");

      if (!confirm("Sicuro di voler eliminare la riparazione selezionata?")) {
        return;
      }

      fetchHandled(`/api/riparazione/elimina?id=${idRiparazione}`, {
        method: "DELETE",
      }).then((isDeleted) => {
        if (isDeleted) {
          location.reload();
        }
      });
    });
  });
}
