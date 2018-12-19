/**
 * 
 */
package com.naveen.demo.brewery.search.data;

import java.util.List;

import com.naveen.demo.brewery.search.model.Brewery;

/**
 * @author naveen
 *
 */
public interface BreweryRepository {
	
	List<Brewery> getBreweries(String page, String count);
	
	List<Brewery> search(String query, String page, String count);

}
