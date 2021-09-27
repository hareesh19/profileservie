/**
 * 
 */
package com.profileservice.poc.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.profileservice.poc.model.Profile;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author hmarri
 *
 */

@Repository
public interface ProfileRepository extends ReactiveMongoRepository<Profile, Integer>, CustomProfileRepository {
	
//	@Query("{ 'firstName': 'hareesh', 'lastName': 'marri','dateOfBirth': '11-11-2021','"
//			+ "permanentAccountNumber': '1234567','adharNumber': '123456789','mobileNumber': '9494920203'}")

	@Query("{$or :[{firstName: ?0},{lastName: ?1},{dateOfBirth: ?2},{permanentAccountNumber: ?3},{adharNumber: ?4},{mobileNumber: ?5}]}")
	//@Query("{$and :[{firstName: ?0},{lastName: ?1},{mobileNumber: ?5}]}")
	Flux<Profile> getProfile(final String firstName, final String lastName, String dateOfBirth, String permanentAccountNumber,
			String adharNumber, String mobileNumber);
	
    @Query("{ 'permanentAccountNumber': ?0 }")
	Flux<Profile> checkPanNumber(String permanentAccountNumber);
}
