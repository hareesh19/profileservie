package com.profileservice.poc.controller;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.profileservice.poc.model.Profile;
import com.profileservice.poc.service.ProfileService;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProfileController {

	@Autowired
	ProfileService profileService;

	
	/**
     * The constant LOGGER.
     */
    private static final Logger  logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    
    @PostMapping("/addprofile")
	public Mono<Object> createProfile(@RequestBody Profile profile) {
        logger.info("create profile request: {}", profile);
		return profileService.checkPanNumber(profile.getPermanentAccountNumber()).collectList().flatMap(l -> {
			if (l.isEmpty()) {
				return profileService.createProfile(profile);
			}
			return Mono.just(new ResponseEntity<>(new String("record already exist with PermanentAccountNumber :: ")
					+ profile.getPermanentAccountNumber(), HttpStatus.CONFLICT));
		});
	}

	@GetMapping("/getprofile")
	public Flux<Profile> getProfile(@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName,
			@RequestParam(value = "dateOfBirth", required = false) String dateOfBirth,
			@RequestParam(value = "permanentAccountNumber", required = false) String permanentAccountNumber,
			@RequestParam(value = "adharNumber", required = false) String adharNumber,
			@RequestParam(value = "mobileNumber", required = false) String mobileNumber) {
		return profileService.getProfile(firstName, lastName, dateOfBirth, permanentAccountNumber, adharNumber,
				mobileNumber);
	}

	@PutMapping("/updateprofile")
	public Mono<Profile> update(@RequestBody Profile profile) {
        logger.info("update profile request: {}", profile);
		return profileService.updateProfile(profile);
	}

	@DeleteMapping("/profile/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable(value = "id") Integer id) {
        logger.info("delete profile request id :: {}", id);
		return profileService.findById(id)
				.flatMap(existingProfile -> profileService.deleteProfile(existingProfile.getId())
						.then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/checkpermanentaccountnumber")
	public Flux<Profile> getProfile(
			@RequestParam(value = "permanentAccountNumber", required = false) String permanentAccountNumber) {
		return profileService.checkPanNumber(permanentAccountNumber);

	}

}
