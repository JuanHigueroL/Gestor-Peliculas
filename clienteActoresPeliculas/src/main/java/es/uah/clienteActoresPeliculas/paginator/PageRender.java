package es.uah.clienteActoresPeliculas.paginator;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageRender<T> {

    private String url;
    private Page<T> page;

    private int totalPaginas;
    private int numElementosPorPagina;
    private int paginaActual;
    private List<PageItem> paginas;

    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.paginas = new ArrayList<>();

        numElementosPorPagina = 5;
        totalPaginas = page.getTotalPages();
        paginaActual = page.getNumber() + 1; // Spring empieza en 0

        int desde, hasta;
        if (totalPaginas <= numElementosPorPagina) {
            // Mostrar todas las páginas
            desde = 1;
            hasta = totalPaginas;
        } else if (paginaActual <= numElementosPorPagina / 2) {
            // Estás al principio
            desde = 1;
            hasta = numElementosPorPagina;
        } else if (paginaActual >= totalPaginas - numElementosPorPagina / 2) {
            // Estás al final
            desde = totalPaginas - numElementosPorPagina + 1;
            hasta = totalPaginas;
        } else {
            // Estás en una zona intermedia
            desde = paginaActual - numElementosPorPagina / 2;
            hasta = desde + numElementosPorPagina - 1;
        }

        for (int i = desde; i <= hasta; i++) {
            paginas.add(new PageItem(i, i == paginaActual));
        }
    }

    public String getUrl() {
        return url;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public int getPaginaActual() {
        return paginaActual;
    }

    public List<PageItem> getPaginas() {
        return paginas;
    }

    public boolean isFirst() {
        return page.isFirst();
    }

    public boolean isLast() {
        return page.isLast();
    }

    public boolean isHasNext() {
        return page.hasNext();
    }

    public boolean isHasPrevious() {
        return page.hasPrevious();
    }
}
