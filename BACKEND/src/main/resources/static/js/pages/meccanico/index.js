import {
  handleFetchError,
  handleFetchResponse,
} from "../../utils/fetchManager.js";

export function handleIndexPage(params) {
  document.querySelectorAll(".btn-completa-riparazione").forEach((button) => {
    button.addEventListener("click", function () {
      let idRiparazione = $(this).attr("data-id");

      fetch(`/api/riparazione/avanza_stato_riparazione?id=${idRiparazione}`, {
        method: "PATCH",
      })
        .then(handleFetchResponse)
        .then((isUpdated) => {
          if (isUpdated) {
            location.reload();
          }
        })
        .catch(handleFetchError);
    });
  });
}
