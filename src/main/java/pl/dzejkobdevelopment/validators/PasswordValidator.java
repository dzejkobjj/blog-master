package pl.dzejkobdevelopment.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jakub Michałowski on 20.11.2017.
 * All rights reserved.
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private Pattern pattern;
    private Matcher matcher;
    private final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
    @Override
    public void initialize(Password password){
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext ctx){
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
