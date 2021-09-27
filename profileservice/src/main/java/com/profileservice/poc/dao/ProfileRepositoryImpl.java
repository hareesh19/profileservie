/**
 * 
 */
package com.profileservice.poc.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.profileservice.poc.model.Profile;

import io.netty.util.internal.StringUtil;
import reactor.core.publisher.Flux;

/**
 * @author hmarri
 *
 */
public class ProfileRepositoryImpl implements CustomProfileRepository {

	private final MongoTemplate mongoTemplate;

	@Autowired
	public ProfileRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public Flux<Profile> serchProfile(String firstName, String lastName, String dateOfBirth,
			String permanentAccountNumber, String adharNumber, String mobileNumber) {
		try {

			List<Criteria> criterias = new ArrayList<>();
			
			Criteria dynamicCriteria = null;
			
			if (!StringUtil.isNullOrEmpty(firstName)) {
				 dynamicCriteria = Criteria.where("firstName").is(firstName);
			}
			
			if (!StringUtil.isNullOrEmpty(lastName)) {
				 dynamicCriteria = Criteria.where("lastName").is(lastName);
			}
			if (!StringUtil.isNullOrEmpty(dateOfBirth)) {
				 dynamicCriteria = Criteria.where("dateOfBirth").is(dateOfBirth);
			}
			if (!StringUtil.isNullOrEmpty(permanentAccountNumber)) {
				 dynamicCriteria = Criteria.where("permanentAccountNumber").is(permanentAccountNumber);
			}
			if (!StringUtil.isNullOrEmpty(adharNumber)) {
				 dynamicCriteria = Criteria.where("adharNumber").is(adharNumber);
			}
			if (!StringUtil.isNullOrEmpty(mobileNumber)) {
				 dynamicCriteria = Criteria.where("mobileNumber").is(mobileNumber);
			}
			criterias.add(dynamicCriteria);

			Criteria criteria = new Criteria().orOperator(criterias.toArray(new Criteria[criterias.size()]));

			Query searchQuery = new Query();

			searchQuery.addCriteria(criteria);
			List<Profile> profileList = mongoTemplate.find(searchQuery, Profile.class);

			return (Flux<Profile>) profileList;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

}
