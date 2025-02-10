import {
  handleFetchError,
  handleFetchResponse,
} from "../../utils/fetchManager.js";
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
    fetch(form.action, {
      method: "POST",
      body: formData,
    })
      .then(handleFetchResponse)
      .then((html) => {
        updateInnerHTML("formContainer", html);
      })
      .catch(handleFetchError)
      .finally(() => {
        submitButton.disabled = false;
      });
  });

  $("#accettaRiparazione").on("click", function (event) {
    event.preventDefault(); // Previeni l'invio standard del form

    let idRiparazione = $(this).attr("data-id");

    fetch(`accettazione/process_update_status_riparazione?id=${idRiparazione}`, {
      method: "PATCH",
    })
      .then(handleFetchResponse)
      .catch(handleFetchError);
  });
}
