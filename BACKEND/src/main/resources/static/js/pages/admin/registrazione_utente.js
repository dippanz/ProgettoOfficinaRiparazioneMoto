import { fetchHandled } from "../../utils/fetchManager.js";
import { updateInnerHTML } from "../../utils/domManager.js";

export function handleRegistrationForm() {
  const formContainer = document.getElementById("formContainer");

  if (formContainer) {
    //inzializzazione selectpicker
    $("#selectRuoli").selectpicker("refresh");

    formContainer.addEventListener("submit", function (event) {
      if (event.target && event.target.id === "registrationForm") {
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
            updateInnerHTML("formContainer", html);
            $("#selectRuoli").selectpicker("refresh");
          })
          .finally(() => {
            submitButton.disabled = false;
          });
      }
    });
  }
}
