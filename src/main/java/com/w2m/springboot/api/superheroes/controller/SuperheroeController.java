package com.w2m.springboot.api.superheroes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.w2m.springboot.api.superheroes.annotation.Cronometrado;
import com.w2m.springboot.api.superheroes.model.Superheroe;
import com.w2m.springboot.api.superheroes.service.SuperheroeService;

@RestController
@RequestMapping("/api")
public class SuperheroeController {

	@Autowired
	private SuperheroeService superService;
	
	@GetMapping("/listar")
	@Cronometrado
	public ResponseEntity<List<Superheroe>> listarSuperheroes(@RequestParam(name="nombre") Optional<String> nombre) {
		
		if(nombre.isPresent())
			return ResponseEntity.ok().body(superService.findByNombreContainingIgnoreCase(nombre.get()));
		else
			return ResponseEntity.ok().body(superService.getAllSuperheroe());
	}
	
	@GetMapping("/listar/{id}")
	@Cronometrado
	public ResponseEntity<Superheroe> listarSuperheroesPorId(@PathVariable("id") long id) {
		return ResponseEntity.ok().body(superService.getSuperheroeById(id));
	}
	
	@PostMapping("/superheroe")
	@Cronometrado
	public ResponseEntity<Superheroe> createSuperheroe(@RequestBody Superheroe superheroe) {
		return ResponseEntity.ok().body(superService.createSuperheroe(superheroe));
	}
	
	
	@PutMapping("/superheroe")
	@Cronometrado
	public ResponseEntity<Superheroe> updateSuperheroe(@RequestBody Superheroe superNew) {
		return ResponseEntity.ok().body(superService.updateSuperheroe(superNew));	
	}
	
	
	@DeleteMapping("/superheroe")
	@Cronometrado
	public ResponseEntity<Superheroe> deleteSuperheroe(@RequestBody Superheroe superDel) {
		return ResponseEntity.ok().body(superService.deleteSuperheroe(superDel));
	}
	
}
