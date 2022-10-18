package at.fhtw.swen3.persistence.entity;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Slf4j
public class EntityValidator {
    Validator validator;
    Set<ConstraintViolation<RecipientEntity>> violations;
    public Validator getValidator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
        return validator;
    }


    RecipientEntity recipient = RecipientEntity.builder()
            .name("test name")
            //.street("Hauptstraße 12/12/12")
            //.street("Landstraße 27a")
            .street("Arnold Fink Str. 13")
            .postalCode("A-3830")
            .city("Waidhofen an der Thaya")
            .country("Austria")
            .build();
    public void validate(Validator validator) {
        this.violations = validator.validate(recipient);
    }
    public void showMessages() {
        for (ConstraintViolation<RecipientEntity> violation : violations) {
           log.error(violation.getMessage());
        }
    }
}
