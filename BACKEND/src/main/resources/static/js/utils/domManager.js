export function updateInnerHTML(idELement, innerHTML) {
  const element = document.getElementById(idELement);
  if (element) {
    element.innerHTML = innerHTML;
  }
}

export function showErrorModal(message, isAfertReload = false) {
  if (isAfertReload) {
    sessionStorage.setItem("errorMessage", message);
    location.reload();
    return;
  }
  showModal(message, "modalError", "#contentModalError");
}

export function showSuccessModal(message, isAfertReload = false) {
  if (isAfertReload) {
    sessionStorage.setItem("successMessage", message);
    location.reload();
    return;
  }
  showModal(message, "modalSuccess", "#contentModalSuccess", 3000);
}

function showModal(message, idContainer, idContent, timeoutInMillis = 5000) {
  if (!message || !idContainer || !idContent) {
    return;
  }

  const modalContainer = document.getElementById(idContainer);
  modalContainer.querySelector(idContent).textContent = message;

  //creo ed initializzo la modale
  const modal = new bootstrap.Modal(modalContainer);
  modal.show();

  //setto il timeout dopo il quale la modale viene chiusa
  setTimeout(() => {
    modal.hide();
  }, timeoutInMillis);
}
