import {
  handleFetchError,
  handleFetchResponse,
} from "../../utils/fetchManager.js";
import { updateInnerHTML } from "../../utils/domManager.js";

export function handleAggiungiLavorazioneForm() {
  $("#formContainer").on(
    "submit",
    "#aggiungiLavorazioneForm",
    function (event) {
      event.preventDefault(); // Previeni l'invio standard del form

      // Preparazione dei dati del form
      const form = event.target;
      const formData = new FormData(form);

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
          console.log(html);
          let tempDiv = document.createElement("div");
          tempDiv.innerHTML = html;

          if (tempDiv.querySelector("#aggiungiLavorazioneForm")) {
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
  );

  document.querySelectorAll(".btn-elimina").forEach((button) => {
    button.addEventListener("click", function () {
      const idLavorazione = $(this).data("id")

      console.log(idLavorazione)

      if (!confirm("Sei sicuro di voler eliminare questa riparazione?")) {
        return;
      }

      fetch(`/api/lavorazione/elimina/${idLavorazione}`, {
        method: "DELETE",
      })
        .then(handleFetchResponse)
        .then((isDeleted) => {
          if(isDeleted){
            location.reload();
            //mostra modale di successo
          }
        })
        .catch(handleFetchError);
    });
  });
}
