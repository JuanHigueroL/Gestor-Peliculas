/* =========================================
   LÓGICA ESPECÍFICA DE ACTORES
   ========================================= */

function mostrarDetalles(elemento) {
    // 1. Obtener datos
    const id = elemento.getAttribute('data-id');
    const nombre = elemento.getAttribute('data-nombre');
    const fechaRaw = elemento.getAttribute('data-fecha');
    const pais = elemento.getAttribute('data-pais');

    // 2. Gestionar visibilidad
    document.getElementById('mensaje-vacio').style.display = 'none';
    const contenido = document.getElementById('contenido-detalle');
    contenido.style.display = 'flex';

    // 3. Rellenar textos básicos
    document.getElementById('det-id').innerText = id;
    document.getElementById('det-nombre').innerText = nombre;
    document.getElementById('det-pais').innerText = pais;

    // 4. ACTUALIZAR LOS BOTONES CON EL ID DEL ACTOR
    const btnEditar = document.getElementById('btn-editar-actor');
    const btnBorrar = document.getElementById('btn-borrar-actor');
    const btnUnirPelicula = document.getElementById('btn btn-unirPelicula-actor')

    // Asumiendo rutas: /actores/editar/ID y /actores/borrar/ID
    btnEditar.href = '/actores/editar/' + id;
    btnBorrar.href = '/actores/borrar/' + id;
    btnUnirPelicula.href ='/actores/unirPelicula/'+ id;

    // 5. Lógica de Fecha y Edad
    if (fechaRaw) {
        const dateObj = new Date(fechaRaw);
        const fechaFormateada = dateObj.toLocaleDateString();
        document.getElementById('det-fecha').innerText = fechaFormateada;

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

    // 6. Scroll
    document.getElementById('detalle-destacado').scrollIntoView({ behavior: 'smooth' });
}