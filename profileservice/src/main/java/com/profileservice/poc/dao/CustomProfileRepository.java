/**
 * 
 */
package com.profileservice.poc.dao;

import com.profileservice.poc.model.Profile;

import reactor.core.publisher.Flux;

/**
 * @author hmarri
 *
 */
public interface CustomProfileRepository {
	
	Flux<Profile> serchProfile(final String firstName, String lastName, String dateOfBirth, String permanentAccountNumber,
			String adharNumber, String mobileNumber);

}
