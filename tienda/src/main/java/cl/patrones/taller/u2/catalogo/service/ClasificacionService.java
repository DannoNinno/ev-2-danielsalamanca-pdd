package cl.patrones.taller.u2.catalogo.service;

import java.util.List;
import java.util.Optional;

import cl.patrones.taller.u2.catalogo.domain.Clasificacion;

public interface ClasificacionService {

	Optional<Clasificacion> getClasificacionPorSku(String sku);
	List<Clasificacion> getClasificacionesPorCategoriaId(Long categoriaId);
}