package com.profileservice.poc.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ProfileConstraintValidator.class)
@Target( { ElementType.TYPE, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface ProfileValidator {

    //error message
    public String message();
    //represents group of constraints
    public Class<?>[] groups() default {};
    //represents additional information about annotation
    public Class<? extends Payload>[] payload() default {};

}
