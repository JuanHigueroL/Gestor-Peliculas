/* =========================================
   LÓGICA GLOBAL (SCRIPTS.JS)
   ========================================= */

// 1. FUNCIONALIDAD DEL SIDEBAR IZQUIERDO (BÚSQUEDA)
function toggleSidebar() {
    const sidebarBusqueda = document.getElementById('sidebar'); // Izquierda
    const sidebarAjustes = document.getElementById('sidebar-ajustes'); // Derecha
    const overlay = document.getElementById('overlay');

    // Comprobar si el sidebar de AJUSTES (Derecha) está abierto
    if (sidebarAjustes && sidebarAjustes.classList.contains('activo')) {
        // Si está abierto, lo cerramos
        sidebarAjustes.classList.remove('activo');
        // NO tocamos el overlay (toggle), porque queremos que siga oscuro para la búsqueda
    } else {
        // Si no había nada abierto, entonces sí alternamos el fondo oscuro
        if (overlay) overlay.classList.toggle('activo');
    }

    // Finalmente abrimos/cerramos la búsqueda
    if (sidebarBusqueda) {
        sidebarBusqueda.classList.toggle('activo');
    }
}


// 2. FUNCIONALIDAD DEL SIDEBAR DERECHO (AJUSTES)
function toggleSettingsSidebar() {
    const sidebarBusqueda = document.getElementById('sidebar'); // Izquierda
    const sidebarAjustes = document.getElementById('sidebar-ajustes'); // Derecha
    const overlay = document.getElementById('overlay');

    // Comprobar si el sidebar de BÚSQUEDA (Izquierda) está abierto
    if (sidebarBusqueda && sidebarBusqueda.classList.contains('activo')) {
        // Si está abierto, lo cerramos
        sidebarBusqueda.classList.remove('activo');
        // NO tocamos el overlay, queremos que siga oscuro para los ajustes
    } else {
        // Si no había nada abierto, alternamos el fondo oscuro
        if (overlay) overlay.classList.toggle('activo');
    }

    // Finalmente abrimos/cerramos los ajustes
    if (sidebarAjustes) {
        sidebarAjustes.classList.toggle('activo');
    }
}

// 3. CERRAR TOD0 AL HACER CLIC EN EL FONDO OSCURO (OVERLAY)
// Asegúrate de tener esto para que al clicar fuera se cierre cualquiera de los dos
function closeAllSidebars() {
    const sidebarBusqueda = document.getElementById('sidebar');
    const sidebarAjustes = document.getElementById('sidebar-ajustes');
    const overlay = document.getElementById('overlay');

    if (sidebarBusqueda) sidebarBusqueda.classList.remove('activo');
    if (sidebarAjustes) sidebarAjustes.classList.remove('activo');
    if (overlay) overlay.classList.remove('activo');
}

//3. MODO OSCURO

// Al cargar la página, revisamos si hay una preferencia guardada
document.addEventListener('DOMContentLoaded', () => {
    const isDarkMode = localStorage.getItem('theme') === 'dark';

    // Aplicar la clase si estaba guardada
    if (isDarkMode) {
        document.body.classList.add('dark-mode');
        updateButtonText(true);
    }
});

function toggleDarkMode(event) {
    if(event) event.preventDefault(); // Evita que el enlace recargue la página

    const body = document.body;
    body.classList.toggle('dark-mode');

    // Guardar preferencia
    if (body.classList.contains('dark-mode')) {
        localStorage.setItem('theme', 'dark');
        updateButtonText(true);
    } else {
        localStorage.setItem('theme', 'light');
        updateButtonText(false);
    }
}

function updateButtonText(isDark) {
    const texto = document.getElementById('textoModo');
    // Opcional: Cambiar el texto según el modo
    if(texto) texto.innerText = isDark ? "Modo Claro" : "Modo Oscuro";

    const luna = document.getElementById('icono-luna');
    const sol = document.getElementById('icono-sol');

    if (isDark) {
        luna.style.display = 'none';
        sol.style.display = 'block';
    } else {
        luna.style.display = 'block';
        sol.style.display = 'none';
    }
}
