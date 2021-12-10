package com.bae.gardening.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.gardening.entity.Plants;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // sets up the mockmvc object
@Sql(scripts = {"classpath:plant-schema.sql", "classpath:plant-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class PlantsServiceTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void testCreate() throws Exception {
		Plants testPlant = new Plants("Daffodil",  "green", "October", "sun", "yellow");
		String testPlantAsJSON = this.mapper.writeValueAsString(testPlant);
		
		RequestBuilder req = post("/createPlant").contentType(MediaType.APPLICATION_JSON)
				.content(testPlantAsJSON);
		
		Plants testCreatedPlant = new Plants(2, "Daffodil",  "green", "October", "sun", "yellow");
		String testCreatedPlantAsJSON = this.mapper.writeValueAsString(testCreatedPlant);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedPlantAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	
	}
	
	@Test
	void testGetAll() throws Exception {
		Plants testPlant = new Plants(1, "Daffodil",  "green", "October", "sun", "yellow");
		String testPlantAsJSON = this.mapper.writeValueAsString(List.of(testPlant));
		
		RequestBuilder req = get("/getAll");
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testPlantAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getPlantById() throws Exception {
		Plants testPlant = new Plants("Daffodil",  "green", "October", "sun", "yellow");
		String testPlantAsJSON = this.mapper.writeValueAsString(testPlant);
		
		RequestBuilder req = get("/getPlantById");
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testPlantAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
		void testGetPlantByName() throws Exception {
		Plants testPlant = new Plants(1, "Daffodil",  "green", "October", "sun", "yellow");
		String testPlantAsJSON = this.mapper.writeValueAsString(testPlant);
		
		RequestBuilder req = get("/testGetPlantByName/Daffodil");
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testPlantAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
		void testUpdatePlant() throws Exception {
		Plants testPlant = new Plants(1, "Daffodil",  "green", "October", "sun", "orange");
		String testPlantAsJSON = this.mapper.writeValueAsString(testPlant);
		
		RequestBuilder req = put("/updatePlant/1").contentType(MediaType.APPLICATION_JSON)
				.content(testPlantAsJSON);
		
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testPlantAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	
	@Test
		void testDeletePlant() throws Exception  {
		RequestBuilder req = delete("/deletePlant/1").contentType(MediaType.APPLICATION_JSON);
		ResultMatcher checkStatusGet = status().isNoContent();
		
		this.mvc.perform(req).andExpect(checkStatusGet);
	}
		
		
	}


