/**
 * 
 */
package com.naveen.demo.brewery.search.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.naveen.demo.brewery.search.model.Brewery;

import lombok.extern.slf4j.Slf4j;

/**
 * @author naveen
 *
 */
@Component
@Slf4j
public class SimpleBreweriesRepository implements BreweryRepository {
	
	@Value("${app.openbrewerydb.breweries.url}")
	private String openBreweryURL;
	@Value("${app.openbrewerydb.search.url}")
	private String openBrewerySearchURL;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	@Cacheable("breweries")
	public List<Brewery> getBreweries(String page, String pageCount) {
		log.info("Getting Breweries info from OpenBreweryDB API");

		List<Brewery> breweries = new ArrayList<>();
		UriComponents uriComponents = UriComponentsBuilder.fromUriString(openBreweryURL).buildAndExpand(page, pageCount,
				"name");
		ResponseEntity<Brewery[]> response = restTemplate.getForEntity(uriComponents.toUriString(), Brewery[].class);

		if (response.getStatusCodeValue() == 200) {
			breweries = Arrays.asList(response.getBody());
			// simple sort on name
			// breweries.sort(Comparator.comparing(Brewery::getName));
		}

		return breweries;
	}

	@Override
	@Cacheable("searchedbreweries")
	public List<Brewery> search(String query, String page, String pageCount) {
		log.info("Search using OpenBreweryDB API");
		List<Brewery> breweries = new ArrayList<>();
		UriComponents uriComponents = UriComponentsBuilder.fromUriString(openBrewerySearchURL).buildAndExpand(query,
				page, pageCount);
		ResponseEntity<Brewery[]> response = restTemplate.getForEntity(uriComponents.toUriString(), Brewery[].class);

		if (response.getStatusCodeValue() == 200) {
			breweries = Arrays.asList(response.getBody());
			// simple sort on name
			breweries.sort(Comparator.comparing(Brewery::getName));
		}

		return breweries;
	}

}
