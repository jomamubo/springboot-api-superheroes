package com.w2m.springboot.api.superheroes.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.w2m.springboot.api.superheroes.exceptions.ResourceNotFoundException;
import com.w2m.springboot.api.superheroes.model.Superheroe;
import com.w2m.springboot.api.superheroes.repository.SuperRepository;

@Service
@Transactional
public class SuperheroeServiceImpl implements SuperheroeService{

	private SuperRepository superRepository;
	
	public SuperheroeServiceImpl(SuperRepository superRepository) {
		super();
		this.superRepository = superRepository;
	}

	@Override
	public Superheroe createSuperheroe(Superheroe s) {
		return superRepository.save(s);
	}

	@Override
	public Superheroe updateSuperheroe(Superheroe s) {
		
		Superheroe superExistente = superRepository.findById(s.getId())
				.orElseThrow(()->
						new ResourceNotFoundException("No se ha encontrado el superheroe con ID: " + s.getId()));

		superExistente.setNombre(s.getNombre());
		superRepository.save(superExistente);
		
		return superExistente;
	}

	@Override
	public List<Superheroe> getAllSuperheroe() {
		return superRepository.findAll();
	}

	@Override
	public Superheroe getSuperheroeById(long id) {
		Superheroe superExistente = superRepository.findById(id)
				.orElseThrow(()->
						new ResourceNotFoundException("No se ha encontrado el superheroe con ID: " + id));
		
		
		return superExistente;

	}
	
	@Override
	public List<Superheroe> findByNombreContainingIgnoreCase(String nombre) {
		List<Superheroe> superHeroesConNombre = superRepository.findByNombreContainingIgnoreCase(nombre);
		
		return superHeroesConNombre;

	}
	

	@Override
	public Superheroe deleteSuperheroe(Superheroe s) {
		Superheroe superExistente = superRepository.findById(s.getId())
				.orElseThrow(()->
						new ResourceNotFoundException("No se ha encontrado el superheroe con ID: " + s.getId()));
		
		superRepository.deleteById(s.getId());
		
		return superExistente;
	}

}
