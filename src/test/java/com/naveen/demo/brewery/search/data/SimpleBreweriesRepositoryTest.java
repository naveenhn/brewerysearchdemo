package com.naveen.demo.brewery.search.data;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.naveen.demo.brewery.search.model.Brewery;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SimpleBreweriesRepositoryTest {
	
	@Autowired
	private BreweryRepository simpleBreweriesRepository;
	
	private ResponseEntity<Brewery[]> responseEntity ;
	
	@MockBean
	private RestTemplate restTemplate;
	

	@Before
	public void setUp() throws Exception {
		
		Brewery brewery = Brewery.builder()
				.id(1)
				.breweryType("micro")
				.name("abc")
				.city("Phx")
				.build();
		Brewery brewer1 = Brewery.builder()
				.id(2)
				.breweryType("micro")
				.name("def")
				.city("Phx")
				.build();
		
		Brewery[] breweries = new Brewery[]{brewery,brewer1};
		responseEntity = new ResponseEntity<Brewery[]>( breweries, HttpStatus.OK);
		
		
	}

	@Test
	public void testGetBreweries() {
		given(restTemplate.getForEntity(anyString(), eq(Brewery[].class))).willReturn(responseEntity);
		List<Brewery> breweries = simpleBreweriesRepository.getBreweries("1", "20");
		assertEquals(2, breweries.size());
		assertEquals("abc", breweries.get(0).getName());
		
	}
	
	
	@Test
	public void testSearch() {
		given(restTemplate.getForEntity(anyString(), eq(Brewery[].class))).willReturn(responseEntity);
		List<Brewery> breweries = simpleBreweriesRepository.search("a", "1", "20");
		assertEquals(2, breweries.size());
		assertEquals("abc", breweries.get(0).getName());
	}

}
