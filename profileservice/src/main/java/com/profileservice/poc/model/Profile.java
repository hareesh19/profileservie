
package com.profileservice.poc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.profileservice.poc.validation.ProfileValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author hmarri
 *
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ProfileValidator(message = "message")
public class Profile {
	
	@Id
    int id;
	String firstName;
	String lastName;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
	Date dateOfBirth;
	String permanentAccountNumber;
	String adharNumber;
	String mobileNumber;

}
