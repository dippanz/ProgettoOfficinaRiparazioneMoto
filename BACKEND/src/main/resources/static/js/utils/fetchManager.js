import { showErrorModal } from "../utils/domManager.js";

export function handleFetchError(error) {
  try {
    const errorParsed = JSON.parse(error.message);
    showErrorModal(errorParsed.message); // Mostra il messaggio di errore JSON
  } catch (e) {
    showErrorModal(); // Mostra errore generico
  }
}

export function handleFetchResponse(response) {
  // Controllo se la risposta è un redirect
  if (response.redirected) {
    window.location.href = response.url; // Effettua il redirect manuale
    return Promise.reject("Redirecting..."); // Interrompe il flusso della Promise
  }

  console.log(response);

  if (response.ok) {
    return response.text(); // Restituisce il contenuto della risposta
  }

  // Se la risposta è un errore, estraiamo il testo dell'errore
  return response.text().then((errorText) => {
    throw new Error(errorText);
  });
}
