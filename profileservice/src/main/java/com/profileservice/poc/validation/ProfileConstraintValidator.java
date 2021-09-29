package com.profileservice.poc.validation;

import com.profileservice.poc.model.Profile;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProfileConstraintValidator implements ConstraintValidator<ProfileValidator, Profile> {

    @Override
    public void initialize(ProfileValidator contactNumber) {
    }

    @Value("${firstname.not.null}")
    private String firstname;

    @Value("${lastname.not.null}")
    private String lastname;

    @Value("${pannumber.not.null}")
    private String pannumber;

    @Value("${adhar.not.null}")
    private String adhar;

    @Value("${mobile.not.null}")
    private String mobilenumber;

    @Override
    public boolean isValid(Profile profile, ConstraintValidatorContext context) {
        try {

            if (!validateName(profile.getFirstName())) {
                buildContext(context, profile.getFirstName(), firstname);
                return false;
            }

            if (!validateName(profile.getLastName())) {
                buildContext(context, profile.getLastName(), lastname);
                return false;
            }

            if (!validatePan(profile.getPermanentAccountNumber())) {
                buildContext(context, profile.getPermanentAccountNumber(), pannumber);
                return false;
            }
            if (!validateAdhar(profile.getAdharNumber())) {
                buildContext(context, profile.getAdharNumber(), adhar);
                return false;
            }
            if (!validateMobileNumber(profile.getMobileNumber())) {
                buildContext(context, profile.getMobileNumber(), mobilenumber);

                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean isValid(String s, ConstraintValidatorContext cvc) {
        return s != null && s.matches("[0-9]+")
                && (s.length() > 8) && (s.length() < 14);
    }

    private boolean validateName(String s) {
        return s != null && s.matches("^[A-Za-z\\s]+$");
    }


    private boolean validatePan(String s) {
         return s != null && s.matches("^[A-Z0-9]+$");
    }

    private boolean validateAdhar(String s) {
        return s != null && s.matches("^[1-9][0-9]\\d{10}$");
    }

    private boolean validateMobileNumber(String s) {
        return s != null && s.matches("^[6-9]\\d{9}$");
    }

    public void buildContext (ConstraintValidatorContext context, String value, String msg) {
        context.buildConstraintViolationWithTemplate(msg)
                .addPropertyNode(value)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
    }
}

