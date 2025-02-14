import { fetchHandled } from "../../utils/fetchManager.js";
import { updateInnerHTML } from "../../utils/domManager.js";

export function handleModificaClienteForm() {
  const $form = $("#modificaClienteForm");
  const $submitButton = $form.find("button[type='submit']");
  $submitButton.prop("disabled", true);
  let hasValue = false;

  function checkInputs() {
    hasValue = false;
    // Controlla ogni input: se almeno uno non è vuoto, imposta hasValue a true.
    $form
      .find("input")
      .not("[type='hidden']")
      .each(function () {
        const value = $(this).val().trim();
        if (value != "" && value != null) {
          hasValue = true;
          return; // esci dal loop
        }
      });
    // Abilita o disabilita il bottone in base al risultato.
    $submitButton.prop("disabled", !hasValue);
  }

  // Associa l'evento keyup (puoi usare anche change se preferisci)
  $form.find("input").on("input", checkInputs);

  $("#formContainer").on("submit", "#modificaClienteForm", function (event) {
    event.preventDefault(); // Previeni l'invio standard del form

    // Preparazione dei dati del form
    const form = event.target;
    const formData = new FormData(form);

    // Disabilita il pulsante di invio per evitare doppie richieste
    $submitButton.prop("disabled", true);

    if (!confirm("Sicuro di confermare i dati inseriti?")) {
      $submitButton.prop("disabled", false);
      return;
    }

    // Chiamata AJAX
    fetchHandled(form.action, {
      method: "POST",
      body: formData,
    })
      .then((html) => {
        updateInnerHTML("formContainer", html);
      })
      .finally(() => {
        $submitButton.prop("disabled", false);
      });
  });
}
