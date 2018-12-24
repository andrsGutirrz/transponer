/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$("#formTransponer").submit(function (event) {
    
    if ($("#instrumentos option:selected").val() == "Escoger...") {
        $("#labelInstrumentos").addClass("text-danger");
        event.preventDefault();
    }
    if ($("#nombreTabla").val().length == 0) {
        $("#labelNombreTabla").addClass("text-danger");
        $("#nombreTabla").addClass("is-invalid");
        event.preventDefault();
    }
});