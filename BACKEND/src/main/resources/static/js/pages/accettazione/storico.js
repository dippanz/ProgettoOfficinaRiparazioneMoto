export function handleStoricoPage() {
  const filterStatus = document.getElementById("filterStatus");
  const rows = document.querySelectorAll("tbody tr");

  filterStatus.addEventListener("change", function () {
    const selectedStatus = this.value.toLowerCase();

    rows.forEach((row) => {
      const stato = row
        .querySelector("td:nth-child(4)")
        .textContent.trim()
        .toLowerCase();

      if (selectedStatus === "" || stato === selectedStatus) {
        row.style.display = "";
      } else {
        row.style.display = "none";
      }
    });
  });
}
