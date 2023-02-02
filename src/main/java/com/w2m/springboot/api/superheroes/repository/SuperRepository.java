package com.w2m.springboot.api.superheroes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.w2m.springboot.api.superheroes.model.Superheroe;

@Repository
public interface SuperRepository extends JpaRepository<Superheroe, Long>{
	List<Superheroe> findByNombreContainingIgnoreCase(String nombre);
	List<Superheroe> findByNombre(String nombre);
}
