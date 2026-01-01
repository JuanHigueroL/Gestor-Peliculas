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
    document.getElementById('btn-editar-actor').href = '/actores/editar/' + data.id;
    document.getElementById('btn-borrar-actor').href = '/actores/borrar/' + data.id;
    document.getElementById('btn btn-unirPelicula-actor').href ='/actores/unirPelicula/'+ data.id;

    // Gestión de la imagen
    const imgElement = document.getElementById('det-img');
    const placeholderElement = document.getElementById('default-user-icon');

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

    // Scroll
    //document.getElementById('contenido-detalle').scrollIntoView({ behavior: 'smooth' });
}