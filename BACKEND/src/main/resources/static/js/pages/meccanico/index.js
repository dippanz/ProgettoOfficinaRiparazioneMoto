import { fetchHandled } from "../../utils/fetchManager.js";

export function handleIndexPage(params) {
  document.querySelectorAll(".btn-completa-riparazione").forEach((button) => {
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
}
