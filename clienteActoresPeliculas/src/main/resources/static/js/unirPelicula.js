function toggleSeleccion(elemento) {
    // 1. Activa visualmente la tarjeta
    elemento.classList.toggle('activo');

    // 2. Marca el checkbox oculto (si lo tienes)
    const checkbox = elemento.querySelector('input[type="checkbox"]');
    if(checkbox) {
        checkbox.checked = !checkbox.checked;
    }
}

