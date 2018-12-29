
$(document).ready(function () {
    $("#tableResultados").DataTable({
        dom: 'Bfrtip',
        responsive: true,
        scrollY: 420,
        scrollX: 370,
        buttons: [
            'copy', 'csv', 'excel'
        ]
    });
});