package cl.patrones.taller.u2.tienda.adapter;

import java.util.List;

import cl.patrones.taller.u2.catalogo.domain.Categoria;
import cl.patrones.taller.u2.tienda.menu.ItemMenu;
import cl.patrones.taller.u2.tienda.menu.util.Slugger;

public class CategoriaMenuAdapter implements ItemMenu {

    private final Categoria categoria;
    private final List<Categoria> categorias;

    public CategoriaMenuAdapter(Categoria categoria, List<Categoria> categorias) {
        this.categoria = categoria;
        this.categorias = categorias;
    }

    @Override
    public String getTexto() {
        return categoria.getNombre();
    }

    @Override
    public String getSlug() {
        return Slugger.toSlug(categoria.getNombre());
    }

    @Override
    public String getEnlace() {
        return "/categoria/" + categoria.getId() + "/" + getSlug();
    }

    @Override
    public boolean tieneHijos() {
        return !getHijos().isEmpty();
    }

    @Override
    public List<CategoriaMenuAdapter> getHijos() {
        return categorias.stream()
                .filter(categoriaHija -> categoriaHija.getPadre() != null)
                .filter(categoriaHija -> categoria.getId().equals(categoriaHija.getPadre().getId()))
                .map(categoriaHija -> new CategoriaMenuAdapter(categoriaHija, categorias))
                .toList();
    }
}