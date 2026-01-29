/* =========================================
   LÓGICA ESPECÍFICA DE ACTORES
   ========================================= */

function mostrarDetalles(elemento) {
    // Obtener datos del dataset
    const data = elemento.dataset;
    // Rellenar textos
    document.getElementById('det-id').innerText = data.id ? data.id : "Desconocido";
    document.getElementById('det-nombre').innerText = data.nombre ? data.nombre : "Desconocido";
    document.getElementById('det-pais').innerText = data.pais ? data.pais : "Desconocido";
    document.getElementById('det-fecha').innerText = data.fecha ? data.fecha : "Desconocida";
    //Actualizar botones de editar y borrar
    const btnEditarActor = document.getElementById('btn-editar-actor');
    if (btnEditarActor) {
        btnEditarActor.href = '/actores/editar/' + data.id;
    }

    const btnBorrarActor = document.getElementById('btn-borrar-actor');
    if (btnBorrarActor) {
        btnBorrarActor.href = '/actores/borrar/' + data.id;
    }

    // OJO: Aquí he corregido el ID también (quitando los espacios) para que coincida con el HTML arreglado
    const btnUnirPelicula = document.getElementById('btn-btn-unirPelicula-actor');
    if (btnUnirPelicula) {
        btnUnirPelicula.href = '/actores/unirPelicula/' + data.id;
    }
    // Gestión de la imagen
    const imgElement = document.getElementById('det-img');
    const placeholderElement = document.getElementById('default-user-icon');

    if (data.imagen && data.imagen !== '') {
        imgElement.src ='/actores/uploads/' + data.imagen;
        imgElement.style.display = 'block';
        placeholderElement.style.display = 'none';
    } else {
        imgElement.style.display = 'none';
        placeholderElement.style.display = 'block';
    }

    // Mostrar contenedor y ocultar mensaje vacío
    document.getElementById('mensaje-vacio').style.display = 'none';
    document.getElementById('contenido-detalle').style.display = 'flex';
    document.getElementById('contenido-listado').style.display = 'flex';
    document.getElementById('contenido-edicion-acciones').style.display = 'flex';
    document.querySelector('.contenido-invisible').style.display = 'flex';

    // Scroll
    //document.getElementById('contenido-detalle').scrollIntoView({ behavior: 'smooth' });


    // --- LÓGICA DE LA LISTA DE PELICULAS ---
    // Oculta todas las listas de películas que haya en pantalla
    const todasLasListas = document.querySelectorAll('.lista-peliculas-item');
    todasLasListas.forEach(function(lista) {
        lista.style.display = 'none';
    });

    // Muestra la lista de películas correspondiente a la película seleccionada
    document.getElementById('lista-peliculas-' + data.id).style.display = 'flex';
}