/* =========================================
   LÓGICA ESPECÍFICA DE PELÍCULAS
   ========================================= */

function mostrarDetalles(elemento) {
    // Obtener datos del dataset
    const data = elemento.dataset;
    // Rellenar textos
    document.getElementById('det-id').innerText = data.id;
    document.getElementById('det-titulo').innerText = data.titulo;
    document.getElementById('det-anio').innerText = data.anio;
    document.getElementById('det-duracion').innerText = data.duracion ? data.duracion + ' min' : '';
    document.getElementById('det-pais').innerText = data.pais;
    document.getElementById('det-genero').innerText = data.genero;
    document.getElementById('det-direccion').innerText = data.direccion;
    document.getElementById('det-sinopsis').innerText = data.sinopsis;
    // Actualizar botones de editar y borrar
    document.getElementById('btn-editar-detalle').href = '/peliculas/editar/' + data.id;
    document.getElementById('btn-borrar-detalle').href = '/peliculas/borrar/' + data.id;
    // Gestión de la imagen
    const imgElement = document.getElementById('det-img');
    const placeholderElement = document.getElementById('det-img-placeholder');

    if (data.imagen && data.imagen !== '') {
        imgElement.src = data.imagen;
        imgElement.style.display = 'block';
        placeholderElement.style.display = 'none';
    } else {
        imgElement.style.display = 'none';
        placeholderElement.style.display = 'block';
    }

    // Mostrar contenedor y ocultar mensaje vacío
    document.getElementById('mensaje-vacio').style.display = 'none';
    document.getElementById('contenido-detalle').style.display = 'flex';

    // 6. Scroll suave hacia el detalle
    document.getElementById('detalle-destacado').scrollIntoView({ behavior: 'smooth' });
}