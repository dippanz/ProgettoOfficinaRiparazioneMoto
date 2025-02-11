import { fetchHandled } from "../../utils/fetchManager.js";
import { updateInnerHTML, showSuccessModal } from "../../utils/domManager.js";

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
      fetchHandled(form.action, {
        method: "POST",
        body: formData,
      })
        .then((html) => {
          let tempDiv = document.createElement("div");
          tempDiv.innerHTML = html;

          if (tempDiv.querySelector("#aggiungiLavorazioneForm")) {
            updateInnerHTML("formContainer", html);
          } else {
            updateInnerHTML("tableContainer", html);
            showSuccessModal("Lavarazione e ore annotate correttamente");
            addDeleteListener();
          }
        })
        .finally(() => {
          submitButton.disabled = false;
        });
    }
  );

  addDeleteListener();
}

function addDeleteListener() {
  document.querySelectorAll(".btn-elimina").forEach((button) => {
    button.addEventListener("click", function () {
      const idLavorazione = $(this).data("id");

      if (!confirm("Sei sicuro di voler eliminare questa riparazione?")) {
        return;
      }

      fetchHandled(`/api/lavorazione/elimina/${idLavorazione}`, {
        method: "DELETE",
      }).then((isDeleted) => {
        if (isDeleted) {
          showSuccessModal("Lavarazione e ore eliminate correttamente", true);
        }
      });
    });
  });
}
