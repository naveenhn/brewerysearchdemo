/**
 * 
 */
package com.naveen.demo.brewery.search.rest;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.naveen.demo.brewery.search.data.BreweryRepository;
import com.naveen.demo.brewery.search.model.Brewery;

/**
 * @author naveen
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BreweryRestEndpointTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private BreweryRepository breweryRepository;
	
	@Autowired
	private ObjectMapper mapper;
	
	private List<Brewery> breweries;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	
		Brewery brewery = Brewery.builder()
				.id(1)
				.breweryType("micro")
				.name("abc")
				.city("Phx")
				.build();
		
		breweries = Arrays.asList(brewery);
		
	}

	/**
	 * Test method for {@link com.naveen.demo.brewery.search.rest.BreweryRestEndpoint#breweries(java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public void testBreweries() throws Exception {
		given(breweryRepository.getBreweries(anyString(), anyString())).willReturn(breweries);	
		this.mvc.perform(get("/breweries"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(content().json(mapper.writeValueAsString(breweries), false));
	}
	
	@Test
	public void testBreweries_Negative() throws Exception {
		given(breweryRepository.getBreweries(anyString(), anyString())).willThrow(RuntimeException.class);	
		this.mvc.perform(get("/breweries"))
		.andExpect(status().isInternalServerError());
		
	}

	/**
	 * Test method for {@link com.naveen.demo.brewery.search.rest.BreweryRestEndpoint#search(java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws JsonProcessingException 
	 */
	@Test
	public void testSearch() throws JsonProcessingException, Exception {
		given(breweryRepository.search(anyString(), anyString(), anyString())).willReturn(breweries);	
		this.mvc.perform(get("/breweries/search?query=q"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(content().json(mapper.writeValueAsString(breweries), false));
	}

}
