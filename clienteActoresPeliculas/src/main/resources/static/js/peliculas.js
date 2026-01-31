/* =========================================
   LÓGICA ESPECÍFICA DE PELÍCULAS
   ========================================= */

function mostrarDetalles(elemento) {
    // Obtener datos del dataset
    const data = elemento.dataset;

    // Rellenar textos
    document.getElementById('det-id').innerText = data.id ? data.id : "Desconocido";
    document.getElementById('det-titulo').innerText = data.titulo ? data.titulo : "Desconocido";
    document.getElementById('det-anio').innerText = data.anio ? data.anio : "Desconocido";
    document.getElementById('det-duracion').innerText = data.duracion ? data.duracion + ' min' : "Desconocida";
    document.getElementById('det-pais').innerText = data.pais ? data.pais : "Desconocido";
    document.getElementById('det-genero').innerText = data.genero ? data.genero : "Desconocido";
    document.getElementById('det-direccion').innerText = data.direccion ? data.direccion : "Desconocida";
    document.getElementById('det-sinopsis').innerText = data.sinopsis ? data.sinopsis : "No disponible";

    // --- LÓGICA DE PUNTUACIÓN (CORREGIDA) ---
    const puntuacionElement = document.getElementById('det-puntuacion');
    const nota = parseFloat(data.puntuacion);

    // Usamos !isNaN para que el 0 cuente como nota válida
    if (!isNaN(nota) && nota >= 0) {
        puntuacionElement.innerText = "★ " + nota.toFixed(1) + " / 10";
        puntuacionElement.style.color = "#f5c518";
        puntuacionElement.style.display = "flex"; // Aseguramos que se vea
    } else {
        puntuacionElement.innerText = "Sin votos";
        puntuacionElement.style.color = "#888";
        puntuacionElement.style.display = "flex";
    }

    // Actualizar botones de editar y borrar
    const btnEditar = document.getElementById('btn-editar-detalle');
    if (btnEditar) btnEditar.href = '/peliculas/editar/' + data.id;

    const btnBorrar = document.getElementById('btn-borrar-detalle');
    if (btnBorrar) btnBorrar.href = '/peliculas/borrar/' + data.id;

    const btnUnir = document.getElementById('btn-btn-unirActor-pelicula');
    if (btnUnir) btnUnir.href = '/peliculas/unirActor/' + data.id;

    // --- AQUÍ ESTABA EL FALLO DEL BOTÓN VOTAR ---
    const btnVotar = document.getElementById('btn-btn-puntuar-pelicula');
    if (btnVotar) {
        btnVotar.href = '/opinions/anadir/' + data.id; // Corregido 'opinions' (revisar tu Controller)
        btnVotar.style.display = 'inline-block'; // ¡ESTO FALTABA!
    }

    // Gestión de la imagen
    const imgElement = document.getElementById('det-img');
    const placeholderElement = document.getElementById('det-img-placeholder');

    if (data.imagen && data.imagen !== '') {
        imgElement.src = '/peliculas/uploads/'+ data.imagen;
        imgElement.style.display = 'block';
        placeholderElement.style.display = 'none';
    } else {
        imgElement.style.display = 'none';
        placeholderElement.style.display = 'block';
    }

    // Mostrar contenedores
    document.getElementById('mensaje-vacio').style.display = 'none';
    document.getElementById('contenido-detalle').style.display = 'flex';
    document.getElementById('contenido-listado').style.display = 'flex';
    document.getElementById('contenido-edicion-acciones').style.display = 'flex';

    document.querySelectorAll('.contenido-invisible').forEach(function(el) {
        el.style.display = 'block';
    });

    // Lógica de Actores
    document.querySelectorAll('.lista-actores-item').forEach(function(lista) {
        lista.style.display = 'none';
    });
    const listaActores = document.getElementById('lista-actores-' + data.id);
    if(listaActores) listaActores.style.display = 'flex';

    // Lógica de Opiniones
    document.querySelectorAll('.lista-opiniones-item').forEach(el => {
        el.style.display = 'none';
    });
    const listaOpiniones = document.getElementById('lista-opiniones-' + data.id);
    if (listaOpiniones) listaOpiniones.style.display = 'flex';
}