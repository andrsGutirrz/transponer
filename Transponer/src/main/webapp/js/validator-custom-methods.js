var formValidationDefaults = {
    errorClass: 'client-error',
    highlight: function (element) {
        $(element).parent().removeClass("client-error");
    },
    unhighlight: function (element) {
        $(element).parent().removeClass("client-error");
    },
    onfocusout: false,
    onkeyup: false,
    onclick: false,
    focusInvalid: false
};

jQuery.extend(jQuery.validator.messages, {
    required: "Campo requerido.",
    email: "Por favor ingrese un correo electrnico v&#225;lido.",
    url: "Por favor ingrese un URL v&#225;lido.",
    date: "Por favor ingrese una fecha v&#225;lida.",
    number: "Por favor ingrese un n&#250;mero v&#225;lido",
    digits: "Por favor ingrese solo d&#237;gitos.",
    equalTo: "Por favor ingrese el mismo valor de nuevo.",
    maxlength: jQuery.validator.format("Por favor ingrese no mas de {0} caracteres."),
    minlength: jQuery.validator.format("Por favor ingrese al menos {0} caracteres."),
    rangelength: jQuery.validator.format("Por favor ingrese un valor entre {0} y {1} caracteres."),
    range: jQuery.validator.format("Por favor ingrese un valor entre {0} y {1}"),
    max: jQuery.validator.format("Por favor ingrese un valor menor o igual a {0}"),
    min: jQuery.validator.format("Por favor ingrese un valor mayor o igual a {0}"),
    notEqualTo: "Por favor ingrese un valor diferente, los valores no pueden ser el mismo"
});

$.validator.addMethod("emailFormat",
        function (value, element) {
            return this.optional(element) || /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,})(\]?)+$/ig.test(value);
        },
        "El formato del correo electrnico no es v&#225;lido."
        );

$.validator.addMethod("lettersOnly",
        function (value, element) {
            return this.optional(element) || /^[a-z,A-Z,Ñ,ñ,Á,á,É,é,Í,í,Ó,ó,Ú,ú\s]+$/ig.test(value);
        },
        "Por favor ingrese solo letras o espacios."
        );

$.validator.addMethod("passwordFormat",
        function (value, element) {
            return this.optional(element) || /^((?=.*[\\d])(?=.*[A-Za-z])|(?=.*[^\\w\\d\\s])(?=.*[A-Za-z])).{6,}$/ig.test(value);
        },
        "La contraseña debe contener al menos 6 caracteres, una letra may&#250;scula, una min&#250;scula y un n&#250;mero."
        );

$.validator.addMethod("numberOnly",
        function (value, element) {
            return this.optional(element) || /^[0-9]+$/ig.test(value);
        },
        "Por favor ingrese solo n&#250;meros."
        );

$.validator.addMethod("numberWithTwoDecimalOnly",
        function (value, element) {
            return this.optional(element) || /^\d+(\.\d{1,2})?/ig.test(value);
        },
        "Por favor ingrese solo n&#250;meros con 2 decimales."
        );

$.validator.addMethod("selectOne",
        function (value, element, arg) {
            return !this.optional(element) || value !== "" || arg !== true;
        }, "Debe seleccionar un valor.");

$.validator.addMethod("requiredWithDependency",
        function (value, element, arg) {       
            if (arg[0] === (arg[1]).val()){
                return !this.optional(element) || value !== "";                
            }
            return true;
        },
       "Campo requerido."
        );

function showNotifMessages(msg, type) {
    notif({
        msg: msg,
        type: type,
        position: "center",
        multiline: true
    });
}
;

function decimalOnly(input, e) {
    input.val(input.val().replace(/[^0-9\.]/ig, ''));
    if ((e.which !== 46 || input.val().indexOf('.') !== -1) && (e.which < 48 || e.which > 57)) {
        e.preventDefault();
    }
}
;

function numberOnly(input, e) {
    if (e.which !== 8 && isNaN(String.fromCharCode(e.which))) {
        e.preventDefault();
    }
}
;

// formatea un numero según una mascara dada ej: "-$###,###,##0.00"
// elm   = elemento html <input> donde colocar el resultado
// n     = numero a formatear
// mask  = mascara ej: "-$###,###,##0.00"
// force = formatea el numero aun si n es igual a 0
// La función devuelve el numero formateado

function MASK(form, n, mask, format) {
    mask = mask.replace("&#8353;", "\u20A10");
    if (format === "undefined")
        format = false;
    if (format || NUM(n)) {
        dec = 0, point = 0;
        x = mask.indexOf(".") + 1;
        if (x) {
            dec = mask.length - x;
        }
        if (dec) {
            n = NUM(n, dec) + "";
            x = n.indexOf(".") + 1;
            if (x) {
                point = n.length - x;
            } else {
                n += ".";
            }
        } else {
            n = NUM(n, 0) + "";
        }
        for (var x = point; x < dec; x++) {
            n += "0";
        }
        x = n.length, y = mask.length, XMASK = "";
        while (x || y) {
            if (x) {
                while (y && "#0.".indexOf(mask.charAt(y - 1)) === -1) {
                    if (n.charAt(x - 1) !== "-")
                        XMASK = mask.charAt(y - 1) + XMASK;
                    y--;
                }
                XMASK = n.charAt(x - 1) + XMASK, x--;
            } else if (y && "\u20A1".indexOf(mask.charAt(y - 1)) + 1) {
                XMASK = mask.charAt(y - 1) + XMASK;
            }
            if (y) {
                y--
            }
        }
    } else {
        XMASK = "";
    }
    if (form) {
        if ($(form).is("input")) {
            form.value = XMASK;
        } else {
            form.innerHTML = XMASK;
        }
    }
    return XMASK;
}

//Convierte una cadena alfanumérica a numérica (incluyendo formulas aritméticas)
// s   = cadena a ser convertida a numérica
// dec = numero de decimales a redondear
// La función devuelve el numero redondeado
function NUM(s, dec) {
    for (var s = s + "", num = "", x = 0; x < s.length; x++) {
        c = s.charAt(x);
        if (".-+/*".indexOf(c) + 1 || c !== " " && !isNaN(c)) {
            num += c;
        }
    }
    if (isNaN(num)) {
        num = eval(num);
    }
    if (num === "") {
        num = 0;
    } else {
        num = parseFloat(num);
    }
    if (dec !== undefined) {
        r = .5;
        if (num < 0)
            r = -r;
        e = Math.pow(10, (dec > 0) ? dec : 0);
        return parseInt(num * e + r) / e;
    } else {
        return num;
    }
}