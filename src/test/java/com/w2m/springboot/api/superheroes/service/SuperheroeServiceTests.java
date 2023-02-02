package com.w2m.springboot.api.superheroes.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;

import com.w2m.springboot.api.superheroes.exceptions.ResourceNotFoundException;
import com.w2m.springboot.api.superheroes.model.Superheroe;
import com.w2m.springboot.api.superheroes.repository.SuperRepository;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SuperheroeServiceTests {
	
	@Mock
	private SuperRepository superRepositorio;
	
	@InjectMocks
	private SuperheroeServiceImpl superService;
	
	private Superheroe superHero;
	
	@BeforeEach
	public void setup() {
		superHero = new Superheroe("Supertest");
	}
	
	@DisplayName("JUnit test para metodo getAllSuperheroe")
	@Test
	public void givenSuperheroesList_whenGetAllSuperheroe_thenReturnSuperheroesList(){
		Superheroe super2 = new Superheroe("Supertest2");
		
		//given
		given(superRepositorio.findAll()).willReturn(List.of(superHero, super2));
		
		//when
		List<Superheroe> listaSuperheroes = superService.getAllSuperheroe();
		
		//then
		assertNotNull(listaSuperheroes);			// La lista no debe ser nula
		assertEquals(listaSuperheroes.size(), 2);  	// Debemos tener dos superheroes
		
	}
	
	@DisplayName("JUnit test para metodo getSuperheroeById")
	@Test
	public void givenSuperheroeId_whenGetSuperheroeById_thenReturnSuperheroeObject() {
		// given
		given(superRepositorio.findById(0L)).willReturn(Optional.of(superHero));
		
		// when
		Superheroe superGuardado = superService.getSuperheroeById(superHero.getId());
		
		// then
		assertNotNull(superGuardado);
	}
	
	@DisplayName("JUnit test para metodo createSuperheroe")
	@Test
	public void givenSuperheroeObject_whenCreateSuperheroe_thenReturnSuperheroeObject() {
		// given
		given(superRepositorio.save(superHero)).willReturn(superHero);
		
		// when
		Superheroe guardado = superService.createSuperheroe(superHero);
		
		// then
		assertNotNull(guardado);
	}
	
	@DisplayName("JUnit test para metodo updateSuperheroe")
	@Test
	public void givenSuperheroeObject_whenUpdateSuperheroe_thenReturnUpdatedSuperheroe() {
		// given
		given(superRepositorio.findById(0L)).willReturn(Optional.of(superHero));
		superHero.setNombre("Supertest MOD");
		
		//when
		superService.updateSuperheroe(superHero);
		
		// then
		assertEquals(superHero.getNombre(), "Supertest MOD");
	}
	
	@DisplayName("JUnit test para metodo deleteSuperheroe, borrar Superheroe que no existe")
	@Test
	public void givenSuperheroeObject_whenDeleteSuperheroeNotExists_thenReturnException() {
		
		//given
		Superheroe nuevo = new Superheroe("Superborrado");
		nuevo.setId(20);
		
		//when, then
		// Vamos a borrar un Superheroe que no hemos guardado
		Exception ex = assertThrows(ResourceNotFoundException.class, () -> {
			superService.deleteSuperheroe(nuevo);
	    });
		
		// System.out.println(ex.getMessage());
	}
	
	@DisplayName("JUnit test para metodo deleteSuperheroe, borrar Superheroe")
	@Test
	public void givenSuperheroeObject_whenDeleteSuperheroe_thenReturnDeletedSuperheroe() {
		
		// Creamos 3 superheroes
		Superheroe super1 = new Superheroe("Super1");
		Superheroe super2 = new Superheroe("Super2");
		Superheroe super3 = new Superheroe("Super3");
		
		superService.createSuperheroe(super1);
		superService.createSuperheroe(super2);
		superService.createSuperheroe(super3);
		
		System.out.println(superService.getAllSuperheroe());
		
		// Borramos el segundo superheroe
		Superheroe borrado = superService.deleteSuperheroe(super2);
		
		List<Superheroe> listado = superService.getAllSuperheroe();
		
		assertEquals(listado.size(), 2);
		
		
	}
	
}
