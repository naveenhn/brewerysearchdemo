/**
 * 
 */
package com.naveen.demo.brewery.search.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.naveen.demo.brewery.search.data.BreweryRepository;
import com.naveen.demo.brewery.search.model.Brewery;

/**
 * @author naveen
 *
 */
@RestController
public class BreweryRestEndpoint {
	
	@Autowired
	private BreweryRepository breweryRepo;

	@GetMapping(value= "/breweries")
	public List<Brewery> breweries(@RequestParam(name="page", defaultValue="1") String page, @RequestParam(name="count", defaultValue="10")String count ) throws Exception {
		
		return breweryRepo.getBreweries(page, count);
	}
	
	
	@GetMapping(value= "/breweries/search")
	public List<Brewery> search(@RequestParam(name="query") String query, @RequestParam(name="page", defaultValue="1") String page, @RequestParam(name="count", defaultValue="10")String count ) throws Exception {
		
		return breweryRepo.search(query, page, count);
	}
	
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Exception occurred fetching brewery details")
	@ExceptionHandler(RuntimeException.class)
	public void handleError () {
		
	}
	
}
