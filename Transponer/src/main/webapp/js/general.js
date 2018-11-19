var general = (function ($) {
    var init = function () {
        cargarDatepicker();
        cargarDataTable();
        orderByInputTextDataTable();
        //cargar iconos menu
        $("#menu").find("li").each(function () {
            if ($(this).children("a").attr("href") === "javascript:void(0)") {
                $(this).children("a").append("<span class='glyphicon glyphicon-chevron-down'></span>");
            } else {
                $(this).children("a").html("<span class='glyphicon glyphicon-link'></span>" + $(this).children("a").html());
            }
        });
    };

    var eventHandlers = function () {

    };
    var buttonConfigDataTable = function (titulo) {
        var config = {
            dom: 'lBfrtip',
            title:'andres',
            buttons: [
                {extend: 'copy', className: "btn-export", exportOptions: {columns: 'thead th:not(.not-export-col)'}, footer: true},
                {extend: 'csv', className: "btn-export", exportOptions: {columns: 'thead th:not(.not-export-col)'}, footer: true},
                {extend: 'excel', className: "btn-export", exportOptions: {columns: 'thead th:not(.not-export-col)'}, footer: true},
                {extend: 'pdf', className: "btn-export", exportOptions: {columns: 'thead th:not(.not-export-col)'}, footer: true,messageTop: titulo},
                {extend: 'print', className: "btn-export", exportOptions: {columns: 'thead th:not(.not-export-col)'}, footer: true}
            ]
        };
        return config;
    };
    var cargarDataTable = function () {
        $.extend($.fn.dataTable.defaults, {
            "language": {
                "sProcessing": "Procesando...",
                "sLengthMenu": "Mostrar _MENU_ registros",
                "sZeroRecords": "No se encontraron resultados",
                "sEmptyTable": "Ning&#250;n dato disponible en esta tabla",
                "sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
                "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
                "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
                "sInfoPostFix": "",
                "sSearch": "Buscar:",
                "sUrl": "",
                "sInfoThousands": ",",
                "sLoadingRecords": "Cargando...",
                "oPaginate": {
                    "sFirst": "Primero",
                    "sLast": "&#218;ltimo",
                    "sNext": "Siguiente",
                    "sPrevious": "Anterior"
                },
                "oAria": {
                    "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
                    "sSortDescending": ": Activar para ordenar la columna de manera descendente"
                }
            }
        });
    };

    var cargarDatepicker = function () {
        $.datepicker.regional["es"] = {
            changeMonth: true,
            changeYear: true,
            yearRange: "-150:+0",
            clearText: "Limpiar",
            clearStatus: "",
            closeText: "Cerrar",
            closeStatus: "",
            prevText: "&#x3c;Ant",
            prevStatus: "",
            prevBigText: "&#x3c;&#x3c;",
            prevBigStatus: "",
            nextText: "Sig&#x3e;",
            nextStatus: "",
            nextBigText: "&#x3e;&#x3e;",
            nextBigStatus: "",
            currentText: "Hoy",
            currentStatus: "",
            monthNames: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
            monthNamesShort: ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"],
            monthStatus: "",
            yearStatus: "",
            weekHeader: "Sm",
            weekStatus: "",
            dayNames: ["Domingo", "Lunes", "Martes", "Mi&eacute;rcoles", "Jueves", "Viernes", "S&aacute;bado"],
            dayNamesShort: ["Dom", "Lun", "Mar", "Mi&eacute;", "Juv", "Vie", "S&aacute;b"],
            dayNamesMin: ["Do", "Lu", "Ma", "Mi", "Ju", "Vi", "S&aacute;"],
            dayStatus: "DD",
            dateStatus: "D, M d",
            dateFormat: "dd/mm/yy",
            firstDay: 0,
            initStatus: "",
            isRTL: false
        };
        $.datepicker.setDefaults($.datepicker.regional["es"]);
    };

    var getContextPath = function () {
        return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
    };

    var datatableResponsiveFix = function (form, table) {
        //Remove all component with class = dataTableFix
        $.each($(form).find("input.dataTableFix"), function () {
            $(this).remove();
        });
        // Encode a set of form elements from all pages as an array of names and values
        var params = table.$('input,select,textarea').serializeArray();
        // Iterate over all form elements
        $.each(params, function () {
            // If element doesn't exist in DOM                   
            if (!jQuery.contains(document, form[this.name])) {
                // Create a hidden element 
                $(form).append(
                        $('<input>')
                        .attr('type', 'hidden')
                        .attr('name', this.name)
                        .attr('class', "dataTableFix")
                        .val(this.value)
                        );
            }
        });
    };

    var orderByInputTextDataTable = function () {
        /* Create an array with the values of all the input boxes in a column */
        $.fn.dataTable.ext.order['dom-text'] = function (settings, col) {
            return this.api().column(col, {order: 'index'}).nodes().map(function (td, i) {          
                return $('input[type=text]', td).val();
            });
        }
    }

    var navegacion = function (linkSiguiente, linkRegresar) {
        if (linkSiguiente !== null) {
            $(".linkSiguiente").show();
            $(".linkSiguiente").attr("href", linkSiguiente);
        }
        if (linkRegresar !== null) {
            $(".linkRegresar").show();
            $(".linkRegresar").attr("href", linkRegresar);
        }
    };
    return {
        init: init,
        eventHandlers: eventHandlers,
        getContextPath: getContextPath,
        datatableResponsiveFix: datatableResponsiveFix,
        buttonConfigDataTable: buttonConfigDataTable,
        orderByInputTextDataTable: orderByInputTextDataTable,
        navegacion: navegacion
    };

})(jQuery);

$(document).ready(function () {
    general.init();
    general.eventHandlers();
});