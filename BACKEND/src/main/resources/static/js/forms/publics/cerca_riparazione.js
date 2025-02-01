import {
  handleFetchError,
  handleFetchResponse,
} from "../../utils/fetchManager.js";
import { updateInnerHTML } from "../../utils/domManager.js";

export function handleCercaRiparazioneForm() {
  const formContainer = document.getElementById("formContainer");

  if (formContainer) {
    formContainer.addEventListener("submit", function (event) {
      if (event.target && event.target.id === "cercaRiparazioneForm") {
        event.preventDefault(); // Previeni l'invio standard del form

        // Preparazione dei dati del form
        const form = event.target;
        const formData = new FormData(form);

        // costruisco l'URL con i parametri del formData
        const params = new URLSearchParams(formData).toString();
        const requestUrl = `${form.action}?${params}`;

        // Disabilita il pulsante di invio per evitare doppie richieste
        const submitButton = form.querySelector("button[type='submit']");
        submitButton.disabled = true;

        // Chiamata AJAX
        fetch(requestUrl, {
          method: "GET",
        })
          .then(handleFetchResponse)
          .then((html) => {
            let tempDiv = document.createElement("div");
            tempDiv.innerHTML = html;

            if (tempDiv.querySelector("#cercaRiparazioneForm")) {
              updateInnerHTML("formContainer", html);
            } else {
              updateInnerHTML("tableContainer", html);
            }
          })
          .catch(handleFetchError)
          .finally(() => {
            submitButton.disabled = false;
          });
      }
    });
  }
}
