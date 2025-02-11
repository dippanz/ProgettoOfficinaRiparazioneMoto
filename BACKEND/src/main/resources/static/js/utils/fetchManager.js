import { showErrorModal } from "../utils/domManager.js";

export function handleFetchError(error) {
  try {
    const errorParsed = JSON.parse(error.message);
    showErrorModal(errorParsed.message, true); // Mostra il messaggio di errore JSON
  } catch (e) {
    showErrorModal("Si è verificato un errore imprevisto", true); // Mostra errore generico
  }
}

export function handleFetchResponse(response) {
  const contentType = response.headers.get("content-type");

  // Controllo se la risposta è un redirect
  if (response.redirected) {
    window.location.href = response.url; // Effettua il redirect manuale
    return Promise.reject("Redirecting..."); // Interrompe il flusso della Promise
  }

  if (response.ok) {
    // Controllo se il Content-Type è JSON e lo restituisco in caso positivo
    if (contentType && contentType.includes("application/json")) {
      return response.json();
    } else {
      return response.text();
    }
  }

  // Se la risposta è un errore, estraiamo il testo dell'errore
  return response.text().then((errorText) => {
    throw new Error(errorText);
  });
}

export function fetchHandled(url, options = {}) {
  return fetch(url, options)
    .then(handleFetchResponse)
    .catch(handleFetchError);
}
