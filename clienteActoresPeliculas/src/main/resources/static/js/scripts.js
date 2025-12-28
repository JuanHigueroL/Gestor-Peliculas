/* =========================================
   LÓGICA GLOBAL (SCRIPTS.JS)
   ========================================= */

// 1. FUNCIONALIDAD DEL SIDEBAR IZQUIERDO (BÚSQUEDA). ABRIR Y CERRAR
function toggleSidebar() {
    const sidebarBusqueda = document.getElementById('sidebar'); // Izquierda
    const overlay = document.getElementById('overlay');

    // Si existe la barra de búsqueda, la alternamos junto con el overlay
    if (sidebarBusqueda) {
        sidebarBusqueda.classList.toggle('activo');
        overlay.classList.toggle('activo');
    }
}


// 2. FUNCIONALIDAD DEL SIDEBAR DERECHO (AJUSTES). ABRIR Y CERRAR
function toggleSettingsSidebar() {
    const sidebarAjustes = document.getElementById('sidebar-ajustes'); // Derecha
    const overlay = document.getElementById('overlay');

    // Si existe la barra de ajustes, la alternamos junto con el overlay
    if (sidebarAjustes) {
        sidebarAjustes.classList.toggle('activo');
        overlay.classList.toggle('activo');
    }
}

// 3. CERRAR TOD0 AL HACER CLIC EN EL FONDO OSCURO (OVERLAY)
function closeAllSidebars() {
    const sidebarBusqueda = document.getElementById('sidebar');
    const sidebarAjustes = document.getElementById('sidebar-ajustes');
    const overlay = document.getElementById('overlay');

    if (sidebarBusqueda) sidebarBusqueda.classList.remove('activo');
    if (sidebarAjustes) sidebarAjustes.classList.remove('activo');
    overlay.classList.remove('activo');
}

// 4. MODO OSCURO

// Al cargar la página, se revisa si hay una preferencia guardada
    const isDarkMode = localStorage.getItem('theme') === 'dark';
    // Aplicar el modo oscuro si estaba guardada
    if (isDarkMode) {
        document.body.classList.add('dark-mode');
        updateButtonText(true);
    }

// Función para alternar el modo oscuro
function toggleDarkMode() {
    const body = document.body;
    // Alternar modo oscuro
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

// Función para actualizar el texto del botón y el icono del modo oscuro y modo claro
function updateButtonText(isDark) {
    const texto = document.getElementById('textoModo');
    //Si está en modo oscuro, mostrar "Modo Claro", si no, "Modo Oscuro"
    if(texto) texto.innerText = isDark ? "Modo Claro" : "Modo Oscuro";

    const luna = document.getElementById('icono-luna');
    const sol = document.getElementById('icono-sol');

    if (isDark) {
        //si está en modo oscuro, mostrar el icono del sol y ocultar el de la luna
        luna.style.display = 'none';
        sol.style.display = 'block';
    } else {
        //si está en modo claro, mostrar el icono de la luna y ocultar el del sol
        luna.style.display = 'block';
        sol.style.display = 'none';
    }
}
