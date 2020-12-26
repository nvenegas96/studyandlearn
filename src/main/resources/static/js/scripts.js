/*!
    * Start Bootstrap - Agency v6.0.3 (https://startbootstrap.com/theme/agency)
    * Copyright 2013-2020 Start Bootstrap
    * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-agency/blob/master/LICENSE)
    */
    (function ($) {
    "use strict"; // Start of use strict

    // Smooth scrolling using jQuery easing
    $('a.js-scroll-trigger[href*="#"]:not([href="#"])').click(function () {
        if (
            location.pathname.replace(/^\//, "") ==
                this.pathname.replace(/^\//, "") &&
            location.hostname == this.hostname
        ) {
            var target = $(this.hash);
            target = target.length
                ? target
                : $("[name=" + this.hash.slice(1) + "]");
            if (target.length) {
                $("html, body").animate(
                    {
                        scrollTop: target.offset().top - 72,
                    },
                    1000,
                    "easeInOutExpo"
                );
                return false;
            }
        }
    });


    // Closes responsive menu when a scroll trigger link is clicked
    $(".js-scroll-trigger").click(function () {
        $(".navbar-collapse").collapse("hide");
    });

    // Activate scrollspy to add active class to navbar items on scroll
    $("body").scrollspy({
        target: "#mainNav",
        offset: 74,
    });

    // Collapse Navbar
    var navbarCollapse = function () {
        if ($("#mainNav").offset().top > 100) {
            $("#mainNav").addClass("navbar-shrink");
        } else {
            $("#mainNav").removeClass("navbar-shrink");
        }
    };
    // Collapse now if page is not at top
    navbarCollapse();
    // Collapse the navbar when page is scrolled
    $(window).scroll(navbarCollapse);
})(jQuery); // End of use strict

function commentBox(){
    var name=document.getElementById('name').value;
    var comment=document.getElementById('comment').value;

    if(name =="" || comment ==""){
        alert("Porfavor introduce la informacion requerida!");
    }else{
        var parent=document.createElement('div');
        var el_name=document.createElement('h5');
        var el_message=document.createElement('p');
        var el_line=document.createElement('hr');
        var txt_name=document.createTextNode(name);
        var txt_message=document.createTextNode(comment);
        el_name.appendChild(txt_name);
        el_message.appendChild(txt_message);
        el_line.style.border='1px solid #000';
        parent.appendChild(el_name);
        parent.appendChild(el_line);
        parent.appendChild(el_message);
        parent.setAttribute('class', 'pane');
        document.getElementById('result').appendChild(parent);

        document.getElementById('name').value="";
        document.getElementById('comment').value="";
    }
}

function doSearch()
{
    const tableReg = document.getElementById('datos');
    const searchText = document.getElementById('searchTerm').value.toLowerCase();
    let total = 0;

    // Recorremos todas las filas con contenido de la tabla
    for (let i = 1; i < tableReg.rows.length; i++) {
        // Si el td tiene la clase "noSearch" no se busca en su cntenido
        if (tableReg.rows[i].classList.contains("noSearch")) {
            continue;
        }

        let found = false;
        const cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        // Recorremos todas las celdas
        for (let j = 0; j < cellsOfRow.length && !found; j++) {
            const compareWith = cellsOfRow[j].innerHTML.toLowerCase();
            // Buscamos el texto en el contenido de la celda
            if (searchText.length == 0 || compareWith.indexOf(searchText) > -1) {
                found = true;
                total++;
            }
        }
        if (found) {
            tableReg.rows[i].style.display = '';
        } else {
            // si no ha encontrado ninguna coincidencia, esconde la
            // fila de la tabla
            tableReg.rows[i].style.display = 'none';
        }
    }

    // mostramos las coincidencias
    const lastTR=tableReg.rows[tableReg.rows.length-1];
    const td=lastTR.querySelector("td");
    lastTR.classList.remove("hide", "red");
    if (searchText == "") {
        lastTR.classList.add("hide");
    } else if (total) {
        td.innerHTML="Se ha encontrado "+total+" coincidencia"+((total>1)?"s":"");
    } else {
        lastTR.classList.add("red");
        td.innerHTML="No se han encontrado coincidencias";
    }

}
function confirmationReserva()
{
    if(confirm("¿Desea realizar la reserva?"))
    {
        return true;
    }
    else
    {
        return false;
    }
}

function confirmationPostulación()
{
    if(confirm("¿Desea enviar la postulación?"))
    {
        return true;
    }
    else
    {
        return false;
    }
}

function confirmationContacto()
{
    if(confirm("¿Desea enviar el formulario de contacto?"))
    {
        return true;
    }
    else
    {
        return false;
    }
}

function confirmationCrearSolicitud()
{
    if(confirm("¿Desea continuar con la creación de la solicitud?"))
    {
        return true;
    }
    else
    {
        return false;
    }
}
function confirmationEditarSolicitud()
{
    if(confirm("¿Desea continuar con la edición de la solicitud?"))
    {
        return true;
    }
    else
    {
        return false;
    }
}
function confirmationEliminarSolicitud()
{
    if(confirm("¿Esta seguro de eliminar la solicitud?"))
    {
        return true;
    }
    else
    {
        return false;
    }
}

function confirmationCambiarEstado()
{
    if(confirm("¿Esta seguro que quiere cambiar el estado de la clase?"))
    {
        return true;
    }
    else
    {
        return false;
    }
}


function confirmationCrearDocente()
{
    if(confirm("¿Desea continuar con la creación del docente?"))
    {
        return true;
    }
    else
    {
        return false;
    }
}
function confirmationEditarDocente()
{
    if(confirm("¿Desea continuar con la edición del docente?"))
    {
        return true;
    }
    else
    {
        return false;
    }
}
function confirmationEliminarDocente()
{
    if(confirm("¿Esta seguro de eliminar el docente?"))
    {
        return true;
    }
    else
    {
        return false;
    }
}

function confirmationCreateLesson()
{
    if(confirm("¿Desea continuar con la creación del curso?"))
    {
        return true;
    }
    else
    {
        return false;
    }
}
function confirmationEditarCurso()
{
    if(confirm("¿Desea continuar con la edición del curso?"))
    {
        return true;
    }
    else
    {
        return false;
    }
}
function confirmationEliminarCurso()
{
    if(confirm("¿Esta seguro de eliminar este curso?"))
    {
        return true;
    }
    else
    {
        return false;
    }
}

$('#myModal').on('shown.bs.modal', function () {
    $('#myInput').trigger('focus')
})

function countChars(obj){
    document.getElementById("charNum").innerHTML = obj.value.length+' characters';
}



