package at.fhtw.swen3.persistence.entity;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

public class Validator {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    RecipientEntity user = RecipientEntity.builder()
            .working(true)
            .aboutMe("Its all about me!")
            .age(50)
            .build();
    Set<ConstraintViolation<RecipientEntity>> violations = validator.validate(user);
    for (ConstraintViolation<RecipientEntity> violation : violations) {
        log.error(violation.getMessage());
    }
}
