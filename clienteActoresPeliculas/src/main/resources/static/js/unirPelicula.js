function toggleSeleccion(elemento) {
    // Activa visualmente la tarjeta
    elemento.classList.toggle('activo');

    // Marca el checkbox
    const checkbox = elemento.querySelector('input[type="checkbox"]');
    if(checkbox) {
        //Cambia el valor a lo contrario
        checkbox.checked = !checkbox.checked;
    }
}

