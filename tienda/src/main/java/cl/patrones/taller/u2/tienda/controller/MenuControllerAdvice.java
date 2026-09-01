package cl.patrones.taller.u2.tienda.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import cl.patrones.taller.u2.catalogo.domain.Categoria;
import cl.patrones.taller.u2.catalogo.service.CategoriaService;
import cl.patrones.taller.u2.tienda.adapter.CategoriaMenuAdapter;
import cl.patrones.taller.u2.tienda.menu.EnlaceMenu;
import cl.patrones.taller.u2.tienda.menu.ItemMenu;

@ControllerAdvice
public class MenuControllerAdvice {

	private final CategoriaService categoriaService;

	public MenuControllerAdvice(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	@ModelAttribute("menu")
	public List<ItemMenu> menu() {
		List<Categoria> categorias = categoriaService.getCategorias();
		List<ItemMenu> menu = new ArrayList<>();
		menu.add(new EnlaceMenu("Inicio", "/"));
		menu.add(new EnlaceMenu("Categorías", "/categoria"));
		menu.addAll(categorias.stream()
				.filter(categoria -> categoria.getPadre() == null)
				.map(categoria -> new CategoriaMenuAdapter(categoria, categorias))
				.map(ItemMenu.class::cast)
				.toList());
		menu.add(new EnlaceMenu("Ubicación", "/ubicacion"));
		menu.add(new EnlaceMenu("Contacto", "/contacto"));
		return menu;
	}
	
}
