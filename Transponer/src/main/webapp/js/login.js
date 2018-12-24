/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$("#formLogin").submit(function (event) {

    if ($("#username").val().length == 0) {
        $("#labelUserName").addClass("text-danger");
        $("#username").addClass("is-invalid");
        event.preventDefault();
    }
    if ($("#pass").val().length == 0) {
        $("#labelPass").addClass("text-danger");
        $("#pass").addClass("is-invalid");
        event.preventDefault();
    }
}); 