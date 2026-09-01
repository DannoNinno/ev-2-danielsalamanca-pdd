package cl.patrones.taller.u2.tienda.service;

import java.util.List;

import cl.patrones.taller.u2.catalogo.domain.Aviso;

public interface AvisoService {

	List<Aviso> getAvisos();
	List<Aviso> getAvisosPorCategoriaId(Long categoriaId);
}