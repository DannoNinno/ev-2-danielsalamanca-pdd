package cl.patrones.taller.u2.tienda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cl.patrones.taller.u2.bodegaje.service.BodegajeService;
import cl.patrones.taller.u2.catalogo.domain.Aviso;
import cl.patrones.taller.u2.catalogo.service.ClasificacionService;
import cl.patrones.taller.u2.tienda.adapter.ProductoAvisoAdapter;

@Service
public class AvisoServiceImpl implements AvisoService {

	private final BodegajeService bodegajeService;
	private final ClasificacionService clasificacionService;

	public AvisoServiceImpl(
			BodegajeService bodegajeService,
			ClasificacionService clasificacionService) {
		this.bodegajeService = bodegajeService;
		this.clasificacionService = clasificacionService;
	}

	@Override
	public List<Aviso> getAvisos() {
		return bodegajeService.getProductos().stream()
				.map(producto -> clasificacionService.getClasificacionPorSku(producto.getSku())
						.map(clasificacion -> new ProductoAvisoAdapter(
								producto, clasificacion.getCategoria()).toAviso()))
				.flatMap(Optional::stream)
				.toList();
	}

	@Override
	public List<Aviso> getAvisosPorCategoriaId(Long categoriaId) {
		return clasificacionService.getClasificacionesPorCategoriaId(categoriaId).stream()
				.map(clasificacion -> bodegajeService.getProductoBySku(clasificacion.getSku())
						.map(producto -> new ProductoAvisoAdapter(
								producto, clasificacion.getCategoria()).toAviso()))
				.flatMap(Optional::stream)
				.toList();
	}
}