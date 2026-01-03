function toggleSeleccion(elemento) {
    // Activa visualmente la tarjeta
    elemento.classList.toggle('activo');

    // Marca el checkbox
    const checkbox = elemento.querySelector('input[type="checkbox"]');
    if(checkbox) {
        //Cambia el valor a lo contrario
        checkbox.checked = !checkbox.checked;
    }
    //
    aplicarFiltros();
}

function aplicarFiltros() {
    // Obtener valores de los filtros
    const inputTexto = document.getElementById('buscador').value.toLowerCase();
    const soloSeleccionados = document.getElementById('seleccionar-todos').checked;

    // Obtener todas las tarjetas
    const tarjetas = document.getElementsByClassName('seleccion-card');

    // Recorrerlas una a una
    for (let i = 0; i < tarjetas.length; i++) {
        // Obtener la tarjeta actual
        const tarjeta = tarjetas[i];

        // Se comprueba si cumple el criterio del nombre
        //Coge el título de la tarjeta
        const nombrePelicula = tarjeta.getElementsByTagName("h4")[0].textContent;
        // Se comprueba si el nombre contiene el texto del input (ignorando mayúsculas/minúsculas)
        const coincideNombre = nombrePelicula.toLowerCase().indexOf(inputTexto) > -1;

        // --- CRITERIO B: Estado (Activo/Seleccionado) ---
        // Comprobamos si tiene la clase 'activo'
        const estaActiva = tarjeta.classList.contains('activo');

        // Si el checkbox está marcado, solo pasa si está activa. Si no, pasa siempre.
        const cumpleEstado = !soloSeleccionados || estaActiva;

        // Si cumple ambos criterios, se muestra; si no, se oculta.
        if (coincideNombre && cumpleEstado) {
            tarjeta.style.display = ""; // Mostrar
        } else {
            tarjeta.style.display = "none"; // Ocultar
        }
    }
}