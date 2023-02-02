package com.w2m.springboot.api.superheroes.service;

import java.util.List;

import com.w2m.springboot.api.superheroes.model.Superheroe;

public interface SuperheroeService {
	Superheroe createSuperheroe(Superheroe s);
	Superheroe updateSuperheroe(Superheroe s);
	List<Superheroe> getAllSuperheroe();
	Superheroe getSuperheroeById(long id);
	Superheroe deleteSuperheroe(Superheroe s);
	List<Superheroe> findByNombreContainingIgnoreCase(String nombre);
}
