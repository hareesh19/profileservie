/**
 * 
 */
package com.profileservice.poc.service;

import com.profileservice.poc.model.Profile;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author hmarri
 *
 */
public interface ProfileService {
	
	public Mono<Profile> createProfile(Profile profile);

	public Flux<Profile> getProfile(String firstName, String lastName, String dateOfBirth,
			String permanentAccountNumber, String adharNumber, String mobileNumber);

	Mono<Profile> findById(int id);

	Mono<Void> deleteProfile(Integer id);

	public Mono<Profile> updateProfile(Profile profile);
	
	public Flux<Profile> checkPanNumber(String panNumber);

}
