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

    // Lógica de Fecha y Edad
    if (data.fecha) {
        const dateObj = new Date(data.fecha);
        document.getElementById('det-fecha').innerText = dateObj.toLocaleDateString();

        const hoy = new Date();
        let edad = hoy.getFullYear() - dateObj.getFullYear();
        const m = hoy.getMonth() - dateObj.getMonth();
        if (m < 0 || (m === 0 && hoy.getDate() < dateObj.getDate())) {
            edad--;
        }
        document.getElementById('det-edad').innerText = edad + " años";
    } else {
        document.getElementById('det-fecha').innerText = "Desconocida";
        document.getElementById('det-edad').innerText = "";
    }


    // Mostrar contenedor y ocultar mensaje vacío
    document.getElementById('mensaje-vacio').style.display = 'none';
    document.getElementById('contenido-detalle').style.display = 'flex';

    // Scroll
    document.getElementById('detalle-destacado').scrollIntoView({ behavior: 'smooth' });
}