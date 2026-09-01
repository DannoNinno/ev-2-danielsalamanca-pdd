package cl.patrones.taller.u2.tienda.adapter;

import cl.patrones.taller.u2.bodegaje.domain.Producto;
import cl.patrones.taller.u2.bodegaje.domain.Stock;
import cl.patrones.taller.u2.catalogo.domain.Aviso;
import cl.patrones.taller.u2.catalogo.domain.Categoria;

public class ProductoAvisoAdapter {

    private final Producto producto;
    private final Categoria categoria;

    public ProductoAvisoAdapter(Producto producto, Categoria categoria) {
        this.producto = producto;
        this.categoria = categoria;
    }

    public Aviso toAviso() {
        int stock = producto.getStocks().stream()
                .mapToInt(Stock::getCantidad)
                .sum();

        return new Aviso(
                producto.getId(),
                producto.getSku(),
                producto.getNombre(),
            producto.getCosto() * 130 / 100,
                producto.getImagen(),
                stock,
                categoria);
    }
}