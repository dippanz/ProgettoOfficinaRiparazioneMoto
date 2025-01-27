function handleRegistrationForm() {
  const formContainer = document.getElementById("formContainer");

  if (formContainer) {
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
        fetch(form.action, {
          method: "POST",
          body: formData,
        })
          .then((response) => {
            return response.text();
          })
          .then((html) => {
            const formContainer = document.getElementById("formContainer");
            formContainer.innerHTML = html;
          })
          .catch((error) => {
            console.error("Errore:", error);
          })
          .finally(() => {
            submitButton.disabled = false;
          });
      }
    });
  }
}
