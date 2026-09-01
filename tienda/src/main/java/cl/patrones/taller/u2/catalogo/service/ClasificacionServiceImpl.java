package cl.patrones.taller.u2.catalogo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cl.patrones.taller.u2.catalogo.domain.Clasificacion;
import cl.patrones.taller.u2.catalogo.repository.ClasificacionRepository;

@Service
public class ClasificacionServiceImpl implements ClasificacionService {

	private final ClasificacionRepository repository;

	public ClasificacionServiceImpl(ClasificacionRepository repository) {
		this.repository = repository;
	}

	@Override
	public Optional<Clasificacion> getClasificacionPorSku(String sku) {
		return repository.findFirstBySku(sku);
	}

	@Override
	public List<Clasificacion> getClasificacionesPorCategoriaId(Long categoriaId) {
		return repository.findByCategoriaId(categoriaId);
	}
}