import { fetchHandled } from "../../utils/fetchManager.js";

export function handlePrendiInCaricoPage() {
  const riparazioniSelezionate = new Set();

  // Gestione click sul pulsante "+"
  document.querySelectorAll(".btn-add").forEach((button) => {
    button.addEventListener("click", function () {
      const currentRow = $(this).closest("tr"); // prende il primo antenato
      const buttonCancel = currentRow.find(".btn-cancel");
      const hiddenInput = currentRow.find('input[name="presaInCarico"]');
      const idRiparazione = hiddenInput.attr("value");

      // Aggiungi agli ID selezionati
      riparazioniSelezionate.add(idRiparazione);
      $("#saveButton").prop("disabled", false);
      hiddenInput.prop("disabled", false);

      currentRow.addClass("table-primary");

      $(this).hide();
      buttonCancel.show();
    });
  });

  // Gestione click su annulla
  document.querySelectorAll(".btn-cancel").forEach((button) => {
    button.addEventListener("click", function () {
      const currentRow = $(this).closest("tr"); // prende il primo antenato
      const buttonAdd = currentRow.find(".btn-add");
      const hiddenInput = currentRow.find('input[name="presaInCarico"]');
      const idRiparazione = hiddenInput.attr("value");

      // Rimuovi dagli ID selezionati
      riparazioniSelezionate.delete(idRiparazione);
      hiddenInput.prop("disabled", true);

      currentRow.removeClass("table-primary");

      $(this).hide();
      buttonAdd.show();

      // Disabilita "Salva" se non ci sono modifiche
      if (riparazioniSelezionate.size === 0) {
        $("#saveButton").prop("disabled", true);
      }
    });
  });

  // Gestione click su "Salva"
  $("#formPresaInCarico").on("submit", function (event) {
    event.preventDefault(); // Previeni l'invio standard del form

    // Preparazione dei dati del form
    const form = event.target;
    const formData = new FormData(form);

    // Debug: mostra cosa viene inviato
    // for (let [key, value] of formData.entries()) {
    //   console.log(key, value);
    // }

    fetchHandled(form.action, {
      method: "POST",
      body: formData,
    });
  });
}
