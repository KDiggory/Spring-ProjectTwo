package com.bae.gardening.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.bae.gardening.entity.Plants;
import com.bae.gardening.exceptions.MonthNotFoundException;
import com.bae.gardening.exceptions.PlantingPositionNotFoundException;
import com.bae.gardening.exceptions.PlantsNotFoundException;
import com.bae.gardening.service.PlantsService;



@CrossOrigin
@RestController
public class PlantsController {
	
	private PlantsService service;

	@Autowired 
	public PlantsController(PlantsService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/createPlant")
	public ResponseEntity<Plants> createPlant(@RequestBody Plants plant) {
		Plants created = this.service.createPlant(plant);
		ResponseEntity<Plants> response = new ResponseEntity<Plants>(plant, HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping("getAll")
	public ResponseEntity<List<Plants>> getAllPlants(){
		return ResponseEntity.ok(this.service.getAllPlants());
	}
	
	@GetMapping("/getPlantById/{id}")
	public ResponseEntity<Plants> getPlantById(@PathVariable Integer id) throws PlantsNotFoundException {
		return ResponseEntity.ok(this.service.getByID(id));
	}
	
	@GetMapping("/getPlantByName/{name}")
	public ResponseEntity<Plants> getPlantByName(@PathVariable String name) throws PlantingPositionNotFoundException {
		return ResponseEntity.ok(this.service.getPlantByName(name));
	}
	@GetMapping("/getPlantByMonth/{name}")
	public ResponseEntity<List<Plants>> getPlantByMonth(@PathVariable String month) throws MonthNotFoundException {
		return ResponseEntity.ok(this.service.getByPlantingMonth(month));
	}
	@GetMapping("/getByPlantingPosition/{name}")
	public ResponseEntity<List<Plants>> getByPlantingPosition(@PathVariable String colour) throws PlantingPositionNotFoundException {
		return ResponseEntity.ok(this.service.getByPlantingPosition(colour));
	}
	
	@PutMapping("/updatePlant/{id}") 
	public ResponseEntity<Plants> updatePlant(@PathVariable Integer id, @RequestBody Plants newPlant) {
		Plants body = this.service.updatePlant(newPlant, id);
		ResponseEntity<Plants> response = new ResponseEntity<Plants>(body, HttpStatus.ACCEPTED);
		return response;
	}
	
	@PutMapping("/updatePlantByName/{name}") 
	public ResponseEntity<Plants> updatePlantByName(@PathVariable String name, @RequestBody Plants newPlant) {
		Plants body = this.service.updatePlantByName(name, newPlant);
		ResponseEntity<Plants> response = new ResponseEntity<Plants>(body, HttpStatus.ACCEPTED);
		return response;
	}
	@DeleteMapping("/deletePlant/{id}") 
	public ResponseEntity<Plants> deletePlant(@PathVariable Integer id) {
		this.service.deletePlant(id);
		ResponseEntity<Plants> response = new ResponseEntity<Plants>(HttpStatus.NO_CONTENT);
		return response; 
	}
	
		
	}