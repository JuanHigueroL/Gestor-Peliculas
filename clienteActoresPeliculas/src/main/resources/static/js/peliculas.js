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
    document.getElementById('det-sinopsis').innerText = data.sinopsis ? data.sinopsis : "No disponible";

    // --- NUEVO CÓDIGO PARA LA PUNTUACIÓN ---
    const puntuacionElement = document.getElementById('det-puntuacion');
    const nota = parseFloat(data.puntuacion); // Convertimos el texto a número decimal

    if (nota && nota >= 0) {
        // Si hay nota: Ponemos Estrella + Nota con 1 decimal + /10
        puntuacionElement.innerText = "★ " + nota.toFixed(1) + " / 10";
        puntuacionElement.style.color = "#f5c518"; // Amarillo dorado
    } else {
        // Si es menor a 0 o null
        puntuacionElement.innerText = "Sin votos";
        puntuacionElement.style.color = "#888"; // Gris
    }

    // Actualizar botones de editar y borrar
    const btnEditar = document.getElementById('btn-editar-detalle');
    if (btnEditar) {
        btnEditar.href = '/peliculas/editar/' + data.id;
    }

    const btnBorrar = document.getElementById('btn-borrar-detalle');
    if (btnBorrar) {
        btnBorrar.href = '/peliculas/borrar/' + data.id;
    }

    // OJO: He corregido el ID aquí para quitarle los espacios
    const btnUnir = document.getElementById('btn-btn-unirActor-pelicula');
    if (btnUnir) {
        // Asumiendo que esta es la ruta correcta
        btnUnir.href = '/peliculas/unirActor/' + data.id;
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

    // Mostrar contenedor y ocultar mensaje vacío
    document.getElementById('mensaje-vacio').style.display = 'none';
    document.getElementById('contenido-detalle').style.display = 'flex';
    document.getElementById('contenido-listado').style.display = 'flex';
    document.getElementById('contenido-edicion-acciones').style.display = 'flex';
    document.querySelectorAll('.contenido-invisible').forEach(function(el) {
        el.style.display = 'block';
    });

    // Scroll suave hacia el detalle
    //document.getElementById('contenido-detalle').scrollIntoView({ behavior: 'smooth' });

    // --- LÓGICA DE LA LISTA DE ACTORES ---
    // Oculta todas las listas de actores que haya en pantalla
    const todasLasListas = document.querySelectorAll('.lista-actores-item');
    todasLasListas.forEach(function(lista) {
        lista.style.display = 'none';
    });

    // Muestra la lista de actores correspondiente a la película seleccionada
    document.getElementById('lista-actores-' + data.id).style.display = 'flex';

    // (Esto va dentro de tu peliculas.js, donde gestionas los actores)

    //Ocultar todas las listas de opiniones
    document.querySelectorAll('.lista-opiniones-item').forEach(el => {
        el.style.display = 'none';
    });

    //Mostrar la lista de opiniones de la película seleccionada
    const listaOpiniones = document.getElementById('lista-opiniones-' + data.id);

    if (listaOpiniones) {
        listaOpiniones.style.display = 'flex';
    }

}
