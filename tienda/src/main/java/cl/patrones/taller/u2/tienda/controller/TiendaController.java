package cl.patrones.taller.u2.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.patrones.taller.u2.catalogo.domain.Categoria;
import cl.patrones.taller.u2.catalogo.service.CategoriaService;
import cl.patrones.taller.u2.tienda.service.AvisoService;

@Controller
public class TiendaController {

	private final AvisoService avisoService;
	private final CategoriaService categoriaService;

	public TiendaController(
			AvisoService avisoService,
			CategoriaService categoriaService) {
		this.avisoService = avisoService;
		this.categoriaService = categoriaService;
	}
	
	@GetMapping("/")
	public String inicio(Model model) {
		model.addAttribute("avisos", avisoService.getAvisos());
		return "inicio";
	}
		
	@GetMapping("/categoria/{categoriaId}/{slug}")
	public String categoria(
			@PathVariable(name = "categoriaId") Long categoriaId,
			@PathVariable(name = "slug") String slug,
			Model model
	) {
		Categoria categoria = categoriaService.getCategoriaPorId(categoriaId)
				.orElseThrow();

		model.addAttribute("avisos", avisoService.getAvisosPorCategoriaId(categoriaId));
		model.addAttribute("categoria", categoria);
		return "categoria";
	}
	
	@GetMapping("/ingresar")
	public String login() {
		return "login";
	}
	
	@GetMapping("/ubicacion")
	public String ubicacion() {return "ubicacion";}
	
	@GetMapping("/contacto")
	public String contacto() {return "contacto";}
	
}
