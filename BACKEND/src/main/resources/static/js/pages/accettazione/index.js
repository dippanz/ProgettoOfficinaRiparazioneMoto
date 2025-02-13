import { fetchHandled } from "../../utils/fetchManager.js";
import { showSuccessModal, updateInnerHTML } from "../../utils/domManager.js";

export function handleIndexPage() {
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
          console.log(isUpdated)
          showSuccessModal("Riparazione accettata correttamente", true);
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
          showSuccessModal("Riparazione rifiutata correttamente", true);
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
          console.log(isDeleted)
          showSuccessModal("Riparazione eliminata correttamente", true);
          location.reload();
        }
      });
    });
  });
}
