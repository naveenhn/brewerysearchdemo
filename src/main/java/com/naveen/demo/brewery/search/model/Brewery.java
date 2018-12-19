/**
 * 
 */
package com.naveen.demo.brewery.search.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author naveen
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown=true)
public class Brewery {
	
	private Integer id;
	private String name;
	@JsonAlias("brewery_type")
	private String breweryType;
	private String street;
	private String city;
	private String state;
	@JsonAlias("postal_code")
	private String postalCode;
	private String country;
	private String longitude;
	private String latitude;
	private String phone;
	@JsonAlias("website_url")
	private String websiteUrl;
	@JsonAlias("updated_at")
	private String updatedAt;
	@JsonAlias("tag_list")
	private List<String> tagList;

}
