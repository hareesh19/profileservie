/**
 * 
 */
package com.profileservice.poc.service;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.profileservice.poc.dao.ProfileRepository;
import com.profileservice.poc.model.Profile;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author hmarri
 *
 */
@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	ProfileRepository profileRepository;

	@Override
	public Mono<Profile> createProfile(Profile profile) {
		return profileRepository.save(profile);
	}
    private static final Logger  logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	public Flux<Profile> getProfile(String firstName, String lastName, String dateOfBirth,
			String permanentAccountNumber, String adharNumber, String mobileNumber) {

		try {
			return profileRepository.getProfile(firstName, lastName, dateOfBirth, permanentAccountNumber, adharNumber,
					mobileNumber);
		} catch (Exception e) {
            logger.error("Exception occured in getprofile validation: {}", e);

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Mono<Profile> findById(int id) {
		return profileRepository.findById(id);
	}

	@Override
	public Mono<Void> deleteProfile(Integer id) {
		return profileRepository.deleteById(id);
	}

	@Override
	public Mono<Profile> updateProfile(Profile profile) {
		return profileRepository.save(profile);
	}

	@Override
	public Flux<Profile> checkPanNumber(String permanentAccountNumber) {
		return profileRepository.checkPanNumber(permanentAccountNumber);
	}

	

}
