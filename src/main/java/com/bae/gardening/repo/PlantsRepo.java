package com.bae.gardening.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bae.gardening.entity.Plants;

public interface PlantsRepo extends JpaRepository<Plants, Integer>{
	
	
}
