function toggleClienteSection() {
  let $checkbox = $("#clienteEsistenteCheckbox");
  let $nuovoClienteSection = $("#nuovoClienteSection");
  let $clienteEsistenteSection = $("#clienteEsistenteSection");

  let $email = $("#email");
  let $selectCliente = $("#selectClienti");

  if ($checkbox.is(":checked")) {
    // Mostra la select e nasconde i campi di input
    $nuovoClienteSection.hide();
    $clienteEsistenteSection.show();

    // Rimuove required dai campi input
    $email.prop("required", false);

    // Aggiunge required alla select
    $selectCliente.prop("required", true);
  } else {
    // Mostra i campi di input e nasconde la select
    $nuovoClienteSection.show();
    $clienteEsistenteSection.hide();

    // Aggiunge required ai campi input
    $email.prop("required", true);

    // Rimuove required dalla select
    $selectCliente.prop("required", false);
  }
}

function toggleMotoSection() {
  let $checkbox = $("#motoEsistenteCheckbox");
  let $nuovaMotoSection = $("#nuovaMotoSection");
  let $motoEsistenteSection = $("#motoEsistenteSection");
  let $motoSelectedContainer = $("#motoSelected");

  let $email = $("#email");
  let $targa = $("#targa");
  let $selectMoto = $("#selectMoto");
  let $selectCliente = $("#selectClienti");

  let $checkboxCliente = $("#clienteEsistenteCheckbox");

  if ($checkbox.is(":checked")) {
    // Mostra la select e nasconde i campi di input della moto
    $nuovaMotoSection.hide();
    $motoEsistenteSection.show();
    $motoSelectedContainer.hide();

    // Rimuove required dai campi della nuova moto
    $targa.add($email).prop("required", false);

    // Aggiunge required alla select delle moto
    $selectMoto.prop("required", true);
    $selectCliente.prop("required", false);
  } else {
    // Mostra i campi di input della moto e nasconde la select
    $nuovaMotoSection.show();
    $motoEsistenteSection.hide();
    $motoSelectedContainer.show();

    // Aggiunge required ai campi della nuova moto
    $targa.add($email).prop("required", true);

    // Rimuove il required dalla select delle moto
    $selectMoto.prop("required", false);

    if ($checkboxCliente.is(":checked")) {
      $selectCliente.prop("required", true);
    }
  }
}

function getClienti() {
  fetch(`/api/clienti`)
    .then(handleFetchResponse)
    .then((listaClienti) => {
      const selectClienti = document.getElementById("selectClienti");

      //inserisco i dati
      if (selectClienti) {
        listaClienti.forEach((cliente) => {
          let option = document.createElement("option");
          option.value = cliente.id;
          option.textContent = `${cliente.email}`;
          selectClienti.appendChild(option);
        });

        // Ricarica SelectPicker per aggiornare la lista
        $(".selectpicker").selectpicker("refresh");
      }
    })
    .catch(handleFetchError);
}

function getMoto() {
  fetch(`/api/moto`)
    .then(handleFetchResponse)
    .then((listaMoto) => {
      const selectMoto = document.getElementById("selectMoto");

      //inserisco i dati
      if (selectMoto) {
        listaMoto.forEach((moto) => {
          let option = document.createElement("option");
          option.value = moto.id;
          option.textContent = moto.targa;
          selectMoto.appendChild(option);
        });

        // Ricarica SelectPicker per aggiornare la lista
        $(".selectpicker").selectpicker("refresh");
      }
    })
    .catch(handleFetchError);
}

import {
  handleFetchError,
  handleFetchResponse,
} from "../../utils/fetchManager.js";
import { updateInnerHTML } from "../../utils/domManager.js";

export function handleModuloAccettazioneForm() {
  $(".selectpicker").selectpicker("refresh");

  $("#formContainer").on("submit", "#moduloAccettazioneForm", function (event) {
    event.preventDefault(); // Previeni l'invio standard del form

    // Preparazione dei dati del form
    const form = event.target;
    const formData = new FormData(form);

    if ($("#motoEsistenteCheckbox").is(":checked")) {
      formData.delete("targa");
      formData.delete("modello");
      formData.delete("nome");
      formData.delete("cognome");
      formData.delete("email");
      formData.delete("telefono");
      formData.delete("idCliente");
    } else if ($("#clienteEsistenteCheckbox").is(":checked")) {
      formData.delete("nome");
      formData.delete("cognome");
      formData.delete("email");
      formData.delete("telefono");
      formData.delete("idMoto");
    } else {
      formData.delete("idCliente");
      formData.delete("idMoto");
    }

    // Debug: mostra cosa viene inviato
    for (let [key, value] of formData.entries()) {
      console.log(key, value);
    }

    // Disabilita il pulsante di invio per evitare doppie richieste
    const submitButton = form.querySelector("button[type='submit']");
    submitButton.disabled = true;

    // Chiamata AJAX
    fetch(form.action, {
      method: "POST",
      body: formData,
    })
      .then(handleFetchResponse)
      .then((html) => {
        updateInnerHTML("formContainer", html);
        $(".selectpicker").selectpicker("refresh");
        $("#clienteEsistenteSection").one("mousedown", getClienti);
        $("#motoEsistenteSection").one("mousedown", getMoto);
        $("#motoEsistenteCheckbox").click("click", toggleMotoSection);
        $("#clienteEsistenteCheckbox").click("click", toggleClienteSection);
      })
      .catch(handleFetchError)
      .finally(() => {
        submitButton.disabled = false;
      });
  });

  $("#clienteEsistenteSection").one("mousedown", getClienti);
  $("#motoEsistenteSection").one("mousedown", getMoto);
  $("#motoEsistenteCheckbox").click("click", toggleMotoSection);
  $("#clienteEsistenteCheckbox").click("click", toggleClienteSection);
}
