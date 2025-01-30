export function updateInnerHTML(idELement, innerHTML) {
  const element = document.getElementById(idELement);
  if (element) {
    element.innerHTML = innerHTML;
  }
}

export function showErrorModal(message) {
  const modalContainer = document.getElementById("modalError");
  if (message) {
    modalContainer.querySelector("#contentModalError").textContent = message;
  }

  //creo ed initializzo la modale
  const modal = new bootstrap.Modal(modalContainer);
  modal.show();

  //setto il timeout dopo il quale la modale viene chiusa
  setTimeout(() => {
    modal.hide();
  }, 5000);
}
